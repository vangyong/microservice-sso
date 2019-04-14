package cn.segema.cloud.sso.server.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import cn.segema.cloud.sso.server.filter.JwtAuthenticationFilter;
import cn.segema.cloud.sso.server.handler.HttpStatusLoginFailureHandler;

public class JwtLoginConfig<T extends JwtLoginConfig<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {
    
	private JwtAuthenticationFilter authFilter;
	
	public JwtLoginConfig() {
		this.authFilter = new JwtAuthenticationFilter();
	}
	
	@Override
	public void configure(B http) throws Exception {
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());

		JwtAuthenticationFilter filter = postProcess(authFilter);
		http.addFilterBefore(filter, LogoutFilter.class);
	}
	
	public JwtLoginConfig<T, B> permissiveRequestUrls(String ... urls){
		authFilter.setPermissiveUrl(urls);
		return this;
	}
	
	public JwtLoginConfig<T, B> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
		authFilter.setAuthenticationSuccessHandler(successHandler);
		return this;
	}
	
}
