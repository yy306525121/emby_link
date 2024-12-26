package cn.codeyang.emby.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzy
 */
@ConfigurationProperties("yang")
@Data
@Validated
public class YangProperties {
    private Emby emby = new Emby();
    private List<String> lanIp = new ArrayList<>();
    private Alist alist = new Alist();

    @Data
    public static class Emby {
        private String baseUrl;
        private String apiKey;
    }

    @Data
    public static class Alist {
        private String internalBaseUrl;
        private String externalBaseUrl;
        private String apiKey;
    }
}
