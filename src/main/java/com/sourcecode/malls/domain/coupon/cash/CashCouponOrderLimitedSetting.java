package com.sourcecode.malls.domain.coupon.cash;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.coupon.cash.CashCouponOrderLimitedSettingDTO;

@Table(name = "cash_coupon_order_limited_setting")
@Entity
public class CashCouponOrderLimitedSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@NotNull(message = "订单额不能为空")
	private BigDecimal orderAmount;

	@NotNull(message = "限额不能为空")
	private BigDecimal limitedAmount;

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getLimitedAmount() {
		return limitedAmount;
	}

	public void setLimitedAmount(BigDecimal limitedAmount) {
		this.limitedAmount = limitedAmount;
	}

	public CashCouponOrderLimitedSettingDTO asDTO() {
		CashCouponOrderLimitedSettingDTO dto = new CashCouponOrderLimitedSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
