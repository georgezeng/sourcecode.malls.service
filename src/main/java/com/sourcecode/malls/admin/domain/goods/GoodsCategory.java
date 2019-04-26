package com.sourcecode.malls.admin.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sourcecode.malls.admin.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.admin.dto.merchant.GoodsAttributeDTO;

@Entity
@Table(name = "goods_category")
public class GoodsCategory extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<GoodsSpecificationGroup> groups;

	public List<GoodsSpecificationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<GoodsSpecificationGroup> groups) {
		this.groups = groups;
	}

	public GoodsAttributeDTO asDTO(boolean withAttrs) {
		GoodsAttributeDTO dto = super.asDTO();
		if (withAttrs && groups != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationGroup group : groups) {
				attrs.add(group.asDTO());
			}
			dto.setAttrs(attrs);
		}
		return dto;
	}
}
