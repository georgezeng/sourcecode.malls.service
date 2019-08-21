package com.sourcecode.malls.dto.coupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.enums.CouponRelationType;

public class CouponConsumeEventSettingDTO {
	private List<GoodsAttributeDTO> categories = new ArrayList<>();
	private List<Long> categoryIds = new ArrayList<>();
	private List<GoodsItemDTO> items = new ArrayList<>();
	private List<Long> itemIds = new ArrayList<>();
	private CouponRelationType type;
	private BigDecimal upToAmount;

	public CouponRelationType getType() {
		return type;
	}

	public void setType(CouponRelationType type) {
		this.type = type;
	}

	public BigDecimal getUpToAmount() {
		return upToAmount;
	}

	public void setUpToAmount(BigDecimal upToAmount) {
		this.upToAmount = upToAmount;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<Long> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Long> itemIds) {
		this.itemIds = itemIds;
	}

	public List<GoodsAttributeDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<GoodsAttributeDTO> categories) {
		this.categories = categories;
	}

	public List<GoodsItemDTO> getItems() {
		return items;
	}

	public void setItems(List<GoodsItemDTO> items) {
		this.items = items;
	}

}
