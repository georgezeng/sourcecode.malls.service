package com.sourcecode.malls.web.security.config.attribute;

import org.springframework.security.access.ConfigAttribute;

import com.sourcecode.malls.domain.system.setting.Authority;

public class AuthorityConfigAttribute implements ConfigAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Authority auth;

	public AuthorityConfigAttribute(Authority auth) {
		this.auth = auth;
	}

	public Authority getAuth() {
		return auth;
	}

	@Override
	public String getAttribute() {
		return auth.getCode();
	}

}
