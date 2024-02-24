Feature: lead functionality


Scenario: create_multiple_leads
Given user should be on login page
When user enters valid userid and password
And click on login button
And create multiple leads with firstname as "<fname>" and lastname as "<lname>" and company as "<company>"
|fname | lname | company|
|Sonalee | Ji |google|
|ajay  | ji | microsoft|
|vinayak | ji | apple|
And logout and close the browser

@run
Scenario: create_leads_TC04
Given user should be on login page
When user enters valid userid and password
And click on login button
And create leads with mandatory fields
And logout and close the browser



