package com.sourcecode.malls.admin.dto.goods;

import java.math.BigDecimal;
import java.util.List;

public class GoodsItemDTO {
	private Long id;
	private String name;
	private String code;
	private String thumbnail;
	private BigDecimal marketPrice;
	private BigDecimal realPrice;
	private String sellingPoints;
	private String content;
	private boolean enabled;
	private List<String> photos;
	private Long categoryId;
	private Long brandId;
	private Long merchantId;
	private String searchText;
	private String statusText;
	private List<GoodsItemPropertyDTO> properties;

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public List<GoodsItemPropertyDTO> getProperties() {
		return properties;
	}

	public void setProperties(List<GoodsItemPropertyDTO> properties) {
		this.properties = properties;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSellingPoints() {
		return sellingPoints;
	}

	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
