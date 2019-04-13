package com.sourcecode.malls.admin.context;

import com.sourcecode.malls.admin.domain.system.setting.User;

public final class UserContext {
	private static final ThreadLocal<User> value = new ThreadLocal<>();

	public static User get() {
		User user = value.get();
		return user != null ? user : User.SystemUser;
	}

	public static void set(User user) {
		value.set(user);
	}
}
