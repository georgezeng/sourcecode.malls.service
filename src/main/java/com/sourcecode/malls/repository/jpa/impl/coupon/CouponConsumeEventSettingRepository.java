package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.CouponConsumeEventSetting;

public interface CouponConsumeEventSettingRepository extends JpaRepository<CouponConsumeEventSetting, Long>, JpaSpecificationExecutor<CouponConsumeEventSetting> {
}
