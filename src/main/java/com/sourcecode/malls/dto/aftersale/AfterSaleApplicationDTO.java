package com.sourcecode.malls.dto.aftersale;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.client.ClientAddressDTO;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.dto.order.OrderDTO;
import com.sourcecode.malls.dto.order.SubOrderDTO;
import com.sourcecode.malls.enums.AfterSaleStatus;
import com.sourcecode.malls.enums.AfterSaleType;

public class AfterSaleApplicationDTO extends SimpleQueryDTO {
	private Long id;

	private int nums;

	private String buyer;

	private BigDecimal amount;

	private AfterSaleType type;

	private AfterSaleStatus status;

	private String reason;

	private String description;

	private List<String> photos;

	private ClientAddressDTO address;

	private String specificationValues;

	private OrderDTO order;

	private SubOrderDTO subOrder;

	private Long propertyId;

	private GoodsItemDTO item;

	private String serviceId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date postTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date processedTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date returnTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receiveTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date refundTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date rejectTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pickupTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date sendTime;

	private String rejectReason;

	private String remark;

	private Boolean agree;

	private String clientExpressCompany;

	private String clientExpressNumber;

	private String merchantExpressCompany;

	private String merchantExpressNumber;

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getClientExpressCompany() {
		return clientExpressCompany;
	}

	public void setClientExpressCompany(String clientExpressCompany) {
		this.clientExpressCompany = clientExpressCompany;
	}

	public String getClientExpressNumber() {
		return clientExpressNumber;
	}

	public void setClientExpressNumber(String clientExpressNumber) {
		this.clientExpressNumber = clientExpressNumber;
	}

	public String getMerchantExpressCompany() {
		return merchantExpressCompany;
	}

	public void setMerchantExpressCompany(String merchantExpressCompany) {
		this.merchantExpressCompany = merchantExpressCompany;
	}

	public String getMerchantExpressNumber() {
		return merchantExpressNumber;
	}

	public void setMerchantExpressNumber(String merchantExpressNumber) {
		this.merchantExpressNumber = merchantExpressNumber;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Date getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(Date processedTime) {
		this.processedTime = processedTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getRejectTime() {
		return rejectTime;
	}

	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}

	public GoodsItemDTO getItem() {
		return item;
	}

	public void setItem(GoodsItemDTO item) {
		this.item = item;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public SubOrderDTO getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrderDTO subOrder) {
		this.subOrder = subOrder;
	}

	public String getSpecificationValues() {
		return specificationValues;
	}

	public void setSpecificationValues(String specificationValues) {
		this.specificationValues = specificationValues;
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

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AfterSaleType getType() {
		return type;
	}

	public void setType(AfterSaleType type) {
		this.type = type;
	}

	public AfterSaleStatus getStatus() {
		return status;
	}

	public void setStatus(AfterSaleStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
}
