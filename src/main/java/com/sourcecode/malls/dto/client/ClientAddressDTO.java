package com.sourcecode.malls.dto.client;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sourcecode.malls.domain.aftersale.AfterSaleAddress;
import com.sourcecode.malls.domain.order.OrderAddress;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;

public class ClientAddressDTO extends SimpleQueryDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String phone;
	private String province;
	private String city;
	private String district;
	private String location;
	@JsonProperty("asDefault")
	private boolean isDefault;

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public OrderAddress asOrderAddressEntity() {
		OrderAddress data = new OrderAddress();
		BeanUtils.copyProperties(this, data, "id");
		return data;
	}

	public AfterSaleAddress asAfterSaleAddressEntity() {
		AfterSaleAddress data = new AfterSaleAddress();
		BeanUtils.copyProperties(this, data, "id");
		return data;
	}

}
