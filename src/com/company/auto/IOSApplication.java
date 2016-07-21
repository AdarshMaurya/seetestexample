package com.company.auto;

public class IOSApplication extends ApplicationDefaultImpl {

	public IOSApplication(MyClient client, String appUser, String appPassword) {
		super(client, appUser, appPassword);
	}
	
	public void handleElementIdentificationException() {
		
	}

	@Override
	public void login(String username, String password) {
		navigateToActivity(navigationView.LOGIN);
		if(username == null){
			username = appUser;
		}
		if(password == null){
			password = appPassword;
		}
		client.elementSendText("NATIVE", "placeholder=Username", 0, appUser);
		client.elementSendText("NATIVE", "placeholder=Password", 0, appPassword);
		client.click("NATIVE", "xpath=//*[@accessibilityLabel='loginButton']", 0, 1);
	}

	@Override
	public void makePayment(String name, String phone, String amount,
			String country, boolean approve) {
		navigateToActivity(navigationView.MAKE_PAYMENT);
		client.elementSendText("NATIVE", "placeholder=Phone", 0, phone);
		client.elementSendText("NATIVE", "placeholder=Name", 0, name);
		client.elementSendText("NATIVE", "placeholder=Amount", 0, amount);
		client.click("NATIVE", "text=Select", 0, 1);
		client.elementListSelect("", "text=" + country, 0, true);
		client.click("NATIVE", "text=Send Payment", 0, 1);
		client.click("NATIVE", "text=" + ((approve ? "Yes": "No")), 0, 1);

	}

	@Override
	public void navigateToActivity(navigationView viewName) {
		switch (viewName) {
		case LOGIN:
			if(client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='loginButton']", 0, 2000)){
				return;
			}
			while (!client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='logoutButton']", 0, 1)){
				if (client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1)) {
					client.click("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1);
				}
				client.click("NATIVE", "xpath=//*[@accessibilityLabel='logoutButton']", 0, 1);
				return;
			}
			break;
		case HOME:
			if(client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='loginButton']", 0, 1)){
				login(null, null);
				return;
			}
			while (!client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='logoutButton']", 0, 1)){
				if (client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1)) {
					client.click("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1);
				}
			}
			break;
		case MAKE_PAYMENT:
			if(client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='loginButton']", 0, 1)){
				login(null, null);
			}
			while (!client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='logoutButton']", 0, 1)){
				if (client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1)) {
					client.click("NATIVE", "xpath=//*[@accessibilityLabel='cancelButton' or @accessibilityLabel='backButton']", 0, 1);
				}
			}
			client.click("NATIVE", "xpath=//*[@accessibilityLabel='makePaymentButton']", 0, 1);
			break;
	}	
		
	}

	@Override
	public void install(String appLocation) {
		client.install(appLocation + "\\EriBank.ipa", true, false);
		
	}

	@Override
	public void init(boolean clearData) {
		client.launch("com.experitest.ExperiBank", true, true);		
	}

}