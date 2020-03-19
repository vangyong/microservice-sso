package cn.segema.cloud.sso.server.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("第三方登录关联")
@Data
@Table(name = "oauth_user_connection")
@Entity
public class UserConnection {
    
    @Id
    @Column(name = "user_connection_id")
    private String userConnectionId;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name = "provider_id")
    private String providerId;
    
    @Column(name = "open_id")
    private String openId;
    
    @Column(name = "rank")
    private Integer rank;
    
    @Column(name = "display_name")
    private String display_name;
    
    @Column(name = "profile_url")
    private String profile_url;
    
    @Column(name = "image_url")
    private String image_url;
    
    @Column(name = "access_token")
    private String access_token;
    
    @Column(name = "secret")
    private String secret;
    
    @Column(name = "refresh_token")
    private String refresh_token;
    
    @Column(name = "expire_time")
    private BigInteger expire_time;

}

