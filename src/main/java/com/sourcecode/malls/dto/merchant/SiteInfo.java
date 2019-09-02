package com.sourcecode.malls.dto.merchant;

public class SiteInfo {
	private String title;
	private String wechatServiceAccount;
	private String headerLogo;
	private String loginLogo;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWechatServiceAccount() {
		return wechatServiceAccount;
	}

	public void setWechatServiceAccount(String wechatServiceAccount) {
		this.wechatServiceAccount = wechatServiceAccount;
	}

	public String getHeaderLogo() {
		return headerLogo;
	}

	public void setHeaderLogo(String headerLogo) {
		this.headerLogo = headerLogo;
	}

	public String getLoginLogo() {
		return loginLogo;
	}

	public void setLoginLogo(String loginLogo) {
		this.loginLogo = loginLogo;
	}
}
