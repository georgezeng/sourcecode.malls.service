package com.sourcecode.malls.admin.constants;

public interface RegexpConstant {
	String PASSWORD_PATTERN = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,}$";
	String MOBILE_PATTERN = "^\\d{11}$";
}
