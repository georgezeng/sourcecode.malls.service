package com.sourcecode.malls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.redis.SearchCacheKeyStore;
import com.sourcecode.malls.dto.query.PageInfo;
import com.sourcecode.malls.repository.jpa.impl.client.ClientRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientCouponRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientPointsJournalRepository;
import com.sourcecode.malls.repository.redis.impl.SearchCacheKeyStoreRepository;

@Service
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

	@Async
	@Transactional(readOnly = true)
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
					clearGoodsItemForCoupon(client);
				}
				pageable = pageable.next();
			}
		} while (result.hasNext());
	}

	@Async
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public void clearCategoryRelated(GoodsItem item) {
		List<SearchCacheKeyStore> list = searchCacheKeyStoreRepository.findAllByTypeAndBizKey(
				SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_CATEGORY, "m_" + item.getMerchant().getId());
		list.addAll(searchCacheKeyStoreRepository.findAllByTypeAndBizKey(
				SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_CATEGORY, item.getCategory().getId().toString()));
		list.stream().forEach(it -> {
			cacheEvictService.clearGoodsItemList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(list);
	}

	private void clearGoodsItemForCoupon(Client client) {
		List<ClientCoupon> list = clientCouponRepository.findAllByClient(client);
		list.stream().forEach(it -> {
			List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository
					.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_GOODS_ITEM_BY_COUPON, it.getId().toString());
			stores.stream().forEach(store -> {
				cacheEvictService.clearGoodsItemList(store.getSearchKey());
			});
			searchCacheKeyStoreRepository.deleteAll(stores);
		});
	}

	@Async
	@Transactional(readOnly = true)
	public void clearClientPoints(Client client) {
		cacheEvictService.clearClientCurrentPoints(client.getId());
		long total = clientPointsJournalRepository.countByClient(client) / 10 + 1;
		for (int i = 1; i <= total; i++) {
			cacheEvictService.clearClientPointsJournalList(client.getId(), i);
		}
	}

	@Async
	@Transactional(readOnly = true)
	public void clearClientCoupons(Client client) {
		cacheEvictService.clearClientCouponNums(client.getId());
		List<ClientCoupon> list = clientCouponRepository.findAllByClient(client);
		list.stream().forEach(it -> {
			List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository
					.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_CLIENT_COUPON, it.getId().toString());
			stores.stream().forEach(store -> {
				cacheEvictService.clearClientCouponList(store.getSearchKey());
			});
			searchCacheKeyStoreRepository.deleteAll(stores);
		});
	}

	@Async
	@Transactional(readOnly = true)
	public void clearClientOrders(Client client) {
		cacheEvictService.clearClientOrderNums(client.getId());
		List<SearchCacheKeyStore> stores = searchCacheKeyStoreRepository
				.findAllByTypeAndBizKey(SearchCacheKeyStore.SEARCH_CLIENT_ORDER, client.getId().toString());
		stores.stream().forEach(it -> {
			cacheEvictService.clearClientCouponList(it.getSearchKey());
		});
		searchCacheKeyStoreRepository.deleteAll(stores);
	}

}
