package com.sourcecode.malls.domain.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.order.OrderDTO;
import com.sourcecode.malls.dto.order.SubOrderDTO;
import com.sourcecode.malls.enums.OrderStatus;
import com.sourcecode.malls.enums.Payment;

@Table(name = "parent_order")
@Entity
public class Order extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "订单编号不能为空")
	@Size(max = 255, message = "订单编号长度不能超过255")
	private String orderId;

	@OneToMany(mappedBy = "parent")
	private List<SubOrder> subList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@NotNull(message = "合计金额不能为空")
	private BigDecimal totalPrice;

	@NotNull(message = "实付金额不能为空")
	private BigDecimal realPrice;

	private BigDecimal couponAmount;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "交易状态不能为空")
	private OrderStatus status;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "支付方式不能为空")
	private Payment payment;

	private String transactionId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
	private OrderAddress address;

	@Size(max = 30, message = "买家留言长度不能超过30")
	private String remark;

	private boolean deleted;

	private Date payTime;

	private Date sentTime;

	private Date refundTime;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<Express> expressList;

	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
	private Invoice invoice;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<ClientCoupon> coupons;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fromOrder")
	private List<ClientCoupon> generatedCoupons;

	public List<ClientCoupon> getGeneratedCoupons() {
		return generatedCoupons;
	}

	public void setGeneratedCoupons(List<ClientCoupon> generatedCoupons) {
		this.generatedCoupons = generatedCoupons;
	}

	public List<ClientCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<ClientCoupon> coupons) {
		this.coupons = coupons;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<Express> getExpressList() {
		return expressList;
	}

	public void setExpressList(List<Express> expressList) {
		this.expressList = expressList;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrderAddress getAddress() {
		return address;
	}

	public void setAddress(OrderAddress address) {
		this.address = address;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<SubOrder> getSubList() {
		return subList;
	}

	public void setSubList(List<SubOrder> subList) {
		this.subList = subList;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderDTO asDTO(boolean withSub, boolean withMoreDetail) {
		OrderDTO dto = new OrderDTO();
		BeanUtils.copyProperties(this, dto, "subList", "address", "expressList", "invoice", "coupons");
		dto.setBuyer(client.getUsername());
		if (withSub) {
			if (!CollectionUtils.isEmpty(subList)) {
				List<SubOrderDTO> dtos = new ArrayList<>();
				for (SubOrder sub : subList) {
					dtos.add(sub.asDTO());
				}
				dto.setSubList(dtos);
			}
		}
		if (withMoreDetail) {
			if (address != null) {
				dto.setAddress(address.asDTO());
			}
			if (invoice != null) {
				dto.setInvoice(invoice.asDTO());
			}
			if (expressList != null) {
				dto.setExpressList(expressList.stream().map(it -> it.asDTO()).collect(Collectors.toList()));
			}
			if (coupons != null) {
				dto.setCoupons(coupons.stream().map(it -> it.asOrderDTO()).collect(Collectors.toList()));
			}
		}
		return dto;
	}

}
