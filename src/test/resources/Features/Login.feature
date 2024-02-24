@login
Feature: login page validation

Background:
Given user should be on login page

@run
Scenario: valid_login_TC01
When user enters valid userid and password
And click on login button
Then user should be navigated to Home page
And logout link should be appear on home page
And logout from application



@run
Scenario: invalid_login_TC02
When user enters invalid credentials
And click on login button
Then user can see the error message

@run
Scenario: verify_logo_TC03
And user can validate logo on login page

@invalid
Scenario Outline: multiple_invalid_login_<TCName>
When user user enters invalid userid as "<username>" and invalid password as "<password>"
And click on login button
Then user can see the error message
Examples:
|TCName|username | password|
|TC01|admin1   | pwd1    |
|TC02|Narendra | Modi  |
|TC03|Rahul   | Gandhi  |



