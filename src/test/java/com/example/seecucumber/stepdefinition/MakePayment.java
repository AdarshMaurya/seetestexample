package com.example.seecucumber.stepdefinition;

import static com.example.seecucumber.stepdefinition.Hooks.client;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MakePayment {

	@Given("^User is on Main Activity$")
	public void user_is_on_main_activity() throws Throwable {
		if (client.isElementFound("NATIVE",
				"xpath=//*[@text='Build Version : 1.0.465']", 0)) {

		}
		client.elementSendText("NATIVE", "xpath=//*[@hint='Username']", 0,
				"company");

		client.elementSendText("NATIVE", "xpath=//*[@hint='Password']", 0,
				"company");
		client.click("NATIVE", "xpath=//*[@id='loginButton']", 0, 1);

	}

	@Then("^Confirmation Dialog Box Pops out$")
	public void confirmation_dialog_box_pops_out() throws Throwable {
		if (client.isElementFound("NATIVE",
				"xpath=//*[@text='Are you sure you want to send payment?']", 0)) {
			// If statement
		}

	}

	@Then("^User Balance must decrease$")
	public void user_balance_must_decrease() throws Throwable {
		if (client.isElementFound("NATIVE", "xpath=//*[@nodeName='document']",
				0)) {
			// If statement
		}
	}

	@And("^User clicks on Make Payment$")
	public void user_clicks_on_make_payment() throws Throwable {
		client.click("NATIVE", "xpath=//*[@id='makePaymentButton']", 0, 1);

	}

	@And("^User Enters Phone Number$")
	public void user_enters_phone_number() throws Throwable {
		client.elementSendText("NATIVE", "xpath=//*[@id='phoneTextField']", 0,
				"9999999999");

	}

	@And("^User Enters Name$")
	public void user_enters_name() throws Throwable {
		client.elementSendText("NATIVE", "xpath=//*[@id='nameTextField']", 0,
				"Adarsh");

	}

	@And("^User Enters Amount$")
	public void user_enters_amount() throws Throwable {
		client.elementSendText("NATIVE", "xpath=//*[@id='amountTextField']", 0,
				"2");

	}

	@And("^User Selects Country$")
	public void user_selects_country() throws Throwable {
		client.click("NATIVE", "xpath=//*[@id='countryButton']", 0, 1);
		client.click("NATIVE", "xpath=(//*[@id='rowTextView'])[7]", 0, 1);
		if (client
				.isElementFound("NATIVE", "xpath=//*[@text='New Zealand']", 0)) {
			// If statement
		}

	}

	@And("^User Clicks Send Payment$")
	public void user_clicks_send_payment() throws Throwable {
		client.click("NATIVE", "xpath=//*[@id='sendPaymentButton']", 0, 1);

	}

	@And("^User Clicks On Yes$")
	public void user_clicks_on_yes() throws Throwable {

		//client.click("NATIVE", "xpath=//*[@text='Yes']", 0, 1);
		client.click("NATIVE", "xpath=//*[@text='Yex']", 0, 1);



	}

}
