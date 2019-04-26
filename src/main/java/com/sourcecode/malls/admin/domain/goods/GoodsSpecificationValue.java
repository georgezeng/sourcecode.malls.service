package com.sourcecode.malls.admin.domain.goods;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sourcecode.malls.admin.domain.base.BaseGoodsAttribute;

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
}
