package com.company.auto;

public abstract class ApplicationDefaultImpl implements Application {
	protected MyClient client;
	protected String appUser = null;
	protected String appPassword = null;
	
	public ApplicationDefaultImpl(MyClient client, String appUser, String appPassword){
		this.client = client;
		this.appUser = appUser;
		this.appPassword = appPassword;
	}
	
	public abstract void navigateToActivity(navigationView viewName);
	
	public enum navigationView {
		HOME, LOGIN, MAKE_PAYMENT
	}
	
	@Override
	public double getBalance() {
		navigateToActivity(navigationView.HOME);
		String text = client.elementGetProperty("WEB", "partial_text=Your balance is", 0, "text");
		text = text.substring("Your balance is:".length()).trim();
		if(text.endsWith("$")){
			text = text.substring(0, text.length() - 1);
		}
		return Double.parseDouble(text);
	}
	
}