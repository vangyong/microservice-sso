package cn.segema.cloud.sso.server.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cn.segema.cloud.sso.server.service.UserService;
import cn.segema.cloud.sso.server.vo.ResourceVO;
import cn.segema.cloud.sso.server.vo.RoleVO;
import cn.segema.cloud.sso.server.vo.UserVO;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String getUserToken(String username) {
        //从数据库或者缓存中取出jwt token生成时用的salt 
        String salt = stringRedisTemplate.opsForValue().get("salt:"+username);
        //UserDetails user = loadUserByUsername(username);
        // 将salt放到password字段返回
        String usertoken = stringRedisTemplate.opsForValue().get("token:" + username);
        return usertoken;
    }

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO sysUser = userService.getUserByName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysUser.getRoleList())) {
            for (RoleVO role : sysUser.getRoleList()) {
                if(!CollectionUtils.isEmpty(role.getResourceList())) {
                    for (ResourceVO resourceVO : role.getResourceList()) {
                        authorities.add(new SimpleGrantedAuthority(resourceVO.getCode()));
                    }
                }
            }
        }
        // passwordEncoder.encode(sysUser.getPassword());
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }

    public String saveUserToken(String username) {
        String salt =  BCrypt.gensalt(); //正式开发时可以调用该方法实时生成加密的salt
        // 将salt保存到数据库或者缓存中
        stringRedisTemplate.opsForValue().set("salt:" + username, salt, 3600, TimeUnit.SECONDS);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000); // 设置1小时后过期
        String token = JWT.create().withSubject(username).withExpiresAt(date).withIssuedAt(new Date()).sign(algorithm);
        String old_token = stringRedisTemplate.opsForValue().get("token:" + username);
        stringRedisTemplate.opsForValue().set("token:" + username, token, 3600, TimeUnit.SECONDS);
        return token;
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }
}
