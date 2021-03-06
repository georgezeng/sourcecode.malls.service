package com.sourcecode.malls.dto.coupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.enums.CouponEventType;
import com.sourcecode.malls.enums.CouponRelationType;
import com.sourcecode.malls.enums.CouponSettingStatus;
import com.sourcecode.malls.enums.CouponType;

public class CouponSettingDTO extends SimpleQueryDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String title;
	private BigDecimal amount;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private String imgPath;
	private long totalNums;
	private long sentNums;
	private long usedNums;
	private CouponSettingStatus status;
	private String description;
	private CouponEventType eventType;
	private CouponConsumeEventSettingDTO consumeSetting;
	private CouponInviteEventSettingDTO inviteSetting;
	private CouponRelationType hxType;
	private List<Long> categoryIds = new ArrayList<>();
	private List<GoodsItemDTO> items = new ArrayList<>();
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

	public CouponRelationType getHxType() {
		return hxType;
	}

	public void setHxType(CouponRelationType hxType) {
		this.hxType = hxType;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<GoodsItemDTO> getItems() {
		return items;
	}

	public void setItems(List<GoodsItemDTO> items) {
		this.items = items;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public CouponConsumeEventSettingDTO getConsumeSetting() {
		return consumeSetting;
	}

	public void setConsumeSetting(CouponConsumeEventSettingDTO consumeSetting) {
		this.consumeSetting = consumeSetting;
	}

	public CouponInviteEventSettingDTO getInviteSetting() {
		return inviteSetting;
	}

	public void setInviteSetting(CouponInviteEventSettingDTO inviteSetting) {
		this.inviteSetting = inviteSetting;
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

	public CouponEventType getEventType() {
		return eventType;
	}

	public void setEventType(CouponEventType eventType) {
		this.eventType = eventType;
	}

}
