package com.hrms.stepDefinitions;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.GlobalVariables;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSearchStepDefinitionsDB extends CommonMethods{

	@When("navigate to employee list")
	public void navigate_to_employee_list() {
		click(dash.pimLinkBtn);
		click(dash.empListBtn);
	}

	@When("enter a valid Employee id {string}")
	public void enter_a_valid_Employee_id(String employeeId) {
		waitForVisibility(empList.empNameTextBox);
		waitForClickability(empList.empIdTextBox);
		sendText(empList.empIdTextBox, employeeId);
		GlobalVariables.empId = employeeId;
	}

	@Then("click on Search Button")
	public void click_on_Search_Button() {
		click(empList.searchBtn);
	}

	@Then("verify the table is displayed")
	public void verify_the_table_is_displayed() {
		Assert.assertTrue(empList.isTableDisplayed());
	}

	@When("get first name from the table")
	public void get_first_name_from_the_table() {
	    System.out.println(empList.getFirstNameFromTable());
	}

	@Then("validate first names from ui against db")
	public void validate_first_names_from_ui_against_db() {
	    Assert.assertEquals(DBStepsDefinitions.dbData, empList.getFirstNameFromTable());
	}
}
