package com.sourcecode.malls.dto.client;

import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.enums.VerificationStatus;

public class ClientIdentityDTO extends SimpleQueryDTO {
	private Long id;
	private String username;
	private String name;
	private VerificationStatus status;
	private String number;
	private Long merchantId;

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
