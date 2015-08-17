package com.avaya.smgr.RTS.synchronization;
/*
 * synchronization Links
 */

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


public class Synchronization_Links{
	boolean b;
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
@BeforeMethod(alwaysRun=true)
public void Beforemethodsetup() throws IOException, InterruptedException{
}

@Test(description="Verification of Communication system title is showing correctly",groups={"Sanity"})
public void CommunicationSysTitle() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,Communication system
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("Communication_system"));
	action.WaitForTitle("Synchronize CM Data and Configure Options");
	//Verify that the title name is showing correctly
	action.VerifyTitle("Synchronize CM Data and Configure Options");
}
@Test(description="Verification of IP Office title is showing correctly",groups={"Sanity"})
public void IPOfficeTitle() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,IP Office
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("IP_Office"));
	action.WaitForTitle("Synchronize IP Office System Configuration");
	//Verify that the title name is showing correctly
	action.VerifyTitle("Synchronize IP Office System Configuration");
}
@Test(description="Verification of Messaging System title is showing correctly",groups={"Sanity"})
public void MessagingSystemTitle() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,Messaging System
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("Messaging_System"));
	action.WaitForTitle("Synchronize Messaging System Data");
	//Verification of Messaging System title is showing correctly
	action.VerifyTitle("Synchronize Messaging System Data");
}
@Test(description="Verification of UCM and Application Server title is showing correctly",groups={"Sanity"})
public void UCMApplicationTitle() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,UCM and Application_Server
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("UCM_and_Application_Server"));
	action.WaitForTitle("Synchronize IP Office System Configuration");
	//Verify that the title name is showing correctly
	action.VerifyTitle("Synchronize IP Office System Configuration");
}
@Test(description="Verification of VMPro title is showing correctly")
public void VMProTitle() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,VMPro
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("VMPro"));
	action.WaitForTitle("Synchronize IP Office System Configuration");
	//Verification of VMPro title is showing correctly
	action.VerifyTitle("Synchronize IP Office System Configuration");
}
@Test(description="Verification of CS 1000 and CallPilot Synchronization title is showing correctly",groups={"Sanity"})
public void CS1000Title() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Synchronization"));
	action.ClickLink(locator.getProperty("CS_1000_and_CallPilot_Synchronization"));
	action.WaitForTitle("System List");
	//Verify that the title name is showing correctly
	action.VerifyTitle("System List");
}

@Test(description="Verification of CS 1000 and CallPilot Synchronization title is showing correctly",groups={"Sanity"})
public void SRSandSCS() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,Synchronization,
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Create_Profiles_and_Discover_SRS/SCS"));
	action.VerifyStringValue(locator.getProperty("Create_Profiles_and_Discover_SRS/SCS"));
	Thread.sleep(1000);

}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}



  @AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
 }

