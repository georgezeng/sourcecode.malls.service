package com.sourcecode.malls.admin.domain.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.merchant.Merchant;
import com.sourcecode.malls.admin.dto.merchant.GoodsAttributeDTO;

@MappedSuperclass
public abstract class BaseGoodsAttribute extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	@NotBlank(message = "名称不能为空")
	@Size(max = 50, min = 1, message = "名称长度应该在1-50之间")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public GoodsAttributeDTO asDTO() {
		GoodsAttributeDTO dto = new GoodsAttributeDTO();
		BeanUtils.copyProperties(this, dto, "merchant");
		return dto;
	}

}
