package com.sourcecode.malls.admin.repository.jpa.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.merchant.Merchant;
import com.sourcecode.malls.admin.domain.merchant.MerchantShopApplication;

public interface MerchantShopApplicationRepository
		extends JpaRepository<MerchantShopApplication, Long>, JpaSpecificationExecutor<MerchantShopApplication> {
	Optional<MerchantShopApplication> findByMerchant(Merchant merchant);

	Optional<MerchantShopApplication> findByDomain(String domain);
}
