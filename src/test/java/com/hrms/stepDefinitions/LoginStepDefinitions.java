package com.hrms.stepDefinitions;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions extends CommonMethods {

	@When("login with valid credentials")
	public void login_with_valid_credentials() {
		sendText(login.userNametextBox, ConfigsReader.getPropValue("username"));
		sendText(login.passwordTextBox, ConfigsReader.getPropValue("password"));
		click(login.btnLogin);
	}

	@Then("verify the dashboard logo is displayed")
	public void verify_the_dashboard_logo_is_displayed() {
		Assert.assertTrue(dash.welcome.isDisplayed());
	}

	@When("login with invalid credentials")
	public void login_with_invalid_credentials() {
		login.loginToHrms("Admin11", "Syntax");
	}

	@Then("verify the error message")
	public void verify_the_error_message() {
		Assert.assertTrue(login.spanMessage.isDisplayed());
	}

	@When("login with empty username")
	public void login_with_empty_username() {
		login.loginToHrms("", ConfigsReader.getPropValue("password"));
		click(login.btnLogin);
	}

	@When("login with empty password")
	public void login_with_empty_password() {
		login.loginToHrms(ConfigsReader.getPropValue("username"), "");
		click(login.btnLogin);
	}

	@Then("verify the empty username error message")
	public void verify_the_empty_username_error_message() {
		Assert.assertEquals(login.spanMessage.getText(), "Username cannot be empty");
	}

	@Then("verify the empty password error message")
	public void verify_the_empty_password_error_message() {
		Assert.assertEquals(login.spanMessage.getText(), "Password cannot be empty");
	}

}