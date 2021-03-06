package com.sourcecode.malls.domain.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Entity
@Table(name = "merchant_shop_application_instruction")
public class MerchantShopApplicationInstruction extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "店铺引导图不能为空")
	@Size(max = 50, message = "店铺引导图长度不能大于255")
	private String path;

	@Column(name="order_num")
	private int order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	@NotNull
	private MerchantShopApplication shopApplication;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public MerchantShopApplication getShopApplication() {
		return shopApplication;
	}

	public void setShopApplication(MerchantShopApplication shopApplication) {
		this.shopApplication = shopApplication;
	}

}
