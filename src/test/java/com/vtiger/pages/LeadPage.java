package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.PageActions;

public class LeadPage extends PageActions {

	public LeadPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="New Lead")
	WebElement lnk_NewLead;
	
	@FindBy(linkText="Leads")
	WebElement lnk_Leads;
	
	@FindBy(name="firstname")
	WebElement tb_firstname;
	
	@FindBy(name="lastname")
	WebElement tb_lastname;
	
	@FindBy(name="company")
	WebElement tb_company;
	
	@FindBy(name="button")
	WebElement btn_save;
	
	
	public void CreateLead(String fname,String lname, String comp)
	{
		setFirstName(fname);
		setLastName(lname);
		setCompany(comp);
		clickSave();
	}
	
	
	public void setFirstName(String fname)
	{
		InputText(tb_firstname,fname,"Data "+fname+" has been entered into First Name");
	}
	
	public void setLastName(String lname)
	{
		InputText(tb_lastname,lname,"Data "+lname+" has been entered into Last Name");
	}
	
	public void setCompany(String comp)
	{
		InputText(tb_company,comp,"Data "+comp+" has been entered into Company");	
	}
	
	public void clickSave()
	{
		ClickElement(btn_save,"Save button clicked");
	}
	
	public void clickNewLead()
	{
		ClickElement(lnk_NewLead,"Link New Lead clicked");
	}
	
	

}
