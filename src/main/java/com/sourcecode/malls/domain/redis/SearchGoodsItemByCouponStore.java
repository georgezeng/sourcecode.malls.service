package com.sourcecode.malls.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "SearchGoodsItemByCouponStore")
public class SearchGoodsItemByCouponStore {

	@Id
	private String id;
	@Indexed
	private String couponId;
	private String key;

	public SearchGoodsItemByCouponStore() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}