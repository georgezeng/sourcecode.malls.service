package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.coupon.CouponSetting;

public interface ClientCouponRepository
		extends JpaRepository<ClientCoupon, Long>, JpaSpecificationExecutor<ClientCoupon> {

	List<ClientCoupon> findAllByClientAndSetting(Client client, CouponSetting setting);

}
