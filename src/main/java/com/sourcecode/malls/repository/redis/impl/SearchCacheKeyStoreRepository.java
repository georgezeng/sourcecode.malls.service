package com.sourcecode.malls.repository.redis.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.domain.redis.SearchCacheKeyStore;

public interface SearchCacheKeyStoreRepository extends CrudRepository<SearchCacheKeyStore, String> {
	List<SearchCacheKeyStore> findAllByTypeAndBizKey(String type, String bizKey);
}
