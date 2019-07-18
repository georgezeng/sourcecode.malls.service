package com.sourcecode.malls.domain.order;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.order.ExpressDTO;

@Table(name = "express")
@Entity
public class Express extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@NotNull(message = "订单不能为空")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	private String company;
	private String number;
	private Date expressTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "express_sub_order", joinColumns = { @JoinColumn(name = "express_id") }, inverseJoinColumns = {
			@JoinColumn(name = "sub_order_id") })
	private List<SubOrder> subList;

	public Date getExpressTime() {
		return expressTime;
	}

	public void setExpressTime(Date expressTime) {
		this.expressTime = expressTime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	public List<SubOrder> getSubList() {
		return subList;
	}

	public void setSubList(List<SubOrder> subList) {
		this.subList = subList;
	}

	public ExpressDTO asDTO() {
		ExpressDTO dto = new ExpressDTO();
		BeanUtils.copyProperties(this, dto, "subList");
		if (subList != null) {
			dto.setSubList(subList.stream().map(it -> it.asDTO()).collect(Collectors.toList()));
		}
		return dto;
	}

}
