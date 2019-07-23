package com.sourcecode.malls.repository.jpa.impl.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsItemEvaluationPhoto;

public interface GoodsItemEvaluationPhotoRepository
		extends JpaRepository<GoodsItemEvaluationPhoto, Long>, JpaSpecificationExecutor<GoodsItemEvaluationPhoto> {
}
