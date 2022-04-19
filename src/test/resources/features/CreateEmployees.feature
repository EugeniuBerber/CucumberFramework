@CreateEmployees
Feature: Creating Employees
  Description: This feature creates employees
  Background:
  Given a JWT is generated
  
  Scenario Outline: Creating employees
    Given a request is prepared to create employees
    When employee data provided is "<employeeFirstName>", "<employeeLastName>", "<employeeMiddleName>","<employeeGender>","<employeeBirthday>","<employeeJobStatus>","<employeeJobTitle>"
    Then employees are created
    Examples: 
      | employeeFirstName | employeeLastName | employeeMiddleName | employeeGender | employeeBirthday | employeeJobStatus      | employeeJobTitle |
      | Bob               | Bob              | Bob                | M              | 2000-07-11       | Employee               | Cloud Architect  |
      | Gozde             | Gozde            | Gozde              | F              | 2000-07-11       | Independent contractor | API Tester       |
      | Irma              | Irma             | Irma               | F              | 2000-07-11       | Super Contractor       | CEO              |