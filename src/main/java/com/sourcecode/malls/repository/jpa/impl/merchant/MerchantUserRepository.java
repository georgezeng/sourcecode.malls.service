package com.sourcecode.malls.repository.jpa.impl.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.MerchantUser;

public interface MerchantUserRepository extends JpaRepository<MerchantUser, Long>, JpaSpecificationExecutor<MerchantUser> {

}
