package cn.segema.cloud.sso.server.support;

import java.util.Calendar;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtAuthenticationProvider implements AuthenticationProvider{
	
	private MyUserDetailsService myUserDetailsService;
	
	public JwtAuthenticationProvider(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();
		if(jwt.getExpiresAt().before(Calendar.getInstance().getTime()))
			throw new NonceExpiredException("Token expires");
		String username = jwt.getSubject();
		String userToken = myUserDetailsService.getUserToken(username);
		if(userToken == null) {
			throw new NonceExpiredException("Token expires");
		}else {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
			JwtAuthenticationToken token = new JwtAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
			return token;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(JwtAuthenticationToken.class);
	}

}
