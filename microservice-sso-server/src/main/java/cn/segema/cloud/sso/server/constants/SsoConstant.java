package cn.segema.cloud.sso.server.constants;

import cn.segema.cloud.common.constants.ApiConstant;

public class SsoConstant {

    /**
     * 默认登录页面
     */
    public static final String DEFAULT_FORM_LOGIN_PAGE_URL = ApiConstant.API_VERSION + "/sso-server/login";

    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_FORM_LOGIN_PROCESSING_URL = ApiConstant.API_VERSION + "/sso-server/form/token";
    
    /**
     * 默认发送验证码url
     */
    public static final String DEFAULT_MOBILE_LOGIN_CODE_URL = ApiConstant.API_VERSION + "/sso-server/mobile/code";
    
    /**
     * 默认短信登录请求处理url
     */
    public static final String DEFAULT_MOBILE_LOGIN_PROCESSING_URL = ApiConstant.API_VERSION + "/sso-server/mobile/token";
    
    /**
     * 默认短信登录手机号码参数名
     */
    public static final String DEFAULT_MOBILE_PARAMETER = "mobile";
    
    /**
     * 默认短信登录手机验证码参数名
     */
    public static final String DEFAULT_MOBILE_CODE_PARAMETER = "mobileCode";
    
    
    /**
     * 默认短信登录请求处理url
     */
    public static final String DEFAULT_OPENID_LOGIN_PROCESSING_URL = ApiConstant.API_VERSION + "/sso-server/openid/token";
    
    /**
     * 默认第三方登录openId参数名
     */
    public static final String DEFAULT_OPENID_PARAMETER = "openId";
    
    /**
     * 默认第三方提供商参数名
     */
    public static final String DEFAULT_PROVIDERID_PARAMETER = "providerId";
    

}
