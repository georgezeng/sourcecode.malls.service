package com.sourcecode.malls.repository.jpa.impl.merchant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.MerchantRole;

public interface MerchantRoleRepository extends JpaRepository<MerchantRole, Long>, JpaSpecificationExecutor<MerchantRole> {
	Optional<MerchantRole> findByCode(String code);

}
