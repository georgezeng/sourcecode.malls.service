package com.sourcecode.malls.admin.dto.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.base.BaseUser;
import com.sourcecode.malls.admin.domain.merchant.Merchant;
import com.sourcecode.malls.admin.dto.system.setting.AuthorityDTO;

public class MerchantDTO extends BaseUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String statusText;

	private String searchText;

	private List<AuthorityDTO> authorities = new ArrayList<>();

	public void addAuthority(AuthorityDTO authority) {
		authorities.add(authority);
	}

	public List<AuthorityDTO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityDTO> authorities) {
		this.authorities = authorities;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
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
