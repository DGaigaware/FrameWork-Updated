package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class DuplicateTenantuser {
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

@Test(description="Verify the Duplication of tenant user",priority=1) 
public void DuplicateTuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select tenant
	action.SelectCheckBox(locator.getProperty("Tenantchk1"));
	//Click on Refresh button
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(2000);
	//Select user
	action.SelectElementByLoginname(input.getProperty("Tenantlogin1"));
	Thread.sleep(1000);
	//Click on Duplicate button
	action.ClickButton(locator.getProperty("Users.Duplicate"));
	action.WaitForTitle(locator.getProperty("User_Profile_Duplicate"));
	action.VerifyTitle(locator.getProperty("User_Profile_Duplicate"));
	Thread.sleep(1000);
	//Verify the tenant name and Language
	String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Users.tenantlist")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str, input.getProperty("TName"));
	Thread.sleep(1000);
	String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str1, input.getProperty("Danish"));
	
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
