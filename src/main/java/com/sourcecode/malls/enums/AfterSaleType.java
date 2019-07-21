package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum AfterSaleType {
	RefundOnly("仅退款"), SalesReturn("退货退款"), Change("换货");

	private String text;

	private AfterSaleType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name();
	}
}
