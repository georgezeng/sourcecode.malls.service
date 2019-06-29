package com.sourcecode.malls.dto;

import java.util.UUID;

public class LoginSuccessDTO {
	private Long userId;
	private String token = UUID.randomUUID().toString();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
