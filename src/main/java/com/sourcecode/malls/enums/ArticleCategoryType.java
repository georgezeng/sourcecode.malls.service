package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ArticleCategoryType {
	Site("站点文章"), Life("多呗生活");

	private String text;

	private ArticleCategoryType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
