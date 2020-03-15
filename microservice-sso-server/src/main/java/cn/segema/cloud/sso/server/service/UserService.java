package cn.segema.cloud.sso.server.service;


import cn.segema.cloud.sso.server.vo.UserVO;

public interface UserService {

    /**
     * 根据用户名获取系统用户
     */
    UserVO getUserByName(String username);
    
    /**
     * 根据手机号码获取系统用户
     */
    UserVO getUserByMobileNumber(String mobileNumber);

}
