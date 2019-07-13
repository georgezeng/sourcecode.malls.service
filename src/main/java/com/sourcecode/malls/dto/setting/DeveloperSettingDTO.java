package com.sourcecode.malls.dto.setting;

public class DeveloperSettingDTO {
	private String account;
	private String secret;
	private String mch;

	public String getMch() {
		return mch;
	}

	public void setMch(String mch) {
		this.mch = mch;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
