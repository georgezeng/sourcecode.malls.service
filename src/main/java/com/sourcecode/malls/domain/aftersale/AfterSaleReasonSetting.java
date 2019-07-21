package com.sourcecode.malls.domain.aftersale;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.merchant.AfterSaleReasonSettingDTO;
import com.sourcecode.malls.enums.AfterSaleType;

@Table(name = "aftersale_reason_setting")
@Entity
public class AfterSaleReasonSetting extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商户不能为空")
	private Merchant merchant;

	@NotBlank(message = "内容不能为空")
	@Size(max = 50, message = "内容长度不能超过50")
	private String content;

	@NotNull(message = "类型不能为空")
	@Enumerated(EnumType.STRING)
	private AfterSaleType type;

	private int orderNum;

	public AfterSaleType getType() {
		return type;
	}

	public void setType(AfterSaleType type) {
		this.type = type;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AfterSaleReasonSettingDTO asDTO() {
		AfterSaleReasonSettingDTO dto = new AfterSaleReasonSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
