package com.hrms.stepDefinitions;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchEmployeeStepDefinitions extends CommonMethods {

	@Then("navigate to employee list page")
	public void navigate_to_employee_list_page() {
		click(dash.pimLinkBtn);
		click(dash.empListBtn);
	}

	@When("enter employee {string}")
	public void enter_employee(String id) {
		waitForVisibility(empList.empNameTextBox);
		waitForClickability(empList.empIdTextBox);
		sendText(empList.empIdTextBox, id);
	}

	@Then("click on search Button")
	public void click_on_search_Button() {
		click(empList.searchBtn);
	}

	@Then("verify that {string} is successfully found")
	public void verify_that_is_successfully_found(String id) {
		Assert.assertEquals(id, empList.idFoundTableResult.getText());
	}

	@When("enter employee {string},{string} and {string}")
	public void enter_employee_and(String firstName, String middleName, String lastName) {
		waitForVisibility(empList.empNameTextBox);
		waitForClickability(empList.empNameTextBox);
		String fullEmpName = firstName + " " + middleName + " " + lastName;
		sendText(empList.empNameTextBox, fullEmpName);
	}

	@Then("verify that {string},{string} and {string} is successfully found")
	public void verify_that_and_is_successfully_found(String firstName, String middleName, String lastName) {
		String expectedFullEmpName = firstName + " " + middleName + " " + lastName;
		String actualFullEmpName = empList.firstNameFoundTableResult.getText() + " "
				+ empList.lastNameFoundTableResult.getText();
		Assert.assertEquals(expectedFullEmpName, actualFullEmpName);
	}

}
