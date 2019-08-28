package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ClientPointsType {
	ConsumeAdded("购物赠送"), RefundDeduction("退款扣除"), ManuallyAdded("手动增加"), ManuallyReduce("手动减少");

	private String text;

	private ClientPointsType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
