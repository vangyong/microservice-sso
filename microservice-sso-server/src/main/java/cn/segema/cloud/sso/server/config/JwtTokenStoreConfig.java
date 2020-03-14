package cn.segema.cloud.sso.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.sso.server.jwt.SsoJwtTokenEnhancer;

@Configuration
@ConditionalOnProperty(prefix = "sso.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
public class JwtTokenStoreConfig {

	@Value("${sso.security.jwt.signingKey}")
	private String signingkey;

	@Bean
	public TokenEnhancer jwtTokenEnhancer() {
		return new SsoJwtTokenEnhancer();
	}

	@Bean
	public TokenStore jwtTokenStroe() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		// 设置默认值
		if (StringUtils.isEmpty(signingkey)) {
			signingkey = ApiConstant.SSO_SIGNING_KEY;
		}
		// 密钥，放到配置文件中
		jwtAccessTokenConverter.setSigningKey(signingkey);
		return jwtAccessTokenConverter;
	}
}
