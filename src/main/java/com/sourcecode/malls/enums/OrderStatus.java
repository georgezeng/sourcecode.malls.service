package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum OrderStatus {
	UnPay("待付款", "待付款"), Paid("待发货", "待发货"), Shipped("待收货", "已发货"), Canceled("已取消", "已取消"),
	CanceledForRefund("已取消", "已取消"), RefundApplied("退款中", "待退款"), Refunded("已退款", "已退款"), Closed("已关闭", "已关闭"),
	Finished("交易成功", "交易成功");

	private String clientText;
	private String merchantText;

	private OrderStatus(String clientText, String merchantText) {
		this.clientText = clientText;
		this.merchantText = merchantText;
	}

	public String getClientText() {
		return clientText;
	}

	public String getMerchantText() {
		return merchantText;
	}

	public String getName() {
		return name();
	}
}
