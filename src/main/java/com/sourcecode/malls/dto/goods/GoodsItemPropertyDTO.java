package com.sourcecode.malls.dto.goods;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.goods.GoodsItemProperty;

public class GoodsItemPropertyDTO {
	private List<GoodsAttributeDTO> values;
	private String uid;
	private BigDecimal price;
	private int inventory;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<GoodsAttributeDTO> getValues() {
		return values;
	}

	public void setValues(List<GoodsAttributeDTO> values) {
		this.values = values;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public GoodsItemProperty asEntity() {
		GoodsItemProperty entity = new GoodsItemProperty();
		BeanUtils.copyProperties(this, entity, "values");
		entity.setUid(UUID.randomUUID().toString());
		return entity;
	}
}
