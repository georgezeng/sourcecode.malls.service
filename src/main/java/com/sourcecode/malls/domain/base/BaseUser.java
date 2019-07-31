package com.sourcecode.malls.domain.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sourcecode.malls.constants.RegexpConstant;

@MappedSuperclass
public abstract class BaseUser extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户名不能为空")
	@Size(max = 50, message = "用户名长度不能大于50")
	private String username;
	@Size(max = 255, message = "密码长度不能大于255")
	private String password;
	@Size(max = 50, message = "邮箱长度不能大于50")
	@Email
	private String email;
	@Pattern(regexp = RegexpConstant.MOBILE_PATTERN, message = "手机必须是11位数字")
	private String mobile;
	@Size(max = 255, message = "头像长度不能大于255")
	private String avatar;
	private boolean enabled;

	@Transient
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
