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


public class ManageSoftware {

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

@Test(description="Verify the title and elements of IP_Office page")
public void VerifyIPpageElements() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
    action.ClickLink(locator.getProperty("Manage_Software"));
    action.WaitForTitle(locator.getProperty("Manage_Software"));
    action.ClickLink(locator.getProperty("IP_Office"));
    action.WaitForTitle(locator.getProperty("Software_Management_for_IP_Office"));
    action.VerifyTitle(locator.getProperty("Software_Management_for_IP_Office"));
    action.SwithchFrame("iframe0");
    //Verify Get Inventory and Analyze button are are enabled
    action.VerifyEnableButton(locator.getProperty("IP.Inventorylist"));
    action.VerifyEnableButton(locator.getProperty("IP.Analyzelist"));
    //verify download,upgrade,status buttons are disabled
    action.VerifyDisableButton(locator.getProperty("IP.Download"));
    action.VerifyDisableButton(locator.getProperty("IP.Upgrade"));
    action.VerifyDisableButton(locator.getProperty("IP.Status"));
}

@Test(description="Verify the title and elements of IP_Office page")
public void VerifyUCMpageElements() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
    action.ClickLink(locator.getProperty("Manage_Software"));
    action.WaitForTitle(locator.getProperty("Manage_Software"));
    action.ClickLink(locator.getProperty("IP_Office"));
    action.WaitForTitle(locator.getProperty("Software_Management_for_IP_Office"));
    action.ClickLink(locator.getProperty("UCM_and_IPO_Application_Server"));
    action.WaitForTitle(locator.getProperty("Software_Management_for_UCM_and_IP_Office_Application_Server"));
    action.VerifyTitle(locator.getProperty("Software_Management_for_UCM_and_IP_Office_Application_Server"));
    action.SwithchFrame("iframe0");
  //Verify Get Inventory and Analyze button are are enabled
    action.VerifyEnableButton(locator.getProperty("IP.Inventorylist"));
    action.VerifyEnableButton(locator.getProperty("IP.Analyzelist"));
    //verify download,upgrade,status buttons are disabled
    action.VerifyDisableButton(locator.getProperty("IP.Download"));
    action.VerifyDisableButton(locator.getProperty("IP.Upgrade"));
    action.VerifyDisableButton(locator.getProperty("IP.Status"));
}
@Test(description="Verify the title and elements of IP_Office page")
public void VerifyCMpageElements() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
    action.ClickLink(locator.getProperty("Manage_Software"));
    action.WaitForTitle(locator.getProperty("Manage_Software"));
    action.ClickLink(locator.getProperty("Communication_Manager"));
    action.WaitForTitle(locator.getProperty("Software_Management_for_Communication_Manager"));
    action.VerifyTitle(locator.getProperty("Software_Management_for_Communication_Manager"));
    action.SwithchFrame("iframe0");
    //Verify Get Inventory  button are are enabled
    action.VerifyEnableButton(locator.getProperty("IP.Inventorylist"));
    //Verify buttons are disabled
    action.VerifyDisableButton(locator.getProperty("CM.analyze"));
    action.VerifyDisableButton(locator.getProperty("CM.Download"));
    action.VerifyDisableButton(locator.getProperty("CM.Upgrade"));
    action.VerifyDisableButton(locator.getProperty("CM.Update"));
    action.VerifyDisableButton(locator.getProperty("CM.Morebtn"));
  //verify Select column button enabled
    action.VerifyEnableButton(locator.getProperty("CM.SelectClmbtn"));
    
    //Click on Gateway tab
    action.ClickLinkByxpath(locator.getProperty("CM.Gatewaytab"));
    Thread.sleep(2000);
    //Verify buttons are disabled
    action.VerifyDisableButton(locator.getProperty("CM.analyzeGW"));
    action.VerifyDisableButton(locator.getProperty("CM.DownloadGW"));
    action.VerifyDisableButton(locator.getProperty("CM.UpgradeGW"));
    action.VerifyDisableButton(locator.getProperty("CM.MorebtnGW"));
    //verify Select column button enabled
    action.VerifyEnableButton(locator.getProperty("CM.SelectClmbtnGW"));
    
    //Click on TN Board tab
    action.ClickLinkByxpath(locator.getProperty("CM.TNTab"));
    Thread.sleep(2000);
    //Verify buttons are disabled
    action.VerifyDisableButton(locator.getProperty("CM.analyzeTN"));
    action.VerifyDisableButton(locator.getProperty("CM.DownloadTN"));
    action.VerifyDisableButton(locator.getProperty("CM.UpgradeTN"));
  //verify Select column button enabled
    action.VerifyEnableButton(locator.getProperty("CM.SelectClmbtnTN"));
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
