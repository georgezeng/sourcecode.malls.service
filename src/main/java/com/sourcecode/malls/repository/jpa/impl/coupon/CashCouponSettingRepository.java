package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.cash.CashCouponSetting;

public interface CashCouponSettingRepository extends JpaRepository<CashCouponSetting, Long>, JpaSpecificationExecutor<CashCouponSetting> {
}
