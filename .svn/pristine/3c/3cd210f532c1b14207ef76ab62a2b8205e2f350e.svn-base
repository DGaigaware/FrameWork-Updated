package com.avaya.smgr.Tenant8.users;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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


public class TenantOrgdropdown {
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

@Test(description="Verify that Tenant is displaying under Tenant dropdown ",priority=1)
public void Loginwithtenantadmin() throws Exception
{	
	action.RefreshPage();
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	Verifydropdownvalue(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
}
@Test(description="Verify that Site is displaying under Site dropdown ",priority=2)
public void TenantSiteDropdown() throws Exception
	{	
	action.RefreshPage();
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	Verifydropdownvalue(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(2000);
}
@Test(description="Verify that Dept is displaying under Dept dropdown ",priority=3,enabled=false)
public void TenantDeptDropdown() throws Exception
{	
	action.RefreshPage();
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(5000);
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(5000);
	Verifydropdownvalue(locator.getProperty("Users.deptlist"),input.getProperty("UpdateDept"));
	Thread.sleep(5000);
}
@Test(description="Verify that Team is displaying under Team dropdown ",priority=4)
public void TenantTeamDropdown() throws Exception
{	action.RefreshPage();
action.RefreshPage();
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
action.ClickLink(locator.getProperty("Manage_Users"));
Thread.sleep(1000);
action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(5000);
	action.SelectFromdropDown(locator.getProperty("Users.deptlist"),input.getProperty("UpdateDept"));
	Thread.sleep(5000);
	Verifydropdownvalue(locator.getProperty("Users.teamlist"),input.getProperty("UpdatTeam"));
	Thread.sleep(3000);
}


public void Verifydropdownvalue(String locator, String Expected) throws InterruptedException {
	boolean match=false;
	 WebElement dropdown = action.driver.findElement(By.xpath(locator));  
	 Select select = new Select(dropdown);  

	 List<WebElement> options = select.getOptions();  
	 for(int i = 0; i < options.size(); i++)
	 {
		 String actual=options.get(i).getText();
		 if(actual.equals(Expected)){
			 Assert.assertEquals(actual, Expected);
			 Thread.sleep(1000);
	    match=true;
	    break;
		 }
	 }
	 Assert.assertTrue(match);
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
