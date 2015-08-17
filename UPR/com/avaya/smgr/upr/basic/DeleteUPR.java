package com.avaya.smgr.upr.basic;

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


public class DeleteUPR {
	boolean b;
	UserAction action =null;
	Tenantsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
	
@Test(description="Verify the Delete Button",groups={"Sanity"}) 
	public void Delete_UPR() throws Exception
	{

	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Select one Upr from table
	setup.SelectUPR(action,input.getProperty("uprcmsm3"));
	action.WaitToClick(locator.getProperty("Users.Delete"));
	//click on Delete button and verify the title of the page
	action.ClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rule_Delete_Confirmation"));
	//Click on cancel button and verify the title of the user
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	setup.VerifydeleteUprname(action,input.getProperty("uprcmsm3"));
	Thread.sleep(1000);
	}
	
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
	}

	@AfterClass
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}

}
