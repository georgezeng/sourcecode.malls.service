package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ClientActivityEventStatus {
	In("进行中"), Paused("暂停中"), UnStarted("未开始"), Stopped("已结束");

	private String text;

	private ClientActivityEventStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
