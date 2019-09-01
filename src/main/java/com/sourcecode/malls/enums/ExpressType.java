package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ExpressType {
	Self("自取"), Delivery("投递");

	private String text;

	private ExpressType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
