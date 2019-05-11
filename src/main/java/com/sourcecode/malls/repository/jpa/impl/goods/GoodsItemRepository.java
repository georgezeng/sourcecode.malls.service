package com.sourcecode.malls.repository.jpa.impl.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsItem;

public interface GoodsItemRepository extends JpaRepository<GoodsItem, Long>, JpaSpecificationExecutor<GoodsItem> {

}
