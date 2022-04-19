@login
Feature: Login Functionality

  @regression
  Scenario: Login with valid credentials
    When login with valid credentials
    Then verify the dashboard logo is displayed

  @smoke
  Scenario: Login with invalid credentials
    When login with invalid credentials
    Then verify the error message
#Scenario: Login with empty username
#When login with empty username
#Then verify the empty username error message 
#
#Scenario: Login with empty password
#When login with empty password
#Then verify the empty password error message 