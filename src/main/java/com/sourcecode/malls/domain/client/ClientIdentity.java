package com.sourcecode.malls.domain.client;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.client.ClientIdentityDTO;
import com.sourcecode.malls.enums.VerificationStatus;

@Table(name = "client_identity")
@Entity
public class ClientIdentity extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	@NotBlank(message = "真实姓名不能为空")
	@Size(max = 50, message = "真实姓名长度不能大于50")
	private String name;
	@Size(max = 50, message = "身份证号长度不能大于50")
	@NotBlank(message = "身份证号不能为空")
	private String number;
	@NotNull(message = "审核状态不能为空")
	@Enumerated(EnumType.STRING)
	private VerificationStatus status;
	@NotBlank(message = "正面证照不能为空")
	private String facePhoto;
	@NotBlank(message = "国徽面证照不能为空")
	private String badgePhoto;
	@NotBlank(message = "持卡证照不能为空")
	private String peoplePhoto;
	@Size(max = 255, message = "失败原因长度不能大于255")
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
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

	public ClientIdentityDTO asDTO() {
		ClientIdentityDTO dto = new ClientIdentityDTO();
		BeanUtils.copyProperties(this, dto);
		if (client != null) {
			dto.setUsername(client.getUsername());
		}
		return dto;
	}

}
