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


public class Tenant_Validations {
	boolean b=false,match=false;
	com.avaya.smgr.Tenant1.setup.TenantSetUp setup= null;
	UserAction action =null;
	Properties locator=null;
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

@Test(description="Verify the Elements of the page")
public void Tenantpage() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Verify buttons are enabled
	action.VerifyEnableButton(locator.getProperty("New_Tenant"));
	action.VerifyEnableButton(locator.getProperty("Users.Refreshtenant"));
}

@Test(description="Verify the Error message on Tenant name empty")
public void VerifyErrormsg() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.ClickButton(locator.getProperty("New_Tenant"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Verify Buttons Visibility
	action.VerifyDisableButton(locator.getProperty("TenantCommit"));
	action.VerifyEnableButton(locator.getProperty("tenantcancel"));
	action.VerifyDisableButton(locator.getProperty("tenant.add"));
	action.VerifyDisableButton(locator.getProperty("tenantcommitbtm"));
	action.VerifyEnableButton(locator.getProperty("tenantcancelbtm"));
	//Click on Update Hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	//Verify following error message
	action.VerifyStringValue("Tenant name can not be empty");
	
}

@Test(description="Verify the Cancel button while adding site to tenant") 
public void VerifyCancelTenant() throws Exception
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
	action.entertext(locator.getProperty("Tenantname"),input.getProperty("UpdateTname"));
	action.ClearText(locator.getProperty("Maxusers"));
	action.entertext(locator.getProperty("Maxusers"),input.getProperty("number"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	//Click on Tenant
	action.ClickLink(input.getProperty("UpdateTname"));
	Thread.sleep(1000);
	//Click on Add button
	action.ClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("UpdateTname"));
	//Click on Cancel button
	action.ClickButton(locator.getProperty("Tenantcancelop"));
	Thread.sleep(2000);
	action.VerifyStringValue("Add operation is cancelled.");
	
}

@Test(description="Verify that error message when last name is empty",priority=1)
public void TadminLastName() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	Thread.sleep(1000);
	//Fill the required fields
	//action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
	//Thread.sleep(1000);
    action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	action.VerifyStringValue("Last Name should not be blank");
	Thread.sleep(2000);

}

@Test(description="Verify that error message when First name is empty",priority=2)
public void TadminFirstName() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	Thread.sleep(1000);
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
	Thread.sleep(1000);
   // action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	action.VerifyStringValue("First Name should not be blank");
	Thread.sleep(2000);

}

@Test(description="Verify that error message when Login name is empty",priority=7)
public void TadminLogin() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link

	action.RefreshPage();
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	Thread.sleep(1000);
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
	Thread.sleep(1000);
   action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	//action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	action.VerifyStringValue("Administrator Login is invalid");
	Thread.sleep(2000);

}
@Test(description="Verify that error message when Password is empty",priority=4)
public void TadminPassword() throws Exception
{

	action.RefreshPage();
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	Thread.sleep(1000);
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
	Thread.sleep(1000);
   action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenant.Login"));
	//action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Password"));
	//action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.VerifyStringValue("Password should be atleast 7 characters long");
	Thread.sleep(2000);

}
@Test(description="Verify that error message when Password and confirm password do not match",priority=6)
public void TadminPasswordmismatch() throws Exception
{
	
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.SelectElement(locator.getProperty("Tenant.Total"),input.getProperty("TName"));
	//action.ClickLink(locator.getProperty("Administrators"));
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	//Click on the Administrators button
	action.ClickButton(locator.getProperty("Tent.AdminLink"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	Thread.sleep(1000);
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("LName"));
	Thread.sleep(1000);
   action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("FName"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("User.Loginname"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("User.Loginname"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("NewPassword"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.VerifyStringValue("Password and confirm password do not match.");
	Thread.sleep(2000);

}

@Test(description="verify that error message when site is empty in the organization hierarchy")
public void Siteempty() throws Exception
{	
	action.RefreshPage();
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
	//Click on the Edit button
	
	action.ClickButton(locator.getProperty("Membership"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level1"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level2"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level2"),input.getProperty("Dept1"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level3"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level3"),input.getProperty("team1"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	//verify that Tenant name is Edited successfully
	action.VerifyStringValue("Organization hierarchy Level 1 name can not be empty");
	Thread.sleep(1000);

}

@Test(description="verify that error message when Department is empty in the organization hierarchy")
public void Deptempty() throws Exception
{	
	action.RefreshPage();
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
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Membership"));
	Thread.sleep(1000);
	//Click on the Edit button
	action.ClearText(locator.getProperty("Tenant.Org.Level1"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level1"),input.getProperty("site1"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level2"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level3"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level3"),input.getProperty("team1"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	//verify that Tenant name is Edited successfully
	action.VerifyStringValue("Organization hierarchy Level 2 name can not be empty");
	Thread.sleep(1000);
}

@Test(description="verify that error message when Team is empty in the organization hierarchy",priority=5)
public void Teamempty() throws Exception
{	
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("New_Tenant"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));	action.entertext(locator.getProperty("Tenantname"),input.getProperty("TName"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Membership"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level1"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level1"),input.getProperty("site1"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level2"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Tenant.Org.Level2"),input.getProperty("Dept1"));
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Tenant.Org.Level3"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	action.VerifyStringValue("Organization hierarchy Level 3 name can not be empty");
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
