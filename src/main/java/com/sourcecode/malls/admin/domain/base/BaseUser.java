package com.sourcecode.malls.admin.domain.base;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sourcecode.malls.admin.constants.RegexpConstant;

@MappedSuperclass
public abstract class BaseUser extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户名不能为空")
	@Size(min = 5, max = 50, message = "用户名长度必须在5-50之间")
	private String username;
	@NotBlank(message = "密码不能为空")
	@Size(max = 255, message = "密码长度小于255位")
	private String password;
	@NotBlank(message = "邮箱不能为空")
	@Size(min = 5, max = 50, message = "邮箱长度必须在5-50之间")
	@Email
	private String email;
	@Pattern(regexp = RegexpConstant.MOBILE_PATTERN, message = "手机必须是11位数字")
	private String mobile;
	@NotBlank(message = "头像不能为空")
	private String header;
	private boolean enabled;

	public BaseUser() {

	}

	protected BaseUser(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
