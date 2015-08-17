package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import com.avaya.smgr.Tsetup.Tenantsetup;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class UPRTest {
	
	boolean b=false,match=false;;
	Tenantsetup usetup=null;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	usetup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }	
	
@Test(priority=1)
public void createUpr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
	action.VerifyTitle(locator.getProperty("New_User_Provisioning_Rule"));
	//Fill up the required fields of Upr
	Thread.sleep(3000);
	action.entertext(locator.getProperty("Uprname"),input.getProperty("TUpr"));
	Thread.sleep(1000);
	action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
	action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
	//Click on Commit Button and Verify the title of the page
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	usetup.VerifyUprname(action,input.getProperty("TUpr"));
}
	
@Test(priority=2)
public void AssignUpr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickElement(locator.getProperty("Tenanttree3"));
	Thread.sleep(1000);
	action.ClickLink(input.getProperty("site1"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.ClickButton(locator.getProperty("Tenantedit"));
    Thread.sleep(1000);
    //Select the Upr From table
    action.ClickButton(locator.getProperty("Tenantuprbtn0"));
    Thread.sleep(1000);
    //Click on Commit button
    action.ClickButton(locator.getProperty("Commit"));
    Thread.sleep(1000);
    
    action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(2000);
	action.VerifyStringValue("site1 is updated successfully. Refresh the tree to see updated node.");
}

@Test(priority=3)
public void VerifyAssignedUpr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickElement(locator.getProperty("Tenanttree3"));
	Thread.sleep(1000);
	action.ClickLink(input.getProperty("site1"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.ClickButton(locator.getProperty("Tenantedit"));
    Thread.sleep(2000);
    action.VerifyElementValue(locator.getProperty("TassignedUpr"), input.getProperty("TUpr"));
}

@Test(priority=4)
public void UnassignUpr() throws Exception
{
	
		action.driver.navigate().refresh();
		//Click on tenant Management Link
		action.ClickLink(locator.getProperty("Tenant_Management"));
		action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
		action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
		action.SwithchFrame("iframe0");
		action.ClickElement(locator.getProperty("Tenanttree3"));
		Thread.sleep(1000);
		action.ClickLink(input.getProperty("site1"));
		action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
		action.ClickButton(locator.getProperty("Tenantedit"));
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Tenantselectedupr"));
		Thread.sleep(1000);
		 //Click on Commit button
	    action.ClickButton(locator.getProperty("Commit"));
	    Thread.sleep(1000);
	    action.ClickButton(locator.getProperty("Commit"));
	    action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
		action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
		
}

@Test(priority=5)
public void VerifyUnAssignedUpr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickElement(locator.getProperty("Tenanttree3"));
	Thread.sleep(1000);
	action.ClickLink(input.getProperty("site1"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.ClickButton(locator.getProperty("Tenantedit"));
    Thread.sleep(2000);
    action.VerifyElementValue(locator.getProperty("TnoUPR"), "No Records");
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
