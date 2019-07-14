package com.sourcecode.malls.domain.client;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Table(name = "wechat_token")
@Entity
public class WechatToken extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String openId;
	private String accessToken;
	private String refreshToken;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}