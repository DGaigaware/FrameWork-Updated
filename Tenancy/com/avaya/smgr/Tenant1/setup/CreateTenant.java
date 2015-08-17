package com.avaya.smgr.Tenant1.setup;

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


public class CreateTenant {
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


@Test(description="verify the Creation of tenant and Site",priority=1,groups={"Sanity"})
public void TenantCreation() throws Exception
{
	action.RefreshPage();
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
	//Click on update hierarchy button
	action.DoubleClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	//Click on Tenant
	action.ClickLink(input.getProperty("TName"));
	Thread.sleep(1000);
	//Click on Add button
	action.DoubleClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("site1"));
	//Click on update hierarchy button
	action.DoubleClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("TenantCommit"));
	action.DoubleClickButton(locator.getProperty("TenantCommit"));
	Thread.sleep(2000);				
	action.DoubleClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(1000);
	//Verify that Tenant is added successfully
	setup.VerifyAddedTenant(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(1000);
	//Select the Tenant
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//Verify the Site is added successfully
	setup.VerifyAddedSite(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	Thread.sleep(1000);
}

@Test(description="Verify the creation of tenant admin user",priority=2,groups={"Sanity"})
public void CreateTadminUser() throws Exception
{
	int flag=0;
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	Thread.sleep(500);
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.DoubleClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Tent.AdminLink"));
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Tenantadminuser"));

	//Click on the New button
	action.DoubleClickButton(locator.getProperty("Tenantadminuser"));

	action.WaitForObj(locator.getProperty("Commit"));
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
    action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Tenant.Login"));
	action.DoubleClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);	
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Commit"));
	Thread.sleep(2000);	
	//action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.DoubleClickButton(locator.getProperty("Tent.AdminLink"));
	Thread.sleep(2000);	
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='adminTable_core_table_content']/tbody/tr"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='adminTable_core_table_content']/tbody/tr["+i+"]/td[4]")).getText();
    	b=sValue.contains(input.getProperty("Tenant.Login"));
    	if(b){
    		Assert.assertTrue(b);
			Thread.sleep(1000);
			flag=1;
			break;

    	}
	}
	if(flag==0){
		Assert.assertTrue(b);
	}
}


@Test(description="Verify the Add Department to the tenant ",priority=3,groups={"Sanity"})
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
	//setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("site1"));
	
	//Click on Site link
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Tenantadd"));

	//Click on Add button
	action.DoubleClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	//Add department to tenant
	action.entertext(locator.getProperty("deptname"),input.getProperty("Dept1"));
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(1000);
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	//verify that Dept created successfully
	setup.VerifyDepartment(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"));
	Thread.sleep(1000);
}




@Test(description="Verify the Add Team to the tenant ",priority=4,groups={"Sanity"})
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
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	setup.SelectDept(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"));
	
	//Add Team to tenant
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.entertext(locator.getProperty("teamname"),input.getProperty("team1"));
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	action.WaitForObj(locator.getProperty("Tenant.Total"));
	
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	setup.SelectSiteTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"));
	setup.SelectDeptTree(action,locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"));
	setup.VerifyTeam(action, locator.getProperty("Tenant.Total"),input.getProperty("TName"),input.getProperty("site1"),input.getProperty("Dept1"),input.getProperty("team1"));
	Thread.sleep(2000);

}

@Test(description="Verify that Login by Tenant admin user successfully",priority=5,groups={"Sanity"})
public void TenantLogin() throws Exception{
	action.RefreshPage();
	action.driver.switchTo().defaultContent();
	action.ClickButton(locator.getProperty("LogOff"));
	//Navigate to SMGR,Groups & Roles,Roles
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Tenant.Login"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.WaitForObj(locator.getProperty("UserId"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("Password.change"));

action.WaitForTitle(locator.getProperty("Dashboard"));

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
