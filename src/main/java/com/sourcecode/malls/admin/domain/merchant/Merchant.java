package com.sourcecode.malls.admin.domain.merchant;

import javax.persistence.Entity;
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

	public MerchantDTO asDTO() {
		MerchantDTO dto = new MerchantDTO();
		BeanUtils.copyProperties(this, dto, "password");
		return dto;
	}
}
