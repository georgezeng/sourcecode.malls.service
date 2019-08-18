package com.sourcecode.malls.domain.coupon.cash;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.coupon.cash.CashCouponSettingDTO;
import com.sourcecode.malls.enums.CashCouponEventType;
import com.sourcecode.malls.enums.CouponSettingStatus;

@Table(name = "cash_coupon_setting")
@Entity
public class CashCouponSetting extends LongKeyEntity {

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
	private CashCouponEventType eventType;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "setting")
	private CashCouponConsumeEventSetting consumeSetting;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "setting")
	private CashCouponInviteEventSetting inviteSetting;
	
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public CashCouponConsumeEventSetting getConsumeSetting() {
		return consumeSetting;
	}

	public void setConsumeSetting(CashCouponConsumeEventSetting consumeSetting) {
		this.consumeSetting = consumeSetting;
	}

	public CashCouponInviteEventSetting getInviteSetting() {
		return inviteSetting;
	}

	public void setInviteSetting(CashCouponInviteEventSetting inviteSetting) {
		this.inviteSetting = inviteSetting;
	}

	public CashCouponEventType getEventType() {
		return eventType;
	}

	public void setEventType(CashCouponEventType eventType) {
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

	public CashCouponSettingDTO asDTO(boolean withSetting) {
		CashCouponSettingDTO dto = new CashCouponSettingDTO();
		BeanUtils.copyProperties(this, dto, "consumeSetting", "inviteSetting");
		if(withSetting) {
			if (consumeSetting != null) {
				dto.setConsumeSetting(consumeSetting.asDTO());
			}
			if (inviteSetting != null) {
				dto.setInviteSetting(inviteSetting.asDTO());
			}
		}
		return dto;
	}

}
