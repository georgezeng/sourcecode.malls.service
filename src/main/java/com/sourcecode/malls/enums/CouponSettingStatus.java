package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum CouponSettingStatus {
	WaitForPut("待发放"), PutAway("发放中"), SoldOut("已下架"), SentOut("已领完");

	private String text;

	private CouponSettingStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
