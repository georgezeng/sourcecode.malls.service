package com.sourcecode.malls.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.sourcecode.malls.constants.CacheNameConstant;
import com.sourcecode.malls.enums.AdvertisementType;
import com.sourcecode.malls.enums.AfterSaleType;

@Service
public class CacheEvictService {

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_LIST, allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNUSE_COUPON_NUMS, allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, allEntries = true, condition = "#clientId == null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNUSE_COUPON_NUMS, key = "#clientId", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-UnUse'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-Used'", condition = "#clientId != null"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_NUMS, key = "#clientId + '-Out'", condition = "#clientId != null") })
	public void clearClientCouponNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COUPON_LIST, key = "#key")
	public void clearClientCouponList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_INTIVE_POSTER, key = "#clientId")
	public void clearClientInvitePoster(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_SUB_LIST, key = "#clientId + '-' + #num")
	public void clearClientSubList(Long clientId, int num) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_TOTAL_INVITE_INFO, key = "#clientId")
	public void clearClientTotalInviteInfo(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_BONUS_INFO, key = "#merchantId")
	public void clearClientBonusInfo(Long merchantId) {
	}
	
	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_INFO, key = "#clientId")
	public void clearClientInfo(Long clientId) {
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-UnPay'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-All'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Paid'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Shipped'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Canceled'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-CanceledForRefund'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-RefundApplied'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Refunded'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Closed'"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_NUMS, key = "#clientId + '-Finished'") })
	public void clearClientOrderNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_LOAD_ONE, key = "#id")
	public void clearClientOrder(Long id) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ORDER_LIST, key = "#key")
	public void clearClientOrderList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNCOMMENT_NUMS, key = "#clientId")
	public void clearClientUnCommentNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_UNCOMMENT_LIST, key = "#key")
	public void clearClientUnCommentList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_COMMENT_LIST, key = "#key")
	public void clearClientCommentList(String key) {
	}
	
	@CacheEvict(cacheNames = CacheNameConstant.MERCHANT_LOAD_BY_DOMAIN, key = "#domain")
	public void clearMerchantInfo(String domain) {
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.CLIENT_CURRENT_POINTS, key = "#clientId"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_POINTS_ACC_IN_AMOUNT, key = "#clientId") })
	public void clearClientPoints(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_POINTS_JOURNAL_LIST, key = "#clientId + '-' + #num")
	public void clearClientPointsJournalList(Long clientId, int num) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_AFTERSALE_UNFINISHED_NUMS, key = "#clientId")
	public void clearClientAfterSaleUnFinishedtNums(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_CART_ITEM_NUMS, key = "#clientId + '-' + #itemId")
	public void clearClientCartItem(Long clientId, Long itemId) {
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.CLIENT_CART_ITEM_LIST, key = "#clientId"),
			@CacheEvict(cacheNames = CacheNameConstant.CLIENT_CART_ITEM_NUMS, key = "#clientId + '-0'") })
	public void clearClientCartItems(Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ACTIVITY_EVENT_TIME, key = "#merchantId")
	public void clearClientActivityEventTime(Long merchantId) {
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_LOAD_ONE, key = "#itemId"),
			@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_LOAD_DEFINITIONS, key = "#itemId") })
	public void clearGoodsItemLoadOne(Long itemId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_TOP_COMMENT, key = "#itemId")
	public void clearGoodsItemTopEvaluation(Long itemId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ITEM_TOTAL_COMMENT, key = "#itemId + '-' + #value")
	public void clearGoodsItemTotalCommentNums(Long itemId, String value) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.CLIENT_ITEM_COMMENT_LIST, key = "#key")
	public void clearGoodsItemCommentList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_SHARE_POSTER, key = "#itemId + '-' + #index + '-' + #clientId")
	public void clearGoodsItemSharePosters(Long itemId, int index, Long clientId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.GOODS_ITEM_LIST, key = "#key")
	public void clearGoodsItemList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.MERCHANT_SITE_INFO, key = "#merchantId")
	public void clearSiteInfo(Long merchantId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.WEPAY_CONFIG, key = "#merchantId")
	public void clearWePayConfig(Long merchantId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.GOODS_CATEGORY_LIST_LEVEL1, key = "#merchantId")
	public void clearGoodsCategoryLevel1(Long merchantId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.GOODS_CATEGORY_LIST_LEVEL2, key = "#parentId")
	public void clearGoodsCategoryLevel2(Long parentId) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.ADVERTISEMENT_ALL, key = "#merchantId + '-' + #type.name()")
	public void clearAdvertisementList(Long merchantId, AdvertisementType type) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.ARTICLE_LOAD_ONE, key = "#id")
	public void clearArticleOne(Long id) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.ARTICLE_LOAD_ONE, key = "#merchantId + '-' + #title")
	public void clearArticleOne(Long merchantId, String title) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.AFTER_SALE_REASON_LIST, key = "#merchantId + '-' + #type.name()")
	public void clearAfterSaleReasonList(Long merchantId, AfterSaleType type) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.AFTER_SALE_LIST, key = "#key")
	public void clearAfterSaleList(String key) {
	}

	@CacheEvict(cacheNames = CacheNameConstant.AFTER_SALE_LOAD_ONE, key = "#id")
	public void clearAfterSale(Long id) {
	}
}
