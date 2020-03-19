package cn.segema.cloud.sso.server.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户VO")
@Data
public class UserVO implements Serializable {

	private static final long serialVersionUID = -228300848350217557L;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "盐值")
	private String salt;

	@ApiModelProperty(value = "用户角色")
	private List<RoleVO> roleList;

}
