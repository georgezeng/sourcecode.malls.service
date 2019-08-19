package com.sourcecode.malls.dto.coupon.cash;

public class CashCouponHxDTO extends CashCouponConsumeEventSettingDTO {
	private Long id;
	private int limitedNums;

	public int getLimitedNums() {
		return limitedNums;
	}

	public void setLimitedNums(int limitedNums) {
		this.limitedNums = limitedNums;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
