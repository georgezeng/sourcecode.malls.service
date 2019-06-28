package com.sourcecode.malls.repository.jpa.impl.merchant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.InvoiceSetting;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface InvoiceSettingRepository
		extends JpaRepository<InvoiceSetting, Long>, JpaSpecificationExecutor<InvoiceSetting> {
	Page<InvoiceSetting> findAllByMerchant(Merchant merchant, Pageable pageable);

	Page<InvoiceSetting> findAllByMerchantAndContentLike(Merchant merchant, String content, Pageable pageable);

}
