package com.sourcecode.malls.repository.jpa.impl.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.goods.GoodsItemRank;

public interface GoodsItemRankRepository extends JpaRepository<GoodsItemRank, Long>, JpaSpecificationExecutor<GoodsItemRank> {

}
