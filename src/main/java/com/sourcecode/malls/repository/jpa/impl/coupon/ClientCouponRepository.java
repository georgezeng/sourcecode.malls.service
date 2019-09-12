package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.coupon.CouponSetting;
import com.sourcecode.malls.enums.ClientCouponStatus;

public interface ClientCouponRepository extends JpaRepository<ClientCoupon, Long>, JpaSpecificationExecutor<ClientCoupon> {

	List<ClientCoupon> findAllByClientAndSetting(Client client, CouponSetting setting);

	List<ClientCoupon> findAllByClient(Client client);

	@Query(value = "select count(*) from client_coupon cc inner join coupon_setting cs where cc.setting_id=cs.id and cc.client_id=?1 and cs.event_type=?2", nativeQuery = true)
	long countTotalCoupons(long clientId, String type);

	@Query(value = "update client_coupon set status=?1 where setting_id=?2 and status=?3", nativeQuery = true)
	@Modifying
	void updateStatus(ClientCouponStatus status, Long settingId, ClientCouponStatus filterStatus);

}
