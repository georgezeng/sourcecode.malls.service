package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.cash.CashCouponSetting;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.enums.CashCouponEventType;

public interface CashCouponSettingRepository
		extends JpaRepository<CashCouponSetting, Long>, JpaSpecificationExecutor<CashCouponSetting> {
	List<CashCouponSetting> findAllByMerchantAndEventType(Merchant merchant, CashCouponEventType eventType);
}
