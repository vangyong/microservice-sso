package cn.segema.cloud.sso.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.OauthUserConnection;

@Repository
public interface UserConnectionRepository extends JpaRepository<OauthUserConnection, String> {

    @Query("SELECT uc from OauthUserConnection uc  where uc.providerId = ?1 and uc.openId = ?2 ")
    public OauthUserConnection findByOpenId(String providerId, String openId);
}
