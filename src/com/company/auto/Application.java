package com.company.auto;

public interface Application {
	
	public void init(boolean clearData);
	
	public void handleElementIdentificationException();

	public void login(String username, String password);

	public void makePayment(String name, String phone, String amount,
			String country, boolean approve);

	public void install(String appLocation);

	public double getBalance();
	
}