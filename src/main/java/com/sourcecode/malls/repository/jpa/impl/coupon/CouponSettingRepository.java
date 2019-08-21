package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.CouponSetting;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.enums.CashCouponEventType;
import com.sourcecode.malls.enums.CouponSettingStatus;

public interface CouponSettingRepository
		extends JpaRepository<CouponSetting, Long>, JpaSpecificationExecutor<CouponSetting> {
	List<CouponSetting> findAllByMerchantAndEventTypeAndStatusAndEnabled(Merchant merchant,
			CashCouponEventType eventType, CouponSettingStatus status, boolean enabled);
}
