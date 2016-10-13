package com.example.seecucumber.core;

import java.util.Date;

import org.junit.Assert;

import com.experitest.client.Client;

public class MyClient extends Client {
	
	public MyClient(String host, int port){
		super(host, port, true);
	}
		
	public void assertJUnitAndSeeTestReport(boolean condition, String messageIfTrue, String messageIfFalse) {
		if (condition) {
			this.report(messageIfTrue, true);
		} 
		else {
			this.report(messageIfFalse, false);
		}
		Assert.assertTrue(messageIfFalse, condition);
	}
	
	public long webClick(String zone, String element, int index, int clickCount) {
		this.click(zone, element, index, clickCount);
		long before = new Date().getTime();
		this.hybridWaitForPageLoad(30000);
		long after = new Date().getTime();
		return after - before;
	}
	
}