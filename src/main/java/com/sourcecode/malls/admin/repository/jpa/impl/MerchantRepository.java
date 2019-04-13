package com.sourcecode.malls.admin.repository.jpa.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.merchant.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long>, JpaSpecificationExecutor<Merchant> {
	Optional<Merchant> findByUsername(String username);

}
