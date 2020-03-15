package cn.segema.cloud.sso.server.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("手机验证码VO")
@Data
public class MobileCodeVO implements Serializable {

    private static final long serialVersionUID = 6381759637345454964L;

    @ApiModelProperty(value = "手机号码")
    private String mobileNumber;

    @ApiModelProperty(value = "状态(1: 已验证 2：未验证)")
    private Integer status;

    @ApiModelProperty(value = "验证码")
    private String code;
}
