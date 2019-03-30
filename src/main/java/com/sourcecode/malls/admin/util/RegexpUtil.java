package com.sourcecode.malls.admin.util;

import java.util.regex.Pattern;

import com.sourcecode.malls.admin.constants.RegexpConstant;

public class RegexpUtil {
	private static final Pattern MOBILE_PTN = Pattern.compile(RegexpConstant.MOBILE_PATTERN);
	private static final Pattern EMAIL_PTN = Pattern.compile(RegexpConstant.EMAIL_PATTERN);
	private static final Pattern PASSWORD_PTN = Pattern.compile(RegexpConstant.PASSWORD_PATTERN);

	public static boolean matchMobile(String text) {
		return MOBILE_PTN.matcher(text).matches();
	}

	public static boolean matchEmail(String text) {
		return EMAIL_PTN.matcher(text).matches();
	}

	public static boolean matchPassword(String text) {
		return PASSWORD_PTN.matcher(text).matches();
	}
}
