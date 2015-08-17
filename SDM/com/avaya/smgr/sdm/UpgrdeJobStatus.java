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


public class UpgrdeJobStatus {
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
	action.ClickLink(locator.getProperty("Upgrade_Jobs_Status"));
	//verify Elements
	action.SwithchFrame("iframe0");
	String[] Expected={"Select Job Type","Refresh Element(s)","Analyze","Pre-Upgrade Check"};
	
	Thread.sleep(1000);
	action.VerifydropDownValues(locator.getProperty("Upgrade_Jobs_Dropdown"),Expected);
	Thread.sleep(1000);
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
