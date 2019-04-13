package com.sourcecode.malls.admin.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum MerchantVerificationType {
	SaleLicense("营业执照"), Identity("身份证");

	private String text;

	private MerchantVerificationType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public String getName() {
		return name();
	}
}
