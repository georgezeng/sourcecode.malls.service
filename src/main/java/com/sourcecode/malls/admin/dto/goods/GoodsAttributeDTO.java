package com.sourcecode.malls.admin.dto.goods;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsAttributeDTO {

	private Long id;

	private String name;

	private int order;
	
	private int level;
	
	private int leafLevel;
	
	private Long pid;
	
	private String icon;

	private List<GoodsAttributeDTO> attrs;
	
	private List<Long> parentIds;

	private GoodsAttributeDTO parent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String searchText;

	private Long merchantId;

	public List<Long> getParentIds() {
		return parentIds;
	}

	public void setParentIds(List<Long> parentIds) {
		this.parentIds = parentIds;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLeafLevel() {
		return leafLevel;
	}

	public void setLeafLevel(int leafLevel) {
		this.leafLevel = leafLevel;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public GoodsAttributeDTO getParent() {
		return parent;
	}

	public void setParent(GoodsAttributeDTO parent) {
		this.parent = parent;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public List<GoodsAttributeDTO> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<GoodsAttributeDTO> attrs) {
		this.attrs = attrs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
