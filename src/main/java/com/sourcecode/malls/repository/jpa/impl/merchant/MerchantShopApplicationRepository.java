package com.sourcecode.malls.repository.jpa.impl.merchant;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.constants.CacheNameConstant;
import com.sourcecode.malls.domain.merchant.MerchantShopApplication;

public interface MerchantShopApplicationRepository
		extends JpaRepository<MerchantShopApplication, Long>, JpaSpecificationExecutor<MerchantShopApplication> {
	Optional<MerchantShopApplication> findByMerchantId(Long merchantId);

	@Cacheable(cacheNames = CacheNameConstant.MERCHANT_LOAD_BY_DOMAIN, key = "#domain")
	Optional<MerchantShopApplication> findByDomain(String domain);
}
