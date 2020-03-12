package cn.segema.cloud.sso.server.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(name = "sys_user")
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -4099450485824039374L;

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "nick_name")
	private String nickName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "credentials_salt")
	private String credentialsSalt;
	
	@Column(name = "gender")
	private Integer gender;
	
	@Column(name = "delete_status")
	private Integer deletestatus;
	
	@Column(name = "create_time")
	private BigInteger createTime;
	
	@Column(name = "tenant_id")
	private BigInteger tenantId;
	
	@Column(name = "open_id")
	private String openId;

	@ApiModelProperty(value = "第三方类型(wechat:微信,qq:腾讯QQ,aliyay:支付宝)")
	@Column(name = "third_party_type")
	private String thirdPartyType;
	
	@Column(name = "avatar_url")
	private String avatarUrl;

	
	
}
