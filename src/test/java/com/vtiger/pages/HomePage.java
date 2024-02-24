package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.PageActions;

public class HomePage extends PageActions {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="123Logout")
	WebElement lnk_logout;
	
	@FindBy(linkText="Home")
	WebElement lnk_Home;
	
	public void clickLogout()
	{
		ClickElement(lnk_logout,"Link Logout clicked");
	}
	
	public void verifyLogout()
	{
		ElementExist(lnk_logout, "Logout displayed on Home page");
	}
	
	public void verifyHome()
	{
		ElementExist(lnk_Home,"Home is displayed");
	}

}
