package cn.segema.cloud.sso.server.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户-角色关系
 * @author wangyong
 *
 */
@Table(name = "sys_user_role")
@Entity
public class UserRole {
	@Id
	@Column(name = "user_role_id")
	private BigInteger userRoleId;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public BigInteger getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(BigInteger userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
