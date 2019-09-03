package com.sourcecode.malls.repository.redis.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.domain.redis.SearchGoodsItemByCategoryStore;

public interface SearchGoodsItemByCategoryStoreRepository
		extends CrudRepository<SearchGoodsItemByCategoryStore, String> {
	List<SearchGoodsItemByCategoryStore> findAllByCategoryId(String categoryId);
}
