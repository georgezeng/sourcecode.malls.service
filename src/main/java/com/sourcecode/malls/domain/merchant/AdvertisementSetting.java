package com.sourcecode.malls.domain.merchant;

import java.util.Date;

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
import com.sourcecode.malls.dto.merchant.AdvertisementSettingDTO;
import com.sourcecode.malls.enums.AdvertisementType;

@Table(name = "advertisement_setting")
@Entity
public class AdvertisementSetting extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "用户不能为空")
	private Merchant merchant;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "位置不能为空")
	private AdvertisementType type;

	@NotBlank(message = "名称不能为空")
	@Size(max = 50, message = "名称长度不能超过50")
	private String name;

	private int orderNum;

	@Size(max = 255, message = "链接路径长度不能超过255")
	private String link;

	@NotNull(message = "类型不能为空")
	private Date startDate;

	@NotNull(message = "类型不能为空")
	private Date endDate;

	@NotBlank(message = "图片路径不能为空")
	@Size(max = 255, message = "图片路径长度不能超过255")
	private String path;

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public AdvertisementType getType() {
		return type;
	}

	public void setType(AdvertisementType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public AdvertisementSettingDTO asDTO() {
		AdvertisementSettingDTO dto = new AdvertisementSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
