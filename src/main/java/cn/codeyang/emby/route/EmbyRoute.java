package cn.codeyang.emby.route;

import cn.codeyang.emby.config.YangProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


/**
 * @author yangzy
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class EmbyRoute {
    private final YangProperties yangProperties;

    @Bean
    public RouteLocator embyRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("emby", r ->r.method(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD).uri(yangProperties.getEmby().getBaseUrl()))
                .build();

    }
}
