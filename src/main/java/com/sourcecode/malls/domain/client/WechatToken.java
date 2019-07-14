package com.sourcecode.malls.domain.client;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Table(name = "wechat_token")
@Entity
public class WechatToken extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "用户不能为空")
	private Long userId;
	@Size(max = 255, message = "openId长度不能大于255")
	@NotBlank(message = "openId不能为空")
	private String openId;
	@Size(max = 255, message = "accessToken长度不能大于255")
	@NotBlank(message = "accessToken不能为空")
	private String accessToken;
	@Size(max = 255, message = "refreshToken长度不能大于255")
	@NotBlank(message = "refreshToken不能为空")
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