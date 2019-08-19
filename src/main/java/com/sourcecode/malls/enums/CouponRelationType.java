package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum CouponRelationType {
	All("无限制"), Category("限分类"), Item("限商品");

	private String text;

	private CouponRelationType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name();
	}
}
