package cn.segema.cloud.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("用户角色关系")
@Data
@Table(name = "sys_user_role")
@Entity
public class UserRole {
    @Id
    @Column(name = "user_role_id")
    private String userRoleId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

}

