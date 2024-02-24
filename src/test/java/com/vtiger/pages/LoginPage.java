package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.PageActions;

public class LoginPage extends PageActions {
	
	
	
	public LoginPage(WebDriver driver)
	{
		super(driver);		
		PageFactory.initElements(driver, this);
	}
	
	//String username = "user_name";
	
	//By username = By.name("user_name");
	
	@FindBy(name="user_name")
	WebElement tb_username;
	
	@FindBy(name="user_password")
	WebElement tb_password;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(xpath="//*[contains(text(),'You must specify a valid username and password.')]")
	WebElement txt_errorMsg;
	
	@FindBy(xpath="//img[@src='include/images/123vtiger-crm.gif']")
	WebElement img_logo;
	
	
	
	public void login(String userid, String pwd)
	{
		setUserid(userid);
		setPassword(pwd);
		clickLogin();
	}
	
	public void setUserid(String userid)
	{
		InputText(tb_username,userid,"Data "+userid+" has been entered into username field");
	}
	
	public void setPassword(String pwd)
	{
		InputText(tb_password,pwd,"Data "+pwd+" has been entered into Password field");	
	}
	
	public void clickLogin()
	{
		ClickElement(btn_login,"Login button clicked");
	}
	
	public void verifyErrorMsg()
	{
		ElementExist(txt_errorMsg,"Error message validate successfully");
	}
	
	public void verifyLogo()
	{
		ElementExist(img_logo,"Logo is displyed on login page");
	}

}
