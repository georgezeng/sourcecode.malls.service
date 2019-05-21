package com.sourcecode.malls.util;

import java.util.Base64;

public class Base64Util {
	public static String encode(String str) {
		try {
			return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String decode(String str) {
		try {
			return new String(Base64.getDecoder().decode(str.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
