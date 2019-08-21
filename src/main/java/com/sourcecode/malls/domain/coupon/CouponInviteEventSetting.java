package com.sourcecode.malls.domain.coupon;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.coupon.CouponInviteEventSettingDTO;

@Table(name = "coupon_invite_event_setting")
@Entity
public class CouponInviteEventSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setting_id")
	@NotNull(message = "优惠券不能为空")
	private CouponSetting setting;

	private int memberNums;

	public CouponSetting getSetting() {
		return setting;
	}

	public void setSetting(CouponSetting setting) {
		this.setting = setting;
	}

	public int getMemberNums() {
		return memberNums;
	}

	public void setMemberNums(int memberNums) {
		this.memberNums = memberNums;
	}

	public CouponInviteEventSettingDTO asDTO() {
		CouponInviteEventSettingDTO dto = new CouponInviteEventSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
