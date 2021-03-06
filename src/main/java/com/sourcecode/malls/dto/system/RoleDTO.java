package com.sourcecode.malls.dto.system;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.BaseRole;
import com.sourcecode.malls.domain.system.Role;

public class RoleDTO extends BaseRole {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthorityDTO> authorities;
	private List<UserDTO> users;

	public Role asEntity() {
		Role entity = new Role();
		BeanUtils.copyProperties(this, entity, "authorities", "users");
		return entity;
	}

	public List<AuthorityDTO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityDTO> authorities) {
		this.authorities = authorities;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

}
