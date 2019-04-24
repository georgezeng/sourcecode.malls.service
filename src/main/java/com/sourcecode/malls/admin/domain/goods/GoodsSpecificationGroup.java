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
@Table(name = "goods_specification_group")
public class GoodsSpecificationGroup extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "goods_specification_group_definition", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "definition_id"))
	private List<GoodsSpecificationDefinition> definitions;

	public List<GoodsSpecificationDefinition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<GoodsSpecificationDefinition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public GoodsAttributeDTO asDTO() {
		GoodsAttributeDTO dto = super.asDTO();
		if (definitions != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationDefinition definition : definitions) {
				attrs.add(definition.asDTO());
			}
			dto.setAttrs(attrs);
		}
		return dto;
	}
}
