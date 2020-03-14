package cn.segema.cloud.sso.server.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private DataSource dataSource;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore redisTokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    public SsoAuthorizationServerConfig(
            // AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService, TokenStore redisTokenStore) {
        // this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.redisTokenStore = redisTokenStore;
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	 //从数据库中获取
    	 clients.jdbc(dataSource);
    	 //从内存中获取
//    	 clients.inMemory()
//         .withClient("client1")
//         .secret(new BCryptPasswordEncoder().encode("client1"))
//         .accessTokenValiditySeconds(3600) //token有效时间秒
//         .authorizedGrantTypes("authorization_code", "password", "refresh_token")
//         //.redirectUris("http://localhost:8080/test1")
//         .scopes("all","read","write")
//         .and()
//         .withClient("client2")
//         .secret(new BCryptPasswordEncoder().encode("client2"))
//         .authorizedGrantTypes("authorization_code","password", "refresh_token")
//         //.redirectUris("http://localhostl:8081/")
//         .scopes("all","read","write");
    }

    /**
     * 配置Authorization Server endpoints的一些非安全特性的,
     * 比如token存储、token自定义、授权类型等
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用Redis作为Token的存储
        endpoints.tokenStore(redisTokenStore)
        .userDetailsService(userDetailsService);
        
        // 1、设置token为jwt形式
        // 2、设置jwt 拓展认证信息
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<TokenEnhancer>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);

            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }
}
