package com.avaya.smgr.Tenant2.add;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class AddTenant {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	com.avaya.smgr.Tenant1.setup.TenantSetUp setup= null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	setup = new com.avaya.smgr.Tenant1.setup.TenantSetUp();
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }


@Test(description="verify that site is added successfully",priority=1,groups={"Sanity"})
public void Addsite() throws Exception
{
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	
	//Click on Add button
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	action.ClickButton(locator.getProperty("Tenantadd"));
	Thread.sleep(2000);

	
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("site2"));
	Thread.sleep(2000);

	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Tenantadd"));
	Thread.sleep(2000);
	//action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));

	Thread.sleep(2000);
//Verify that Tenant is added successfully
	//Select the Tenant
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(1000);
	//Verify the Site is added successfully
	setup.VerifyAddedSite(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"));
	Thread.sleep(1000);
}

@Test(description="Verify that the Add Department to the Site",priority=2)
public void AddDepartment() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select on tenant
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(1000);
	//setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("site2"));
	
	//Click on Site link
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"));
	//Click on Add button
	action.ClickButton(locator.getProperty("Tenantadd"));
	Thread.sleep(2000);

	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	//Add department to tenant
	action.entertext(locator.getProperty("deptname"),input.getProperty("Dept2"));
	action.ClickButton(locator.getProperty("Tenantadd"));
	Thread.sleep(2000);
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(1000);
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"));
	Thread.sleep(1000);
	//setup.SelectDept(action,locator.getProperty("Tenant.Total"),input.getProperty("Dept1"));
	Thread.sleep(1000);
	//verify that Dept created successfully
	setup.VerifyDepartment(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"),input.getProperty("Dept2"));
	Thread.sleep(1000);
}




@Test(description="Verify that the Add  Team to the Department ",priority=3)
public void AddTeam() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	
	//click on tree buttons
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(1000);
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"));
	Thread.sleep(1000);
	setup.SelectDept(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"),input.getProperty("Dept2"));
	Thread.sleep(1000);
	
	//Add Team to tenant
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.entertext(locator.getProperty("teamname"),input.getProperty("team2"));
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"));
	setup.SelectDeptTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"),input.getProperty("Dept2"));
	Thread.sleep(1000);
	setup.VerifyTeam(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site2"),input.getProperty("Dept2"),input.getProperty("team2"));
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
