package com.hrms.API.steps.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardcodedExamples {

	/*
	 * REST Assured - Java library specifically developed to automate REST endpoints
	 * 
	 * Given - Preparing your request
	 * When - What action will you perform, what type of call are you making 
	 * Then - Verification
	 * 
	 */

	/** Concatenates with endpoints during run time */
	String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDM2MzU3NDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYwMzY3ODk0MCwidXNlcklkIjoiMTI4NyJ9.0piTi6VoR6yaLFqA0zf7DcCosLYjPKPpiH7tbaIwW7E";
	static String employeeID;

	//@Test
	public void sampleTest() {

		/** BaseURI for all endpoints */
		// RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

		/** JWT */
		// token = "Bearer
		// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDM1NTgxNzAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYwMzYwMTM3MCwidXNlcklkIjoiMTI4NyJ9.glKKAAWk0jvpFTQ2bpfLJaJfbk7oYpFWQPdH6BFEe2o";

		/** Preparing request for /getOneEmployee.php */
		/** Using .log().all() to print out everything being sent with request */
		RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
				.header("Content-Type", "application/json").queryParam("employee_id", "3707");

		/** Making call to /getOneEmployee.php */
		Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");

		/** One way to print response object */
		// System.out.println(getOneEmployeeResponse.asString());

		/** Second way to print rest assured prettyPrint() */
		getOneEmployeeResponse.prettyPrint();

		/** Using assertThat() to verify status code */
		getOneEmployeeResponse.then().assertThat().statusCode(200);

	}

	@Test
	public void aPOSTcreatedEmployee() {

		/** Preparing request for creating an employee */
		RequestSpecification createEmployeeRequest = given().header("Authorization", token)
				.header("Content-Type", "application/json")
				.body("{\r\n" + "  \"emp_firstname\": \"SyntaxTechnologies\",\r\n"
						+ "  \"emp_lastname\": \"SyntaxTechnologies\",\r\n" + "  \"emp_middle_name\": \"Jr\",\r\n"
						+ "  \"emp_gender\": \"M\",\r\n" + "  \"emp_birthday\": \"2001-10-11\",\r\n"
						+ "  \"emp_status\": \"Super Contractor\",\r\n"
						+ "  \"emp_job_title\": \"Application Developer\"\r\n" + "}");

		/** making call to /createEmployee.php */
		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

		/** Printing response */
		//createEmployeeResponse.prettyPrint();

		/**
		 * Using jsonPath() to view the response body which lets us get the employee ID
		 * We are storing the employeeID as a global variable tj be able to use with
		 * other call
		 */
		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");

		/** Optional: Printing employeeID */
		//System.out.println(employeeID);

		/** Verify status code is 201 */
		createEmployeeResponse.then().assertThat().statusCode(201);

		/**
		 * Verification response body "Message" is paired with "Entry Created";
		 * equalTo() method comes from static Hamcrest package - NEED TO IMPORT MANUALLY
		 * import static org.hamcrest.Matchers.;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));

		/** Verifying created employee first name */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("SyntaxTechnologies"));

		/** Verifying server Apache/2.4.39 (Win64) PHP/7.2.18 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

	}

	//@Test
	public void bGETcreatedEmployee() {

		/** Preparing request to get created employee */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);

		/** Storing response for retrieving created employee */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");

		/** Printing response */
		//getCreatedEmployeeResponse.prettyPrint();

		/**
		 * Storing response employee ID into empID to compare with static global
		 * employee ID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");

		/** Comparing empID with stored employeeID from created employee call */
		boolean verifyingEmployeeID = empID.equalsIgnoreCase(employeeID);

		/** Asserting to verify the above condition is true */
		Assert.assertTrue(verifyingEmployeeID);

		/** Verifying status code is 200 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);

		/**
		 * Storing full response as a string so that we are able to pass it as an
		 * argument wuth JsonPath
		 */
		String response = getCreatedEmployeeResponse.asString();

		/** Created object of JsonPath */
		JsonPath js = new JsonPath(response);

		/** Grabbing employee ID using 'js' */
		String employeeId = js.getString("employee[0].employee_id");
		String firstname = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastname = js.getString("employee[0].emp_lastname");
		String birthday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String status = js.getString("employee[0].emp_status");

		/** Asserting response employee ID matches stored employee ID */
		Assert.assertTrue(employeeId.contentEquals(employeeID));

		/** Asserting the rest of the values match exprcted data */
		Assert.assertEquals(firstname, "SyntaxTechnologies");
		Assert.assertEquals(middleName, "Jr");
		Assert.assertEquals(lastname, "SyntaxTechnologies");
		Assert.assertEquals(birthday, "2001-10-11");
		Assert.assertEquals(gender, "Male");
		Assert.assertEquals(jobTitle, "Application Developer");
		Assert.assertEquals(status, "Super Contractor");

	}

	//@Test
	public void cGETallEmployees() {

		/** Preparing request to get all employees */
		RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token);

		/** Storing response into getAllEmployeesResponse */
		Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

		/** Printing response */
		//getAllEmployeesResponse.prettyPrint();

		/** Storing response as a String */
		String response = getAllEmployeesResponse.asString();

		/**
		 * Creating object of JsonPath and passing response as a String as an argument
		 */
		JsonPath js = new JsonPath(response);

		/** Retrieving the size of the array(the number of objects in the array) */
		int count = js.getInt("Employees.size()");
		//System.out.println(count);

