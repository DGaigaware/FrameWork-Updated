package com.avaya.smgr.Tenant;

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


public class ViewTenantuser {
	boolean b=false,match=false;;
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

@Test(description="Verify the View operation of tenantuser")
public void ViewTuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select the tenant
	action.SelectCheckBox(locator.getProperty("Tenantchk1"));
	//Click on refresh button
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(2000);
	//Select the user from table
	action.SelectElementByLoginname(input.getProperty("Tenantlogin1"));
	Thread.sleep(1000);
	//Click on view button
	action.ClickButton(locator.getProperty("Users.View"));
	action.WaitForTitle(locator.getProperty("User_Profile_View"));
	action.VerifyTitle(locator.getProperty("User_Profile_View"));
	Thread.sleep(1000);
	//Click on Identity tab
	action.ClickLinkByxpath(locator.getProperty("Identity"));
	Thread.sleep(1000);
	//Verify that the user is viewed successfully
	action.VerifyReadOnlyDisplayed(locator.getProperty("Lastname"));
	action.VerifyReadOnlyDisplayed(locator.getProperty("Firstname"));
	//Verify the buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));	
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
