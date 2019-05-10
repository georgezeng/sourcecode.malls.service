package com.sourcecode.malls.admin.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.goods.GoodsItem;
import com.sourcecode.malls.admin.domain.goods.GoodsItemProperty;

public interface GoodsItemPropertyRepository extends JpaRepository<GoodsItemProperty, Long>, JpaSpecificationExecutor<GoodsItemProperty> {
	List<GoodsItemProperty> findAllByItem(GoodsItem item);
}
