package com.sourcecode.malls.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sourcecode.malls.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;

@Entity
@Table(name = "goods_specification_group")
public class GoodsSpecificationGroup extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
	private List<GoodsSpecificationDefinition> definitions;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private GoodsCategory category;

	public GoodsCategory getCategory() {
		return category;
	}

	public void setCategory(GoodsCategory category) {
		this.category = category;
	}

	public List<GoodsSpecificationDefinition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<GoodsSpecificationDefinition> definitions) {
		this.definitions = definitions;
	}

	public GoodsAttributeDTO asDTO(boolean withAttrs) {
		GoodsAttributeDTO dto = super.asDTO();
		if (category != null) {
			dto.setParent(category.asDTO());
		}
		if (withAttrs && definitions != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationDefinition definition : definitions) {
				attrs.add(definition.asDTO());
			}
			dto.setAttrs(attrs);
		}
		return dto;
	}
}
