@deleteEmployee
Feature: Delete Employee

  Background: 
    When login with valid credentials
    Then navigate to employee list page

  Scenario Outline: Delete employee
    When enter employee "<Id>"
    Then click on search Button
    And verify that "<First Name>","<Middle Name>" and "<Last Name>" is successfully found
    Then click on user checkbox
    Then click on delete Button
    And confirm Deleting
    Then verify that employee was deleted successfully

    Examples: 
      | Id   | First Name | Middle Name | Last Name |
      | 8266 | Mark       | J           | Smith     |
      | 8267 | Hunter     | ABC         | Musoev    |
      | 8268 | John       | M           | Wick      |
      | 8269 | John       | F           | Kennedy   |

  Scenario: Deleting multiple employees from excel file
    When delete multiple employees from "AddEmployee"
