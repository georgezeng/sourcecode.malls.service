package com.sourcecode.malls.admin.domain.system.setting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sourcecode.malls.admin.domain.base.BaseUser;
import com.sourcecode.malls.admin.dto.system.UserDTO;

@Table(name = "${spring.datasource.table.user}")
@Entity
public class User extends BaseUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final User SystemUser = new User("System");

	public User() {

	}

	private User(String username) {
		super(username);
	}

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Role> roles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private User parent;

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new HashSet<>();
		}
		roles.add(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> set = new HashSet<>();
		if (roles != null) {
			for (Role role : roles) {
				set.addAll(role.getGrantedAuthorities());
			}
		}
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public UserDTO asDTO() {
		return asDTO(false);
	}

	public UserDTO asDTO(boolean withRoles) {
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(this, dto, "password", "roles");
		dto.setAccountText(parent != null ? "副账号" : "主账号");
		if (withRoles) {
			if (roles != null) {
				dto.setRoles(roles.stream().map(role -> role.asDTO()).collect(Collectors.toList()));
			}
		}
		List<String> auths = new ArrayList<>();
		for (GrantedAuthority auth : getAuthorities()) {
			auths.add(auth.getAuthority());
		}
		dto.setAuthorities(auths);
		return dto;
	}
}
