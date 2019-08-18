package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.cash.CashCouponInviteEventSetting;

public interface CashCouponInviteEventSettingRepository extends JpaRepository<CashCouponInviteEventSetting, Long>, JpaSpecificationExecutor<CashCouponInviteEventSetting> {
}
