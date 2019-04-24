package com.sourcecode.malls.admin.repository.jpa.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.goods.GoodsSpecificationDefinition;
import com.sourcecode.malls.admin.domain.merchant.Merchant;

public interface GoodsSpecificationDefinitionRepository
		extends JpaRepository<GoodsSpecificationDefinition, Long>, JpaSpecificationExecutor<GoodsSpecificationDefinition> {
	List<GoodsSpecificationDefinition> findByMerchant(Merchant merchant);

}
