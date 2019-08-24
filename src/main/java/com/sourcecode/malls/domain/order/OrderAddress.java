package com.sourcecode.malls.domain.order;

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

@Table(name = "order_address")
@Entity
public class OrderAddress extends LongKeyEntity {

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
	@NotBlank(message = "省份不能为空")
	@Size(max = 50, message = "省份长度不能大于50")
	private String province;
	@NotBlank(message = "城市不能为空")
	@Size(max = 50, message = "城市长度不能大于50")
	private String city;
	@NotBlank(message = "城区不能为空")
	@Size(max = 50, message = "城市长度不能大于50")
	private String district;
	@NotBlank(message = "详细地址不能为空")
	@Size(max = 255, message = "详细地址长度不能大于255")
	private String location;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@NotNull(message = "订单不能为空")
	private Order order;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ClientAddressDTO asDTO() {
		ClientAddressDTO dto = new ClientAddressDTO();
		BeanUtils.copyProperties(this, dto, "order");
		return dto;
	}

}
