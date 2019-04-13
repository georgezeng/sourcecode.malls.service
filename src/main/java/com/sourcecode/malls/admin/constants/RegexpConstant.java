package com.sourcecode.malls.admin.constants;

public interface RegexpConstant {
	String PASSWORD_PATTERN = "^(?=.*[0-9].*)(?=.*[A-Za-z].*).{8,}$";
	String MOBILE_PATTERN = "^\\d{11}$";
}
