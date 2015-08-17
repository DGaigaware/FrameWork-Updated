package com.avaya.smgr.upr.basic;

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

import com.avaya.smgr.Tsetup.Tenantsetup;

import utility.UserAction;


public class ViewUPR {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Tenantsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator=new Properties();
	
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the View Button")
public void View_UPR() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
action.SwithchFrame("iframe0");
//Select one UPR from table
setup.SelectUPR(action,input.getProperty("uprcmsm2"));
WebDriverWait wait=new WebDriverWait(action.driver,60);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.View"))));
//click on view button and verify the title
action.DoubleClickButton(locator.getProperty("Users.View"));
action.WaitForTitle(locator.getProperty("View_User_Provisioning_Rule"));
//Verify the uprname field is  disabled
action.VerifyDisableTextbox(locator.getProperty("Uprname"));
action.VerifyEnableButton(locator.getProperty("Edit"));
action.VerifyEnableButton(locator.getProperty("Done"));
//Click on Communication profile and verify the text
//action.VerifyReadOnlyDisplayed(locator.getProperty("Uprname"));
//action.ClickLink(locator.getProperty("Communication_Profile"));
//action.VerifyStringValue("Session Manager Profile");
//Click on Done button and verify the title
action.ClickButton(locator.getProperty("Done"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
 
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
