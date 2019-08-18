package com.sourcecode.malls.domain.coupon.cash;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import com.sourcecode.malls.dto.coupon.cash.CashCouponConsumeEventSettingDTO;

@Table(name = "cash_coupon_consume_event_setting")
@Entity
public class CashCouponConsumeEventSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setting_id")
	@NotNull(message = "优惠券不能为空")
	private CashCouponSetting setting;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "cash_consume_event_goods_category", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<GoodsCategory> categories;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "cash_consume_event_goods_item", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<GoodsItem> items;

	private boolean applyToAll;

	@NotNull(message = "满赠金额不能为空")
	private BigDecimal upToAmount;

	public BigDecimal getUpToAmount() {
		return upToAmount;
	}

	public void setUpToAmount(BigDecimal upToAmount) {
		this.upToAmount = upToAmount;
	}

	public CashCouponSetting getSetting() {
		return setting;
	}

	public void setSetting(CashCouponSetting setting) {
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

	public boolean isApplyToAll() {
		return applyToAll;
	}

	public void setApplyToAll(boolean applyToAll) {
		this.applyToAll = applyToAll;
	}

	public CashCouponConsumeEventSettingDTO asDTO() {
		CashCouponConsumeEventSettingDTO dto = new CashCouponConsumeEventSettingDTO();
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