package com.sourcecode.malls.dto.merchant;

import java.io.Serializable;

public class SiteInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
