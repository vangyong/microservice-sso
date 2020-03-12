package cn.segema.cloud.sso.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

//    private RoleVO admin = new RoleVO("ADMIN", "管理员");
//    private RoleVO developer = new RoleVO("DEVELOPER", "开发者");
//
//    {
//    	
//    	 	ResourceVO p0 = new ResourceVO();
//    	 	p0.setCode("index");
//    	 	p0.setName("首页");
//    	 	p0.setUrl("/index");
//    	
//    	 	ResourceVO p1 = new ResourceVO();
//        p1.setCode("memberExport");
//        p1.setName("会员列表导出");
//        p1.setUrl("/member/export");
//
//        ResourceVO p2 = new ResourceVO();
//        p2.setCode("BookList");
//        p2.setName("图书列表");
//        p2.setUrl("/book/list");
//
//
//        admin.setResourceList(Arrays.asList(p1, p2));
//        developer.setResourceList(Arrays.asList(p1));
//
//    }

    @Query("SELECT u from User u  where u.userName = ?1 ") 
	 public User findByUserName(String userName); 
    
//    public UserVO findByName(String username) {
//    	
//    	
//        log.info("从数据库中查询用户");
//        if ("zhangsan".equals(username)) {
//        		UserVO sysUser = new UserVO("zhangsan", "$2a$10$aZDOWYEvK06TrxN6g0Mta.X3gtnj1sHPReRic5YRcOiXl4yMctwS6");
//            sysUser.setRoleList(Arrays.asList(admin, developer));
//            return sysUser;
//        }else if ("lisi".equals(username)) {
//            UserVO sysUser = new UserVO("lisi", "$2a$10$aZDOWYEvK06TrxN6g0Mta.X3gtnj1sHPReRic5YRcOiXl4yMctwS6");
//            sysUser.setRoleList(Arrays.asList(developer));
//            return sysUser;
//        }
//        return null;
//    }

}
