package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsSpecificationDefinition;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface GoodsSpecificationDefinitionRepository
		extends JpaRepository<GoodsSpecificationDefinition, Long>, JpaSpecificationExecutor<GoodsSpecificationDefinition> {
	List<GoodsSpecificationDefinition> findByMerchant(Merchant merchant);

}
