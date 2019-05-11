package com.sourcecode.malls.util;

import java.util.Random;

public class CodeUtil {

	public static String generateRandomNumbers(int length) {
		Random r = new Random();
		String code = "";
		int count = 0;
		while (count < length) {
			code += r.nextInt(9);
			count++;
		}
		return code;
	}
}
