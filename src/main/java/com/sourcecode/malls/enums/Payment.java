package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Payment {
	AliPay("支付宝"), WePay("微信");

	private String text;

	private Payment(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public String getName() {
		return name();
	}
}
