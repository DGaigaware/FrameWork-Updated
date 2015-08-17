package com.avaya.smgr.Tenant5.delete;

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



public class TenantDelete{
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	com.avaya.smgr.Tenant1.setup.TenantSetUp setup= null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	String Site="html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div[2]/div[2]/div[2]/ul/li[2]/ul/li/a";
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

@Test(description="verify that Tenant is Deleted successfully",priority=4)
public void DeleteTenant() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant
	setup.SelectTenant(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	Thread.sleep(1000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForObj(locator.getProperty("Users.Deletecnf"));	
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	Thread.sleep(1000);
	//action.VerifydropDownValues(objID, expected2);(locator.getProperty("TenantName.View"),input.getProperty("UpdateTname"));
	//Thread.sleep(1000);
}

@Test(description="verify that Site is Deleted successfully",priority=3)
public void DeleteSite() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"));
	Thread.sleep(2000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	action.WaitForObj(locator.getProperty("Users.Deletecnf"));	
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	Thread.sleep(1000);
	
}

@Test(description="verify that Dept is Deleted successfully",priority=2)
public void DeleteDept() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	Thread.sleep(1000);

	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"));
	Thread.sleep(1000);

	setup.SelectDept(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"),input.getProperty("UpdateDept"));
	Thread.sleep(1000);
	///Click on the Edit button
	action.ClickButton(locator.getProperty("Users.Cancel"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	Thread.sleep(1000);
	//action.VerifyNoElementdisplay(locator.getProperty("Dept.View"));
	Thread.sleep(1000);


}

@Test(description="verify that Team is Deleted successfully",priority=1)
public void DeleteTeam() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Select Tenant,Site
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"));
	setup.SelectDeptTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"),input.getProperty("UpdateDept"));
	setup.SelectTeam(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"),input.getProperty("UpdateDept"),input.getProperty("UpdatTeam"));
	Thread.sleep(1000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
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
