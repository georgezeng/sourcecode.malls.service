package com.sourcecode.malls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.domain.aftersale.AfterSaleApplication;
import com.sourcecode.malls.domain.article.Article;
import com.sourcecode.malls.domain.article.ArticleCategory;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.coupon.CouponSetting;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemEvaluation;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.redis.SearchCacheKeyStore;
import com.sourcecode.malls.dto.query.PageInfo;
import com.sourcecode.malls.enums.ClientPointsType;
import com.sourcecode.malls.repository.jpa.impl.article.ArticleRepository;
import com.sourcecode.malls.repository.jpa.impl.client.ClientRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientCouponRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientPointsJournalRepository;
import com.sourcecode.malls.repository.redis.impl.SearchCacheKeyStoreRepository;

@Service
@Transactional
public class CacheClearer {

	@Autowired
	private CacheEvictService cacheEvictService;

	@Autowired
	private SearchCacheKeyStoreRepository searchCacheKeyStoreRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientCouponRepository clientCouponRepository;

	@Autowired
	private ClientPointsJournalRepository clientPointsJournalRepository;

	@Autowired
	private ArticleRepository articleRepository;

	private int pageSize = 10;

	@Async
	public void clearPosterRelated(GoodsItem item) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNum(1);
		pageInfo.setSize(1000);
		pageInfo.setProperty("createTime");
		pageInfo.setOrder(Direction.ASC.name());
		Pageable pageable = pageInfo.pageable();
		Page<Client> result = null;
		do {
			result = clientRepository.findAllByMerchant(item.getMerchant(), pageable);
			if (result.hasContent()) {
				for (Client client : result.getContent()) {
					for (int i = 0; i < item.getPhotos().size(); i++) {
						cacheEvictService.clearGoodsItemSharePosters(item.getId(), i, client.getId());
					}
				}
				pageable = pageable.next();
			}
		} while (result.hasNext());
	}

	@Async
	public void clearCouponRelated(GoodsItem item) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNum(1);
		pageInfo.setSize(1000);
		pageInfo.setProperty("createTime");
		pageInfo.setOrder(Direction.ASC.name());
		Pageable pageable = pageInfo.pageable();
		Page<Client> result = null;
		do {
			result = clientRepository.findAllByMerchant(item.getMerchant(), pageable);
			if (result.hasContent()) {
				for (Client client : result.getContent()) {
					clearGoodsItemForCoupon(client);
				}
				pageable = pageable.next();
			}
		} while (result.hasNext());
	}

	@Async
	public void clearCategoryRelated(GoodsItem item) {
		List<SearchCacheKeyStore> list = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_CATEGORY,
				"m_" + item.getMerchant().getId());
		list.addAll(searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_CATEGORY,
				item.getCategory().getId().toString()));
		list.stream().forEach(it -> {
			cacheEvictService.clearGoodsItemList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(list);
	}

	private void clearGoodsItemForCoupon(Client client) {
		List<ClientCoupon> list = clientCouponRepository.findAllByClient(client);
		list.stream().forEach(it -> {
			List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_COUPON,
					it.getId().toString());
			stores.stream().forEach(store -> {
				cacheEvictService.clearGoodsItemList(store.getSearchKey());
			});
			searchCacheKeyStoreRepository.deleteAll(stores);
		});
	}

	@Async
	public void clearClientPoints(Client client) {
		cacheEvictService.clearClientPoints(client.getId());
		long total = clientPointsJournalRepository.countByClient(client) / pageSize + 1;
		for (int i = 1; i <= total; i++) {
			cacheEvictService.clearClientPointsJournalList(client.getId(), i);
		}
	}

	@Async
	public void clearCouponRelated(CouponSetting setting) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNum(1);
		pageInfo.setSize(1000);
		pageInfo.setProperty("createTime");
		pageInfo.setOrder(Direction.ASC.name());
		Pageable pageable = pageInfo.pageable();
		Page<Client> result = null;
		do {
			result = clientRepository.findAll(pageable);
			if (result.hasContent()) {
				for (Client client : result.getContent()) {
					clearClientCoupons(client);
				}
				pageable = pageable.next();
			}
		} while (result.hasNext());
	}

	@Async
	public void clearClientCoupons(Client client) {
		cacheEvictService.clearClientCouponNums(client.getId());
		List<ClientCoupon> list = clientCouponRepository.findAllByClient(client);
		list.stream().forEach(it -> {
			List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_CLIENT_COUPON,
					it.getId().toString());
			stores.stream().forEach(store -> {
				cacheEvictService.clearClientCouponList(store.getSearchKey());
			});
			searchCacheKeyStoreRepository.deleteAll(stores);
		});
	}

	@Async
	public void clearClientOrders(Order order) {
		cacheEvictService.clearClientOrder(order.getId());
		cacheEvictService.clearClientOrderNums(order.getClient().getId());
		List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_CLIENT_ORDER,
				order.getClient().getId().toString());
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientOrderList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
	}

	@Async
	public void clearAfterSales(AfterSaleApplication data) {
		cacheEvictService.clearAfterSale(data.getId());
		List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_AFTER_SALE,
				data.getClient().getId() + "-" + data.getOrder().getId());
		stores.stream().forEach(it -> {
			cacheEvictService.clearAfterSaleList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_AFTER_SALE, data.getClient().getId() + "-0");
		stores.stream().forEach(it -> {
			cacheEvictService.clearAfterSaleList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
	}

	@Async
	public void clearEvaluation(Order order) {
		cacheEvictService.clearClientUnCommentNums(order.getClient().getId());
		List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_UNCOMMENT,
				order.getClient().getId() + "-0");
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientUnCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_COMMENT, order.getClient().getId() + "-0");
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientCommentList(it.getSearchKey());
		});
	}

	@Async
	public void clearEvaluation(GoodsItemEvaluation data) {
		cacheEvictService.clearClientUnCommentNums(data.getClient().getId());
		cacheEvictService.clearGoodsItemTotalCommentNums(data.getItem().getId(), data.getValue().name());
		cacheEvictService.clearGoodsItemTotalCommentNums(data.getItem().getId(), "All");
		List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_UNCOMMENT,
				data.getClient().getId() + "-" + data.getOrder().getId());
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientUnCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_UNCOMMENT, data.getClient().getId() + "-0");
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientUnCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_COMMENT,
				data.getClient().getId() + "-" + data.getOrder().getId());
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_COMMENT, data.getClient().getId() + "-0");
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
		stores = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_ITEM_COMMENT, data.getOrder().getId().toString());
		stores.stream().forEach(it -> {
			cacheEvictService.clearGoodsItemCommentList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
	}

	@Async
	public void clearArticle(ArticleCategory category) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNum(1);
		pageInfo.setSize(1000);
		pageInfo.setProperty("createTime");
		pageInfo.setOrder(Direction.ASC.name());
		Pageable pageable = pageInfo.pageable();
		Page<Article> result = null;
		do {
			result = articleRepository.findAllByCategory(category, pageable);
			if (result.hasContent()) {
				for (Article data : result.getContent()) {
					cacheEvictService.clearArticleOne(data.getId());
				}
				pageable = pageable.next();
			}
		} while (result.hasNext());
	}

	@Async
	public void clearArticleCategory(ArticleCategory category) {
		articleRepository.findAllByCategory(category).stream().forEach(it -> clearArticle(it));
	}

	@Async
	public void clearArticle(Article article) {
		cacheEvictService.clearArticleOne(article.getId());
		cacheEvictService.clearArticleOne(article.getMerchant().getId(), article.getTitle());
	}

	@Async
	public void clearClientSubList(Client parent) {
		cacheEvictService.clearClientTotalInviteInfo(parent.getId());
		long total = clientPointsJournalRepository.countByTypeAndClient(ClientPointsType.Invite, parent) / pageSize + 1;
		for (int i = 1; i <= total; i++) {
			cacheEvictService.clearClientSubList(parent.getId(), i);
		}
	}

}
