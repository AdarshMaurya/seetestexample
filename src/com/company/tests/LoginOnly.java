package com.company.tests;

import org.junit.Test;

import com.company.auto.TestBase;

public class LoginOnly extends TestBase {
	
	@Test
	public void loginOnlyTest() {

		application.login(data.get("Username"), data.get("Password"));
		
	}
}