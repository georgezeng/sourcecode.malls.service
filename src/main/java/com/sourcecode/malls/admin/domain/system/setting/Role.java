package com.sourcecode.malls.admin.domain.system.setting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sourcecode.malls.admin.domain.base.BaseRole;
import com.sourcecode.malls.admin.dto.system.RoleDTO;

@Table(name = "${spring.datasource.table.role}")
@Entity
public class Role extends BaseRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "${spring.datasource.table.user_role}", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@OrderBy("username ASC")
	private List<User> users;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "${spring.datasource.table.role_authority}", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	@OrderBy("name ASC")
	private List<Authority> authorities;

	private boolean hidden;

	public Set<GrantedAuthority> getGrantedAuthorities() {
		Set<GrantedAuthority> set = new HashSet<>();
		if (authorities != null) {
			for (Authority auth : authorities) {
				set.add(new SimpleGrantedAuthority(auth.getCode()));
			}
		}
		return set;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void addAuthority(Authority auth) {
		if (authorities == null) {
			authorities = new ArrayList<>();
		}
		authorities.add(auth);
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public RoleDTO asDTO() {
		return asDTO(false);
	}

	public RoleDTO asDTO(boolean withUserAndAuthority) {
		RoleDTO dto = new RoleDTO();
		BeanUtils.copyProperties(this, dto, "users", "authorities");
		if (withUserAndAuthority) {
			if (authorities != null) {
				dto.setAuthorities(authorities.stream().map(auth -> auth.asDTO()).collect(Collectors.toList()));
			}
			if (users != null) {
				dto.setUsers(users.stream().map(user -> user.asDTO()).collect(Collectors.toList()));
			}
		}
		return dto;
	}

}
