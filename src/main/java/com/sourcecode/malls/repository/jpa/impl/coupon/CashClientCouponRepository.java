package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.cash.CashClientCoupon;
import com.sourcecode.malls.domain.coupon.cash.CashCouponSetting;

public interface CashClientCouponRepository
		extends JpaRepository<CashClientCoupon, Long>, JpaSpecificationExecutor<CashClientCoupon> {
	Optional<CashClientCoupon> findByClientAndSetting(Client client, CashCouponSetting setting);
}
