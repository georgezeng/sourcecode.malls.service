package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.coupon.CouponSetting;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.enums.CouponEventType;
import com.sourcecode.malls.enums.CouponSettingStatus;

public interface CouponSettingRepository
		extends JpaRepository<CouponSetting, Long>, JpaSpecificationExecutor<CouponSetting> {
	List<CouponSetting> findAllByMerchantAndEventTypeAndStatusAndEnabled(Merchant merchant, CouponEventType eventType,
			CouponSettingStatus status, boolean enabled);

	Optional<CouponSetting> findFirstByMerchantAndEventTypeAndStatusAndEnabledOrderByCreateTimeDesc(Merchant merchant,
			CouponEventType eventType, CouponSettingStatus status, boolean enabled);
}
