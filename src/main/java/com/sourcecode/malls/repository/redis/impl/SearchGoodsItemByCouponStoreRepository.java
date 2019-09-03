package com.sourcecode.malls.repository.redis.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.domain.redis.SearchGoodsItemByCouponStore;

public interface SearchGoodsItemByCouponStoreRepository extends CrudRepository<SearchGoodsItemByCouponStore, String> {
	List<SearchGoodsItemByCouponStore> findAllByCouponId(String couponId);
}
