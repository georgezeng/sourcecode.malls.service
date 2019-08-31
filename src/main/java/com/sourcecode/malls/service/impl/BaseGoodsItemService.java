package com.sourcecode.malls.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.constants.ExceptionMessageConstant;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemPropertyRepository;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemRepository;
import com.sourcecode.malls.util.AssertUtil;

public class BaseGoodsItemService {
	@Autowired
	protected GoodsItemRepository itemRepository;

	@Autowired
	protected GoodsItemPropertyRepository propertyRepository;

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
