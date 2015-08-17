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


public class Breadcrumbartest {
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
	
	@Test(priority=1,description="Verify the Bread-Crumb-bar Tabs are properly working")
	public void Breadcrumb_Bar() throws Exception
	{
		//Click on differnt links in Communication manager
		action.ClickLink(locator.getProperty("Communication_Manager"));
		action.WaitForTitle(locator.getProperty("Feature_Management"));
		action.ClickLink(locator.getProperty("Endpoints"));
		action.ClickLink(locator.getProperty("Off_PBX_Telephone"));
		action.ClickLink(locator.getProperty("Off_PBX_Configuration_Set"));
		action.VerifyTitle(locator.getProperty("Off_PBX_Configuration_Set"));
		//click on Endpoint,Home Breadcrumbar button
		action.ClickLinkByxpath(locator.getProperty("Endpointbar"));
	    action.WaitForTitle(locator.getProperty("Endpoints"));
	    action.ClickLinkByxpath(locator.getProperty("Homebar"));
	    action.WaitForTitle(locator.getProperty("Dashboard"));
	}

	
	@Test(priority=2,description="Verify the Administrators and Communication_Server_1000 Tabs open in the same browser window")
	public void Administrator_Link_Test() throws Exception
	{
		action.driver.navigate().refresh();
		action.ClickLink(locator.getProperty("Administrators"));
		//Get name of the tab and verify it with actual link
		action.VerifyElementValue(locator.getProperty("Administratortab"), "Administrators");
		action.ClickLink(locator.getProperty("Home"));
		//Get name of the tab and verify it with actual link
		action.ClickLink(locator.getProperty("Communication_Server_1000"));
		action.VerifyElementValue(locator.getProperty("CommunicationServertab"), "Communication Server 1000");
		action.ClickLink(locator.getProperty("Home"));
	
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
