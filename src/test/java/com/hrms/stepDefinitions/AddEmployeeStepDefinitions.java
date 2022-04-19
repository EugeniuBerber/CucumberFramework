package com.hrms.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddEmployeeStepDefinitions extends CommonMethods {

	@Then("navigate to add employee page")
	public void navigate_to_add_employee_page() {
		click(dash.pimLinkBtn);
		click(dash.addEmpBtn);
	}

	@Then("enter first and last name")
	public void enter_first_and_last_name() {
		sendText(addEmp.firstNameField, "John");
		sendText(addEmp.lastNameField, "Wick");
	}

	@Then("click on save Button")
	public void click_on_save_Button() {
		click(addEmp.saveButton);
	}

	@Then("verify the employee is added successfully")
	public void verify_the_employee_is_added_successfully() {
		String profileName = persDetails.profileName.getText();
		Assert.assertEquals("John Wick", profileName);
	}

	@When("enter first name as {string} middle name as {string} and last name as {string}")
	public void enter_first_name_as_middle_name_as_and_last_name_as(String firstName, String middleName,
			String lastName) {
		sendText(addEmp.firstNameField, firstName);
		sendText(addEmp.middleName, middleName);
		sendText(addEmp.lastNameField, lastName);
	}

	@Then("verify that {string} is added successfully")
	public void verify_that_is_added_successfully(String fullName) {
		String profileName = persDetails.profileName.getText();
		Assert.assertEquals(fullName, profileName);
	}

	@When("enter employee {string}, {string} and {string}")
	public void enter_employee_and(String firstName, String middleName, String lastName) {
		sendText(addEmp.firstNameField, firstName);
		sendText(addEmp.middleName, middleName);
		sendText(addEmp.lastNameField, lastName);
	}

	@Then("verify that {string}, {string} and {string} is successfully added")
	public void verify_that_and_is_successfully_added(String firstName, String middleName, String lastName) {
		String fullName = firstName + " " + middleName + " " + lastName;
		String fullProfileName = persDetails.profileName.getText();
		Assert.assertEquals(fullName, fullProfileName);
	}

	@When("add multiple employees and veiry thay are added")
	public void add_multiple_employees_and_veiry_thay_are_added(DataTable employees) throws InterruptedException {
		List<Map<String, String>> employeesNames = employees.asMaps();

		for (Map<String, String> employeesName : employeesNames) {
			String firstName = employeesName.get("First Name");
			String middleName = employeesName.get("Middle Name");
			String lastName = employeesName.get("Last Name");
			String empId = employeesName.get("Employee ID");

			sendText(addEmp.firstNameField, firstName);
			sendText(addEmp.middleName, middleName);
			sendText(addEmp.lastNameField, lastName);
			sendText(addEmp.idField, empId);

			click(addEmp.saveButton);
			String actualName = persDetails.profileName.getText();
			String expectedName = firstName + " " + middleName + " " + lastName;
			Assert.assertEquals("Verifying employee names", actualName, expectedName);
			click(dash.addEmpBtn);
			Thread.sleep(1000);
		}
	}
	
	@When("add multiple employees from {string} verify they are added successfully")
	public void add_multiple_employees_from_verify_they_are_added_successfully(String sheetName) {
		List<Map<String,String>> excelData = ExcelUtility.excelToListMap(Constants.TESTDATA_FILEPATH, sheetName);
		for (Map<String, String> excelEmpName : excelData) {
			String firstName = excelEmpName.get("FirstName");
			String middleName = excelEmpName.get("MiddleName");
			String lastName = excelEmpName.get("LastName");
			String empId = excelEmpName.get("Employee ID");
			
			sendText(addEmp.firstNameField, firstName);
			sendText(addEmp.middleName, middleName);
			sendText(addEmp.lastNameField, lastName);
			sendText(addEmp.idField, empId);

			click(addEmp.saveButton);
			String actualName = persDetails.profileName.getText();
			String expectedName = firstName + " " + middleName + " " + lastName;
			Assert.assertEquals("Verifying employee names", actualName, expectedName);
			jsClick(dash.addEmpBtn);
		}
	}

//	@When("navigate to add employee page")
//	public void navigate_to_add_employee_page() {
//		jsClick(dash.pimLinkBtn);
//	    jsClick(dash.addEmpBtn);
//	}
//
//	@When("add employee firstname and lastname")
//	public void add_employee_firstname_and_lastname() {
//		sendText(addEmp.firstNameField, "Neil");
//		sendText(addEmp.lastName, "Armstrong");
//	}
//
//	@Then("verify successful employee adding")
//	public void verify_successful_employee_adding() {
//		click(addEmp.saveButton);
//	    Assert.assertEquals("Neil Armstrong", persDetails.profileName.getText());
//	}
//
//	@When("create login details")
//	public void create_login_details() {
//	    click(addEmp.createLoginDetailsCheckbox);
//	    sendText(addEmp.userName, "Neil_Armstrong");
//	    sendText(addEmp.userPassword, "N_Arm$trong789!##");
//	    sendText(addEmp.confirmPassword, "N_Arm$trong789!##");
//	}
}
