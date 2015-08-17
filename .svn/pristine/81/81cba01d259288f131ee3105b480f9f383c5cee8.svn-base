package com.avaya.smgr.Tenant1.setup;

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



public class ViewTenant{
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

@Test(description="verify that Tenant is view successfully")
public void ViewTenant1() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant
	setup.SelectTenant(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(1000);
	//verify that Tenant is view successfully
	action.VerifyElementValue(locator.getProperty("TenantName.View"),input.getProperty("TName"));
	Thread.sleep(1000);
}
@Test(description="verify that Site is view successfully")
public void ViewSite() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	Thread.sleep(1000);
	//verify that Tenant is view successfully
	action.VerifyElementValue(locator.getProperty("SiteName.View"),input.getProperty("site1"));
	Thread.sleep(1000);
}
@Test(description="verify that Dept is view successfully")
public void ViewDept() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	setup.SelectDept(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"));
	Thread.sleep(1000);
	//verify that Tenant is view successfully
	action.VerifyElementValue(locator.getProperty("Dept.View"),input.getProperty("Dept1"));
	Thread.sleep(1000);
}
@Test(description="verify that Team is view successfully")
public void ViewTeam() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	setup.SelectDeptTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"));
	setup.SelectTeam(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"),input.getProperty("team1"));
	Thread.sleep(1000);
	//verify that Tenant is view successfully
	action.VerifyElementValue(locator.getProperty("Team.View"),input.getProperty("team1"));
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
