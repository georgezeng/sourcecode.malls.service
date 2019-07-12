package com.sourcecode.malls.dto.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.client.ClientAddressDTO;
import com.sourcecode.malls.enums.OrderStatus;
import com.sourcecode.malls.enums.Payment;

public class OrderDTO {
	private Long id;
	private String orderId;
	private BigDecimal totalPrice;
	private List<SubOrderDTO> subList;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private OrderStatus status;
	private ClientAddressDTO address;
	private InvoiceDTO invoice;
	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public InvoiceDTO getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceDTO invoice) {
		this.invoice = invoice;
	}

	public ClientAddressDTO getAddress() {
		return address;
	}

	public void setAddress(ClientAddressDTO address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<SubOrderDTO> getSubList() {
		return subList;
	}

	public void setSubList(List<SubOrderDTO> subList) {
		this.subList = subList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
