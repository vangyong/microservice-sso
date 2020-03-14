package cn.segema.cloud.sso.server.support;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.segema.cloud.sso.server.service.UserService;
import cn.segema.cloud.sso.server.vo.RoleVO;
import cn.segema.cloud.sso.server.vo.UserVO;


@Service
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
//				for (ResourceVO resourceVO : role.getResourceList()) {
//					authorities.add(new SimpleGrantedAuthority(resourceVO.getCode()));
//				}
				authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
			}
		}
		// passwordEncoder.encode(sysUser.getPassword());
		return new User(sysUser.getUserName(), sysUser.getPassword(), authorities);
	}

//	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//		logger.info("设计登录用户Id:" + userId);
//		return buildUser(userId);
//	}
//
//	private SocialUser buildUser(String userId) {
//		// 根据用户名查找用户信息
//		// 根据查找到的用户信息判断用户是否被冻结
//		String password = passwordEncoder.encode("123456");
//		logger.info("数据库密码是:" + password);
//		return new SocialUser(userId, password, true, true, true, true,
//				AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
//	}
}
