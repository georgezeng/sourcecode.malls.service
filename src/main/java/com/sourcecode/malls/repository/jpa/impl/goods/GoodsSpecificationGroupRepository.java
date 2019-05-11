package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsCategory;
import com.sourcecode.malls.domain.goods.GoodsSpecificationGroup;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface GoodsSpecificationGroupRepository
		extends JpaRepository<GoodsSpecificationGroup, Long>, JpaSpecificationExecutor<GoodsSpecificationGroup> {
	List<GoodsSpecificationGroup> findByMerchant(Merchant merchant);

	List<GoodsSpecificationGroup> findByCategoryAndMerchant(GoodsCategory category, Merchant merchant);
}
