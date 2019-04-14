package cn.segema.cloud.sso.server.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "oauth_client_details")
@Entity
public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "client_id")
    private String client_id;

	@Column(name = "resource_ids")
    private String resource_ids;
  
	@Column(name = "client_secret")
    private String client_secret;

	@Column(name = "scope")
    private String scope;

	@Column(name = "authorized_grant_types")
    private String authorized_grant_types;
    
	@Column(name = "web_server_redirect_uri")
    private String web_server_redirect_uri;
    
	@Column(name = "authorities")
    private String authorities;
    
	@Column(name = "access_token_validity")
    private String access_token_validity;
    
	@Column(name = "refresh_token_validity")
    private String refresh_token_validity;
    
	@Column(name = "additional_information")
    private String additional_information;
    
	@Column(name = "autoapprove")
    private String autoapprove;

   
}
