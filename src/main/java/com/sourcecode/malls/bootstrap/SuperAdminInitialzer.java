package com.sourcecode.malls.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sourcecode.malls.service.impl.UserService;

@Component
public class SuperAdminInitialzer {

	@Autowired
	private UserService service;

	@PostConstruct
	public void init() {
		service.prepareSuperAdmin();
	}
}
