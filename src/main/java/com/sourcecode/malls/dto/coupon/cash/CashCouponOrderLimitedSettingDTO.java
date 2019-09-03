package com.sourcecode.malls.dto.coupon.cash;

import java.io.Serializable;
import java.math.BigDecimal;

public class CashCouponOrderLimitedSettingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
