package cn.segema.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @Description [微服务名称配置] 
 * @author wangyong
 * @CreateDate 2019/04/07
 */
@ConfigurationProperties(prefix = "microservice")
@Data
public class MicroserviceProperties {
    private String system = "microservice-system";
    private String mall = "microservice-mall";
    private String thirdpart = "microservice-thirdpart";
    private String filecenter = "microservice-filecenter";

}
