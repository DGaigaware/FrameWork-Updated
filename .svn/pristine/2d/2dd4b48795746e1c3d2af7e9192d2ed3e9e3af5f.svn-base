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


public class Userpreferences {
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
	
	@Test(description="verify the setting of max. no. of user preferences set in configuration", priority=1)
	public void Setmaxprefrence() throws Exception
	{
		action.RefreshPage();
	//Click Configuration,Settings,Common Console
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	//String str1=action.driver.getWindowHandle();
	action.ClickLink(locator.getProperty("Common_Console"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.SwithchFrame("iframe0");

	//Set the maximum number of user preferences Allow on Dash board
	action.ClickButton(locator.getProperty("Edit"));
	action.ClearText(locator.getProperty("maxpreftextbox"));
	action.entertext(locator.getProperty("maxpreftextbox"), "10");
	action.ClickButton(locator.getProperty("Commit"));
	//Logout and login
	action.logout(action);
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="verify the Addition of user preferences and verify error message on exceeding max. no. of user preferences set in configuration", priority=2)
	public void Shrtcut_Test() throws Exception
	{
		action.RefreshPage();
  // Click on Different Links and add User preferences button
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Agent_Management_Service"));
	action.ClickLinkByxpath(locator.getProperty("Agent_Management_Serviceprf"));
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Communication_Manager"));
	action.ClickLink(locator.getProperty("Call_Center"));
	action.ClickLinkByxpath(locator.getProperty("Call_Centerprf"));
	action.ClickLink(locator.getProperty("Agents"));
	action.ClickLinkByxpath(locator.getProperty("Agentsprf"));
	action.ClickLink(locator.getProperty("Announcements"));
	action.ClickLinkByxpath(locator.getProperty("Announcementsprf"));
	action.ClickLink(locator.getProperty("Audio_Group"));
	action.ClickLinkByxpath(locator.getProperty("Audio_Groupprf"));
	action.ClickLink(locator.getProperty("Holiday_Table"));
	action.ClickLinkByxpath(locator.getProperty("Holiday_Tableprf"));
	action.ClickLink(locator.getProperty("Coverage"));
	action.ClickLinkByxpath(locator.getProperty("Coverageprf"));
	action.ClickLink(locator.getProperty("Endpoints"));
	action.ClickLinkByxpath(locator.getProperty("Endpointsprf"));
	action.ClickLink(locator.getProperty("Site_Data"));
	action.ClickLinkByxpath(locator.getProperty("Site_Dataprf"));
	action.ClickLink(locator.getProperty("Groups"));
	action.WaitForTitle(locator.getProperty("Groups"));
	action.VerifyTitle(locator.getProperty("Groups"));
	action.ClickLinkByxpath(locator.getProperty("Groupsprf"));
	action.ClickLink(locator.getProperty("Network"));
	action.ClickLinkByxpath(locator.getProperty("Networkprf"));
	action.ClickLink(locator.getProperty("Parameters"));
	action.ClickLinkByxpath(locator.getProperty("Networkprf"));
	
	//Check for error if user preference goes above 10
	action.VerifyStringValue("Exceeded the maximum limit for user preference.");
	
	//Assert First User Preference is Agent Management Service
	action.ClickLink(locator.getProperty("Home"));
	action.WaitForTitle(locator.getProperty("Dashboard"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("previousprf"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("firstprf"), "Agent Management Service");
	//logout and login
	action.logout(action);
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	//Assert First User Preference is Agent Management Service 
	action.VerifyElementValue(locator.getProperty("firstprf"), "Agent Management Service");

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
