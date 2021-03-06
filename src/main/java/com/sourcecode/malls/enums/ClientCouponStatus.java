package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ClientCouponStatus {
	UnUse("已领取"), Used("已使用"), Out("已失效");

	private String text;

	private ClientCouponStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
