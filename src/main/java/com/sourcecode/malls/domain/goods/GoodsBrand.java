package com.sourcecode.malls.domain.goods;

import javax.persistence.Column;
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
import com.sourcecode.malls.dto.goods.GoodsBrandDTO;

@Entity
@Table(name = "goods_brand")
public class GoodsBrand extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "品牌名称不能为空")
	@Size(max = 50, message = "品牌名称长度不能大于50")
	private String name;
	@NotBlank(message = "品牌logo不能为空")
	@Size(max = 255, message = "品牌logo长度不能大于255")
	private String logo;
	@Column(name = "order_num")
	private int order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull(message = "商品分类不能为空")
	private GoodsCategory category;

	public GoodsCategory getCategory() {
		return category;
	}

	public void setCategory(GoodsCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public GoodsBrandDTO asDTO() {
		GoodsBrandDTO dto = new GoodsBrandDTO();
		BeanUtils.copyProperties(this, dto, "category");
		if (category != null) {
			dto.setCategoryId(category.getId());
		}
		return dto;
	}
}
