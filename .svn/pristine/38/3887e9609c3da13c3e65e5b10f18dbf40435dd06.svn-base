package com.avaya.smgr.upm.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Importglobal {
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

@Test(description="Verify the Import global setting page elements",priority=1)
public void Verify_Page() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	//Click on More button
	Thread.sleep(500);
	action.ClickButton(locator.getProperty("Users.More"));
	Thread.sleep(1000);
	//Click on Import global settings
	action.ClickLink(locator.getProperty("Importglobalsettings"));
	action.WaitForTitle(locator.getProperty("Import_global_settings"));
	action.VerifyTitle(locator.getProperty("Import_global_settings"));
	WebDriverWait wait=new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("import.done"))));
	//Verify buttons are enabled
	action.VerifyEnableButton(locator.getProperty("import.done"));
	action.VerifyEnableButton(locator.getProperty("ImportBtn"));
	action.VerifyEnableButton(locator.getProperty("Imp.Browse"));
	action.VerifyDisableButton(locator.getProperty("Import.Cancel"));
	action.VerifyDisableButton(locator.getProperty("ViewJob"));
	action.VerifyDisableButton(locator.getProperty("DeleteJob"));
	
	
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
