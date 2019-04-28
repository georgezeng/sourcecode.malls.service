package com.sourcecode.malls.admin.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private GoodsCategory parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<GoodsCategory> subList;

	private int level;

	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<GoodsSpecificationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<GoodsSpecificationGroup> groups) {
		this.groups = groups;
	}

	public GoodsAttributeDTO asDTO(boolean withGroup, boolean withSubList) {
		GoodsAttributeDTO dto = super.asDTO();
		if (withGroup && groups != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsSpecificationGroup group : groups) {
				attrs.add(group.asDTO());
			}
			dto.setAttrs(attrs);
		}
		if (withSubList && subList != null) {
			List<GoodsAttributeDTO> attrs = new ArrayList<>();
			for (GoodsCategory sub : subList) {
				attrs.add(sub.asDTO(withGroup, withSubList));
			}
			dto.setAttrs(attrs);
		}
		if (parent != null) {
			dto.setParent(parent.asDTO());
		}
		return dto;
	}
}
