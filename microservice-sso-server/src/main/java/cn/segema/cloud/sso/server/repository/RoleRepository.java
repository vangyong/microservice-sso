package cn.segema.cloud.sso.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	 
	 @Query("select r from Role r where r.roleName = ?1") 
	 List<Role> findByRoleName(String roleName); 
	 
	 @Query("select r from Role r,UserRole ur,User u where u.userId = ?1 and r.roleId=ur.roleId and u.userId =ur.userId") 
	 List<Role> findByUserId(String userId); 
	 
	 
}
