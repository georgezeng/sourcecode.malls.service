package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.ClientLevelSetting;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface ClientLevelSettingRepository
		extends JpaRepository<ClientLevelSetting, Long>, JpaSpecificationExecutor<ClientLevelSetting> {
	
	Optional<ClientLevelSetting> findByMerchantAndLevel(Merchant merchant, int level);
	
	Optional<ClientLevelSetting> findFirstByMerchantAndNameNotNullOrderByLevelDesc(Merchant merchant);
	
	List<ClientLevelSetting> findAllByMerchantAndNameNotNullOrderByLevelDesc(Merchant merchant);

	List<ClientLevelSetting> findAllByMerchantOrderByLevelAsc(Merchant merchant);
}
