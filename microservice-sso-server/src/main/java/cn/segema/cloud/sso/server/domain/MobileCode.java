package cn.segema.cloud.sso.server.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("短信验证码")
@Data
@Table(name = "third_mobile_code")
@Entity
public class MobileCode {
	@Id
	@Column(name = "mobile_code_id")
	private String mobileCodeId;

	@ApiModelProperty(value = "手机号码")
	@Column(name = "mobile_number")
	private String mobileNumber;

	@ApiModelProperty(value = "状态(1: 已验证 2：未验证)")
	@Column(name = "status")
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;

	@ApiModelProperty(value = "验证码")
	@Column(name = "code")
	private String code;

	@ApiModelProperty(value = "短信模版id")
	@Column(name = "sms_temp_id")
    private BigInteger smsTempId;

}
