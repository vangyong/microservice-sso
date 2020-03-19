package cn.segema.cloud.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.sso.server.authentication.mobile.MobileAuthenticationSecurityConfig;
import cn.segema.cloud.sso.server.authentication.openid.OpenIdAuthenticationSecurityConfig;
import cn.segema.cloud.sso.server.constants.SsoConstant;

@Configuration
@EnableResourceServer
public class SsoResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;
    
    @Autowired
    private MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;
    
    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
        //登录页面，app用不到
        .loginPage(SsoConstant.DEFAULT_FORM_LOGIN_PAGE_URL)
        //登录提交action，app会用到,用户名密码登录地址
        .loginProcessingUrl(SsoConstant.DEFAULT_FORM_LOGIN_PROCESSING_URL)
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler);
        
        http.apply(mobileAuthenticationSecurityConfig)
            .and()
            .apply(openIdAuthenticationSecurityConfig)
            .and()
            .authorizeRequests()
            .antMatchers(ApiConstant.API_VERSION + "/sso-server/service/*",
                        SsoConstant.DEFAULT_FORM_LOGIN_PAGE_URL,
                        SsoConstant.DEFAULT_FORM_LOGIN_PROCESSING_URL,
                        SsoConstant.DEFAULT_MOBILE_LOGIN_CODE_URL,
                        SsoConstant.DEFAULT_MOBILE_LOGIN_PROCESSING_URL,
                        SsoConstant.DEFAULT_OPENID_LOGIN_PROCESSING_URL,
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
