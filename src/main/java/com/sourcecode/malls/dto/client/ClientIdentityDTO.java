package com.sourcecode.malls.dto.client;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.enums.VerificationStatus;

public class ClientIdentityDTO extends SimpleQueryDTO {
	private Long id;
	private String username;
	private String name;
	private VerificationStatus status;
	private String number;
	private Long merchantId;
	private String facePhoto;
	private String badgePhoto;
	private String peoplePhoto;
	private String reason;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFacePhoto() {
		return facePhoto;
	}

	public void setFacePhoto(String facePhoto) {
		this.facePhoto = facePhoto;
	}

	public String getBadgePhoto() {
		return badgePhoto;
	}

	public void setBadgePhoto(String badgePhoto) {
		this.badgePhoto = badgePhoto;
	}

	public String getPeoplePhoto() {
		return peoplePhoto;
	}

	public void setPeoplePhoto(String peoplePhoto) {
		this.peoplePhoto = peoplePhoto;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
