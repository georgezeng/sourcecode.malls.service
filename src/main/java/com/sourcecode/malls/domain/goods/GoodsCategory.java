package com.sourcecode.malls.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.BaseGoodsAttribute;
import com.sourcecode.malls.dto.goods.GoodsAttributeDTO;

@Entity
@Table(name = "goods_category")
public class GoodsCategory extends BaseGoodsAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "图标不能为空")
	@Size(max = 255, message = "图标长度不能大于255")
	private String icon;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<GoodsSpecificationGroup> groups;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private GoodsCategory parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy("order ASC")
	private List<GoodsCategory> subList;

	private int level;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<GoodsCategory> getSubList() {
		return subList;
	}

	public void setSubList(List<GoodsCategory> subList) {
		this.subList = subList;
	}

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
			dto.setParent(parent.asDTO(false, false));
		}
		return dto;
	}
}
