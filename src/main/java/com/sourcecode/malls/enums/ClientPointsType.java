package com.sourcecode.malls.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ClientPointsType {
	ConsumeAdded("购物赠送", BalanceType.In), RefundDeduction("退款扣除", BalanceType.Out),
	Registration("注册奖励", BalanceType.In), Invite("邀请奖励", BalanceType.In),
	ManuallyAdded("手动增加", BalanceType.In), ManuallyReduce("手动减少", BalanceType.Out);

	private String text;

	private BalanceType type;

	private ClientPointsType(String text, BalanceType type) {
		this.text = text;
		this.type = type;
	}

	public BalanceType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name();
	}
}
