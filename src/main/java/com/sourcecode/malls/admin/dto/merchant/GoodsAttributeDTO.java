package com.sourcecode.malls.admin.dto.merchant;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.admin.domain.merchant.Merchant;

public class GoodsAttributeDTO {

	private Long id;

	private String name;
	
	private int order;

	private List<GoodsAttributeDTO> attrs;

	private GoodsAttributeDTO parent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String searchText;

	private Merchant merchant;

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

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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
