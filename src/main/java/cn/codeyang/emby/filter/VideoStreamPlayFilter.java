package cn.codeyang.emby.filter;

import cn.codeyang.emby.client.alist.AlistClient;
import cn.codeyang.emby.client.alist.dto.AlistFileInfoResponse;
import cn.codeyang.emby.config.YangProperties;
import cn.codeyang.emby.constant.Constants;
import cn.codeyang.emby.dto.emby.BaseItemQueryResultRespDTO;
import cn.codeyang.emby.utils.URIUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangzy
 */
@Slf4j
@Order(3)
@Component
@RequiredArgsConstructor
public class VideoStreamPlayFilter implements WebFilter {
    private final YangProperties yangProperties;
    private final AntPathMatcher pathMatcher;
    private final AlistClient alistClient;
    public static final String REDIRECT_URL_TEMPLATE = "{}/d/{}?sign={}";
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        Boolean isInner = exchange.getAttribute(Constants.IS_INNER);
        String ip = (String) exchange.getAttributes().get(Constants.IP);

        if (!pathMatcher.match(Constants.STREAM_PLAY_PATH_PATTERN, uri.getPath())) {
            // 不是指定url不处理
            return chain.filter(exchange);
        }
        if (isInner != null && isInner) {
            // 内网不做特殊处理
            log.info("ip: {}, 内网地址不做重定向", ip);
            return chain.filter(exchange);
        }

        try {
            // 获取emby的视频item信息
            BaseItemQueryResultRespDTO itemInfo = getItemInfo(request.getURI());
            if (CollUtil.isEmpty(itemInfo.getItems())) {
                throw new RuntimeException("获取item信息失败");
            }

            BaseItemQueryResultRespDTO.Item firstItem = itemInfo.getItems().getFirst();

            String path;
            if (CollUtil.isNotEmpty(firstItem.getMediaSources())) {
                BaseItemQueryResultRespDTO.MediaSourceInfo mediaSourceInfo = firstItem.getMediaSources().getFirst();
                Map<String, String> params = URIUtil.getParams(uri);
                if (params.containsKey("MediaSourceId")) {
                    String mediaSourceId = params.get("MediaSourceId");
                    BaseItemQueryResultRespDTO.MediaSourceInfo filterMediaSource = firstItem.getMediaSources().stream().filter(mediaSource -> mediaSource.getId().equals(mediaSourceId)).findFirst().orElse(null);
                    mediaSourceInfo = filterMediaSource != null ? filterMediaSource : mediaSourceInfo;
                }
                path = mediaSourceInfo.getPath();
            } else {
                path = firstItem.getPath();
            }

            AlistFileInfoResponse fileInfo = alistClient.getFileInfo(path);
            if (fileInfo.getCode().equals("200")) {
                String redirectUrl = StrUtil.format(REDIRECT_URL_TEMPLATE, yangProperties.getAlist().getExternalBaseUrl(), path, fileInfo.getData().getSign());
                redirectUrl = URLUtil.normalize(redirectUrl);
                log.info("获取直连地址:{}", redirectUrl);
                return redirectMono(exchange, redirectUrl);
            }
        } catch (Exception e) {
            log.info("获取直连地址失败");
            log.error(e.getMessage());
        }

        return chain.filter(exchange);
    }


    private BaseItemQueryResultRespDTO getItemInfo(URI uri) throws JsonProcessingException {
        Map<String, String> uriParams = URIUtil.getParams(uri);

        Map<String, Object> params = new HashMap<>();
        params.put("Limit", "1");
        params.put(Constants.API_KEY_NAME, yangProperties.getEmby().getApiKey());

        List<String> queryFields = new ArrayList<>();
        queryFields.add("MediaSources");
        queryFields.add("Path");
        String fields = String.join(",", queryFields);
        params.put("Fields",  fields);

        if (uriParams.containsKey("MediaSourceId")) {
            params.put("Ids", uriParams.get("MediaSourceId"));
        } else {
            String itemId = URIUtil.getItemIdByUri(uri);
            params.put("Ids", itemId);
        }

        String jsonResp = HttpUtil.get(yangProperties.getEmby().getBaseUrl() + "/Items", params);
        log.info("获取item信息:{}", jsonResp);
        return objectMapper.readValue(jsonResp, BaseItemQueryResultRespDTO.class);
    }

    private Mono<Void> redirectMono(ServerWebExchange exchange, String responseUrl) throws URISyntaxException, UnsupportedEncodingException {
        URI uri = new URI(URLUtil.normalize(responseUrl, true));
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(uri);
        return Mono.empty();
    }
}
