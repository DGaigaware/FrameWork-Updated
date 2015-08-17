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


public class PendingJobs{
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

@Test(description="Verification for the Scheduled jobs should show in the Pending jobs",groups={"Sanity"})
public void SchedulerTitle() throws Exception{
	//Navigate to SMGR,Scheduler,Pending jobs
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Scheduler"));
	action.WaitForTitle(locator.getProperty("Scheduler"));
	action.VerifyTitle(locator.getProperty("Scheduler"));
	Thread.sleep(1000);
}

@Test(description="Verification for the Scheduled jobs should show in the Pending jobs")
public void PenJobCreation() throws Exception{
	//Navigate to SMGR,Scheduler,Pending jobs
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Pending_Jobs"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	action.SwithchFrame("iframe0");
	action.Verify_Add_Thirdcolumn(input.getProperty("PEM.jobname"));
	Thread.sleep(1000);

}

@Test(description="Verification for Pending job should view")
public void PenJobView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Pending_Jobs"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	action.SwithchFrame("iframe0");
	//Select the first pending job
	action.ClickButton(locator.getProperty("FirstItem"));
	Thread.sleep(200);
	action.WaitToClick(locator.getProperty("RTS.View"));
	//View Button is enabled
	action.VerifyEnableButton(locator.getProperty("RTS.View"));
	//Click on the view button
	action.ClickButton(locator.getProperty("RTS.View"));
	action.WaitForTitle(locator.getProperty("View_Pending_Job"));
	//Verification for Pending job should view 
	action.VerifyStringValue(locator.getProperty("View_Pending_Job"));
	
}

@Test(description="Verification for Completed job should Edit Successfully")
public void PenJobEdit() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Pending_Jobs"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	action.SwithchFrame("iframe0");
	//Select the first pending job
	action.ClickButton(locator.getProperty("FirstItem"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("RTS.Edit"));
	//Edit Button is enabled
	action.VerifyEnableButton(locator.getProperty("RTS.Edit"));
	//Click on the view button
	action.DoubleClickButton(locator.getProperty("RTS.Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Pending_Job"));
	//Verification for Completed job should Edit Successfully 
	action.VerifyStringValue(locator.getProperty("Job_Scheduling-Edit"));
	action.VerifyEnableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Cancel"));
}

@Test(description="Verification for Pending job should Delete successfully")
public void PenJobDelete() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Scheduler,Pending jobs
	action.ClickLink(locator.getProperty("Scheduler"));
	action.ClickLink(locator.getProperty("Pending_Jobs"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	action.SwithchFrame("iframe0");
	action.driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	action.WaitForObj(locator.getProperty("ThirdColumnFilter"));	
	action.driver.findElement(By.xpath(locator.getProperty("ThirdColumnFilter"))).sendKeys(input.getProperty("PEM.jobname"));
	action.driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	 Thread.sleep(2000);
	 List<WebElement> rows = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=3;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
				boolean b= str1.equals(input.getProperty("PEM.jobname"));
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-3)+"']")).click();
					Thread.sleep(1000);
					flag=1;
					break;
					
				}
    
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	//Select the first pending job
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitToClick(locator.getProperty("Sched.disable"));
	//Click on the Disable button
	action.ClickButton(locator.getProperty("Sched.disable"));
	action.WaitForTitle(locator.getProperty("Disable_Confirmation"));
	//Click on the disable confirmation button
	action.ClickButton(locator.getProperty("Continue_Cnf"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	//Click on the delete button
	action.ClickButton(locator.getProperty("GLS.Delete"));
	action.WaitForTitle(locator.getProperty("Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Continue_Cnf"));
	action.WaitForTitle(locator.getProperty("Pending_Jobs"));
	//Verification for Pending job should view 
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("PEM.jobname"));
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

