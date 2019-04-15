package com.sourcecode.malls.admin.dto.system.setting;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.base.BaseUser;
import com.sourcecode.malls.admin.domain.system.setting.User;

public class UserDTO extends BaseUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RoleDTO> roles;

	private List<String> authorities;

	private String accountText;

	public String getAccountText() {
		return accountText;
	}

	public void setAccountText(String accountText) {
		this.accountText = accountText;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public User asEntity() {
		User entity = new User();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

}
