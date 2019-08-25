package com.sourcecode.malls.dto.coupon;

public class CouponHxDTO extends CouponConsumeEventSettingDTO {
	private Long id;
	private int limitedNums;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
