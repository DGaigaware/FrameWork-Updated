package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Validatevalues {
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
	
@Test(priority=1,description="Validate the different fields in Common Console page")
	public void Validate_Inputvalues() throws Exception
	{
	// Click on Configuration,Settings,SMGR,Common Console
	action.ClickLink(locator.getProperty("Configurations"));
	action.WaitForTitle(locator.getProperty("Home_Screen"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Common_Console"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.SwithchFrame("iframe0");
	
	//Click on Edit Button
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	
	//Give the invalid inputs for different fields 
	action.ClearText(locator.getProperty("maxtabtextbox"));
	action.entertext(locator.getProperty("maxtabtextbox"),"@#!");
	
	//Click on Commit and Check for Error
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	Thread.sleep(1000);
	action.VerifyStringValue("Invalid input. Enter a valid integer value.");
    action.ClearText(locator.getProperty("maxpreftextbox"));
	action.entertext(locator.getProperty("maxpreftextbox"),"@#43");
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	Thread.sleep(1000);
	action.VerifyStringValue("Invalid input. Enter a valid integer value.");
	

	action.ClearText(locator.getProperty("noofrowstextbox"));
	action.entertext(locator.getProperty("noofrowstextbox"),"@@");
	
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	Thread.sleep(1000);
	action.VerifyStringValue("Invalid input. Enter a valid integer value.");
	

	action.ClearText(locator.getProperty("maxnoofrecord"));
	action.entertext(locator.getProperty("maxnoofrecord"),"<>");
	
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	Thread.sleep(1000);
	action.VerifyStringValue("Invalid input. Enter a valid integer value.");

	//Provide valid values for Different fields.
	 action.ClearText(locator.getProperty("maxtabtextbox"));
	 action.ClearText(locator.getProperty("maxpreftextbox"));
	 action.ClearText(locator.getProperty("noofrowstextbox"));
	 action.ClearText(locator.getProperty("maxnoofrecord"));

	 action.entertext(locator.getProperty("maxtabtextbox"),"4");
	 action.entertext(locator.getProperty("maxpreftextbox"),"12");
	 action.entertext(locator.getProperty("noofrowstextbox"),"17");
	 action.entertext(locator.getProperty("maxnoofrecord"),"500");
	 action.ClickButton(locator.getProperty("Commit"));
	 action.WaitForTitle(locator.getProperty("View_Profile"));
	 action.VerifyTitle(locator.getProperty("View_Profile"));
	 
	}  

	@Test(priority=2,description="Validate the different fields in Common Console page")
	public void Validate_Range() throws Exception
	{
	    action.driver.navigate().refresh();
	 // Click on Configuration,Settings,SMGR,Common Console
		action.ClickLink(locator.getProperty("Configurations"));
		action.WaitForTitle(locator.getProperty("Home_Screen"));
		action.ClickLink(locator.getProperty("Settings"));
		action.ClickLink(locator.getProperty("SMGR"));
		//String str1=action.driver.getWindowHandle();
		action.ClickLink(locator.getProperty("Common_Console"));
		action.WaitForTitle(locator.getProperty("View_Profile"));
		action.VerifyTitle(locator.getProperty("View_Profile"));
		//Thread.sleep(5000);
		action.SwithchFrame("iframe0");
		//Click on Edit Button
		action.ClickButton(locator.getProperty("Edit"));
		action.WaitForTitle(locator.getProperty("Edit_Profile"));
		
		//Give the invalid inputs for different fields 
		action.entertext(locator.getProperty("maxtabtextbox"),"411");
		//Click on Commit and Check for Error
		action.DoubleClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("Edit_Profile"));
		action.VerifyStringValue("Input value is out of range. Enter a value between 2 to 7");
		

		 action.entertext(locator.getProperty("maxpreftextbox"),"121");
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("Edit_Profile"));
		action.VerifyStringValue("Input value is out of range. Enter a value between 10 to 20.");
		
		
		action.entertext(locator.getProperty("noofrowstextbox"),"173");
		action.DoubleClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("Edit_Profile"));
		action.VerifyStringValue("Input value is out of range. Enter a value between 15 to 100.");
		

		action.entertext(locator.getProperty("maxnoofrecord"),"10");
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("Edit_Profile"));
		action.VerifyStringValue("Input value is out of range. Enter a value between 100 to 9999.");
		
		//Clear fields and Provide valid values for Different fields.
		 action.ClearText(locator.getProperty("maxtabtextbox"));
		 action.ClearText(locator.getProperty("maxpreftextbox"));
		 action.ClearText(locator.getProperty("noofrowstextbox"));
		 action.ClearText(locator.getProperty("maxnoofrecord"));

		 action.entertext(locator.getProperty("maxtabtextbox"),"4");
		 action.entertext(locator.getProperty("maxpreftextbox"),"12");
		 action.entertext(locator.getProperty("noofrowstextbox"),"17");
		 action.entertext(locator.getProperty("maxnoofrecord"),"500");
		 action.DoubleClickButton(locator.getProperty("Commit"));
		 action.WaitForTitle(locator.getProperty("View_Profile"));
		 action.VerifyTitle(locator.getProperty("View_Profile"));
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
