package cn.segema.cloud.sso.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 单点登录SSO客户端1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SsoClient1Application {
	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
}
