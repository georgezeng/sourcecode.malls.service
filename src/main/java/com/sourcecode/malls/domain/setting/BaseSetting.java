package com.sourcecode.malls.domain.setting;

import javax.persistence.MappedSuperclass;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@MappedSuperclass
public abstract class BaseSetting extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
