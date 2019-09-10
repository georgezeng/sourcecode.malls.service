package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum AfterSaleStatus {
	NotYet("尚未申请", "尚未申请"), Processing("审核中", "审核中"), WaitForReturn("待发货", "待发货"), WaitForReceive("待收货", "待收货"),
	WaitForRefund("退款中", "待退款"), WaitForSend("待发货", "待发货"), WaitForPickup("待收货", "已发货"), Finished("已完成", "已完成"),
	Rejected("未通过", "未通过");

	private String clientText;
	private String merchantText;

	private AfterSaleStatus(String clientText, String merchantText) {
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
