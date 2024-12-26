package cn.codeyang.emby.filter;

import cn.codeyang.emby.config.YangProperties;
import cn.codeyang.emby.constant.Constants;
import cn.codeyang.emby.dto.emby.MediaSourceInfo;
import cn.codeyang.emby.dto.emby.PlaybackInfoResponse;
import cn.codeyang.emby.utils.URIUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * @author yangzy
 */
@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
public class PlaybackInfoFilter implements WebFilter {
    private final YangProperties yangProperties;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    private String generateDirectStreamUrl(URI uri, String sourceId, String resourceKey) {
        if (StrUtil.isEmpty(resourceKey)) {
            resourceKey = "stream.";
        }

        Map<String, String> params = URIUtil.getParams(uri);
        addDefaultApiKey(params);
        params.put(Constants.MEDIA_SOURCE_ID, sourceId);
        params.put("Static", Boolean.TRUE.toString());

        String queryStr = URLUtil.buildQuery(params, StandardCharsets.UTF_8);

        String path = uri.getPath();
        path = path.replace("Items", "videos");
        path = path.replace("PlaybackInfo", resourceKey);

        return path + "?" + queryStr;
    }

    private void addDefaultApiKey(Map<String, String> urlParams) {
        if (CollUtil.isNotEmpty(urlParams) && !urlParams.containsKey(Constants.API_KEY_NAME) && !urlParams.containsKey(Constants.EMBY_TOKEN_NAME)) {
            urlParams.put(Constants.EMBY_TOKEN_NAME, yangProperties.getEmby().getApiKey());
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        Boolean isInner = exchange.getAttribute(Constants.IS_INNER);
        if (!pathMatcher.match(Constants.PLAY_BACK_INFO_PATH_PATTERN, uri.getPath())) {
            return chain.filter(exchange);
        }

        if (isInner != null && isInner) {
            // 如果是内网，不走直连
            return chain.filter(exchange);
        }

        return operationExchange(exchange, chain);
    }

    private Mono<Void> operationExchange(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        DataBufferFactory bufferFactory = response.bufferFactory();
        ServerHttpResponseDecorator responseDecorator = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (Objects.equals(getStatusCode(), HttpStatus.OK) && body instanceof Flux) {
                    // 获取响应ContentType
                    String responseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    if (StrUtil.isNotEmpty(responseContentType) && StrUtil.containsAny(responseContentType, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE)) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            DefaultDataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer join = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[join.readableByteCount()];
                            join.read(content);
                            DataBufferUtils.release(join);
                            String responseData = new String(content, StandardCharsets.UTF_8);

                            // 修改返回内容
                            responseData = modifyResponseData(request.getURI(), responseData);

                            //重新计算内容长度
                            int newContentLength = responseData.getBytes(StandardCharsets.UTF_8).length;
                            getDelegate().getHeaders().setContentLength(newContentLength);

                            return bufferFactory.wrap(responseData.getBytes());
                        }));
                    }

                }
                return super.writeWith(body);
            }
        };

        return chain.filter(exchange.mutate().response(responseDecorator).build());
    }

    private String modifyResponseData(URI uri, String jsonData) {
        Gson gson = new Gson();
        PlaybackInfoResponse playBackInfo = gson.fromJson(jsonData, PlaybackInfoResponse.class);
        List<MediaSourceInfo> mediaSources = playBackInfo.getMediaSources();
        if (CollUtil.isNotEmpty(mediaSources)) {
            mediaSources.forEach(mediaSource -> {
                String id = mediaSource.getId();
                Boolean isInfiniteStream = mediaSource.getIsInfiniteStream();
                // String originalStreamUrl = mediaSource.getDirectStreamUrl();
                // String path = mediaSource.getPath();
                String container = mediaSource.getContainer();

                // 设置不支持转码
                mediaSource.setSupportsTranscoding(false);

                String locationPath = isInfiniteStream ? "master" : "stream";
                String resourceKey = locationPath + StrPool.DOT + container;

                String directStreamUrl = generateDirectStreamUrl(uri, id, resourceKey);
                mediaSource.setDirectStreamUrl(directStreamUrl);
            });
        }

        return gson.toJson(playBackInfo);
    }
}
