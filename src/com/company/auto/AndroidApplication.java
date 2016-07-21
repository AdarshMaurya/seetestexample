package com.company.auto;

public class AndroidApplication extends ApplicationDefaultImpl {

	public AndroidApplication(MyClient client, String appUser, String appPassword) {
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
		client.elementSendText("NATIVE", "hint=Username", 0, appUser);
		client.elementSendText("NATIVE", "hint=Password", 0, appPassword);
		client.click("NATIVE", "xpath=//*[@text='Login' and @class='android.widget.Button']", 0, 1);
	}

	@Override
	public void makePayment(String name, String phone, String amount,
			String country, boolean approve) {
		navigateToActivity(navigationView.MAKE_PAYMENT);
		client.elementSendText("NATIVE", "hint=Phone", 0, phone);
		client.elementSendText("NATIVE", "hint=Name", 0, name);
		client.elementSendText("NATIVE", "hint=Amount", 0, amount);
		client.click("NATIVE", "text=Select", 0, 1);
		client.elementListSelect("", "text=" + country, 0, true);
		client.click("NATIVE", "text=Send Payment", 0, 1);
		client.click("NATIVE", "text=" + ((approve ? "Yes" : "No")), 0, 1);
	}

	@Override
	public void navigateToActivity(navigationView viewName) {
		switch (viewName) {
			case LOGIN:
				if(client.waitForElement("NATIVE", "xpath=//*[@id='loginButton' and @class='android.widget.Button']", 0, 2000)){
					return;
				}
				for(int i = 0; i < 4; i++){
					if(client.waitForElement("NATIVE", "xpath=//*[@text='Logout' and @class='android.widget.Button']", 0, 1)){
						client.click("NATIVE", "xpath=//*[@text='Logout' and @class='android.widget.Button']", 0, 1);
						return;
					}
					client.sendText("{ESC}");
					client.sleep(1000);
				}
				break;
			case HOME:
				if(client.waitForElement("NATIVE", "xpath=//*[@id='loginButton' and @class='android.widget.Button']", 0, 1)){
					login(null, null);
					return;
				}
				for(int i = 0; i < 4; i++){
					if(client.waitForElement("NATIVE", "xpath=//*[@text='Logout' and @class='android.widget.Button']", 0, 1)){
						return;
					}
					client.sendText("{ESC}");
					client.sleep(1000);
				}
				break;
			case MAKE_PAYMENT:
				if(client.waitForElement("NATIVE", "xpath=//*[@id='loginButton' and @class='android.widget.Button']", 0, 1)){
					login(null, null);
				}
				for(int i = 0; i < 4; i++){
					if(client.waitForElement("NATIVE", "xpath=//*[@text='Logout' and @class='android.widget.Button']", 0, 1)){
						client.click("NATIVE", "xpath=//*[@text='Make Payment' and @class='android.widget.Button']", 0, 1);
						return;
					}
					client.sendText("{ESC}");
					client.sleep(1000);
				}
				break;
		}		
	}

	@Override
	public void install(String appLocation) {
		client.install(appLocation + "\\eribank.apk", true, false);
		
	}

	@Override
	public void init(boolean clearData) {
		if(clearData){
			client.applicationClearData("com.experitest.ExperiBank");
		}
		client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
		
	}
	
}