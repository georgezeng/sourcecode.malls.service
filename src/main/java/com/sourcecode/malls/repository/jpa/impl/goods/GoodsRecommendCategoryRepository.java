package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsCategory;
import com.sourcecode.malls.domain.goods.GoodsRecommendCategory;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface GoodsRecommendCategoryRepository
		extends JpaRepository<GoodsRecommendCategory, Long>, JpaSpecificationExecutor<GoodsRecommendCategory> {
	List<GoodsRecommendCategory> findTop3ByMerchantAndCategory(Merchant merchant, GoodsCategory category);
}
