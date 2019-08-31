package com.sourcecode.malls.dto.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String number;
	private String code;
	private String thumbnail;
	private String vedioPath;
	private BigDecimal marketPrice;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private String sellingPoints;
	private String content;
	private boolean enabled;
	private List<String> photos;
	private Long categoryId;
	private Long brandId;
	private String brand;
	private Long merchantId;
	private String searchText;
	private String statusText;
	private List<GoodsItemPropertyDTO> properties;
	private BigDecimal goodEvaluationRate;
	private long orderNums;
	private long totalEvaluations;
	private GoodsItemEvaluationDTO topEvaluation;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date putTime;

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

	public String getVedioPath() {
		return vedioPath;
	}

	public void setVedioPath(String vedioPath) {
		this.vedioPath = vedioPath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public long getTotalEvaluations() {
		return totalEvaluations;
	}

	public void setTotalEvaluations(long totalEvaluations) {
		this.totalEvaluations = totalEvaluations;
	}

	public GoodsItemEvaluationDTO getTopEvaluation() {
		return topEvaluation;
	}

	public void setTopEvaluation(GoodsItemEvaluationDTO topEvaluation) {
		this.topEvaluation = topEvaluation;
	}

	public BigDecimal getGoodEvaluationRate() {
		return goodEvaluationRate;
	}

	public void setGoodEvaluationRate(BigDecimal goodEvaluationRate) {
		this.goodEvaluationRate = goodEvaluationRate;
	}

	public long getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(long orderNums) {
		this.orderNums = orderNums;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
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
