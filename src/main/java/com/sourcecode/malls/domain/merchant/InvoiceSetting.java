package com.sourcecode.malls.domain.merchant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.merchant.InvoiceSettingDTO;

@Table(name = "invoice_setting")
@Entity
public class InvoiceSetting extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "用户不能为空")
	private Merchant merchant;

	@NotBlank(message = "内容不能为空")
	@Size(max = 50, message = "内容长度不能超过50")
	private String content;

	private int orderNum;

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

	public InvoiceSettingDTO asDTO() {
		InvoiceSettingDTO dto = new InvoiceSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
