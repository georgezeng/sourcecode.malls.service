package com.sourcecode.malls.admin.domain.merchant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.base.BaseUser;
import com.sourcecode.malls.admin.dto.merchant.MerchantDTO;

@Table(name = "merchant_user")
@Entity
public class Merchant extends BaseUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Merchant parent;

	public Merchant getParent() {
		return parent;
	}

	public void setParent(Merchant parent) {
		this.parent = parent;
	}

	public MerchantDTO asDTO() {
		MerchantDTO dto = new MerchantDTO();
		BeanUtils.copyProperties(this, dto, "password", "parent");
		return dto;
	}
	
}
