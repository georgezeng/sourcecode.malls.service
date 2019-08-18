package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.cash.CashCouponConsumeEventSetting;

public interface CashCouponConsumeEventSettingRepository extends JpaRepository<CashCouponConsumeEventSetting, Long>, JpaSpecificationExecutor<CashCouponConsumeEventSetting> {
}
