package com.sourcecode.malls.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.constants.ExceptionMessageConstant;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
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

	@Transactional(readOnly = true)
	public GoodsItemDTO load(Long merchantId, Long id) {
		Optional<GoodsItem> dataOp = itemRepository.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		AssertUtil.assertTrue(dataOp.get().getMerchant().getId().equals(merchantId),
				ExceptionMessageConstant.NO_SUCH_RECORD);
		GoodsItemDTO dto = dataOp.get().asDTO(true, true, true);
		return dto;
	}

}
