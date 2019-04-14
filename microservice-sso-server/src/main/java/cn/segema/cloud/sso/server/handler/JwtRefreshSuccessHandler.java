package cn.segema.cloud.sso.server.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.auth0.jwt.interfaces.DecodedJWT;
import cn.segema.cloud.sso.server.support.JwtAuthenticationToken;
import cn.segema.cloud.sso.server.support.MyUserDetailsService;

public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final int tokenRefreshInterval = 300;  //刷新间隔5分钟
	
	private MyUserDetailsService myUserDetailsService;
	
	public JwtRefreshSuccessHandler(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();
		
		boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
		if(shouldRefresh) {
            String newToken = myUserDetailsService.saveUserToken(((UserDetails)authentication.getPrincipal()).getUsername());
            response.setHeader("Authorization", newToken);
        }else {
        	 String userToken = myUserDetailsService.saveUserToken(((UserDetails)authentication.getPrincipal()).getUsername());
        	response.setHeader("Authorization", userToken);
        }	
	}
	
	protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

}
