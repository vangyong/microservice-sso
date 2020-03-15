package cn.segema.cloud.sso.server.authentication.mobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cn.segema.cloud.sso.server.constants.SsoConstant;

//短信验证码拦截器
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher(SsoConstant.DEFAULT_MOBILE_LOGIN_PROCESSING_URL, "POST"));
    }

    /**
     * 添加未认证用户认证信息，然后在provider里面进行正式认证
     */
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        if (!httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String mobile = httpServletRequest.getParameter(SsoConstant.DEFAULT_MOBILE_PARAMETER);
        String mobileCode = httpServletRequest.getParameter(SsoConstant.DEFAULT_MOBILE_CODE_PARAMETER);
        //todo:验证短信验证码
        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile, mobileCode);
        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
