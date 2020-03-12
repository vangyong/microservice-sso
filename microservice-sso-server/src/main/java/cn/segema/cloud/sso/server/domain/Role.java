package cn.segema.cloud.sso.server.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel("角色")
@Data
@Table(name = "sys_role")
@Entity
public class Role implements Serializable {
    
    private static final long serialVersionUID = 7813962037134101133L;

    @Id
    @Column(name = "role_id")
    private String roleId;
    
    @Column(name = "role_name")
    private String roleName;
    
    @Column(name = "role_code")
    private String roleCode;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "locked")
    private Integer locked;
    
    @Column(name = "delete_status")
    private Integer deletestatus;

    @Column(name = "create_time")
    private BigInteger createTime;
    
    @Column(name = "tenant_id")
    private String tenantId;

    
}