//		for (int i = 0; i < count; i++) {
//			String allEmployeeIDs = js.getString("Employees[" + i + "].employee_id");
//			//System.out.println(allEmployeeIDs);
//			
//			if(allEmployeeIDs.contentEquals(employeeID)) {
//				System.out.println("Employee ID: " + employeeID + " is present in the body");
//				String firstNameOfEmpID = js.getString("Employees[" + i + "].emp_firstname");
//				System.out.println(firstNameOfEmpID);
//				break;
//				
//			}			
//			
//		}	
		
		/** for loop to print out first names of all employees */
//		for (int i = 0; i < count; i++) {
//			String allFirstNames = js.getString("Employees[" + i + "].emp_firstname");
//			System.out.println(allFirstNames);
//			
//		}
//		
		
				
	}
	
	@Test
	/** Preparing request to get update employee */
	public void dPUTupdateCreatedEmployee() {
		RequestSpecification putUpdateCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body("{\r\n" + 
						"  \"employee_id\": \""+employeeID+"\",\r\n" + 
						"  \"emp_firstname\": \"Darth\",\r\n" + 
						"  \"emp_lastname\": \"Vader\",\r\n" + 
						"  \"emp_middle_name\": \"\",\r\n" + 
						"  \"emp_gender\": \"M\",\r\n" + 
						"  \"emp_birthday\": \"2050-10-17\",\r\n" + 
						"  \"emp_status\": \"Freelance\",\r\n" + 
						"  \"emp_job_title\": \"Developer\"\r\n" + 
						"}").log().all();
		
		/** Storing response into putUpdateCreatedEmployeeResponse */
		Response putUpdateCreatedEmployeeResponse = putUpdateCreatedEmployeeRequest.when().put("/updateEmployee.php");
		
		/** Printing response */
		putUpdateCreatedEmployeeResponse.prettyPrint();
		
		/** Storing response as a String */
		String response = putUpdateCreatedEmployeeResponse.asString();
		
		/** Created object of JsonPath */
		JsonPath js = new JsonPath(response);

		/** Grabbing employee info using 'js' */
		String employeeId = js.getString("employee[0].employee_id");
		String firstname = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastname = js.getString("employee[0].emp_lastname");
		String birthday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String status = js.getString("employee[0].emp_status");
		
		
		/** Asserting the rest of the values match expected data */
		Assert.assertTrue(employeeId.contentEquals(employeeID));		
		Assert.assertEquals(firstname, "Darth");
		Assert.assertEquals(middleName, null);
		Assert.assertEquals(lastname, "Vader");
		Assert.assertEquals(birthday, "2050-10-17");
		Assert.assertEquals(gender, "Male");
		Assert.assertEquals(jobTitle, "Developer");
		Assert.assertEquals(status, "Freelance");
	}

}
