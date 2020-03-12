package cn.segema.cloud.sso.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {
	
	 
	 @Query("select r from Resource r where r.resourceName = ?1") 
	 List<Resource> findByResourceName(String resourceName); 
	 
	 @Query("select r from Resource r,RoleResource rr,Role ro where ro.roleId = ?1 and r.resourceId=rr.resource and ro.roleId =rr.role") 
	 List<Resource> findByRoleId(String roleId); 
}
