package com.sourcecode.malls.repository.jpa.impl.aftersale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.aftersale.AfterSaleReasonSetting;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.enums.AfterSaleType;

public interface AfterSaleReasonSettingRepository
		extends JpaRepository<AfterSaleReasonSetting, Long>, JpaSpecificationExecutor<AfterSaleReasonSetting> {

	List<AfterSaleReasonSetting> findAllByMerchantAndType(Merchant merchant, AfterSaleType type);
}
