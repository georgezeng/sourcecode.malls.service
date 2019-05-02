package com.sourcecode.malls.admin.repository.jpa.impl.merchant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sourcecode.malls.admin.domain.merchant.MerchantVerification;

public interface MerchantVerificationRepository extends JpaRepository<MerchantVerification, Long>, JpaSpecificationExecutor<MerchantVerification> {
	@Query(value = "select * from merchant_verification where merchant_id=?1", nativeQuery = true)
	Optional<MerchantVerification> findByMerchantId(Long merchantId);
}
