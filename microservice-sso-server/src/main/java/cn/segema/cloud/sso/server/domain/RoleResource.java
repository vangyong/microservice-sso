package cn.segema.cloud.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("角色资源关系")
@Data
@Table(name = "sys_role_resource")
@Entity
public class RoleResource {
    @Id
    @Column(name = "role_resource_id")
    private String roleResourceId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "resource_id")
    private String resourceId;

}

