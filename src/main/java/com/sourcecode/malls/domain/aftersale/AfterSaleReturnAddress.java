package com.sourcecode.malls.domain.aftersale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.client.ClientAddressDTO;

@Table(name = "aftersale_return_address")
@Entity
public class AfterSaleReturnAddress extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "收件人不能为空")
	@Size(max = 50, message = "收件人长度不能大于50")
	private String name;
	@NotBlank(message = "联系电话不能为空")
	@Size(max = 50, message = "联系电话长度不能大于50")
	private String phone;
	@NotBlank(message = "回寄地址不能为空")
	@Size(max = 255, message = "回寄地址长度不能大于255")
	private String location;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	@NotNull(message = "售后记录不能为空")
	private AfterSaleApplication application;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AfterSaleApplication getApplication() {
		return application;
	}

	public void setApplication(AfterSaleApplication application) {
		this.application = application;
	}

	public ClientAddressDTO asDTO() {
		ClientAddressDTO dto = new ClientAddressDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
