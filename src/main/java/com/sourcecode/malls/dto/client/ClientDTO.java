package com.sourcecode.malls.dto.client;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.enums.Sex;

public class ClientDTO extends SimpleQueryDTO {
	private Long id;
	private String username;
	private String nickname;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Sex sex;
	private String avatar;
	private Long merchantId;
	private boolean enabled;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
