package cn.segema.cloud.sso.server.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import cn.segema.cloud.sso.server.domain.MobileCode;
import cn.segema.cloud.sso.server.repository.MobileCodeRepository;
import cn.segema.cloud.sso.server.support.MyMobileDetailsService;

// 用户认证所在类
public class MobileAuthenticationProvider implements AuthenticationProvider {

    // 注意这里的userdetailservice ，因为SmsCodeAuthenticationProvider类没有@Component
    // 所以这里不能加@Autowire，只能通过外面设置才行
    private MyMobileDetailsService myMobileDetailsService;
    
    private MobileCodeRepository mobileCodeRepository;

    //在这里认证用户信息
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken)authentication;
        String mobile = authentication.getName();
        String mobileCode = (String)authenticationToken.getCredentials();

        UserDetails user = myMobileDetailsService.loadUserByMobileNumber(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("can't find user by mobile");
        }
        //todo:验证短信验证码，一般生产环境用redis缓存的验证码
        MobileCode mobileCodeOld = mobileCodeRepository.findByMobileNumberCode(mobile,mobileCode);
        if(mobileCodeOld == null) {
            throw new RuntimeException("mobile code error");
        }

        MobileAuthenticationToken authenticationResult =
            new MobileAuthenticationToken(user, null, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public MyMobileDetailsService getMyMobileDetailsService() {
        return myMobileDetailsService;
    }

    public void setMyMobileDetailsService(MyMobileDetailsService myMobileDetailsService) {
        this.myMobileDetailsService = myMobileDetailsService;
    }

    public MobileCodeRepository getMobileCodeRepository() {
        return mobileCodeRepository;
    }

    public void setMobileCodeRepository(MobileCodeRepository mobileCodeRepository) {
        this.mobileCodeRepository = mobileCodeRepository;
    }
    
}
