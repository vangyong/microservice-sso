package cn.segema.cloud.sso.server.support;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.segema.cloud.sso.server.service.UserService;
import cn.segema.cloud.sso.server.vo.RoleVO;
import cn.segema.cloud.sso.server.vo.UserVO;

@Service
public class MyMobileDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public UserDetails loadUserByMobileNumber(String mobileNumber) throws UsernameNotFoundException {
        UserVO sysUser = userService.getUserByName(mobileNumber);
        if (null == sysUser) {
            throw new UsernameNotFoundException(mobileNumber);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysUser.getRoleList())) {
            for (RoleVO role : sysUser.getRoleList()) {
                // for (ResourceVO resourceVO : role.getResourceList()) {
                // authorities.add(new SimpleGrantedAuthority(resourceVO.getCode()));
                // }
                authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
            }
        }
        return new User(sysUser.getUserName(), sysUser.getPassword(), authorities);
    }

}
