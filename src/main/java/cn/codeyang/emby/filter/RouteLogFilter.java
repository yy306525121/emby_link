package cn.codeyang.emby.filter;

import cn.codeyang.emby.constant.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author yangzy
 */
@Slf4j
@Order(0)
@Component
@RequiredArgsConstructor
public class RouteLogFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String ip = (String) exchange.getAttributes().get(Constants.IP);
        Boolean isInner = exchange.getAttribute(Constants.IS_INNER);
        URI uri = exchange.getRequest().getURI();
        log.info("route url: {}, ip: {}, 内网: {}", uri.getPath(), ip, isInner);
        return chain.filter(exchange);
    }
}
