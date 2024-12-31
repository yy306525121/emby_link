package cn.codeyang.emby.config;

import EmbyClient.ApiClient;
import EmbyClient.Configuration;
import EmbyClient.auth.ApiKeyAuth;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangzy
 */
@AutoConfiguration
@EnableConfigurationProperties({YangProperties.class})
public class YangAutoConfiguration {
    private YangProperties yangProperties;

    public YangAutoConfiguration(YangProperties yangProperties) {
        this.yangProperties = yangProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApiKeyAuth apiKeyAuth() {
        ApiClient apiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) apiClient.getAuthentication("apikeyauth");
        apiKeyAuth.setApiKey(yangProperties.getEmby().getApiKey());

        return apiKeyAuth;
    }
}
