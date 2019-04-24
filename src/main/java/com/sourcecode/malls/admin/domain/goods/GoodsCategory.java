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
@Table(name = "goods_category")
public class GoodsCategory extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "goods_category_specification_group", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<GoodsSpecificationGroup> groups;

	@Override
	public GoodsAttributeDTO asDTO() {
		GoodsAttributeDTO dto = super.asDTO();
		if (groups != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationGroup group : groups) {
				attrs.add(group.asDTO());
			}
			dto.setAttrs(attrs);
		}
		return dto;
	}
}
