package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum OrderStatus {
	UnPay("待付款"), Paid("已付款"), Canceled("已取消"), Closed("已关闭"), Finished("已完成");

	private String text;

	private OrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public String getName() {
		return name();
	}
}
