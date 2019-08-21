package com.sourcecode.malls.domain.coupon;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.goods.GoodsCategory;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.dto.coupon.CouponConsumeEventSettingDTO;
import com.sourcecode.malls.enums.CouponRelationType;

@Table(name = "coupon_consume_event_setting")
@Entity
public class CouponConsumeEventSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setting_id")
	@NotNull(message = "优惠券不能为空")
	private CouponSetting setting;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "consume_event_goods_category", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<GoodsCategory> categories;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "consume_event_real_category", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<GoodsCategory> realCategories;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "consume_event_goods_item", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<GoodsItem> items;

	@NotNull(message = "关联类型不能为空")
	@Enumerated(EnumType.STRING)
	private CouponRelationType type;

	@NotNull(message = "满赠金额不能为空")
	private BigDecimal upToAmount;

	public List<GoodsCategory> getRealCategories() {
		return realCategories;
	}

	public void setRealCategories(List<GoodsCategory> realCategories) {
		this.realCategories = realCategories;
	}

	public BigDecimal getUpToAmount() {
		return upToAmount;
	}

	public void setUpToAmount(BigDecimal upToAmount) {
		this.upToAmount = upToAmount;
	}

	public CouponSetting getSetting() {
		return setting;
	}

	public void setSetting(CouponSetting setting) {
		this.setting = setting;
	}

	public List<GoodsCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<GoodsCategory> categories) {
		this.categories = categories;
	}

	public List<GoodsItem> getItems() {
		return items;
	}

	public void setItems(List<GoodsItem> items) {
		this.items = items;
	}

	public CouponRelationType getType() {
		return type;
	}

	public void setType(CouponRelationType type) {
		this.type = type;
	}

	public CouponConsumeEventSettingDTO asDTO() {
		CouponConsumeEventSettingDTO dto = new CouponConsumeEventSettingDTO();
		BeanUtils.copyProperties(this, dto, "categories", "items");
		if (!CollectionUtils.isEmpty(categories)) {
			for (GoodsCategory category : categories) {
				dto.getCategories().add(category.asDTO(false, false));
			}
		}
		if (!CollectionUtils.isEmpty(items)) {
			for (GoodsItem item : items) {
				dto.getItems().add(item.asDTO(false, false, false));
			}
		}
		return dto;
	}

}
