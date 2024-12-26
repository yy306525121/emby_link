package cn.codeyang.emby.filter;

import cn.codeyang.emby.config.YangProperties;
import cn.codeyang.emby.constant.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * 该过滤器主要判断是否是内网请求
 * @author yangzy
 */
@Slf4j
@Order(-1)
@Component
@RequiredArgsConstructor
public class RemoteAddressFilter implements WebFilter {
    private final YangProperties yangProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();

        for (String lanIp : yangProperties.getLanIp()) {
            if (clientIp.startsWith(lanIp)) {
                exchange.getAttributes().put(Constants.IS_INNER, true);
                log.info("当前请求地址:{}, 属于内网", clientIp);
            }
        }

        return chain.filter(exchange);
    }
}
