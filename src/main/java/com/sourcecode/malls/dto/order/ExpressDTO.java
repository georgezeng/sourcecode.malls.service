package com.sourcecode.malls.dto.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.domain.order.Express;

public class ExpressDTO {
	private Long id;
	private Long orderId;
	private String company;
	private String number;
	private List<SubOrderDTO> subList;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date expressTime;

	public Date getExpressTime() {
		return expressTime;
	}

	public void setExpressTime(Date expressTime) {
		this.expressTime = expressTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<SubOrderDTO> getSubList() {
		return subList;
	}

	public void setSubList(List<SubOrderDTO> subList) {
		this.subList = subList;
	}

	public Express asEntity() {
		Express data = new Express();
		BeanUtils.copyProperties(this, data, "subList");
		return data;
	}
}
