package com.avaya.smgr.sdm;

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


public class Downloadmanagement {
	
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the title and elements of Download_Management page")
public void VerifyDownloadpageElements() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Download_Management"));
	action.WaitForTitle(locator.getProperty("Download_Management"));
	action.VerifyTitle(locator.getProperty("Download_Management"));
	action.SwithchFrame("iframe0");
	//Verify Show Files button is enabled
	action.VerifyEnableButton(locator.getProperty("Download.Showlist"));
	//Verify Delete button disabled
	action.VerifyDisableButton(locator.getProperty("Download.Delete"));
	action.VerifyNoElementdisplay(locator.getProperty("Upgrade.Commit"));
	//Click on file select expansion tab
	action.ClickButton(locator.getProperty("Download.Selectfile"));
	action.WaitForTitle(locator.getProperty("Download_Management"));

	action.VerifyElementDisplay(locator.getProperty("Upgrade.Commit"));
	action.VerifyDisableButton(locator.getProperty("Upgrade.Commit"));
	
	action.SelectFromdropDown(locator.getProperty("Download.Sourcelist"), "My Computer");
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("Upgrade.Commit"));
	//Click on status collapse button 
	action.ClickButton(locator.getProperty("Download.Status"));
	action.WaitForTitle(locator.getProperty("Download_Management"));
	//Verify button is  not visible
	action.VerifyNoElementdisplay(locator.getProperty("Download.Delete"));
	action.ClickButton(locator.getProperty("Download.Device"));
	action.WaitForTitle(locator.getProperty("Download_Management"));
	action.VerifyNoElementdisplay(locator.getProperty("Download.Showlist"));
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}




}
