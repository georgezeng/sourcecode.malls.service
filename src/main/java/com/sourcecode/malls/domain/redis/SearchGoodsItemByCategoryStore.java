package com.sourcecode.malls.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "SearchGoodsItemByCategoryStore")
public class SearchGoodsItemByCategoryStore {

	@Id
	private String id;
	@Indexed
	private String categoryId;
	private String key;

	public SearchGoodsItemByCategoryStore() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}