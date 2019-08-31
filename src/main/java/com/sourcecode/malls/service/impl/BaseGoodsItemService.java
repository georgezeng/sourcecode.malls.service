package com.sourcecode.malls.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.constants.ExceptionMessageConstant;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemValue;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.dto.goods.GoodsItemPropertyDTO;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemPropertyRepository;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemRepository;
import com.sourcecode.malls.repository.jpa.impl.goods.GoodsItemValueRepository;
import com.sourcecode.malls.util.AssertUtil;

public class BaseGoodsItemService {
	@Autowired
	protected GoodsItemRepository itemRepository;

	@Autowired
	protected GoodsItemValueRepository valueRepository;
	
	@Autowired
	protected GoodsItemPropertyRepository propertyRepository;

	@Transactional(readOnly = true)
	public GoodsItemDTO load(Long merchantId, Long id) {
		Optional<GoodsItem> dataOp = itemRepository.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		AssertUtil.assertTrue(dataOp.get().getMerchant().getId().equals(merchantId),
				ExceptionMessageConstant.NO_SUCH_RECORD);
		GoodsItemDTO dto = dataOp.get().asDTO(true, true, true);
		if (!CollectionUtils.isEmpty(dto.getProperties())) {
			for (GoodsItemPropertyDTO p : dto.getProperties()) {
				List<GoodsItemValue> values = valueRepository.findAllByUid(p.getUid());
				p.setValues(values.stream().map(it -> it.getValue().asDTO()).collect(Collectors.toList()));
			}
		}
		return dto;
	}

}
