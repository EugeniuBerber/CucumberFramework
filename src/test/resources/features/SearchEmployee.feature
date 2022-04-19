@searchEmployee
Feature: Search for employee

  Background: 
    When login with valid credentials
    Then navigate to employee list page

  Scenario Outline: Search employee by id
    When enter employee "<Id>"
    Then click on search Button
    And verify that "<Id>" is successfully found

    Examples: 
      | Id   |
      | 7846 |
      | 7847 |
      | 7848 |
      | 7849 |
      | 7850 |

  Scenario Outline: Search employee by name
    When enter employee "<First Name>","<Middle Name>" and "<Last Name>"
    Then click on search Button
    And verify that "<First Name>","<Middle Name>" and "<Last Name>" is successfully found

    Examples: 
      | First Name | Middle Name | Last Name |
      | Bilbo      | B           | Baggins   |
      | Frodo      | F           | Baggins   |
      | Gandalf    | W           | Grey      |
      | Peregrin   | P           | Took      |
      | Samwise    | S           | Gamgee    |
