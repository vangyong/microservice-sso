package cn.segema.cloud.sso.server.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.segema.cloud.sso.server.support.MyUserDetailsService;

public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private MyUserDetailsService myUserDetailsService;
	
	public JsonLoginSuccessHandler(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = myUserDetailsService.saveUserToken(((UserDetails)authentication.getPrincipal()).getUsername());
		response.setHeader("Authorization", token);
	}
	
}
