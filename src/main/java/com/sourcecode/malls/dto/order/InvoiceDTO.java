package com.sourcecode.malls.dto.order;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.order.Invoice;
import com.sourcecode.malls.enums.InvoiceType;

public class InvoiceDTO {
	private Long id;
	private InvoiceType type;
	private String title;
	private String code;
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Invoice asEntity() {
		Invoice data = new Invoice();
		BeanUtils.copyProperties(this, data, "id");
		return data;
	}
}
