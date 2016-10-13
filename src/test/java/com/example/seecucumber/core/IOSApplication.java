package com.example.seecucumber.core;

public class IOSApplication extends ApplicationDefaultImpl {

	public IOSApplication(MyClient client, String appPackage,
			String launchActivity) {
		super(client, appPackage, launchActivity);
	}

	public void init(boolean clearData) {
		if (clearData) {
			client.applicationClearData(appPackage);
		}

		if (client!=null)
			client.launch(launchActivity, true, true);

		client.launch(launchActivity, true, true);

	}

	public void handleElementIdentificationException() {

	}

	public void install(String appLocation) {
		client.install(appLocation + "\\eribank.apk", true, false);

	}

}
