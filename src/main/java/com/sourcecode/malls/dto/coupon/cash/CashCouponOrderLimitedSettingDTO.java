package com.sourcecode.malls.dto.coupon.cash;

import java.math.BigDecimal;

public class CashCouponOrderLimitedSettingDTO {
	private Long id;

	private BigDecimal orderAmount;

	private BigDecimal limitedAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
