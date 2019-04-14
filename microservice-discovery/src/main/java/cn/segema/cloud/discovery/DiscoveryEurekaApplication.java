package cn.segema.cloud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description [服务注册中心启动类]
 * @author wangyong
 * @CreateDate 2019/04/05
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaApplication.class, args);
    }
}
