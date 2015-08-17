package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class UserLinks{
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod
public void setup() throws IOException, InterruptedException
{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
}

@Test(description="Verify the links of Administrator Tab")
 
 public void Administrator() throws Exception{
	try{
	//Click on different Administrator Links and verify title of page
	action.ClickLink(locator.getProperty("Administrators"));
	action.WaitForTitle(locator.getProperty("Avaya Aura® System Manager"));
	action.VerifyTitle(locator.getProperty("Avaya Aura® System Manager"));
	}
 catch(Exception e){
	 
 }
}

@Test(description="Verify the Links of Directory Synchronization Tab")
public void Directory_Synchronization() throws Exception{
	//Click on different Directory Synchronization Links and verify title of page
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("Directory_Synchronization"));
	action.WaitForTitle(locator.getProperty("User_Synchronization"));
	action.VerifyTitle(locator.getProperty("User_Synchronization"));
}

@Test(description="Verify the Links of groups and roles Tab")
public void Groups_and_Roles() throws Exception{
	//Click on different Groups and Roles Links and verify title of page
	
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("Groups_&Roles"));
	action.WaitForTitle(locator.getProperty("Groups_and_Roles"));
	action.VerifyTitle(locator.getProperty("Groups_and_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.WaitForTitle(locator.getProperty("Group_Management"));
	action.VerifyTitle(locator.getProperty("Group_Management"));
	action.ClickLink(locator.getProperty("Resources"));
	action.WaitForTitle(locator.getProperty("Resources"));
	action.VerifyTitle(locator.getProperty("Resources"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Roles"));	
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Manage_Roles"));
	action.VerifyTitle(locator.getProperty("Manage_Roles"));
}

@Test(description="Verify the links of User management Tab")
public void User_Management() throws Exception{
	//Click on different User Management Links and verify title of page
	
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Shared_Addresses"));
	action.WaitForTitle(locator.getProperty("Shared_Address"));
	action.VerifyTitle(locator.getProperty("Shared_Address"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Communication_Profile_Password_Policy"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Communication_Profile_Password_Policy"));
	action.VerifyTitle(locator.getProperty("Communication_Profile_Password_Policy"));
	
}

@Test(description="Verify the links of User Provisioning Rule Tab")
public void Use_Provisioning_Rule() throws Exception{
	//Click on different User Provisioning Rule Links and verify title of page
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}
  
  @AfterMethod
  public void Close() throws IOException, InterruptedException{
  	action.driver.close();
  }
}
