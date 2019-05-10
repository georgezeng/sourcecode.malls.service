package com.sourcecode.malls.admin.repository.jpa.impl.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.goods.GoodsItemValue;

public interface GoodsItemValueRepository extends JpaRepository<GoodsItemValue, Long>, JpaSpecificationExecutor<GoodsItemValue> {
	List<GoodsItemValue> findAllByUid(String uid);
}
