package com.sourcecode.malls.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonTests {
	@Test
	public void generatePwd() {
		System.out.println(new BCryptPasswordEncoder().encode("111111"));
	}
}
