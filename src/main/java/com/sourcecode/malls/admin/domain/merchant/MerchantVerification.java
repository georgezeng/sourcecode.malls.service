package com.sourcecode.malls.admin.domain.merchant;

import javax.persistence.CascadeType;
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

import com.sourcecode.malls.admin.domain.base.LongKeyEntity;
import com.sourcecode.malls.admin.dto.merchant.MerchantVerificationDTO;
import com.sourcecode.malls.admin.enums.MerchantVerificationType;
import com.sourcecode.malls.admin.enums.VerificationStatus;

@Table(name = "merchant_verification")
@Entity
public class MerchantVerification extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "商家名称不能为空")
	@Size(max = 50, message = "商家名称长度不能大于50")
	private String name;
	@NotNull(message = "证件类型不能为空")
	@Enumerated(EnumType.STRING)
	private MerchantVerificationType type;
	@NotBlank(message = "证件号不能为空")
	@Size(max = 50, message = "证件号长度不能大于50")
	private String number;
	@NotBlank(message = "证件照片不能为空")
	private String photo;
	private String contact;
	private String phone;
	private String address;
	private String description;
	@NotNull(message = "审核状态不能为空")
	@Enumerated(EnumType.STRING)
	private VerificationStatus status;
	private String reason;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	public MerchantVerificationDTO asDTO() {
		MerchantVerificationDTO dto = new MerchantVerificationDTO();
		BeanUtils.copyProperties(this, dto, "merchant");
		dto.setUsername(merchant.getUsername());
		return dto;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
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

	public MerchantVerificationType getType() {
		return type;
	}

	public void setType(MerchantVerificationType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
