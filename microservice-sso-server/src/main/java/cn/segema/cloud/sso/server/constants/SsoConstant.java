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
    

}
