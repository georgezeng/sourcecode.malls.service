package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum AdvertisementType {
	HomeBanner("首页轮播图"), HomeRecommend("首页推荐图"), CategoryBanner("分类页轮播图"), CategoryBrand("分类页品牌");

	private String text;

	private AdvertisementType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
