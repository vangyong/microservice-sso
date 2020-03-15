package cn.segema.cloud.sso.server.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import cn.segema.cloud.sso.server.repository.MobileCodeRepository;
import cn.segema.cloud.sso.server.support.MyMobileDetailsService;

/**
 * * 自定义验证密码或者验证码
 * https://blog.csdn.net/tzdwsy/article/details/50738043
 * https://blog.csdn.net/xiejx618/article/details/42609497
 * https://blog.csdn.net/jiangshanwe/article/details/73842143
 * https://longfeizheng.github.io/2018/01/14/Spring-Security%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90%E4%BA%94-Spring-Security%E7%9F%AD%E4%BF%A1%E7%99%BB%E5%BD%95/
 * https://cloud.tencent.com/developer/article/1040105
 */
@Component
public class MobileAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyMobileDetailsService myMobileDetailsService;
    
    @Autowired
    private MobileCodeRepository mobileCodeRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
        mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mobileAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        mobileAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        //todo:可以往这个类里面注入验证短信验证码的service
        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setMyMobileDetailsService(myMobileDetailsService);
        mobileAuthenticationProvider.setMobileCodeRepository(mobileCodeRepository);
        http.authenticationProvider(mobileAuthenticationProvider)
                .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
