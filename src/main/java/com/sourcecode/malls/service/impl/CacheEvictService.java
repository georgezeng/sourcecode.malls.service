package com.sourcecode.malls.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.sourcecode.malls.constants.CacheNameConstant;
import com.sourcecode.malls.domain.goods.GoodsItem;

@Service
public class CacheEvictService {

	@Caching(evict = {
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNUSE_COUPON_NUMS, allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNUSE_COUPON_NUMS, key = "#clientId", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-UnUse'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-Used'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-Out'", condition = "#clientId != null") })
	public void clearClientCoupons(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_INTIVE_POSTER, key = "#clientId")
	public void clearClientInvitePoster(Long clientId) {
	}

	@Caching(evict = {
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-UnPay'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Paid'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Shipped'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Canceled'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-CanceledForRefund'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-RefundApplied'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Refunded'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Closed'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId.toString() + '-Finished'") })
	public void clearClientOrders(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNCOMMENT_NUMS, key = "#clientId")
	public void clearClientUnCommentNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_CURRENT_POINTS, key = "#clientId")
	public void clearClientCurrentPoints(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_AFTERSALE_UNFINISHED_NUMS, key = "#clientId")
	public void clearClientAfterSaleUnFinishedtNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ACTIVITY_EVENT_TIME, key = "#merchantId")
	public void clearClientActivityEventTime(Long merchantId) {
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_LOAD_ONE, key = "#item.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_LOAD_DEFINITIONS, key = "#item.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 0 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 1 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 2 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 3 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 4 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 5 + '-' + #item.client.id"), })
	public void clearGoodsItemLoadOne(GoodsItem item) {
	}

	@Caching(evict = {
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 0 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 1 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 2 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 3 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 4 + '-' + #item.client.id"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#item.id + '-' + 5 + '-' + #item.client.id"), })
	public void clearGoodsItemSharePosters(GoodsItem item) {
	}

}
