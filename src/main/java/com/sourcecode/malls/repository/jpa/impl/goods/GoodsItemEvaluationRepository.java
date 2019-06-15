package com.sourcecode.malls.repository.jpa.impl.goods;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemEvaluation;

public interface GoodsItemEvaluationRepository extends JpaRepository<GoodsItemEvaluation, Long>, JpaSpecificationExecutor<GoodsItemEvaluation> {
	Optional<GoodsItemEvaluation> findFirstByItemOrderByCreateTimeDesc(GoodsItem item);
}
