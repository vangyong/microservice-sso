package cn.segema.cloud.sso.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.segema.cloud.sso.server.domain.Resource;
import cn.segema.cloud.sso.server.domain.Role;
import cn.segema.cloud.sso.server.domain.User;
import cn.segema.cloud.sso.server.repository.ResourceRepository;
import cn.segema.cloud.sso.server.repository.RoleRepository;
import cn.segema.cloud.sso.server.repository.UserRepository;
import cn.segema.cloud.sso.server.vo.ResourceVO;
import cn.segema.cloud.sso.server.vo.RoleVO;
import cn.segema.cloud.sso.server.vo.UserVO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;

    public UserVO getUserByName(String username) {
      	User user = userRepository.findByUserName(username);
    		if(user!=null) {
    			UserVO userVO = new UserVO();
    			userVO.setUserName(user.getUserName());
    			userVO.setPassword(user.getPassword());
    			List<Role> roleList = roleRepository.findByUserId(user.getUserId());
    			
    			if(!CollectionUtils.isEmpty(roleList)) {
    				List<RoleVO> roleVOList = new ArrayList<RoleVO>();
    				for(Role role:roleList) {
    					RoleVO roleVO = new RoleVO();
    					roleVO.setRoleId(role.getRoleId());
    					roleVO.setRoleCode(role.getRoleCode());
    					roleVO.setRoleName(role.getRoleName());
    					List<Resource> resourceList = resourceRepository.findByRoleId(role.getRoleId());
    					if(!CollectionUtils.isEmpty(resourceList)) {
    						List<ResourceVO> resourceVOList = new ArrayList<ResourceVO>();
    						for(Resource resource:resourceList) {
    							ResourceVO resourceVO = new ResourceVO();
    							resourceVO.setId(resource.getResourceId());
    							resourceVO.setName(resource.getResourceName());
    							resourceVO.setCode(resource.getResourceCode());
    							resourceVO.setPid(resource.getParentId());
    							resourceVO.setUrl(resource.getResourceUrl());
    							resourceVO.setType(resource.getType());
    							resourceVO.setRank(resource.getRank());
    							resourceVO.setSortNumber(resource.getSortNumber());
    							resourceVOList.add(resourceVO);
    						}
    						roleVO.setResourceList(resourceVOList);
    					}
    					roleVOList.add(roleVO);
    				}
    				userVO.setRoleList(roleVOList);
    			}
    			 return userVO;
    		}
    		return null;
    }
}
