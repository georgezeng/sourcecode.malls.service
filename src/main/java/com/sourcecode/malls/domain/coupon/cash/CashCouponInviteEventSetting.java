package com.sourcecode.malls.domain.coupon.cash;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.coupon.cash.CashCouponInviteEventSettingDTO;

@Table(name = "cash_coupon_invite_event_setting")
@Entity
public class CashCouponInviteEventSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setting_id")
	@NotNull(message = "优惠券不能为空")
	private CashCouponSetting setting;

	private int memberNums;

	public CashCouponSetting getSetting() {
		return setting;
	}

	public void setSetting(CashCouponSetting setting) {
		this.setting = setting;
	}

	public int getMemberNums() {
		return memberNums;
	}

	public void setMemberNums(int memberNums) {
		this.memberNums = memberNums;
	}

	public CashCouponInviteEventSettingDTO asDTO() {
		CashCouponInviteEventSettingDTO dto = new CashCouponInviteEventSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
