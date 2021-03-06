package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import utility.UserAction;


public class loginTest {
	

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
	
	
@Test(description="Validation for User Name and Password Fields ",groups={"Sanity"})
  public void Login_Test() throws Exception{
		//Click LogOff link
		action.ClickButton(locator.getProperty("LogOff"));
		
		//Validate User name and password fields and check for errors
		action.ClearText(locator.getProperty("UserId"));
		action.entertext(locator.getProperty("UserId"),"");
		action.ClearText(locator.getProperty("Password"));
		action.entertext(locator.getProperty("Password"),"");
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForTitle(locator.getProperty("System_Manager"));
		action.VerifyTitle(locator.getProperty("System_Manager"));
		//Verify Error message
		action.VerifyStringValue("Enter your User ID.");
		action.entertext(locator.getProperty("UserId")," ");
		action.entertext(locator.getProperty("Password")," ");
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForTitle(locator.getProperty("System_Manager"));
		action.VerifyTitle(locator.getProperty("System_Manager"));
		//Verify Error message
		action.VerifyStringValue("Authentication failed. Check your keyboard's caps lock and re-enter your User ID and password.");
		//Clear text box and reenter login details
		action.ClearText(locator.getProperty("UserId"));
		action.entertext(locator.getProperty("UserId"),input.getProperty("use1"));
		action.ClearText(locator.getProperty("Password"));
		action.entertext(locator.getProperty("Password"),input.getProperty("pass2"));
		//Click on Log On button
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForTitle(locator.getProperty("System_Manager"));
		action.VerifyTitle(locator.getProperty("System_Manager"));
		//Verify Error Message
		action.VerifyStringValue("Authentication failed. Check your keyboard's caps lock and re-enter your User ID and password.");
		//Clear text box and reenter login details
		action.entertext(locator.getProperty("UserId"),input.getProperty("use2"));
		action.entertext(locator.getProperty("Password"),input.getProperty("pass1"));
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForTitle(locator.getProperty("System_Manager"));
		action.VerifyTitle(locator.getProperty("System_Manager"));
		//Verify Error Message
		action.VerifyStringValue("Authentication failed. Check your keyboard's caps lock and re-enter your User ID and password.");
		
		//Login with valid values of User name and password
		action.entertext(locator.getProperty("UserId"),input.getProperty("use2"));
		action.entertext(locator.getProperty("Password"),input.getProperty("pass2"));
		action.ClickButton(locator.getProperty("LogOn"));
		action.driver.getPageSource().contains("dashboard");
		//Check the Logo text of Avaya
		String imgtxt=action.driver.findElement(By.tagName("img")).getAttribute("alt");
		action.VerifyStringValue(imgtxt);
		
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
