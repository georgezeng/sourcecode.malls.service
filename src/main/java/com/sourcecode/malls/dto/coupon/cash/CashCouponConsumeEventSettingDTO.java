package com.sourcecode.malls.dto.coupon.cash;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;

public class CashCouponConsumeEventSettingDTO {
	private List<GoodsAttributeDTO> categories = new ArrayList<>();
	private List<Long> categoryIds = new ArrayList<>();
	private List<GoodsItemDTO> items = new ArrayList<>();
	private List<Long> itemIds = new ArrayList<>();
	private boolean applyToAll;
	private BigDecimal upToAmount;
	
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

	public boolean isApplyToAll() {
		return applyToAll;
	}

	public void setApplyToAll(boolean applyToAll) {
		this.applyToAll = applyToAll;
	}

}
