package com.sourcecode.malls.admin.repository.jpa.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.goods.GoodsCategory;
import com.sourcecode.malls.admin.domain.merchant.Merchant;

public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long>, JpaSpecificationExecutor<GoodsCategory> {
	List<GoodsCategory> findByMerchant(Merchant merchant);

}
