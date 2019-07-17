package com.sourcecode.malls.repository.redis.impl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.domain.redis.WechatStore;

public interface WechatStoreRepository extends CrudRepository<WechatStore, String> {
	Optional<WechatStore> findByCategoryAndKey(String category, String key);
}
