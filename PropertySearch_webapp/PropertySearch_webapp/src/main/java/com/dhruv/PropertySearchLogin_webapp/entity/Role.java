package com.dhruv.PropertySearchLogin_webapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

	@Id
	private long roleId;
	private String roleName;
	
	 
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="role")
    private Set<UserRoles> userRoles = new HashSet<>();
	
	
	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
	public Role() {
	
	}
	public Role(long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
