package com.sourcecode.malls.admin.util;

import java.util.regex.Pattern;

public class RegexpUtil {
	private static final Pattern PHONE_PTN = Pattern.compile("^\\d{11}$");
	private static final Pattern EMAIL_PTN = Pattern.compile(
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	private static final Pattern PASSWORD_PTN = Pattern.compile("^(?=.*[0-9].*)(?=.*[A-Za-z].*).{8,}$");

	public static boolean matchMobile(String text) {
		return PHONE_PTN.matcher(text).matches();
	}

	public static boolean matchEmail(String text) {
		return EMAIL_PTN.matcher(text).matches();
	}

	public static boolean matchPassword(String text) {
		return PASSWORD_PTN.matcher(text).matches();
	}
}
