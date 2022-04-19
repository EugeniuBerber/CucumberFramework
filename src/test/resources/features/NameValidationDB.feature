Feature: Name validation against DB
@bdValidation
Scenario: First name validation against DB - serching by Employee ID
When login with valid credentials
Then verify the dashboard logo is displayed
When navigate to employee list
And enter a valid Employee id "0056266"
Then click on Search Button
And verify the table is displayed
When get first name from the table
Then get first name from db
Then validate first names from ui against db
