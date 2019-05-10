package com.sourcecode.malls.admin.domain.goods;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.admin.domain.base.LongKeyEntity;
import com.sourcecode.malls.admin.dto.goods.GoodsItemPropertyDTO;

@Entity
@Table(name = "goods_item_property")
public class GoodsItemProperty extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "uid不能为空")
	private String uid;
	@NotNull(message = "价格不能为空")
	private BigDecimal price;
	private int inventory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public GoodsItemPropertyDTO asDTO() {
		GoodsItemPropertyDTO dto = new GoodsItemPropertyDTO();
		BeanUtils.copyProperties(this, dto, "item");
		return dto;
	}

}
