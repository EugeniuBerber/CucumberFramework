package com.hrms.stepDefinitions;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobTitlesStepDefinitions extends CommonMethods {

	@When("navigate to Job Titles")
	public void navigate_to_Job_Titles() {
		jsClick(dash.adminLinkBtn);
		jsClick(dash.jobTitlesLinkBtn);
	}

	@When("get all Job Titles from the table")
	public void get_all_Job_Titles_from_the_table() {
		System.out.println(jobTitles.getJobTitlesFromTable());
	}

	@Then("validate Job Titles from ui against db")
	public void validate_Job_Titles_from_ui_against_db() {
		System.out.println("DB 'Job Titles List' size is: " + DBStepsDefinitions.dbData.size());
		System.out.println("UI 'Job Titles List' size is: " + jobTitles.getJobTitlesFromTable().size());
		Assert.assertEquals(DBStepsDefinitions.dbData, jobTitles.getJobTitlesFromTable());
	}

	// DB 'Job Titles List' size is: 27
	// UI 'Job Titles List' size is: 24
	// Error: 
	// db has:{job_title=Freelancer}, {job_title=Sales&Marketing Manager}, {job_title=Sales&Marketing Manager}

}
