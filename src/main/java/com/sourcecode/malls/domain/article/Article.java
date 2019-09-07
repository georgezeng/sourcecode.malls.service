package com.sourcecode.malls.domain.article;

import javax.persistence.Entity;
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
import com.sourcecode.malls.dto.article.ArticleDTO;

@Table(name = "article")
@Entity
public class Article extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商户不能为空")
	private Merchant merchant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull(message = "分类不能为空")
	private ArticleCategory category;

	@NotBlank(message = "文章标题不能为空")
	@Size(max = 50, message = "文章标题长度不能超过50")
	private String title;

	private int orderNum;

	private String content;

	@Size(max = 255, message = "图片路径长度不能超过255")
	private String vedioPath;

	@Size(max = 255, message = "视频路径长度不能超过255")
	private String imgPath;
	
	private boolean hidden;

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVedioPath() {
		return vedioPath;
	}

	public void setVedioPath(String vedioPath) {
		this.vedioPath = vedioPath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public ArticleDTO asDTO() {
		ArticleDTO dto = new ArticleDTO();
		BeanUtils.copyProperties(this, dto, "category");
		if (category != null) {
			dto.setCategoryId(category.getId());
			dto.setCategory(category.getName());
			dto.setType(category.getType());
		}
		return dto;
	}

}
