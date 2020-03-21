package cn.segema.cloud.sso.server.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.sso.server.domain.User;
import cn.segema.cloud.sso.server.domain.OauthUserConnection;
import cn.segema.cloud.sso.server.repository.UserConnectionRepository;
import cn.segema.cloud.sso.server.repository.UserRepository;

@Service
public class MyOpenIdUserDetailsService{

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConnectionRepository userConnectionRepository;

    public UserDetails loadUserByOpenId(String providerId,String openId) throws UsernameNotFoundException {
       
        OauthUserConnection userConnection = userConnectionRepository.findByOpenId(providerId,openId);
        if(userConnection!=null) {
            Optional<User> userOption = userRepository.findById(userConnection.getUserId());
            if (userOption.isPresent()) {
                User user = userOption.get();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                if (!CollectionUtils.isEmpty(user.getRoleList())) {
//                    for (RoleVO role : user.getRoleList()) {
//                        authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
//                    }
//                }
                return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
            }else {
                throw new UsernameNotFoundException(" providerId: "+ providerId+" openId: "+openId);
            }
        }else {
            //todo:发送请求到第三方平台验证是否登录成功
            
            userConnection = new OauthUserConnection();
            userConnection.setUserConnectionId(IdGeneratorUtil.generateSnowFlakeId());
            userConnection.setProviderId(providerId);
            userConnection.setOpenId(openId);
            User user = new User();
            user.setUserId(IdGeneratorUtil.generateSnowFlakeId());
            userConnection.setUserId(user.getUserId());
            userConnectionRepository.save(userConnection);
            userRepository.save(user);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        }
    }

}
