package com.hrms.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;

public class JobTitlesPageElements extends BaseClass {

	@FindBy(xpath = "//table[@id = 'resultTable']")
	public WebElement resultTable;

	@FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr/td/a")
	public List<WebElement> jobTitlesListTableResult;

	public boolean isTableDisplayed() {
		return resultTable.isDisplayed();
	}

	public List<Map<String, String>> getJobTitlesFromTable() {
		List<Map<String, String>> uiJobTitles = new ArrayList<>();
		for (WebElement jobTitleRow : jobTitlesListTableResult) {
			Map<String, String> mapData = new LinkedHashMap<>();
			String tableName = jobTitleRow.getText();
			mapData.put("job_title", tableName);
			uiJobTitles.add(mapData);
		}
		return uiJobTitles;
	}

	public JobTitlesPageElements() {
		PageFactory.initElements(driver, this);
	}

}
