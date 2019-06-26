package com.sourcecode.malls.domain.client;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.order.InvoiceDTO;
import com.sourcecode.malls.enums.InvoiceType;

@Table(name = "invoice_template")
@Entity
public class InvoiceTemplate extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "类型不能为空")
	private InvoiceType type;

	@NotBlank(message = "抬头不能为空")
	@Size(max=255, message = "抬头长度不能超过255")
	private String title;

	@Size(max=255, message = "编号长度不能超过255")
	private String code;

	@NotBlank(message = "内容不能为空")
	@Size(max=50, message = "内容长度不能超过50")
	private String content;

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public InvoiceDTO asDTO() {
		InvoiceDTO dto = new InvoiceDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
