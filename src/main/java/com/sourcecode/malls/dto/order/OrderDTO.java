package com.sourcecode.malls.dto.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.aftersale.AfterSaleApplicationDTO;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.client.ClientAddressDTO;
import com.sourcecode.malls.dto.coupon.OrderCouponDTO;
import com.sourcecode.malls.enums.OrderStatus;
import com.sourcecode.malls.enums.Payment;

public class OrderDTO extends SimpleQueryDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long merchantId;

	private String buyer;
	private Long id;
	private String orderId;
	private BigDecimal totalPrice;
	private Boolean cancelForRefund;
	private List<SubOrderDTO> subList;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;
	private OrderStatus status;
	private ClientAddressDTO address;
	private InvoiceDTO invoice;
	private Payment payment;
	private List<ExpressDTO> expressList;
	private String transactionId;
	private String remark;
	private boolean applied;
	private BigDecimal realPrice;
	private BigDecimal couponAmount;
	private List<OrderCouponDTO> coupons;
	private boolean comment;
	private BigDecimal discount;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date refundTime;
	private String clientLevelName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pickupTime;
	private List<AfterSaleApplicationDTO> aftersaleList;
	private BigDecimal expressFee;

	public BigDecimal getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}

	public List<AfterSaleApplicationDTO> getAftersaleList() {
		return aftersaleList;
	}

	public void setAftersaleList(List<AfterSaleApplicationDTO> aftersaleList) {
		this.aftersaleList = aftersaleList;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getClientLevelName() {
		return clientLevelName;
	}

	public void setClientLevelName(String clientLevelName) {
		this.clientLevelName = clientLevelName;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public List<OrderCouponDTO> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<OrderCouponDTO> coupons) {
		this.coupons = coupons;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public boolean isApplied() {
		return applied;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	public Boolean getCancelForRefund() {
		return cancelForRefund;
	}

	public void setCancelForRefund(Boolean cancelForRefund) {
		this.cancelForRefund = cancelForRefund;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public List<ExpressDTO> getExpressList() {
		return expressList;
	}

	public void setExpressList(List<ExpressDTO> expressList) {
		this.expressList = expressList;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

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
