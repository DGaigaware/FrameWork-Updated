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


public class ServiceLink {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod(alwaysRun=true)
 public void setup() throws IOException, InterruptedException
 {
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
 }
 
@Test(description="Verify the links of Bulk Import and Export Tab", groups={"Sanity"})
public void Import_Export() throws Exception{
	try{
	action.driver.navigate().refresh();
	
	//Click on different Bulk Import and Export Links and verify title of page
	action.ClickLink(locator.getProperty("Bulk_Import_and_Export"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Bulk_Import_and_export"));
	action.VerifyTitle(locator.getProperty("Bulk_Import_and_export"));
	
	action.ClickLink(locator.getProperty("export"));
	Thread.sleep(1000);
	System.out.println(action.driver.getTitle());
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Bulk_Import_and_export"));
	action.VerifyTitle(locator.getProperty("Bulk_Import_and_export"));
	action.ClickLink(locator.getProperty("Routing"));
	System.out.println(action.driver.getTitle());

	action.WaitForTitle(locator.getProperty("Export_Routing_Data"));
	action.VerifyTitle(locator.getProperty("Export_Routing_Data"));
	action.ClickLink(locator.getProperty("Session_Manager"));
	action.VerifyTitle(locator.getProperty("Session_Manager"));
	action.ClickLink(locator.getProperty("Local_Host_Name_Resolution"));
	action.VerifyTitle(locator.getProperty("Session_Manager"));
	
	action.ClickLink(locator.getProperty("Import"));
	
	action.WaitForTitle(locator.getProperty("Bulk_Import_and_export"));
	action.VerifyTitle(locator.getProperty("Bulk_Import_and_export"));
	
	action.ClickLink(locator.getProperty("Inventory"));
	action.VerifyTitle(locator.getProperty("Bulk_Import_and_export"));
	action.ClickLink(locator.getProperty("Elements"));
	action.VerifyTitle(locator.getProperty("Import_Elements"));
	
	action.ClickLink(locator.getProperty("Subnets"));
	Thread.sleep(1000);
	System.out.println(action.driver.getTitle());
	action.VerifyTitle(locator.getProperty("Subnet_Bulk_Import"));
	//action.ClickLinkByxpath(locator.getProperty("Import.Routing"));
	action.ClickLink(locator.getProperty("Routing"));
	Thread.sleep(1000);

	action.VerifyTitle(locator.getProperty("Import_Routing_Data"));
	
	action.ClickLink(locator.getProperty("Session_Manager"));
	Thread.sleep(2000);
	System.out.println(action.driver.getTitle());
	action.WaitForTitle(locator.getProperty("Session_Manager"));

	action.VerifyTitle(locator.getProperty("Session_Manager"));
	action.ClickLink(locator.getProperty("Local_Host_Name_Resolution"));
	Thread.sleep(1000);

	action.VerifyTitle(locator.getProperty("Session_Manager"));
	action.ClickLink(locator.getProperty("User_Management"));
	Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Bulk_Import_and_export"));
	action.VerifyTitle(locator.getProperty("Bulk_Import_and_export"));
	action.ClickLink(locator.getProperty("Global_Settings"));
	Thread.sleep(1000);

	action.VerifyTitle(locator.getProperty("Import_global_settings"));
	action.ClickLink(locator.getProperty("Users"));
	Thread.sleep(1000);

	action.VerifyTitle(locator.getProperty("Import_users"));
	}
catch(Exception e){
	 
}
}

@Test(description="Verify the Links of Licenses Tab", groups={"Sanity"})
public void Licenses() throws Exception{
	
	//Click on different Licenses Links and verify title of page
		action.ClickLinkByxpath(locator.getProperty("LicensesLink"));
		System.out.println(action.driver.getTitle());

	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	
}

@Test(description="Verify the Links of Replication Tab",groups={"Sanity"})
public void Replication() throws Exception
{
	
	//Click on different Replication Links and verify title of page
	
    action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));

	action.ClickLink(locator.getProperty("Replication"));
	action.WaitForTitle(locator.getProperty("Replica_Groups"));
	action.VerifyTitle(locator.getProperty("Replica_Groups"));	
}

@Test(description="Verify the Links of Reports Tab", groups={"Sanity"})
public void Reports() throws Exception{

	//Click on different Reports Links and verify title of page
	
	
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
    Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Reports"));
    Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Reports_Management"));
	action.VerifyTitle(locator.getProperty("Reports_Management"));
	action.ClickLink(locator.getProperty("Generation"));
    Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Reports_Generation"));
	action.VerifyTitle(locator.getProperty("Reports_Generation"));
	
	action.ClickLink(locator.getProperty("History"));
    Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Reports_History"));
	action.VerifyTitle(locator.getProperty("Reports_History"));
	action.ClickLink(locator.getProperty("Remote_Server_Configuration"));
    Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Remote_Server_Configuration"));
	action.VerifyTitle(locator.getProperty("Remote_Server_Configuration"));
}

@Test(description="Verify the links of Security Tab", groups={"Sanity"})
public void Security() throws Exception{
	
	//Click on different Security Links and verify title of page
	
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("Security"));
	 Thread.sleep(1000);
	action.VerifyStringValue(locator.getProperty("Security"));	
	
}

