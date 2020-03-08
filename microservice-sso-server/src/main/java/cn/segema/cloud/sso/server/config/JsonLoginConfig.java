package cn.segema.cloud.sso.server.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

import cn.segema.cloud.sso.server.filter.MyUsernamePasswordAuthenticationFilter;
import cn.segema.cloud.sso.server.handler.HttpStatusLoginFailureHandler;

public class JsonLoginConfig<T extends JsonLoginConfig<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B>  {

	private MyUsernamePasswordAuthenticationFilter authFilter;

	public JsonLoginConfig() {
		this.authFilter = new MyUsernamePasswordAuthenticationFilter();
	}
	
	@Override
	public void configure(B http) throws Exception {
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
		authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

		MyUsernamePasswordAuthenticationFilter filter = postProcess(authFilter);
		http.addFilterAfter(filter, LogoutFilter.class);
	}
	
	public JsonLoginConfig<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
		authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
		return this;
	}

}
