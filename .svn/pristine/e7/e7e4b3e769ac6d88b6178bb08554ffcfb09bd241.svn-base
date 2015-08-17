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


public class DeleteTSDT {
	
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

@Test(priority=1)
public void createTenant() throws Exception
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
	action.entertext(locator.getProperty("Tenantname"),input.getProperty("TName2"));
	action.ClearText(locator.getProperty("Maxusers"));
	action.entertext(locator.getProperty("Maxusers"),input.getProperty("number"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	//Click on Tenant
	action.ClickLink(input.getProperty("TName2"));
	Thread.sleep(1000);
	//Click on Add button
	action.ClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("Sit1"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);		
	action.ClickLink(input.getProperty("TName2"));
	Thread.sleep(1000);
	//Click on Add button
	action.ClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("Sit2"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	action.ClickLink(input.getProperty("Sit2"));
	Thread.sleep(5000);
	//Click on Add button
	action.ClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fillup department details and click update hierarchy button
	action.entertext(locator.getProperty("deptname"),input.getProperty("Dept"));
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	//Click on Department and Add button
	action.ClickLink(input.getProperty("Dept"));
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fillup the team details
	action.entertext(locator.getProperty("teamname"),input.getProperty("Team"));
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("TenantCommit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
}
	
@Test(priority=2)
public void DeleteTeam() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Team"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	Thread.sleep(2000);
	action.ClickLink(input.getProperty("Team"));
	Thread.sleep(1000);
	//Click on Delete button
	action.ClickButton(locator.getProperty("Cancel"));
	action.WaitForTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
    action.ClickButton(locator.getProperty("Users.Refreshtenant"));
    Thread.sleep(1000);
    action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Team"));
 	//Click on Search button
 	action.ClickButton(locator.getProperty("Tsearchbtn"));
 	//verify the following message
 	action.VerifyStringValue("No Data Found ...");
}

@Test(priority=3)
public void DeleteDepartment() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Dept"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	Thread.sleep(2000);
	action.ClickLink(input.getProperty("Dept"));
	Thread.sleep(1000);
	//Click on Delete button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
    action.ClickButton(locator.getProperty("Users.Refreshtenant"));
    Thread.sleep(1000);
    action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Dept"));
 	//Click on Search button
 	action.ClickButton(locator.getProperty("Tsearchbtn"));
 	//verify the following message
 	action.VerifyStringValue("No Data Found ...");
}

@Test(priority=4)
public void DeleteSite() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Sit2"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	Thread.sleep(2000);
	action.ClickLink(input.getProperty("Sit2"));
	Thread.sleep(1000);
	//Click on Delete button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
    action.ClickButton(locator.getProperty("Users.Refreshtenant"));
    Thread.sleep(1000);
    action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Sit2"));
 	//Click on Search button
 	action.ClickButton(locator.getProperty("Tsearchbtn"));
 	//verify the following message
 	action.VerifyStringValue("No Data Found ...");
}

@Test(priority=5)
public void VerifyErrorMsg() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("Sit1"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	Thread.sleep(2000);
	action.ClickLink(input.getProperty("Sit1"));
	Thread.sleep(1000);
	//Click on Delete button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	Thread.sleep(1000);
	action.VerifyStringValue("Organization Unit can not be deleted. Tenant must contain at least one Level 1 Organization Unit.");
}

@Test(priority=6)
public void Deletetenant() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickLink(input.getProperty("TName2"));
	//Click on Delete button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Organization_Unit_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	 Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Refreshtenant"));
    Thread.sleep(1000);
    action.entertext(locator.getProperty("Searchtenant"), input.getProperty("TName2"));
 	//Click on Search button
 	action.ClickButton(locator.getProperty("Tsearchbtn"));
 	//verify the following message
 	action.VerifyStringValue("No Data Found ...");
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
