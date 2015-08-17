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


public class VerifyTMaxusers {
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


@Test(description="Verify the Error message on max number of users less than 10")
public void Verifymaxusers() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on New Tenant button
	action.ClickButton(locator.getProperty("New_Tenant"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up the required fields
	action.entertext(locator.getProperty("Tenantname"),input.getProperty("TName"));
	action.ClearText(locator.getProperty("Maxusers"));
	action.entertext(locator.getProperty("Maxusers"),input.getProperty("number1"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	action.VerifyStringValue("Allowed range for Maximum no. of users is minimum 10 to maximum 250000.");
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
