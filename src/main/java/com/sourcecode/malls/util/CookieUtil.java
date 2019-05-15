package com.sourcecode.malls.util;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static String getValue(HttpServletRequest req, String name) throws Exception {
		return getValue(req, name, true);
	}

	public static String getValue(HttpServletRequest req, String name, boolean decode) throws Exception {
		Cookie[] cookies = req.getCookies();
		String decodedValue = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					decodedValue = cookie.getValue();
					if (decode) {
						decodedValue = new String(Base64.getDecoder().decode(decodedValue.getBytes("UTF-8")), "UTF-8");
					}
					return decodedValue;
				}
			}
		}
		return null;
	}

	public static void writeCookie(HttpServletResponse res, String name, String value, boolean isSecure, int expiry) throws Exception {
		writeCookie(res, name, value, isSecure, expiry, true, true);
	}

	public static void writeCookie(HttpServletResponse res, String name, String value, boolean isSecure, int expiry, boolean isEncode,
			boolean isHttpOnly) throws Exception {
		String encodedValue = value;
		if (isEncode) {
			encodedValue = new String(Base64.getEncoder().encode(value.getBytes("UTF-8")));
		}
		Cookie cookie = new Cookie(name, encodedValue);
		if (expiry != 0) {
			cookie.setMaxAge(expiry);
		}
		cookie.setSecure(isSecure);
		cookie.setHttpOnly(isHttpOnly);
		cookie.setPath("/");
		res.addCookie(cookie);
	}

}