package cn.segema.cloud.sso.server.authentication.openid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cn.segema.cloud.sso.server.constants.SsoConstant;

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SsoConstant.DEFAULT_OPENID_LOGIN_PROCESSING_URL, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {
        if (!httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String providerId = httpServletRequest.getParameter(SsoConstant.DEFAULT_PROVIDERID_PARAMETER);
        String openid = httpServletRequest.getParameter(SsoConstant.DEFAULT_OPENID_PARAMETER);

        if (openid == null) {
            openid = "";
        }
        if (providerId == null) {
            providerId = "";
        }

        openid = openid.trim();
        providerId = providerId.trim();

        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(providerId, openid);

        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    
    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
    
}
