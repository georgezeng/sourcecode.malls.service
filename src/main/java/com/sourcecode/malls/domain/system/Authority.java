package com.sourcecode.malls.domain.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.BaseAuthority;
import com.sourcecode.malls.dto.system.AuthorityDTO;

@Table(name = "${spring.datasource.table.authority}")
@Entity
public class Authority extends BaseAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<Role> roles;

	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(role);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public AuthorityDTO asDTO() {
		AuthorityDTO dto = new AuthorityDTO();
		BeanUtils.copyProperties(this, dto, "roles");
		return dto;
	}

}
