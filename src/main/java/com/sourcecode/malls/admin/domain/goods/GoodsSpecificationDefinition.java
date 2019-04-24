package com.sourcecode.malls.admin.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sourcecode.malls.admin.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.admin.dto.merchant.GoodsAttributeDTO;

@Entity
@Table(name = "goods_specification_definition")
public class GoodsSpecificationDefinition extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "goods_specification_definition_value", joinColumns = @JoinColumn(name = "definition_id"), inverseJoinColumns = @JoinColumn(name = "value_id"))
	private List<GoodsSpecificationValue> values;

	public List<GoodsSpecificationValue> getValues() {
		return values;
	}

	public void setValues(List<GoodsSpecificationValue> values) {
		this.values = values;
	}

	@Override
	public GoodsAttributeDTO asDTO() {
		GoodsAttributeDTO dto = super.asDTO();
		if (values != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationValue value : values) {
				attrs.add(value.asDTO());
			}
			dto.setAttrs(attrs);
		}
		return dto;
	}

}
