package cn.codeyang.emby.filter;

import cn.codeyang.emby.config.YangProperties;
import cn.codeyang.emby.constant.Constants;
import cn.codeyang.emby.dto.alist.AlistFsResponseDTO;
import cn.codeyang.emby.dto.emby.BaseItemDto;
import cn.codeyang.emby.dto.emby.MediaSourceInfo;
import cn.codeyang.emby.dto.emby.QueryResultBaseItemDto;
import cn.codeyang.emby.utils.AlistUtil;
import cn.codeyang.emby.utils.URIUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
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

import java.net.URI;
import java.net.URISyntaxException;
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


    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        Boolean isInner = exchange.getAttribute(Constants.IS_INNER);

        if (!pathMatcher.match(Constants.STREAM_PLAY_PATH_PATTERN, uri.getPath())) {
            // 不是指定url不处理
            return chain.filter(exchange);
        }
        if (isInner != null && isInner) {
            // 内网不做特殊处理
            return chain.filter(exchange);
        }

        try {
            // 获取emby的视频item信息
            QueryResultBaseItemDto itemInfo = getItemInfo(request.getURI());
            if (CollUtil.isEmpty(itemInfo.getItems())) {
                throw new RuntimeException("获取item信息失败");
            }

            BaseItemDto item = itemInfo.getItems().getFirst();

            String path;
            if (CollUtil.isNotEmpty(item.getMediaSources())) {
                MediaSourceInfo mediaSourceInfo = item.getMediaSources().getFirst();
                Map<String, String> params = URIUtil.getParams(uri);
                if (params.containsKey("MediaSourceId")) {
                    String mediaSourceId = params.get("MediaSourceId");
                    MediaSourceInfo filterMediaSource = item.getMediaSources().stream().filter(mediaSource -> mediaSource.getId().equals(mediaSourceId)).findFirst().orElse(null);
                    mediaSourceInfo = filterMediaSource != null ? filterMediaSource : mediaSourceInfo;
                }
                path = mediaSourceInfo.getPath();
                String itemName = mediaSourceInfo.getName();
                Boolean notLocal = mediaSourceInfo.getIsInfiniteStream() || mediaSourceInfo.getIsRemote();
            } else {
                path = item.getPath();
            }

            HttpResponse response = AlistUtil.fetchAlistPath(yangProperties.getAlist().getInternalBaseUrl(), path, yangProperties.getAlist().getApiKey());
            if (response.isOk()) {
                Gson gson = new Gson();
                AlistFsResponseDTO resp = gson.fromJson(response.body(), AlistFsResponseDTO.class);
                if (resp.getCode().equals("200")) {
                    return redirectMono(exchange, resp.getData().getRawUrl());
                }
            }

            System.out.println("aaa");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return chain.filter(exchange);
    }


    private QueryResultBaseItemDto getItemInfo(URI uri) {
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

        String resp = HttpUtil.get(yangProperties.getEmby().getBaseUrl() + "/Items", params);
        Gson gson = new Gson();
        return gson.fromJson(resp, QueryResultBaseItemDto.class);
    }

    private Mono<Void> redirectMono(ServerWebExchange exchange, String responseUrl) throws URISyntaxException {
        URI uri = new URI(responseUrl);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        response.getHeaders().setLocation(uri);
        return Mono.empty();
    }
}
