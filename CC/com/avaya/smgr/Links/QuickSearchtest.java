package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class QuickSearchtest {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(priority=1,description="Verify the search criteria based on the Links present in SMGR")
	public void Search_Test()throws Exception
	{
		//Write 'Server' keyword in Search box and click on Communication Server 1000 link
	    action.WaitForTitle(locator.getProperty("Dashboard"));
	    action.VerifyTitle(locator.getProperty("Dashboard"));
	    //Thread.sleep(3000);
		action.entertext(locator.getProperty("Searchbox"),"Server");
	    action.driver.findElement(By.xpath(locator.getProperty("Searchbox"))).sendKeys(Keys.RETURN);
		Thread.sleep(1000);
        action.VerifyStringValue("Communication Server 1000");
		action.ClickLink(locator.getProperty("Communication_Server_1000"));
		Thread.sleep(1000);
		//Verify Title
		action.VerifyStringValue("Elements");
	    //Write Public keyword in Search box and Verify public Contact link
	    action.ClearText(locator.getProperty("Searchbox"));
	    action.entertext(locator.getProperty("Searchbox"),"Contact");
	    action.driver.findElement(By.xpath(locator.getProperty("Searchbox"))).sendKeys(Keys.RETURN);
	    //Verify Text
	    action.VerifyStringValue("Public Contacts");
	   	    
	}

	@Test(priority=2,description="Verify the search criteria based on the bulk import export link")
	public void Search_Bulkimport_Test()throws Exception
	{
		//Write bulk keyword in Search box and Verify Bulk Import and Export link
		action.driver.navigate().refresh();
		action.ClearText(locator.getProperty("Searchbox"));
		action.entertext(locator.getProperty("Searchbox"),"bulk");
		action.driver.findElement(By.xpath(locator.getProperty("Searchbox"))).sendKeys(Keys.RETURN);
		Thread.sleep(1000);
		action.VerifyStringValue("Bulk Import and Export");
	    action.ClickLink(locator.getProperty("Bulk_Import_and_Export"));
	    //Verify Title
		action.WaitForTitle(locator.getProperty("Bulk_Import_and_export"));
		action.ClickLinkByxpath(locator.getProperty("Hometab"));
	}
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
	}
@AfterClass
	  public void Close() throws IOException, InterruptedException{
		action.logout(action);
		}
}
