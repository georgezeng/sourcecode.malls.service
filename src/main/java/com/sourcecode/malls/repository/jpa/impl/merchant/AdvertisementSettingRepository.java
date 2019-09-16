package com.sourcecode.malls.repository.jpa.impl.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.AdvertisementSetting;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface AdvertisementSettingRepository
		extends JpaRepository<AdvertisementSetting, Long>, JpaSpecificationExecutor<AdvertisementSetting> {
	List<AdvertisementSetting> findAllByMerchant(Merchant merchant);
}
