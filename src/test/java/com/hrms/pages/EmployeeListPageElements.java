package com.hrms.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;

public class EmployeeListPageElements extends BaseClass{

	@FindBy(xpath = "//input[@id='empsearch_employee_name_empName' and @autocomplete='off']")
	public WebElement empNameTextBox;
	
	@FindBy(css = "input#empsearch_id")
	public WebElement empIdTextBox;
	
	@FindBy(css = "input#searchBtn")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr/td/a")
	public WebElement foundTableResults;
	
	@FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr/td/a[1]")
	public WebElement idFoundTableResult;
	
	@FindBy(xpath = "(//table[@id = 'resultTable']/tbody/tr/td/a)[2]")
	public WebElement firstNameFoundTableResult;
	
	@FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr/td[3]")
	public List<WebElement> firstNamesFoundTableResult;
	
	@FindBy(xpath = "(//table[@id = 'resultTable']/tbody/tr/td/a)[3]")
	public WebElement lastNameFoundTableResult;
	
	@FindBy(xpath = "(//input[starts-with(@id, 'ohrmList_chkSelectRec')])[1]")
	public WebElement firstCheckBox;
	
	@FindBy(css = "input.delete")
	public WebElement deleteBtn;
	
	@FindBy(css = "input#dialogDeleteBtn")
	public WebElement confirmDeletingBtn;
	
	@FindBy(xpath = "//td[text()='No Records Found']")
	public WebElement noRecordsMessage;
	
	@FindBy(xpath = "//table[@id = 'resultTable']")
	public WebElement resultTable;
	
	public boolean isTableDisplayed() {
		return resultTable.isDisplayed();
	}
	
	public List<Map<String, String>> getFirstNameFromTable(){
		List<Map<String, String>> uiFirstName = new ArrayList<>();
		for(WebElement firstNameRow: firstNamesFoundTableResult) {
			Map<String, String> mapData = new LinkedHashMap<>();
			String tableName=firstNameRow.getText();
			mapData.put("emp_firstname", tableName);
			uiFirstName.add(mapData);
		}
		return uiFirstName;
	}

	public EmployeeListPageElements() {
		PageFactory.initElements(driver, this);
	}
}
