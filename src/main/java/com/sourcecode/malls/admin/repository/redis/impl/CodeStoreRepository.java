package com.sourcecode.malls.admin.repository.redis.impl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sourcecode.malls.admin.domain.redis.CodeStore;

public interface CodeStoreRepository extends CrudRepository<CodeStore, String> {
	Optional<CodeStore> findByCategoryAndKey(String category, String key);
}
