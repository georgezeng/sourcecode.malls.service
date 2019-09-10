package com.sourcecode.malls.domain.aftersale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.order.SubOrder;
import com.sourcecode.malls.dto.aftersale.AfterSaleApplicationDTO;
import com.sourcecode.malls.enums.AfterSaleStatus;
import com.sourcecode.malls.enums.AfterSaleType;

@Table(name = "aftersale_application")
@Entity
public class AfterSaleApplication extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商户不能为空")
	private Merchant merchant;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@NotNull(message = "订单不能为空")
	private Order order;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_order_id")
	@NotNull(message = "子订单不能为空")
	private SubOrder subOrder;

	private int nums;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private AfterSaleType type;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "状态不能为空")
	private AfterSaleStatus status;

	@Size(max = 50, message = "原因长度不能超过50")
	private String reason;

	@Size(max = 255, message = "描述长度不能超过255")
	private String description;

	@OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private List<AfterSalePhoto> photos;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "application")
	private AfterSaleAddress address;

	@Size(max = 255, message = "规格组长度不能大于255")
	private String specificationValues;

	@Size(max = 50, message = "物流公司名称长度不能超过50")
	private String clientExpressCompany;

	@Size(max = 255, message = "物流单号长度不能超过255")
	private String clientExpressNumber;

	@Size(max = 50, message = "物流公司名称长度不能超过50")
	private String merchantExpressCompany;

	@Size(max = 255, message = "物流单号长度不能超过255")
	private String merchantExpressNumber;

	private Date postTime;

	private Date processedTime;

	private Date returnTime;

	private Date receiveTime;

	private Date pickupTime;

	private Date sendTime;

	private Date refundTime;

	private Date rejectTime;

	@NotBlank(message = "服务单号不能为空")
	@Size(max = 255, message = "服务单号长度不能超过255")
	private String serviceId;

	@Size(max = 255, message = "拒绝原因长度不能超过255")
	private String rejectReason;

	@Size(max = 255, message = "备注长度不能超过255")
	private String remark;

	@OneToOne(mappedBy = "application", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private AfterSaleReturnAddress returnAddress;

	public AfterSaleReturnAddress getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(AfterSaleReturnAddress returnAddress) {
		this.returnAddress = returnAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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

	public String getSpecificationValues() {
		return specificationValues;
	}

	public void setSpecificationValues(String specificationValues) {
		this.specificationValues = specificationValues;
	}

	public AfterSaleAddress getAddress() {
		return address;
	}

	public void setAddress(AfterSaleAddress address) {
		this.address = address;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public List<AfterSalePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<AfterSalePhoto> photos) {
		this.photos = photos;
	}

	public AfterSaleApplicationDTO asDTO() {
		AfterSaleApplicationDTO dto = new AfterSaleApplicationDTO();
		BeanUtils.copyProperties(this, dto, "client", "merchant", "order", "subOrder", "photos", "returnAddress");
		dto.setBuyer(client.getUsername());
		if (returnAddress != null) {
			dto.setReturnAddress(returnAddress.asDTO());
		}
		if (subOrder != null) {
			dto.setSubOrder(subOrder.asDTO(false));
		}
		if (photos != null) {
			List<String> list = new ArrayList<>();
			for (AfterSalePhoto photo : photos) {
				list.add(photo.getPath());
			}
			dto.setPhotos(list);
		}
		if (order != null) {
			dto.setOrder(order.asDTO(false, false));
		}
		if (AfterSaleStatus.Rejected.equals(status)) {
			dto.setAgree(false);
		} else if (!AfterSaleStatus.Processing.equals(status)) {
			dto.setAgree(true);
		}
		return dto;
	}

}
