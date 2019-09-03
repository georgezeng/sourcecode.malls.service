package com.sourcecode.malls.dto.merchant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.enums.VerificationStatus;

public class MerchantShopApplicationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Long id;
	private String name;
	private String domain;
	private String logo;
	private boolean androidType;
	private boolean iosType;
	private String androidSmallIcon;
	private String androidBigIcon;
	private String iosSmallIcon;
	private String iosBigIcon;
	private String instructionImg;
	private String loginBgImg;
	private String description;
	private VerificationStatus status;
	private String searchText;
	private String statusText;
	private List<String> instructions;
	private String reason;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updateTime;
	private String androidUrl;
	private String iosUrl;
	private boolean deployed;
	private boolean noPermit;

	public boolean isNoPermit() {
		return noPermit;
	}

	public void setNoPermit(boolean noPermit) {
		this.noPermit = noPermit;
	}

	public boolean isDeployed() {
		return deployed;
	}

	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}

	public String getAndroidUrl() {
		return androidUrl;
	}

	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}

	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void addInstrcution(String instruction) {
		if (instructions == null) {
			instructions = new ArrayList<>();
		}
		instructions.add(instruction);
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAndroidType() {
		return androidType;
	}

	public void setAndroidType(boolean androidType) {
		this.androidType = androidType;
	}

	public boolean isIosType() {
		return iosType;
	}

	public void setIosType(boolean iosType) {
		this.iosType = iosType;
	}

	public String getAndroidSmallIcon() {
		return androidSmallIcon;
	}

	public void setAndroidSmallIcon(String androidSmallIcon) {
		this.androidSmallIcon = androidSmallIcon;
	}

	public String getAndroidBigIcon() {
		return androidBigIcon;
	}

	public void setAndroidBigIcon(String androidBigIcon) {
		this.androidBigIcon = androidBigIcon;
	}

	public String getIosSmallIcon() {
		return iosSmallIcon;
	}

	public void setIosSmallIcon(String iosSmallIcon) {
		this.iosSmallIcon = iosSmallIcon;
	}

	public String getIosBigIcon() {
		return iosBigIcon;
	}

	public void setIosBigIcon(String iosBigIcon) {
		this.iosBigIcon = iosBigIcon;
	}

	public String getInstructionImg() {
		return instructionImg;
	}

	public void setInstructionImg(String instructionImg) {
		this.instructionImg = instructionImg;
	}

	public String getLoginBgImg() {
		return loginBgImg;
	}

	public void setLoginBgImg(String loginBgImg) {
		this.loginBgImg = loginBgImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
	}
}
