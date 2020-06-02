package com.sourcecode.malls.domain.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;
import com.sourcecode.malls.dto.goods.GoodsItemPropertyDTO;

@Entity
@Table(name = "goods_item_property")
public class GoodsItemProperty extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "价格不能为空")
	private BigDecimal price;
	private int inventory;
	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "goods_item_property_value", joinColumns = {
			@JoinColumn(name = "property_id") }, inverseJoinColumns = { @JoinColumn(name = "value_id") })
	private List<GoodsSpecificationValue> values;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<GoodsSpecificationValue> getValues() {
		return values;
	}

	public void setValues(List<GoodsSpecificationValue> values) {
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

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public GoodsItemPropertyDTO asDTO() {
		GoodsItemPropertyDTO dto = new GoodsItemPropertyDTO();
		BeanUtils.copyProperties(this, dto, "item", "values");
		if (values != null) {
			List<GoodsAttributeDTO> list = new ArrayList<>();
			for (GoodsSpecificationValue value : values) {
				list.add(value.asDTO());
			}
			dto.setValues(list);
		}
		return dto;
	}

}
