package cn.segema.cloud.sso.server.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 角色-资源关系
 * 
 * @author wangyong
 *
 */
@Table(name = "sys_role_resource")
@Entity
public class RoleResource {
	@Id
	@Column(name = "role_resource_id")
	private BigInteger roleResourceId;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;

	public BigInteger getRoleResourceId() {
		return roleResourceId;
	}

	public void setRoleResourceId(BigInteger roleResourceId) {
		this.roleResourceId = roleResourceId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
