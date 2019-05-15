package com.sourcecode.malls.dto.system;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.BaseAuthority;
import com.sourcecode.malls.domain.system.Authority;

public class AuthorityDTO extends BaseAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Authority asEntity() {
		Authority entity = new Authority();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
