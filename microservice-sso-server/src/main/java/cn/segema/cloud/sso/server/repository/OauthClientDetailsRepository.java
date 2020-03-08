package cn.segema.cloud.sso.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.OauthClientDetails;

@Repository
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String>  {

}
