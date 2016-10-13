package com.example.seecucumber.core;

public abstract class ApplicationDefaultImpl implements Application {
	protected MyClient client;
	protected String appPackage = null;
	protected String launchActivity = null;

	public ApplicationDefaultImpl(MyClient client, String appPackage,
			String launchActivity) {
		this.client = client;
		this.appPackage = appPackage;
		this.launchActivity = launchActivity;
	}
}
