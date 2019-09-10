package com.sourcecode.malls.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "SearchCacheKeyStore")
public class SearchCacheKeyStore {
	public final static String SEARCH_GOODS_ITEM_BY_CATEGORY = "SEARCH_GOODS_ITEM_BY_CATEGORY";
	public final static String SEARCH_GOODS_ITEM_BY_COUPON = "SEARCH_GOODS_ITEM_BY_COUPON";
	public final static String SEARCH_CLIENT_COUPON = "SEARCH_CLIENT_COUPON";
	public final static String SEARCH_CLIENT_ORDER = "SEARCH_CLIENT_ORDER";
	public final static String SEARCH_ARTICLE = "SEARCH_ARTICLE";
	public final static String SEARCH_AFTER_SALE = "SEARCH_AFTER_SALE";
	public final static String SEARCH_UNCOMMENT = "SEARCH_UNCOMMENT";
	public final static String SEARCH_COMMENT = "SEARCH_COMMENT";
	
	@Id
	private String id;
	@Indexed
	private String type;
	@Indexed
	private String bizKey;
	private String searchKey;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
