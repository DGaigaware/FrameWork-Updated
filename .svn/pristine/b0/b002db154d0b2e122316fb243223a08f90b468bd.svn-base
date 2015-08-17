package com.avaya.smgr.upr.tenancy;


import com.avaya.smgr.Tsetup.Tenantsetup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignCM {
	boolean b;
	UserAction action =null;
	Tenantsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}


@Test(description="Verify the creation of New Tenant",priority=1)
public void Create_Tenant() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on new tenant button
	action.DoubleClickButton(locator.getProperty("New_Tenant"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up the required fields
	action.entertext(locator.getProperty("Tenantname"),input.getProperty("Avaya"));
	action.ClearText(locator.getProperty("Maxusers"));
	action.entertext(locator.getProperty("Maxusers"),input.getProperty("number"));
	//Click on update hierarchy button
	action.DoubleClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(1000);
	//Click on Tenant
	action.ClickLink(input.getProperty("Avaya"));
	Thread.sleep(2000);
	//Click on Add button
	action.DoubleClickButton(locator.getProperty("tenant.add"));
	action.WaitForTitle(locator.getProperty("Tenant_Management"));
	action.VerifyTitle(locator.getProperty("Tenant_Management"));
	//Fill up site details
	action.entertext(locator.getProperty("sitename"),input.getProperty("Pune"));
	//Click on update hierarchy button
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	//Click on any site
	action.ClickLink(input.getProperty("Pune"));
	Thread.sleep(5000);
	action.ClickButton(locator.getProperty("Updatehierarchy"));
	Thread.sleep(2000);
	//Click on Commit button
	action.ClickButton(locator.getProperty("TenantCommit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(1000);
	setup.VerifyAddedTenant(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
}


@Test(description="Verify the Error message on assigning the CM to tenant without Administrator",priority=2)
public void Assign_CM_Without_Admin() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(1000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	//Click on Element Link
	action.ClickLink(locator.getProperty("Elements"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(2000);
	setup.VerifyElementtobeadded(action, input.getProperty("cm29"));
	// Select CM element from table
	 setup.selectElement(action, input.getProperty("cm29"));
	Thread.sleep(2000);
	setup.VerifyAddedElement(action, input.getProperty("cm29"));
	//Fill up the tenant number and tenant location
	action.ClearText(locator.getProperty("Tenantnumber"));
	action.entertext(locator.getProperty("Tenantnumber"), input.getProperty("number"));
	action.ClearText(locator.getProperty("Tenantlocation"));
	//action.entertext(locator.getProperty("Tenantlocation"), input.getProperty("number"));
	//Click on Commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(1000);
	//Verify the following error message
    action.VerifyStringValue("Tenant Administrator is mandatory when site(s) contains Communication Manager element(s).");    
}


@Test(description="Verify the Error message on assigning the UPR without assigning CM",priority=3)
public void Assign_UPR_without_CM() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(2000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	Thread.sleep(2000);
	//Click on User Provisioning Rule Link
    action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
  //Click on edit Button
  	action.ClickButton(locator.getProperty("Tenantedit"));
  //Select the uprsm to assign
  	Thread.sleep(2000);
  	setup.selectUpr(action, input.getProperty("upr1"));
  	Thread.sleep(2000);
  	//Click on Commit button 
    action.ClickButton(locator.getProperty("Commit"));
    action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
    Thread.sleep(1000);
    //Verify the Following Error message
    action.VerifyStringValue("The Site pune does not have the elements whose Communication Profile is used in UPR upr1");    
} 


@Test(description="Verify that Administrator is added sucessfully for Tenenat", priority=4)
public void Assign_CM_with_Admin() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	//Click on Avaya
	setup.SelectTenant(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
    Thread.sleep(2000);
    //Click on Administrator link,Edit button
	action.ClickLink(locator.getProperty("Administrators"));
	action.ClickButton(locator.getProperty("Tenantedit"));
	//Click on New button    
	action.ClickButton(locator.getProperty("Tenantadminuser"));
	//Fill the required fields
	action.entertext(locator.getProperty("Tenantlastname"), input.getProperty("Tenantlastname"));
    action.entertext(locator.getProperty("Tenantfirstname"),input.getProperty("Tenantfirstname"));
	action.entertext(locator.getProperty("Tenantlogin"), input.getProperty("Tenantlogin"));
	action.entertext(locator.getProperty("Tenantpass"), input.getProperty("Tenantpass"));
	action.entertext(locator.getProperty("Tenantcnfpass"), input.getProperty("Tenantcnfpass"));
	action.ClickButton(locator.getProperty("TenantDone"));
	Thread.sleep(2000);
	//Click on Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));	
}


@Test(description="Verify the assignment of CM element to the tenant site",priority=5)
public void Assign_CM_Element() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	Thread.sleep(1000);
	//Click on Element Link
	action.ClickLink(locator.getProperty("Elements"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(2000);
	setup.VerifyElementtobeadded(action, input.getProperty("cm29"));
	// Select CM element from table
	 setup.selectElement(action, input.getProperty("cm29"));
	Thread.sleep(2000);
	setup.VerifyAddedElement(action, input.getProperty("cm29"));
	//Fill up the tenant number and tenant location
	action.ClearText(locator.getProperty("Tenantnumber"));
	action.entertext(locator.getProperty("Tenantnumber"), "1");
	action.ClearText(locator.getProperty("Tenantlocation"));
	//Click on Commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.ClickLink(locator.getProperty("Elements"));
	
}


@Test(description="Verify  assignment of Upr ",priority=6)
public void Assign_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	//Click on User Provisioning Rule Link
    action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
    action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
    //Select the uprsm to assign
  	setup.selectUpr(action, input.getProperty("upr1"));
  	Thread.sleep(1000);
  	setup.VerifyAddedUPR(action, input.getProperty("upr1"));
  	//Click on Commit button 
    action.ClickButton(locator.getProperty("Commit"));
    action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));     
} 

@Test(description="Verify the create user with CM element of Tenant site",priority=7)
public void Verify_UPR() throws Exception
{
	action.driver.navigate().refresh();
		
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	
	action.SwithchFrame("iframe0");
	
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	//action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	
	//Select the tenant
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"), input.getProperty("Avaya"));
	Thread.sleep(2000);
	//Select the site
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"), input.getProperty("Pune"));
	Thread.sleep(3000);
	//verify Upr in the list
	WebElement selectElement =action.driver.findElement(By.xpath(locator.getProperty("Users.Uprdropdown")));
	
	Select select = new Select(selectElement);
	List<WebElement> options=select.getOptions();
	int n=options.size();
	Thread.sleep(1000);
	String[] exp ={"",input.getProperty("upr1")};
	for(int i=0;i<n;i++)
	{
		Assert.assertTrue(options.get(i).getText().equals(exp[i]));
	}
	//Select the UPR from UPR List
	action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"), input.getProperty("upr1"));
	action.ClickButton(locator.getProperty("Users.Uprchangeok"));
	Thread.sleep(5000);
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
	Thread.sleep(1000);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
	Thread.sleep(1000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UPRLoginname"),Keys.TAB);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Loginname")),input.getProperty("UPRLoginname")));
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
	action.SelectElementByLoginname(input.getProperty("UPRLoginname"));
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	//verify the CM Element fields as per specified in UPR
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
	Thread.sleep(1000);

}



@Test(description="Verify Error message on Unassign CM without UPR",priority=8)
public void Unassign_CM_Without_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(2000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	
	//Click on Element Link
	action.ClickLink(locator.getProperty("Elements"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(2000);
	setup.VerifyAddedElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	setup.DeselectElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	//Click on Commit button 
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(1000);
	action.VerifyStringValue("The Site pune does not have the elements whose Communication Profile is used in UPR upr1");
}


@Test(description="Verify Unassign CM with UPR",priority=9)
public void Unassign_CM_With_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	//Click on User provisioning rule Link
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	setup.DeselectUPR(action, input.getProperty("upr1"));
	Thread.sleep(1000);
	setup.VerifyUprtobeadded(action, input.getProperty("upr1"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Elements"));
	setup.DeselectElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	setup.VerifyElementtobeadded(action, input.getProperty("cm29"));
	//Click on Commit button 
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	
}


@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
}

}
