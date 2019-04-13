package com.sourcecode.malls.admin.dto.merchant;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.base.BaseUser;
import com.sourcecode.malls.admin.domain.merchant.Merchant;

public class MerchantDTO extends BaseUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;

	private String searchText;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Merchant asEntity() {
		Merchant entity = new Merchant();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

}
