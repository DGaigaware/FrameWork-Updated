package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class EditTenantuser {
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

@Test(description="Verify the tenant admin user added to the table",priority=1)
public void VerifyTenantuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select the tenant
	action.SelectCheckBox(locator.getProperty("Tenantchk11"));
	//Click on Refresh button
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(1000);
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("Tenantlogin1"));
}


@Test(description="Verify the Edit tenant user operation of tenant",priority=2)
public void EditTuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select tenant 
	action.SelectCheckBox(locator.getProperty("Tenantchk1"));
	//Click on refresh button
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(2000);
	//Select user from table
	action.SelectElementByLoginname(input.getProperty("Tenantlogin1"));
	Thread.sleep(1000);
	//Click on Edit button
	action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Identity tab
	action.ClickLinkByxpath(locator.getProperty("Identity"));
	Thread.sleep(1000);
	//verify the tenant list is enabled
    boolean b=action.driver.findElement(By.xpath(locator.getProperty("Users.tenantlist"))).isEnabled();
    Assert.assertFalse(b);
    //Edit the Time
    action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
    action.ClickButton(locator.getProperty("Users.Commit"));
    action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
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
