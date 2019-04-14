package cn.segema.cloud.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @Description [单点登录SSOserver] 
 * @author wangyong
 * @CreateDate 2019/04/07
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableSocial
//@Import({ WechatAutoConfiguration.class, WechatMpAutoConfiguration.class })
public class SsoServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SsoServerApplication.class, args);
  }
  
//	@Bean
//	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
//			UsersConnectionRepository usersConnectionRepository, WechatSignInAdapter wechatSignInAdapter) {
//		((InMemoryUsersConnectionRepository) usersConnectionRepository)
//				.setConnectionSignUp((Connection<?> connection) -> connection.getKey().getProviderUserId());
//		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, wechatSignInAdapter);
//	}
}
