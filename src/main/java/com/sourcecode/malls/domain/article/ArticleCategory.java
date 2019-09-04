package com.sourcecode.malls.domain.article;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.article.ArticleCategoryDTO;
import com.sourcecode.malls.enums.ArticleCategoryType;

@Table(name = "article_category")
@Entity
public class ArticleCategory extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商户不能为空")
	private Merchant merchant;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "栏目名称不能为空")
	private ArticleCategoryType type;

	@NotBlank(message = "分类名称不能为空")
	@Size(max = 50, message = "分类名称长度不能超过50")
	private String name;

	private int orderNum;

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public ArticleCategoryType getType() {
		return type;
	}

	public void setType(ArticleCategoryType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public ArticleCategoryDTO asDTO() {
		ArticleCategoryDTO dto = new ArticleCategoryDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
