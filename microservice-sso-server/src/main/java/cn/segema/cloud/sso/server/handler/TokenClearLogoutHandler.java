package cn.segema.cloud.sso.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import cn.segema.cloud.sso.server.support.MyUserDetailsService;

public class TokenClearLogoutHandler implements LogoutHandler {
	
	private MyUserDetailsService myUserDetailsService;
	
	public TokenClearLogoutHandler(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		clearToken(authentication);
	}
	
	protected void clearToken(Authentication authentication) {
		if(authentication == null)
			return;
		UserDetails user = (UserDetails)authentication.getPrincipal();
		if(user!=null && user.getUsername()!=null)
		    myUserDetailsService.deleteUserLoginInfo(user.getUsername());
	}

}
