package com.hrms.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteEmployeeStepDefinitions extends CommonMethods {

	@Then("click on user checkbox")
	public void click_on_user_checkbox() {
		waitForVisibility(empList.firstCheckBox);
		waitForClickability(empList.firstCheckBox);
		jsClick(empList.firstCheckBox);
	}

	@Then("click on delete Button")
	public void click_on_delete_Button() {
		jsClick(empList.deleteBtn);
	}

	@Then("confirm Deleting")
	public void confirm_Deleting() {
		waitForVisibility(empList.confirmDeletingBtn);
		jsClick(empList.confirmDeletingBtn);
	}

	@Then("verify that employee was deleted successfully")
	public void verify_that_employee_was_deleted_successfully() {
		waitForVisibility(empList.noRecordsMessage);
		String expectedMessage = "No Records Found";
		String actualMessage = empList.noRecordsMessage.getText();
		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@When("delete multiple employees from {string}")
	public void delete_multiple_employees_from(String sheetName) {
		List<Map<String, String>> excelDara = ExcelUtility.excelToListMap(Constants.SAMPLETESTDATA_FILEPATH,
				"AddEmployee");
		for (Map<String, String> excelEmployee : excelDara) {
			String id = excelEmployee.get("Employee Id");
			String firstName = excelEmployee.get("FirstName");
			String middleName = excelEmployee.get("MiddleName");
			String lastName = excelEmployee.get("LastName");

			waitForVisibility(empList.empNameTextBox);
			waitForClickability(empList.empIdTextBox);
			sendText(empList.empIdTextBox, id);
			click(empList.searchBtn);

			String foundId = empList.idFoundTableResult.getText();
			String expectedEmpName = firstName + " " + middleName + " " + lastName;
			String adctualEmpName = empList.firstNameFoundTableResult.getText() + " "
					+ empList.lastNameFoundTableResult.getText();
			Assert.assertEquals(id, foundId);
			Assert.assertEquals(expectedEmpName, adctualEmpName);

			waitForVisibility(empList.firstCheckBox);
			waitForClickability(empList.firstCheckBox);
			jsClick(empList.firstCheckBox);
			jsClick(empList.deleteBtn);
			waitForVisibility(empList.confirmDeletingBtn);
			jsClick(empList.confirmDeletingBtn);

			waitForVisibility(empList.noRecordsMessage);
			String expectedMessage = "No Records Found";
			String actualMessage = empList.noRecordsMessage.getText();
			Assert.assertEquals(expectedMessage, actualMessage);
		}
	}
}
