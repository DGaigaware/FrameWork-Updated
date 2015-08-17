package com.avaya.smgr.Scheduler;
/*
 * Test Case related to Scheduler Test UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class CompletedJobs{
	boolean b=false;
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

@Test(description="Verification for the back up name is showing in the Completed jobs")
public void CompletedjobCreation() throws Exception{
	//Navigate to SMGR,Scheduler,Pending jobs
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Completed_Jobs"));
	action.WaitForTitle(locator.getProperty("Completed_Jobs"));
	action.SwithchFrame("iframe0");
	//Verification for the Scheduled jobs should show in the Completed jobs
	action.Verify_Add_Thirdcolumn(input.getProperty("PurgeJobStatus"));
	Thread.sleep(1000);
}

@Test(description="Verification for Completed job should view")
public void ComJobView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Completed_Jobs"));
	action.WaitForTitle(locator.getProperty("Completed_Jobs"));
	action.SwithchFrame("iframe0");
	//Select the first pending job
	action.ClickButton(locator.getProperty("FirstItem"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("RTS.View"));
	//View Button is enabled
	action.VerifyEnableButton(locator.getProperty("RTS.View"));
	//Click on the view button
	action.ClickButton(locator.getProperty("RTS.View"));
	action.WaitForTitle(locator.getProperty("Job_Scheduling-View_Job"));
	//Thread.sleep(1000);
	//Verification for Pending job should view 
	action.VerifyStringValue(locator.getProperty("Job_Scheduling-View_Job"));
	Thread.sleep(1000);
}

@Test(description="Verification for Completed job should Edit Successfully")
public void CompJobEdit() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Completed_Jobs"));
	action.WaitForTitle(locator.getProperty("Completed_Jobs"));
	action.SwithchFrame("iframe0");
	//Select the first pending job
	action.DoubleClickButton(locator.getProperty("FirstItem"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("RTS.Edit"));
	//Click on the view button
	action.ClickButton(locator.getProperty("RTS.Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Completed_Job"));
	//Verification for Completed job should Edit Successfully 
	action.VerifyStringValue(locator.getProperty("Job_Scheduling-Edit"));
	Thread.sleep(1000);

}

@Test(description="Verification for Completed job log")
public void CompleteLog() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Completed_Jobs"));
	action.WaitForTitle(locator.getProperty("Completed_Jobs"));
	action.SwithchFrame("iframe0");
	//Select the first pending job
	action.ClickButton(locator.getProperty("FirstItem"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("RTS.View"));
	//Click on the view button
	action.ClickButton(locator.getProperty("RTS.View"));
	action.WaitForObj(locator.getProperty("Edit"));
	//Click on the Log button
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Logging"));
	action.VerifyTitle(locator.getProperty("Logging"));
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

