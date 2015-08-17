package com.avaya.smgr.Tenant3.edit;

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



public class EditTenant{
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

@Test(description="verify that Tenant is Edit successfully",priority=1,groups={"Sanity"})
public void EditTenant1() throws Exception
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
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("TenantName.Edit"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("TenantName.Edit"),input.getProperty("UpdateTname"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("TenantName.View"),input.getProperty("UpdateTname"));
	Thread.sleep(1000);
	
}
@Test(description="verify that Site is Edit successfully",priority=2)
public void EditSite() throws Exception
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
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("site1"));
	Thread.sleep(2000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Done"));
	action.WaitForObj(locator.getProperty("SiteName.Edit"));
	action.ClearText(locator.getProperty("SiteName.Edit"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("SiteName.Edit"),input.getProperty("UpdateSite"));
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(2000);
	action.VerifyElementValue(locator.getProperty("SiteName.View"),input.getProperty("UpdateSite"));
	//verify that Tenant is Edit successfully
	Thread.sleep(1000);
	
}
@Test(description="verify that Dept is Edit successfully",priority=3)
public void EditDept() throws Exception
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
	setup.SelectDept(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"),input.getProperty("Dept1"));
	Thread.sleep(1000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Done"));
	action.WaitForObj(locator.getProperty("Dept.Edit"));	
	action.ClearText(locator.getProperty("Dept.Edit"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Dept.Edit"),input.getProperty("UpdateDept"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Dept.View"),input.getProperty("UpdateDept"));
	Thread.sleep(1000);
	
}
@Test(description="verify that Team is Edit successfully",priority=4)
public void EditTeam() throws Exception
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
	setup.SelectTeam(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"),input.getProperty("UpdateDept"),input.getProperty("team1"));
	Thread.sleep(1000);
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForObj(locator.getProperty("Team.Edit"));	
	action.ClearText(locator.getProperty("Team.Edit"));
	action.entertext(locator.getProperty("Team.Edit"),input.getProperty("UpdatTeam"));
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(2000);
	action.VerifyElementValue(locator.getProperty("Team.View"),input.getProperty("UpdatTeam"));
	Thread.sleep(1000);

}

@Test(description="verify that update organization hierarchy ",priority=5)
public void EditOrgHierarchy() throws Exception
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
	action.ClickButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Membership"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level1"));
	action.entertext(locator.getProperty("Tenant.Org.Level1"),input.getProperty("site1"));
	action.ClearText(locator.getProperty("Tenant.Org.Level2"));
	action.entertext(locator.getProperty("Tenant.Org.Level2"),input.getProperty("Dept1"));
	action.ClearText(locator.getProperty("Tenant.Org.Level3"));
	action.entertext(locator.getProperty("Tenant.Org.Level3"),input.getProperty("team1"));
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(1000);
	setup.SelectTenant(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	Thread.sleep(1000);
	//Click on the organization hierarchy
	action.ClickButton(locator.getProperty("Membership"));
	Thread.sleep(1000);
	//verify that Tenant name is Edited successfully
	action.VerifyElementValue(locator.getProperty("Tenant.Org.Level1"),input.getProperty("site1"));
	action.VerifyElementValue(locator.getProperty("Tenant.Org.Level2"),input.getProperty("Dept1"));
	action.VerifyElementValue(locator.getProperty("Tenant.Org.Level3"),input.getProperty("team1"));
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
