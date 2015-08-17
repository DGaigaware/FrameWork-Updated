package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import utility.UserAction;


public class QuicknavigatorTest {
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
 
@Test(priority=1,description="Verify the functionality of Quick navigator")
public void  Quick_Navigate() throws Exception

{
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));

	action.SwithchFrame("iframe0");
	//Send the text in Quick navigator
	action.entertext(locator.getProperty("Quicknavigetorbox"), "a");
	//Click on First result
	action.ClickLinkByxpath(locator.getProperty("Quickfirstoption"));
	//Verify the Buttons are enabled
    action.VerifyEnableButton(locator.getProperty("Quickeditbtn"));
    action.VerifyEnableButton(locator.getProperty("Quickviewbtn"));
    action.VerifyEnableButton(locator.getProperty("Quickdeletebtn"));
    //Click on edit button and Check the title of page
	action.ClickButton(locator.getProperty("Quickeditbtn"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
    //action.ClickLink(locator.getProperty("Manage_Users"));
    //action.SwithchFrame("iframe0");
    action.entertext(locator.getProperty("Quicknavigetorbox"), "a");
    action.ClickLinkByxpath(locator.getProperty("Quickfirstoption"));
    //Click on View button and Check the title of page
	action.ClickButton(locator.getProperty("Quickviewbtn"));
	action.WaitForTitle(locator.getProperty("User_Profile_View"));
	action.DoubleClickButton(locator.getProperty("Done"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
    //action.ClickLink(locator.getProperty("Manage_Users"));
   // action.SwithchFrame("iframe0");
    action.entertext(locator.getProperty("Quicknavigetorbox"), "a");
    
    action.ClickLinkByxpath(locator.getProperty("Quickfirstoption"));
    //Click on Delete button and Check the title of page
	action.ClickButton(locator.getProperty("Quickdeletebtn"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.DoubleClickButton(locator.getProperty("Cancelbtnbtm"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("User_Management"));
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
