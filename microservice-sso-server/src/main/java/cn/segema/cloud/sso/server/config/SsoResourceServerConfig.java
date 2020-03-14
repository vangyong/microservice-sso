package cn.segema.cloud.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.segema.cloud.common.constants.ApiConstant;

@Configuration
@EnableResourceServer
public class SsoResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    @Autowired
    protected AuthenticationSuccessHandler ssoAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler ssoAuthenticationFailureHandler;
    
//  @Autowired
//  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
        //登录页面，app用不到
        .loginPage(ApiConstant.API_VERSION +"/sso-server/authentication/login")
        //登录提交action，app会用到,用户名密码登录地址
        .loginProcessingUrl(ApiConstant.API_VERSION + "/sso-server/form/token")
        .successHandler(ssoAuthenticationSuccessHandler)
        .failureHandler(ssoAuthenticationFailureHandler);
        
        
        http//.apply(smsCodeAuthenticationSecurityConfig)
            //.and()
            .authorizeRequests()
            .antMatchers(ApiConstant.API_VERSION + "/sso-server/register",
                        ApiConstant.API_VERSION +"/sso-server/service/*",
                        ApiConstant.API_VERSION + "/sso-server/form/token",
                        ApiConstant.API_VERSION + "/sso-server/openid/*",
                        "/oauth",
                        "/swagger-*",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/csrf",
                        "/swagger-*/*",
                        "/v2/api-docs*",
                        "/social/**",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff2",
                        "/code/image")
                .permitAll()//以上的请求都不需要认证
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
