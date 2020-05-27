package com.sourcecode.malls.dto.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.goods.GoodsItemProperty;

public class GoodsItemPropertyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private List<GoodsAttributeDTO> values;
	private String uid;
	private BigDecimal price;
	private int inventory;
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return entity;
	}
}
