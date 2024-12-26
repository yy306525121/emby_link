package cn.codeyang.emby.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author yangzy
 */
@AutoConfiguration
@EnableConfigurationProperties({YangProperties.class})
public class YangAutoConfiguration {

}
