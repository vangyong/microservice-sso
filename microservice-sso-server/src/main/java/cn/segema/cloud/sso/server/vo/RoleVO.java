package cn.segema.cloud.sso.server.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色VO")
@Data
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1960667607641277046L;

	@ApiModelProperty(value = "公共参数")
	private BigInteger roleId;
	
	@ApiModelProperty(value = "角色编码")
	private String roleCode;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@ApiModelProperty(value = "角色拥有的资源")
	private List<ResourceVO> resourceList;

	public RoleVO() {
		
	}
	
	public RoleVO(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
}
