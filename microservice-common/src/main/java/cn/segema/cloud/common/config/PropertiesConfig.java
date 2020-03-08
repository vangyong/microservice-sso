package cn.segema.cloud.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description [开启自动映射配置] 
 * @author wangyong
 * @CreateDate 2019/04/07
 */
@Configuration
@EnableConfigurationProperties(MicroserviceProperties.class)
public class PropertiesConfig {

}
