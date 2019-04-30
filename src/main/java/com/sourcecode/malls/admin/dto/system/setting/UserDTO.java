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

	private String oldPassword;

	private String searchText;

	private String statusText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

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
