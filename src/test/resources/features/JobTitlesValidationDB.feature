Feature: Job Titles validation against DB
@bdValidationJobTitles
Scenario: Job Titles validation against DB
When login with valid credentials
Then verify the dashboard logo is displayed
When navigate to Job Titles
And verify the table is displayed
When get all Job Titles from the table
Then get all Job Titles from db
Then validate Job Titles from ui against db