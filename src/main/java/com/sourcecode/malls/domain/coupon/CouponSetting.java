package com.sourcecode.malls.domain.coupon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.goods.GoodsCategory;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.coupon.CouponSettingDTO;
import com.sourcecode.malls.enums.CouponEventType;
import com.sourcecode.malls.enums.CouponRelationType;
import com.sourcecode.malls.enums.CouponSettingStatus;
import com.sourcecode.malls.enums.CouponType;

@Table(name = "coupon_setting")
@Entity
public class CouponSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@NotBlank(message = "券名称不能为空")
	@Size(max = 50, message = "券名称长度不能超过50")
	private String name;

	@NotNull(message = "面额不能为空")
	private BigDecimal amount;

	@NotNull(message = "生效时间不能为空")
	private Date startDate;

	private Date endDate;

	@NotBlank(message = "图片路径不能为空")
	@Size(max = 255, message = "图片路径长度不能超过255")
	private String imgPath;

	private long totalNums;

	private long sentNums;

	private long usedNums;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "状态不能为空")
	private CouponSettingStatus status;

	@Size(max = 300, message = "使用说明长度不能超过300")
	private String description;

	@Enumerated(EnumType.STRING)
//	@NotNull(message = "用户行为不能为空")
	private CouponEventType eventType;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "setting")
	private CouponConsumeEventSetting consumeSetting;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "setting")
	private CouponInviteEventSetting inviteSetting;

	private boolean enabled;

	@Enumerated(EnumType.STRING)
	private CouponRelationType hxType;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "coupon_setting_goods_category", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<GoodsCategory> categories;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "coupon_setting_real_category", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<GoodsCategory> realCategories;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "coupon_setting_goods_item", joinColumns = @JoinColumn(name = "setting_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<GoodsItem> items;

	private int limitedNums;
	
	private String title;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "优惠券类型不能为空")
	private CouponType type;

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<GoodsCategory> getRealCategories() {
		return realCategories;
	}

	public void setRealCategories(List<GoodsCategory> realCategories) {
		this.realCategories = realCategories;
	}

	public int getLimitedNums() {
		return limitedNums;
	}

	public void setLimitedNums(int limitedNums) {
		this.limitedNums = limitedNums;
	}

	public CouponRelationType getHxType() {
		return hxType;
	}

	public void setHxType(CouponRelationType hxType) {
		this.hxType = hxType;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public CouponConsumeEventSetting getConsumeSetting() {
		return consumeSetting;
	}

	public void setConsumeSetting(CouponConsumeEventSetting consumeSetting) {
		this.consumeSetting = consumeSetting;
	}

	public CouponInviteEventSetting getInviteSetting() {
		return inviteSetting;
	}

	public void setInviteSetting(CouponInviteEventSetting inviteSetting) {
		this.inviteSetting = inviteSetting;
	}

	public CouponEventType getEventType() {
		return eventType;
	}

	public void setEventType(CouponEventType eventType) {
		this.eventType = eventType;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(long totalNums) {
		this.totalNums = totalNums;
	}

	public long getSentNums() {
		return sentNums;
	}

	public void setSentNums(long sentNums) {
		this.sentNums = sentNums;
	}

	public long getUsedNums() {
		return usedNums;
	}

	public void setUsedNums(long usedNums) {
		this.usedNums = usedNums;
	}

	public CouponSettingStatus getStatus() {
		return status;
	}

	public void setStatus(CouponSettingStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CouponSettingDTO asDTO(boolean withSetting) {
		CouponSettingDTO dto = new CouponSettingDTO();
		BeanUtils.copyProperties(this, dto, "consumeSetting", "inviteSetting", "items", "categories");
		if (withSetting) {
			if (consumeSetting != null) {
				dto.setConsumeSetting(consumeSetting.asDTO());
			}
			if (inviteSetting != null) {
				dto.setInviteSetting(inviteSetting.asDTO());
			}
			if (!CollectionUtils.isEmpty(categories)) {
				dto.setCategoryIds(categories.stream().map(it -> it.getId()).collect(Collectors.toList()));
			}
			if (!CollectionUtils.isEmpty(items)) {
				for (GoodsItem item : items) {
					dto.getItems().add(item.asDTO(false, false, false));
				}
			}
		}
		return dto;
	}

}
