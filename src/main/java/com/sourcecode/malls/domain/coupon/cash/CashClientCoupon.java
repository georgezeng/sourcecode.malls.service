package com.sourcecode.malls.domain.coupon.cash;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.dto.coupon.cash.CashClientCouponDTO;
import com.sourcecode.malls.enums.ClientCouponStatus;

@Table(name = "cash_client_coupon")
@Entity
public class CashClientCoupon extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "优惠券编号不能为空")
	@Size(max = 255, message = "优惠券编号长度不能超过255")
	private String couponId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setting_id")
	@NotNull(message = "优惠券不能为空")
	private CashCouponSetting setting;

	@NotNull(message = "领取时间不能为空")
	private Date receivedTime;

	private Date usedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "状态不能为空")
	private ClientCouponStatus status;

	public ClientCouponStatus getStatus() {
		return status;
	}

	public void setStatus(ClientCouponStatus status) {
		this.status = status;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
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

	public CashCouponSetting getSetting() {
		return setting;
	}

	public void setSetting(CashCouponSetting setting) {
		this.setting = setting;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public CashClientCouponDTO asDTO() {
		CashClientCouponDTO dto = new CashClientCouponDTO();
		BeanUtils.copyProperties(this, dto);
		if (client != null) {
			dto.setClientName(client.getUsername());
		}
		if (order != null) {
			dto.setOrderId(order.getOrderId());
		}
		return dto;
	}

}