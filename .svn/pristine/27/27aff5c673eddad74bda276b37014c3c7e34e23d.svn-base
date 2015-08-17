package com.avaya.smgr.upr.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.smgr.Tsetup.Tenantsetup;

import utility.UserAction;


public class SMUPR {
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
	locator=new Properties();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the error messages on required fields empty",priority=1)
public void Verify_Error_Msg() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	//Fill up the required fields
	action.ClearText(locator.getProperty("Uprname"));
	action.entertext(locator.getProperty("Uprname"), input.getProperty("uprsm"));
	//Click on Communication Profile
	action.ClickLink(locator.getProperty("Communication_Profile"));
	//Select SM Check box and fill up required fields
	action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
	Thread.sleep(1000);
	action.SelectFromdropDown(locator.getProperty("Upr.smlist"),input.getProperty("SM_Pune"));
	Thread.sleep(1500);
	action.DoubleClickButton(locator.getProperty("Commit"));
	Thread.sleep(1500);
	action.VerifyStringValue("A Home Location selection is required.");
	action.VerifyStringValue("Communication Profile Password is required for Session Manager Communication Profile");
	action.VerifyStringValue("SIP Domain is required for Session Manager Communication Profile");
	
	
}

@Test(description="Verify the UPR creation with SM Elements",priority=2,groups={"Sanity"})
public void UPR_with_SM() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	//Fill up the required fields
	action.ClearText(locator.getProperty("Uprname"));
	action.entertext(locator.getProperty("Uprname"), input.getProperty("uprsm"));
	action.SelectFromdropDown(locator.getProperty("Upr.SIPdomain"), input.getProperty("Sipdomain"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Upr.comprofilepass"), input.getProperty("Compassword"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Upr.confirmpass"), input.getProperty("Compassword"));
	action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
	action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
	//Click on Communication Profile
	action.ClickLink(locator.getProperty("Communication_Profile"));
	//Select SM Check box and fill up required fields
	action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
	action.SelectFromdropDown(locator.getProperty("Upr.smlist"),input.getProperty("SM_Pune"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Upr.smlisthl"),input.getProperty("Pune"));
	Thread.sleep(1500);
	//Click on Commit Button and verify title
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	Thread.sleep(1000);
	setup.VerifyUprname(action,input.getProperty("uprsm"));
	Thread.sleep(1000);
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
