package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum GoodsItemEvaluationValue {
	Good("好评"), Bad("差评"), Neutrality("中评");

	private String text;

	private GoodsItemEvaluationValue(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public String getName() {
		return name();
	}
}
