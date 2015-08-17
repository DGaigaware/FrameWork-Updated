package com.avaya.smgr.RTS.ManageElements;
/*
 * Manage Elements page UI 
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class ManageElements_Validations{
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }
@BeforeMethod(alwaysRun=true)
public void Beforemethodsetup() throws IOException, InterruptedException{
}
@Test(description="Verification of error message where Name is empty")
public void ManageElmtsNameEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	//Select the Communication Manager from drop down
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	//Click on the Commit button
	action.ClickButton(locator.getProperty("RTS.Commit"));
	//Verification of error message where Name is empty
	action.VerifyStringValue("You must enter a value");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verification of error message where IP address is empty")
public void ManageElmtsIPEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.VerifyTitle(locator.getProperty("Manage_Elements"));
	action.ClearText(locator.getProperty("Manage.Name"));
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("Name"));
	action.ClickButton(locator.getProperty("RTS.Commit"));
	//Verification of error message where IP address is empty
	action.VerifyStringValue("You must enter a value");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verification of error message where Login is empty")
public void ManageElmtsLoginEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.ClearText(locator.getProperty("Manage.Name"));
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Manage.Ip"), input.getProperty("Ip"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("RTS.Commit"));
	//Verification of error message where Login is empty
	action.VerifyStringValue("You must enter a value");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verification of error message where Password is empty")
public void ManageElmtsPasswordEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the new button
	action.ClickButton(locator.getProperty("RTS.New"));
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.VerifyTitle("Manage Elements");
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Manage.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Manage.login"), input.getProperty("Uname"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("RTS.Commit"));
	Thread.sleep(2000);
	//Verification of error message where Password is empty
	action.VerifyStringValue("Password is required.");
	Thread.sleep(1000);
	//action.WaitForTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verification of error message where Password is mismatch")
public void ManageElmtsPasswordMismatch() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the new button
	action.ClickButton(locator.getProperty("RTS.New"));
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.VerifyTitle("Manage Elements");
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Manage.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Manage.login"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Manage.Password"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Manage.CnfPassword"), input.getProperty("Uname"));
	//Verification of error message where Password is mismatch
	action.VerifyStringValue("You must enter a value");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}


@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}



  @AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
 }

