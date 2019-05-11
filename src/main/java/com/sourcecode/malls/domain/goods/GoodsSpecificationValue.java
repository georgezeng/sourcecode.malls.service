package com.sourcecode.malls.domain.goods;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sourcecode.malls.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;

@Entity
@Table(name = "goods_specification_value")
public class GoodsSpecificationValue extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "definition_id")
	private GoodsSpecificationDefinition definition;

	public GoodsSpecificationDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(GoodsSpecificationDefinition definition) {
		this.definition = definition;
	}

	@Override
	public GoodsAttributeDTO asDTO() {
		GoodsAttributeDTO dto = super.asDTO();
		if (definition != null) {
			GoodsAttributeDTO parent = new GoodsAttributeDTO();
			parent.setId(definition.getId());
			dto.setParent(parent);
		}
		return dto;
	}
}
