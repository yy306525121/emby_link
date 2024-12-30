package cn.codeyang.emby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * @author yangzy
 */
@Configuration(proxyBeanMethods = false)
public class AntPathMatcherConfig {
    @Bean
    public AntPathMatcher antPathMatcher() {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false); // 忽略大小写
        return matcher;
    }
}
