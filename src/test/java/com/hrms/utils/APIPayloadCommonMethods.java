package com.hrms.utils;

import org.json.JSONObject;

public class APIPayloadCommonMethods{
	
	public static String createEmployeeBody() {
		
		String createEmployeeBody = "{\r\n" + 
				"  \"emp_firstname\": \"Darth\",\r\n" + 
				"  \"emp_lastname\": \"Vader\",\r\n" + 
				"  \"emp_middle_name\": \"Jr\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"2050-10-17\",\r\n" + 
				"  \"emp_status\": \"Freelance\",\r\n" + 
				"  \"emp_job_title\": \"Developer\"\r\n" + 
				"}";
		return createEmployeeBody;
	}
	
	public static String createEmployeePayload() {		
		JSONObject obj = new JSONObject();		
		obj.put("emp_firstname", "Darth");
		obj.put("emp_lastname", "Vader");
		obj.put("emp_middle_name", "Jr");
		obj.put("emp_gender", "M");
		obj.put("emp_birthday", "2050-10-17");
		obj.put("emp_status", "Freelance");
		obj.put("emp_job_title", "Developer");		
		return obj.toString();		
	}
	
	public static String createEmployeePayloadMoreDynamic(String firstName, String lastName, String middleName,
			String gender, String dob, String employeeStatus, String employeeJobTitle) {
		JSONObject obj = new JSONObject();
		obj.put("emp_firstname", firstName);
		obj.put("emp_lastname", lastName);
		obj.put("emp_middle_name", middleName);
		obj.put("emp_gender", gender);
		obj.put("emp_birthday", dob);
		obj.put("emp_status", employeeStatus);
		obj.put("emp_job_title", employeeJobTitle);
		return obj.toString();
	}
	

}
