package com.sourcecode.malls.dto.coupon;

import java.math.BigDecimal;

public class CouponMerchantSettingDTO {
	private BigDecimal limitedAmount;

	public BigDecimal getLimitedAmount() {
		return limitedAmount;
	}

	public void setLimitedAmount(BigDecimal limitedAmount) {
		this.limitedAmount = limitedAmount;
	}
}
