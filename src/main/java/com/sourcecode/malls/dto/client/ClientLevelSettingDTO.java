package com.sourcecode.malls.dto.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientLevelSettingDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private int level;

	private String name;

	private BigDecimal upToAmount;

	private BigDecimal discount;

	private BigDecimal discountInActivity;
	
	private boolean top;
	
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUpToAmount() {
		return upToAmount;
	}

	public void setUpToAmount(BigDecimal upToAmount) {
		this.upToAmount = upToAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscountInActivity() {
		return discountInActivity;
	}

	public void setDiscountInActivity(BigDecimal discountInActivity) {
		this.discountInActivity = discountInActivity;
	}
}