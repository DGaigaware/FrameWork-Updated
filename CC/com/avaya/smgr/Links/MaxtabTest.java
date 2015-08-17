package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class MaxtabTest {
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
 

@Test(priority=1,description="Verify error message on exceeding maximum number of tabs set in common console",groups={"Sanity"})
public void Maxtab() throws Exception
{
	//Click Configuration,Settings,SMGR,Common Console
	action.ClickLink(locator.getProperty("Configurations"));
	action.VerifyTitle(locator.getProperty("Home_Screen"));	
	action.ClickLink(locator.getProperty("Settings"));
	action.VerifyTitle(locator.getProperty("SPMHome_Screen"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.VerifyTitle(locator.getProperty("View_Profile"));
	action.ClickLink(locator.getProperty("Common_Console"));
	action.VerifyTitle(locator.getProperty("View_Profile"));
	String str=action.driver.getWindowHandle();
	Thread.sleep(5000);
	action.SwithchFrame("iframe0");
	//Set the maximum number of tabs to be allow on Dashboard 
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.driver.findElement(By.xpath(locator.getProperty("maxtabtextbox"))).clear();
	action.entertext(locator.getProperty("maxtabtextbox"), "4");
	//Click on Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	action.driver.switchTo().window(str);
	action.driver.findElement(By.xpath(locator.getProperty("Hometab"))).click();
	Thread.sleep(1000);
	//Click on Different Links on dashboard
	action.ClickLink(locator.getProperty("Directory_Synchronization"));
	action.driver.findElement(By.xpath(locator.getProperty("Hometab"))).click();
	action.ClickLink(locator.getProperty("Groups_&Roles"));
	action.driver.findElement(By.xpath(locator.getProperty("Hometab"))).click();
	action.ClickLink(locator.getProperty("User_Management"));
	action.driver.findElement(By.xpath(locator.getProperty("Hometab"))).click();
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	//After 4 Tabs Verify error message 
	action.VerifyStringValue("You have exceeded maximum numbers of tabs. Close any one of tabs to open new tab.");
	//Close All tabs
	action.ClickLinkByxpath(locator.getProperty("closetab4"));
	action.ClickLinkByxpath(locator.getProperty("closetab3"));
	action.ClickLinkByxpath(locator.getProperty("closetab2"));
	action.ClickLinkByxpath(locator.getProperty("closetab1"));
	
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
