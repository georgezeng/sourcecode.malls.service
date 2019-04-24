package com.sourcecode.malls.admin.domain.base;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseAuthority extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "编码不能为空")
	@Size(min = 5, max = 255, message = "编码长度必须在5-255之间")
	private String code;
	@NotBlank(message = "名称不能为空")
	@Size(min = 2, max = 50, message = "名称长度必须在2-50之间")
	private String name;
	@NotBlank(message = "链接不能为空")
	@Size(min = 10, max = 255, message = "链接长度必须在10-255之间")
	private String link;
	private String method;
	@Size(max = 255, message = "描述长度必须小于等于255")
	private String description;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
