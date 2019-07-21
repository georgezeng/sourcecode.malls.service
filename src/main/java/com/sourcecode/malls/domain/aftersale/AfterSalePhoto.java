package com.sourcecode.malls.domain.aftersale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Table(name = "aftersale_photo")
@Entity
public class AfterSalePhoto extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	@NotNull(message = "申请记录不能为空")
	private AfterSaleApplication application;

	@NotBlank(message = "图片路径不能为空")
	@Size(max = 255, message = "图片路径不能超过255")
	private String path;

	public AfterSaleApplication getApplication() {
		return application;
	}

	public void setApplication(AfterSaleApplication application) {
		this.application = application;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
