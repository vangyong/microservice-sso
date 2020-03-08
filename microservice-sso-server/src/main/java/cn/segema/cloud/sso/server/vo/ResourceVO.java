package cn.segema.cloud.sso.server.vo;

import java.io.Serializable;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("资源VO")
@Data
@ToString
public class ResourceVO implements Serializable {

	private static final long serialVersionUID = 2793561154882639434L;

	@ApiModelProperty(value = "公共参数")
	private BigInteger id;

	@ApiModelProperty(value = "资源名称")
    private String name;

	@ApiModelProperty(value = "资源编码")
    private String code;

	@ApiModelProperty(value = "资源类型")
    private Integer type;

	@ApiModelProperty(value = "资源URL")
    private String url;

	@ApiModelProperty(value = "排序号")
    private BigInteger sort;


	@ApiModelProperty(value = "父菜单id")
    private BigInteger pid;

}
