package com.example.seecucumber.stepdefinition;

import static com.example.seecucumber.stepdefinition.Hooks.client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Authentication {

	@Given("^Login Activity Is On Screen$")
	public void login_Activity_Is_On_Screen() throws Throwable {
		if (client.isElementFound("NATIVE",
				"xpath=//*[@text='Build Version : 1.0.465']", 0)) {

		}

		
		String workingDir = System.getProperty("user.dir");
		   System.out.println("Current working directory : " + workingDir);


	}

	@When("^User Enters Valid Username$")
	public void user_Enters_Valid_Username() throws Throwable {
		client.elementSendText("NATIVE", "xpath=//*[@hint='Username']", 0,
				"company");
	}

	@When("^User Enters Valid Password$")
	public void user_Enters_Valid_Password() throws Throwable {
		client.elementSendText("NATIVE", "xpath=//*[@hint='Password']", 0,
				"company");
	}

	@When("^User Click on Login Button$")
	public void user_Click_On_Login_Button() throws Throwable {
		client.click("NATIVE", "xpath=//*[@id='loginButton']", 0, 1);

	};

	@Then("^User Navigates to Main Activity$")
	public void user_Navigates_to_Main_Activity() throws Throwable {
		if (client.isElementFound("NATIVE",
				"xpath=//*[@text='EriBank'][@id='title']", 0)) {
		}

	}
}
