package cn.segema.cloud.sso.server.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("资源")
@Data
@Table(name = "sys_resource")
@Entity
public class Resource {
    @ApiModelProperty(value="资源id")
    @Id
    @Column(name = "resource_id")
    private String resourceId;
    
    @ApiModelProperty(value="资源名称")
    @Column(name = "resource_name")
    private String resourceName;
    
    @ApiModelProperty(value="父级id")
    @Column(name = "parent_id")
    private String parentId;
    
    @ApiModelProperty(value="资源编码")
    @Column(name = "resource_code")
    private String resourceCode;
    
    @ApiModelProperty(value="http请求类型")
    @Column(name = "type")
    private String type;
    
    @ApiModelProperty(value="资源URL")
    @Column(name = "resource_url")
    private String resourceUrl;
    
    @ApiModelProperty(value="级别")
    @Column(name = "rank")
    private String rank;
    
    @ApiModelProperty(value="图标")
    @Column(name = "icon")
    private String icon;
    
    @ApiModelProperty(value="隐藏标示")
    @Column(name = "hide")
    private Integer hide;
    
    @ApiModelProperty(value="描述")
    @Column(name = "description")
    private String description;
    
    @ApiModelProperty(value="删除标示")
    @Column(name = "delete_status")
    private Integer deleteStatus;

    @ApiModelProperty(value="创建时间")
    @Column(name = "create_time")
    private BigInteger createTime;
    
    @ApiModelProperty(value="排序号",example = "1")
    @Column(name = "sort_number")
    private Integer sortNumber;
    
}
