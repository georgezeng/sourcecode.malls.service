package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.cash.CashCouponOrderLimitedSetting;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface CashCouponOrderLimitedSettingRepository extends JpaRepository<CashCouponOrderLimitedSetting, Long>,
		JpaSpecificationExecutor<CashCouponOrderLimitedSetting> {
	Page<CashCouponOrderLimitedSetting> findAllByMerchant(Merchant merchant, Pageable pageable);
}
