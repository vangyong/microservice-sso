package cn.segema.cloud.sso.server.authentication.openid;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import cn.segema.cloud.sso.server.repository.UserConnectionRepository;
import cn.segema.cloud.sso.server.support.MyOpenIdUserDetailsService;

public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private UserConnectionRepository userConnectionRepository;
    
    private MyOpenIdUserDetailsService myOpenIdUserDetailsService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<String>();
        providerUserIds.add((String) authenticationToken.getPrincipal());
        
        UserDetails user = myOpenIdUserDetailsService.loadUserByOpenId(authenticationToken.getProviderId(),authenticationToken.getPrincipal().toString());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserConnectionRepository getUserConnectionRepository() {
        return userConnectionRepository;
    }

    public void setUserConnectionRepository(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    public MyOpenIdUserDetailsService getMyOpenIdUserDetailsService() {
        return myOpenIdUserDetailsService;
    }

    public void setMyOpenIdUserDetailsService(MyOpenIdUserDetailsService myOpenIdUserDetailsService) {
        this.myOpenIdUserDetailsService = myOpenIdUserDetailsService;
    }

}
