package com.sourcecode.malls.repository.redis.impl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.domain.redis.CodeStore;

public interface CodeStoreRepository extends CrudRepository<CodeStore, String> {
	Optional<CodeStore> findByCategoryAndKey(String category, String key);
}
