package com.sourcecode.malls.dto.merchant;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.domain.merchant.AdvertisementSetting;
import com.sourcecode.malls.enums.AdvertisementType;

public class AdvertisementSettingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private AdvertisementType type;
	private String name;
	private int orderNum;
	private String link;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	private String path;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public AdvertisementSetting asEntity() {
		AdvertisementSetting data = new AdvertisementSetting();
		BeanUtils.copyProperties(this, data, "id");
		return data;
	}

}
