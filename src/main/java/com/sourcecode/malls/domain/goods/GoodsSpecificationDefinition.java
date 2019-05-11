package com.sourcecode.malls.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.sourcecode.malls.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;

@Entity
@Table(name = "goods_specification_definition")
public class GoodsSpecificationDefinition extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "definition", cascade = { CascadeType.REMOVE })
	private List<GoodsSpecificationValue> values;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "goods_definition_group", joinColumns = @JoinColumn(name = "definition_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	@OrderBy("name ASC")
	private List<GoodsSpecificationGroup> groups;

	public void addGroup(GoodsSpecificationGroup group) {
		if (groups == null) {
			groups = new ArrayList<>();
		}
		groups.add(group);
	}

	public List<GoodsSpecificationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<GoodsSpecificationGroup> groups) {
		this.groups = groups;
	}

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
