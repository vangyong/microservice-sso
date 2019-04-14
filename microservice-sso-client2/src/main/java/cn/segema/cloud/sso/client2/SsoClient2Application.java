package cn.segema.cloud.sso.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 单点登录SSO客户端2
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SsoClient2Application {
	public static void main(String[] args) {
		SpringApplication.run(SsoClient2Application.class, args);
	}
}
