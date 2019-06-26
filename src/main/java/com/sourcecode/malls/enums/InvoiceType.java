package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum InvoiceType {
	Company("公司"), Individual("个人");

	private String text;

	private InvoiceType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name();
	}
}
