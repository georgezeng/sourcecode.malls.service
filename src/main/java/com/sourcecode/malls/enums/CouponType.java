package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum CouponType {
	Cash("现金券");

	private String text;

	private CouponType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name();
	}
}
