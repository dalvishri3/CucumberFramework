package com.vtiger.stepdefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDefinitions {
	
	public Properties prop;
	public WebDriver driver;
	public LoginPage lp;
	public HomePage hp;
	public LeadPage ldp;
	public static String path = System.getProperty("user.dir")+"/src/test/resources/TestData/data.xlsx";
	public static Map<String,Map<String,String>> data;
	public static String TCName;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
		
	public void initation()
	{
	
		if(data==null)
		readExcelData(path,"Sheet1");
		if(prop==null)
		readProperties();
		if(driver==null)
		launchApp();
		if(lp==null)
		pageobjectManager();
	}
	
	public void pageobjectManager()
	{
		if(lp==null)
			lp = new LoginPage(driver);
		if(hp==null)
			hp = new HomePage(driver);
		if(ldp==null)
			ldp = new LeadPage(driver);
		
	}
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("vTiger Regression Report"); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
		
	}
	
	public void launchApp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		String timeout = prop.getProperty("implicitWait");
		int time = Integer.parseInt(timeout);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));	
		
	}
	
	
	public void readProperties()
	{		
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Properties/config.properties");
		    prop.load(fis);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readExcelData(String workbook, String sheet)
	{
		Fillo fillo=new Fillo();
		Connection connection;
		try {
		connection = fillo.getConnection(workbook);
		
		String strQuery="Select * from "+sheet;
		Recordset recordset=connection.executeQuery(strQuery);
		int rowcount =recordset.getCount();
		int colmcount = recordset.getFieldNames().size();
		data = new HashMap<String,Map<String,String>>();
		while(recordset.next())
		{
			Map<String,String> rowmap = new HashMap<String,String>();
			for(int j=1;j<colmcount;j++)
			{
				String colname =recordset.getFieldNames().get(j);
				String colmValue =recordset.getField(colname);
				//System.out.println("colmName = "+colname+" colmValue="+colmValue);
				rowmap.put(colname, colmValue);
			}
			System.out.println("=================================");
			data.put(recordset.getField("TCName"), rowmap);
			//System.out.println(data);
		}
		 
		recordset.close();
		connection.close();
		
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
