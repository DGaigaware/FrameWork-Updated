package com.avaya.smgr.sdm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Upgraderelease {
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

@Test(description="Verify the title and elements of Solution_Deployment_Manager page")
public void VerifypageElements() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.VerifyTitle(locator.getProperty("Solution_Deployment_Manager"));
	//verify Elements
	action.SwithchFrame("iframe0");
	action.VerifyElementValue(locator.getProperty("VMHeader"), locator.getProperty("VMs"));
	action.VerifyElementValue(locator.getProperty("VMUpgrade"), locator.getProperty("Upgrades"));
	action.VerifyElementValue(locator.getProperty("DownloadHeader"), locator.getProperty("Downloads"));
	action.VerifyElementValue(locator.getProperty("Softwarelibrary"),locator.getProperty("Software_Libraries"));
	action.VerifyElementValue(locator.getProperty("Settingheader"), locator.getProperty("Settings"));
}


@Test(description="Verify the title and elements of Upgrade_Release_Setting page")
public void VerifypageTitle() throws Exception
{
	action.RefreshPage();
	//navigate to Solution Deployment Manager,Upgrade Release Setting
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Upgrade_Release_Setting"));
	action.WaitForTitle(locator.getProperty("Upgrade_Release_Setting"));
	action.VerifyTitle(locator.getProperty("Upgrade_Release_Setting"));
	action.SwithchFrame("iframe0");
	action.VerifyStringValue("Upgrade Release Selection");
	//Verify Buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Upgrade.Commit"));
	action.VerifyEnableButton(locator.getProperty("Upgrade.Cancel"));
	//verify the options in the listbox
	WebElement selectElement =action.driver.findElement(By.xpath(locator.getProperty("Upgrade.version")));
	Select select = new Select(selectElement);
	List<WebElement> options=select.getOptions();
	int n=options.size();
	String[] exp ={locator.getProperty("SMGRVersion_7.0"),locator.getProperty("SMGRVersion_3.8")};
	for(int i=0;i<n;i++)
	{
		Assert.assertTrue(options.get(i).getText().equals(exp[i]));
	}
}
@Test(description="Verify the SMGR version 3.8selected successfully")
public void SelectVersionThree() throws Exception
	{
	action.RefreshPage();
	//navigate to Solution Deployment Manager,Upgrade Release Setting
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Upgrade_Release_Setting"));
	action.WaitForTitle(locator.getProperty("Upgrade_Release_Setting"));
	action.VerifyTitle(locator.getProperty("Upgrade_Release_Setting"));
	
	action.SwithchFrame("iframe0");
	//Get the Current version		
	String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Upgrade.version")))).getFirstSelectedOption().getText();
	//Set the new version
	if(str.equals(locator.getProperty("SMGRVersion_3.8")))
	 {
	action.SelectFromdropDown(locator.getProperty("Upgrade.version"), locator.getProperty("SMGRVersion_7.0"));
	 }
	else
	 {
	action.SelectFromdropDown(locator.getProperty("Upgrade.version"), locator.getProperty("SMGRVersion_3.8"));
	 }
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Upgrade.Commit"));
	action.WaitForTitle(locator.getProperty("Upgrade_Release_Setting"));
	//Verify text message and click on Ok button
	action.driver.switchTo().activeElement();
	action.VerifyStringValue("Upgrade Release Changed Successfuly. Click on Upgrade Management page for the changes.");
	Thread.sleep(1000);
        
	}	
@Test(description="Verify the SMGR version 7.0 selected successfully")
public void SelectVersionSeven() throws Exception
	{
	action.RefreshPage();
	//navigate to Solution Deployment Manager,Upgrade Release Setting
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Upgrade_Release_Setting"));
	action.WaitForTitle(locator.getProperty("Upgrade_Release_Setting"));
	action.VerifyTitle(locator.getProperty("Upgrade_Release_Setting"));
	
	action.SwithchFrame("iframe0");
	//Get the Current version		
	String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Upgrade.version")))).getFirstSelectedOption().getText();
	//Set the new version
	if(str.equals(locator.getProperty("SMGRVersion_7.0")))
	 {
	action.SelectFromdropDown(locator.getProperty("Upgrade.version"), locator.getProperty("SMGRVersion_3.8"));
	 }
	else
	 {
	action.SelectFromdropDown(locator.getProperty("Upgrade.version"), locator.getProperty("SMGRVersion_7.0"));
	 }
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Upgrade.Commit"));
	action.WaitForTitle(locator.getProperty("Upgrade_Release_Setting"));
	//Verify text message and click on Ok button
	action.driver.switchTo().activeElement();
	action.VerifyStringValue("Upgrade Release Changed Successfuly. Click on Upgrade Management page for the changes.");
        
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
