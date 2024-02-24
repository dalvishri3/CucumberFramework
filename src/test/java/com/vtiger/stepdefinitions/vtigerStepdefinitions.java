package com.vtiger.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class vtigerStepdefinitions extends BaseDefinitions {
	
	
	@Before
	public void getTCName(Scenario scenario)
	{
		if(extent==null)
			createExtentReport();
		TCName = scenario.getName();
		logger = extent.createTest(TCName);
	}

	@After()
	public void tearDown() {
		extent.flush();
		driver.quit();	
	

	}
	
	
	
	@Given("user should be on login page")
	public void user_should_be_on_login_page() {
		initation();
	   
	}
	@When("user enters valid userid and password")
	public void user_enters_valid_userid_and_password() {
		 lp.setUserid(data.get(TCName).get("UserID"));
		   lp.setPassword(data.get(TCName).get("Password"));
	}
	@When("click on login button")
	public void click_on_login_button() {
	    lp.clickLogin();
	}
	@Then("user should be navigated to Home page")
	public void user_should_be_navigated_to_home_page() {
	  
	   hp.verifyHome();
	}
	
	@When("user enters invalid credentials")
	public void user_enters_invalid_credentials() {
		   lp.setUserid(data.get(TCName).get("UserID"));
		   lp.setPassword(data.get(TCName).get("Password"));
	}
	@Then("user can see the error message")
	public void validate_error_message() {
	  // driver.findElement(By.xpath("//*[contains(text(),'You must specify a valid username and password.')]")).isDisplayed();
	   lp.verifyErrorMsg();
	}
	
	
	@Then("logout link should be appear on home page")
	public void logout_link_should_be_appear_on_home_page() {
	   
	    hp.verifyLogout();
	}
	
	@Then("user can validate logo on login page")
	public void verifylogo() {

       lp.verifyLogo();
	}
	
	@When("user user enters invalid userid as {string} and invalid password as {string}")
	public void user_user_enters_invalid_userid_as_and_invalid_password_as(String userid, String pwd) {
	
		
		lp.setUserid(userid);
		lp.setPassword(pwd);
		
	}
	
	
	@When("create multiple leads with firstname as {string} and lastname as {string} and company as {string}")
	public void create_multiple_leads_with_firstname_as_and_lastname_as_and_company_as(String string, String string2, String string3, io.cucumber.datatable.DataTable dataTable) {
	   
		List<Map<String,String>> dt = dataTable.asMaps();
		for(Map<String,String> m:dt)
		{
					
			ldp.clickNewLead();
			ldp.CreateLead(m.get("fname"), m.get("lname"), m.get("company"));
			
		}
		
	}
	
	@When("create leads with mandatory fields")
	public void create_leads_with_mandatory_fields() {
		ldp.clickNewLead();
		ldp.CreateLead(data.get(TCName).get("FirstName"), data.get(TCName).get("LastName"), data.get(TCName).get("Company"));
	}
	
	@When("logout and close the browser")
	public void logout_and_close_the_browser() {
	   
	    hp.clickLogout();
	    driver.quit();
	}
	
	@When("logout from application")
	public void logout() {
	   
	    hp.clickLogout();
	    
	}

}
