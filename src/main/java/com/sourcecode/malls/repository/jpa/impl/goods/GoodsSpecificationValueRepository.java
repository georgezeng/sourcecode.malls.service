package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsSpecificationValue;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface GoodsSpecificationValueRepository
		extends JpaRepository<GoodsSpecificationValue, Long>, JpaSpecificationExecutor<GoodsSpecificationValue> {
	List<GoodsSpecificationValue> findByMerchant(Merchant merchant);

}
