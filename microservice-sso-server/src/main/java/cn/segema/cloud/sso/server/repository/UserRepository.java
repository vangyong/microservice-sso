package cn.segema.cloud.sso.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u from User u  where u.userName = ?1 ")
    public User findByUserName(String userName);
    
    @Query("SELECT u from User u  where u.mobileNumber = ?1 ")
    public User getUserByMobileNumber(String mobileNumber);

    @Query("SELECT u from User u  where u.thirdPartyType = ?1 and u.openId = ?1 ")
    public User findByOpenId(String thirdPartyType, String openId);
}
