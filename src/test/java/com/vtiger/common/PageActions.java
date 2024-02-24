package com.vtiger.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vtiger.stepdefinitions.BaseDefinitions;



public class PageActions {
	
public WebDriver driver;

public WebDriverWait wait;

	
	public PageActions(WebDriver driver)
	{
		this.driver = driver;	
		wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		System.out.println("Hello");
	}
	
	public void InputText(WebElement elm, String value, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(value);
		BaseDefinitions.logger.pass(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseDefinitions.logger.fail("Unable to enter the text due to error "+e.getMessage()+"<a href ='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	
	public void ClickElement(WebElement elm, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();
		BaseDefinitions.logger.pass(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseDefinitions.logger.fail("Unable to click due to error "+e.getMessage()+"<a href ='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public void ElementExist(WebElement elm , String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isDisplayed();
		BaseDefinitions.logger.pass(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseDefinitions.logger.fail("Element not found due to error "+e.getMessage()+"<a href ='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public String getScreenshot() 
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		String path = System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/screenshot/"+fileName+".png";
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File SrcFile=ts.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(path);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	

}
