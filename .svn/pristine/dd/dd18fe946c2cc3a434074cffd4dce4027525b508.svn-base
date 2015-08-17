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


public class EditDepartment {
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

@Test(description="Verify the Edit department operation of tenant")
public void EditDept() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickElement(locator.getProperty("Tenanttree3"));
	Thread.sleep(1000);
	action.ClickElement(locator.getProperty("Tenanttree4"));
	Thread.sleep(1000);
	//Click on Department
	action.ClickLink(input.getProperty("Dept1"));
	Thread.sleep(2000);
	//Click on Edit button
	action.ClickButton(locator.getProperty("Tenantedit"));
	//Edit the department and click on edit button
	action.ClearText(locator.getProperty("deptdesc"));
	action.entertext(locator.getProperty("deptdesc"), "Department");
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(2000);
	action.VerifyStringValue("Dept1 is updated successfully. Refresh the tree to see updated node.");
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
