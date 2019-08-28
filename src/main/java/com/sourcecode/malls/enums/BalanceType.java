package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum BalanceType {
	In("收入"), Out("支出");

	private String text;

	private BalanceType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
