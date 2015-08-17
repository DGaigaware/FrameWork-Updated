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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignCMSMTest {
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


@Test(description="Verify the Assignment of CM and SM element", priority=1)
public void Assign_CM_SM() throws Exception
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
	//Click on Element Link
	action.ClickLink(locator.getProperty("Elements"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(2000);
	setup.VerifyElementtobeadded(action, input.getProperty("SM_Pune"));
	//Click on Enable link
	setup.selectElement(action, input.getProperty("SM_Pune"));
	Thread.sleep(1000);
	 // Select SM element from table
	Thread.sleep(1000);
	//Fill up the tenant number and tenant location
	action.ClearText(locator.getProperty("Tenantnumber"));
	action.entertext(locator.getProperty("Tenantnumber"), "1");
	Thread.sleep(2000);
	//Verify SM element is added
	setup.VerifyAddedElement(action, input.getProperty("SM_Pune"));
	//verify CM element is in element table
	setup.VerifyElementtobeadded(action, input.getProperty("cm29"));
	//Click on Enable link
	setup.selectElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	 // Select SM element from table
	
	//Fill up the tenant number and tenant location
	action.ClearText(locator.getProperty("Tenantnumber1"));
	action.entertext(locator.getProperty("Tenantnumber1"), "1");
	action.ClearText(locator.getProperty("Tenantlocation1"));
	//action.entertext(locator.getProperty("Tenantlocation1"), "1");
	Thread.sleep(1000);
	//Verify CM element is added
	setup.VerifyAddedElement(action, input.getProperty("cm29"));
	//Click on Commit button 
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(3000);
	//verify the following Message
	action.VerifyStringValue("pune is updated successfully. Refresh the tree to see updated node.");
	Thread.sleep(1000);
}


@Test(description="Verify the Error message on assigning the Upr without assigning CM",priority=2)
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
    Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
  //Select the uprsm to assign
  	setup.selectUpr(action, input.getProperty("uprcmsm1"));
	Thread.sleep(1000);
	//setup.VerifyAddedUPR(action, input.getProperty("uprcmsm1"));
	Thread.sleep(1000);
  	//Click on Commit button 
    action.ClickButton(locator.getProperty("Commit"));
    action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));     
} 


@Test(description="Verify the elements of Tenant site by creating new user",priority=3)
public void Verify_Assigned_Element() throws Exception
{
	action.driver.navigate().refresh();
	Thread.sleep(1000);
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	//Select the tenant
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	//Select the site
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"), input.getProperty("Pune"));
	Thread.sleep(2000);
	//verify UPR in the list
	WebElement selectElement =action.driver.findElement(By.xpath(locator.getProperty("Users.Uprdropdown")));
	Select select = new Select(selectElement);
	List<WebElement> options=select.getOptions();
	int n=options.size();
	Thread.sleep(1000);
	String[] exp ={"",input.getProperty("uprcmsm1")};
	for(int i=0;i<n;i++)
	{
		Assert.assertTrue(options.get(i).getText().equals(exp[i]));
	}
	//Select the UPR from UPR list
	action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"), input.getProperty("uprcmsm1"));
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
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UPRSMLoginname"),Keys.TAB);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Loginname")),input.getProperty("UPRSMLoginname")));
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
	action.SelectElementByLoginname(input.getProperty("UPRCMSMLoginname"));
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	//verify the CM Element fields as per specified in UPR
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
	action.VerifySelectcheckbox(locator.getProperty("Users.SMCheckBox"));
	Thread.sleep(1000);

}


@Test(description="Verify Error message on Unassign CM without UPR",priority=4)
public void Unassign_CM_without_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	
	action.SwithchFrame("iframe0");
	//Click on Refresh Button
	action.ClickButton(locator.getProperty("Users.Refreshtenant"));
	Thread.sleep(2000);
	setup.SelectTenantTree(action, locator.getProperty("Tenant.total"), input.getProperty("Avaya"));
	Thread.sleep(1000);
	setup.SelectSite(action, locator.getProperty("Tenant.total"), input.getProperty("Pune"));
	//Click on Element Link
	action.ClickLink(locator.getProperty("Elements"));
	//Click on edit Button
	action.ClickButton(locator.getProperty("Tenantedit"));
	Thread.sleep(1000);
	setup.VerifyAddedElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	setup.DeselectElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	setup.DeselectElement(action, input.getProperty("SM_Pune"));
	Thread.sleep(1000);
	//Click on Commit button 
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(1000);
	action.VerifyStringValue("The Site pune does not have the elements whose Communication Profile is used in UPR auprcmsm2");
}

@Test(description="Verify Error message on Unassign CM with UPR",priority=5)
public void Unassign_CM_with_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Tenant Management
	action.driver.navigate().refresh();
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
	setup.DeselectUPR(action, input.getProperty("uprcmsm1"));
	Thread.sleep(1000);
	setup.VerifyUprtobeadded(action, input.getProperty("uprcmsm1"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Elements"));
	setup.DeselectElement(action, input.getProperty("cm29"));
	Thread.sleep(1000);
	setup.DeselectElement(action, input.getProperty("SM_Pune"));
	Thread.sleep(1000);
	setup.VerifyElementtobeadded(action, input.getProperty("cm29"));
	setup.VerifyElementtobeadded(action, input.getProperty("SM_Pune"));
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
