package com.sourcecode.malls.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CacheEvictService {

	@Caching(evict = {
			@CacheEvict(cacheNames = "client_unuse_coupon_nums", allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = "client_coupon_nums", allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = "client_unuse_coupon_nums", key = "#clientId", condition = "#clientId != null"),
			@CacheEvict(cacheNames = "client_coupon_nums", key = "#clientId + '-UnUse'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = "client_coupon_nums", key = "#clientId + '-Used'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = "client_coupon_nums", key = "#clientId + '-Out'", condition = "#clientId != null")})
	public void clearClientCoupons(Long clientId) {
	}

	@CacheEvict(cacheNames = "client_invite_poster", key = "#clientId")
	public void clearClientInvitePoster(Long clientId) {
	}

	@Caching(evict = {
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-UnPay'"),
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-Paid'"),
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-Shipped'"),
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-Canceled'"),
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-Closed'"),
			@CacheEvict(cacheNames = "client_order_nums", key = "#clientId + '-Finished'"), })
	public void clearClientOrders(Long clientId) {
	}
	
	@CacheEvict(cacheNames = "client_uncomment_nums", key = "#clientId")
	public void clearClientUnCommentNums(Long clientId) {
	}
	
	@CacheEvict(cacheNames = "client_aftersale_unfinished_nums", key = "#clientId")
	public void clearClientAfterSaleUnFinishedtNums(Long clientId) {
	}
}