@Test(description="Verify the links of Shutdown Tab", groups={"Sanity"})
public void Shutdown() throws Exception{
	
	//Click on different Shutdown Links and verify title of page
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	 Thread.sleep(2000);
	action.ClickLink(locator.getProperty("Shutdown"));
	action.WaitForTitle(locator.getProperty("Graceful_Shutdown"));
	action.VerifyTitle(locator.getProperty("Graceful_Shutdown"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Shutdown_History"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("gracefulShutdown"));
	action.VerifyTitle(locator.getProperty("gracefulShutdown"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Shutdown_System_Manager"));
	action.WaitForTitle(locator.getProperty("Graceful_Shutdown"));
	action.VerifyTitle(locator.getProperty("Graceful_Shutdown"));
}


@Test(description="Verify the links of Templates Tab", groups={"Sanity"})
public void Templates() throws Exception{
	
	//Click on different Templates Links and verify title of page
	
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	 Thread.sleep(2000);
	action.ClickLink(locator.getProperty("Templates"));
	action.WaitForTitle(locator.getProperty("Templates"));
	action.VerifyTitle(locator.getProperty("Templates"));
	Thread.sleep(1000);
	
	action.ClickLink(locator.getProperty("IP_Office_Endpoint"));
	Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("IP_Office_Endpoint_Templates"));
	action.VerifyTitle(locator.getProperty("IP_Office_Endpoint_Templates"));
	Thread.sleep(1000);
	
	action.ClickLink(locator.getProperty("IP_Office_System_Configuration"));
	Thread.sleep(1000);

	System.out.println(action.driver.getTitle());
	action.VerifyTitle(locator.getProperty("IP_Office_System_Configuration_Templates"));
	//Thread.sleep(1000);
	
	action.ClickLink(locator.getProperty("UCM_and_Application_Server_Configuration"));
	Thread.sleep(1000);
	System.out.println(action.driver.getTitle());

	action.VerifyTitle(locator.getProperty("IP_Office_System_Configuration_Templates"));
	Thread.sleep(1000);
	
	action.ClickLink(locator.getProperty("VMPro_System_Configuration_Template"));
	Thread.sleep(2000);
	action.WaitForTitle(locator.getProperty("VMPro_System_Configuration_Templates"));
	action.VerifyTitle(locator.getProperty("VMPro_System_Configuration_Templates"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("VMPro_Callflow_Template"));
	Thread.sleep(2000);
	action.WaitForTitle(locator.getProperty("VMPro_Callflow_Templates"));
	action.VerifyTitle(locator.getProperty("VMPro_Callflow_Templates"));
	Thread.sleep(1000);
	
	action.ClickLink(locator.getProperty("CM_Agent"));
	Thread.sleep(1000);

	action.WaitForTitle(locator.getProperty("Agent_Templates"));
	action.VerifyTitle(locator.getProperty("Agent_Templates"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("CM_Endpoint"));
	Thread.sleep(2000);

	action.WaitForTitle(locator.getProperty("Endpoint_Templates"));
	action.VerifyTitle(locator.getProperty("Endpoint_Templates"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Messaging"));
	Thread.sleep(2000);

	action.WaitForTitle(locator.getProperty("Messaging_Templates"));
	action.VerifyTitle(locator.getProperty("Messaging_Templates"));
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);

}

}
