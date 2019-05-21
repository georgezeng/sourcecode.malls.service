package com.sourcecode.malls.repository.jpa.impl.merchant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.merchant.MerchantSetting;

public interface MerchantSettingRepository extends JpaRepository<MerchantSetting, Long>, JpaSpecificationExecutor<MerchantSetting> {
	Optional<MerchantSetting> findByMerchantAndCode(Merchant merchant, String code);
}
