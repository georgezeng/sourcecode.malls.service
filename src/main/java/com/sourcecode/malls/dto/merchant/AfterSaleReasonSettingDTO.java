package com.sourcecode.malls.dto.merchant;

import com.sourcecode.malls.enums.AfterSaleType;

public class AfterSaleReasonSettingDTO {
	private Long id;
	private AfterSaleType type;
	private String content;
	private int orderNum;

	public AfterSaleType getType() {
		return type;
	}

	public void setType(AfterSaleType type) {
		this.type = type;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
