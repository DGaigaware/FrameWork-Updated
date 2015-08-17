package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class DeleteTenant {
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


@Test(description="Verify the Error message on Deletion of Tenant if it associate with some users",priority=1)
public void VerifyErrormsgonDelete() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on Tenant
	action.ClickLink(input.getProperty("TName"));
	//Click on Delete button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(1000);
	//Verify following error message
	action.VerifyStringValue("Selected Organization Unit can not be deleted because some users are associated with it.");
		
	}

@Test(description="Verify the Creation of new tenant user",priority=2)
public void CreateTenantuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	//Select the tenant
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"), input.getProperty("TName"));
	Thread.sleep(2000);
	//Select the Site from list
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"), input.getProperty("Site2"));
	Thread.sleep(2000);
	//Select the Department from list
	action.SelectFromdropDown(locator.getProperty("Users.deptlist"), input.getProperty("Dept1"));
	Thread.sleep(2000);
	//Select the Team from the list
	action.SelectFromdropDown(locator.getProperty("Users.teamlist"), input.getProperty("team1"));
	Thread.sleep(2000);
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname6"),Keys.TAB);
	Thread.sleep(2000);
	//Click on the Commit Button
	action.ClickButton(locator.getProperty("Commit"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	//Click on Commit button
	action.ClickButton(locator.getProperty("Users.Commit"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));

}

@Test(description="Verify the Deletion of tenant user",priority=3)
public void DeleteTenantuser() throws Exception
{
	action.driver.navigate().refresh();
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select tenant
	action.SelectCheckBox(locator.getProperty("Tenantchk1"));
	//Click on refresh button of table
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(2000);
	//Select User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginname6"));
	Thread.sleep(1000);
	//Click on delete button
	action.ClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Delete_Confirmation"));
	//Click on Delete button for confirmation
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("ClearFilter"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Disablefilter"));
	Thread.sleep(1000);
	action.Verify_Delete_Fifthcolumn(input.getProperty("UsersLoginname6"));
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
