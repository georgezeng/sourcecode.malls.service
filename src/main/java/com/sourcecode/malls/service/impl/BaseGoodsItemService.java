package com.sourcecode.malls.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.constants.ExceptionMessageConstant;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.redis.SearchGoodsItemByCategoryStore;
import com.sourcecode.malls.domain.redis.SearchGoodsItemByCouponStore;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.dto.query.PageInfo;
import com.sourcecode.malls.repository.jpa.impl.client.ClientRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientCouponRepository;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemPropertyRepository;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemRepository;
import com.sourcecode.malls.repository.redis.impl.SearchGoodsItemByCategoryStoreRepository;
import com.sourcecode.malls.repository.redis.impl.SearchGoodsItemByCouponStoreRepository;
import com.sourcecode.malls.util.AssertUtil;

public class BaseGoodsItemService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected GoodsItemRepository itemRepository;

	@Autowired
	protected GoodsItemPropertyRepository propertyRepository;
	
	@Autowired
	protected CacheEvictService cacheEvictService;
	
	@Autowired
	protected SearchGoodsItemByCategoryStoreRepository searchGoodsItemByCategoryStoreRepository;

	@Autowired
	protected SearchGoodsItemByCouponStoreRepository searchGoodsItemByCouponStoreRepository;
	
	@Autowired
	protected ClientRepository clientRepository;

	@Autowired
	protected ClientCouponRepository clientCouponRepository;

	@Transactional(readOnly = true)
	public GoodsItemDTO load(Long merchantId, Long id) {
		Optional<GoodsItem> dataOp = itemRepository.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		AssertUtil.assertTrue(dataOp.get().getMerchant().getId().equals(merchantId),
				ExceptionMessageConstant.NO_SUCH_RECORD);
		GoodsItemDTO dto = dataOp.get().asDTO(true, true, true);
		return dto;
	}
	
	@Async
	@Transactional
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
	@Transactional
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
	@Transactional
	public void clearCategoryRelated(GoodsItem item) {
		List<SearchGoodsItemByCategoryStore> list = searchGoodsItemByCategoryStoreRepository
				.findAllByCategoryId("m_" + item.getMerchant().getId());
		list.addAll(
				searchGoodsItemByCategoryStoreRepository.findAllByCategoryId(item.getCategory().getId().toString()));
		list.stream().forEach(it -> {
			cacheEvictService.clearGoodsItemList(it.getKey());
		});
	}

	private void clearGoodsItemForCoupon(Client client) {
		List<ClientCoupon> list = clientCouponRepository.findAllByClient(client);
		list.stream().forEach(it -> {
			List<SearchGoodsItemByCouponStore> stores = searchGoodsItemByCouponStoreRepository
					.findAllByCouponId(it.getId().toString());
			stores.stream().forEach(store -> {
				cacheEvictService.clearGoodsItemList(store.getKey());
			});
		});
	}

}
