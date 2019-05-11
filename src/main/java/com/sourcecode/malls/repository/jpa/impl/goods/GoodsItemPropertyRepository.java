package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemProperty;

public interface GoodsItemPropertyRepository extends JpaRepository<GoodsItemProperty, Long>, JpaSpecificationExecutor<GoodsItemProperty> {
	List<GoodsItemProperty> findAllByItem(GoodsItem item);
}
