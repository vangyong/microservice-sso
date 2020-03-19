package cn.segema.cloud.sso.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.UserConnection;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, String> {

    @Query("SELECT uc from UserConnection uc  where uc.providerId = ?1 and uc.openId = ?2 ")
    public UserConnection findByOpenId(String providerId, String openId);
}
