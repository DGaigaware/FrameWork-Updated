package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Bulkedit {

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
 
	
	@Test(priority=1,description="Verify the functionality of Bulk editing of users")
	public void Bulk_Edit_Test()throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");	
		
		//Click on Different Checkbox
		action.SelectCheckBox(locator.getProperty("Checkbox0"));
		Thread.sleep(1000);    
	    action.SelectCheckBox(locator.getProperty("Checkbox2"));
	    Thread.sleep(1000);   
		action.SelectCheckBox(locator.getProperty("Checkbox3"));
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator.getProperty("Users.More"))));
		Thread.sleep(1000);   
		//Click on More option button
		action.ClickButton(locator.getProperty("Users.More"));	
		Thread.sleep(1000);
		//Click on bulk edit user and verify title of page
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		action.VerifyTitle(locator.getProperty("User_Bulk_Editor"));
	}
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
	}
	
	@AfterClass
	  public void Close() throws IOException, InterruptedException
	    {
		action.logout(action);
		}


}
