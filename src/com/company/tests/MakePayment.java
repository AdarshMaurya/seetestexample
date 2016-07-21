package com.company.tests;

import org.junit.Test;

import com.company.auto.TestBase;

public class MakePayment extends TestBase {
	
	@Test
	public void makePaymentTest() {
		
		double balance = application.getBalance();
		application.makePayment(data.get("Name"), data.get("Phone"), data.get("Amount"), data.get("Country"), true);
		double balanceAfter = application.getBalance();
		boolean confirmTransfer = (balance - Double.valueOf(data.get("Amount")) == balanceAfter);
		client.assertJUnitAndSeeTestReport(confirmTransfer, "Transfer verified as correct", "Transfer not correct");
		
	}
	
}