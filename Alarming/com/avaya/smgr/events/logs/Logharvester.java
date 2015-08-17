package com.avaya.smgr.events.logs;
/*
 * Test Case related to Log harvester Test UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
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


public class Logharvester{
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

@Test(description="Verification of title Log harvester is showing correctly")
public void LogharvestTitle() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Log_Harvester"));
}
@Test(description="Verification of title Log harvester is adding correctly",priority=1)
public void Logharvestcreate() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Logs.New"));
	action.WaitForTitle(locator.getProperty("Create_New_Profile"));
	//enter profile name
	action.entertext(locator.getProperty("Logs.Profile"), input.getProperty("UpdateSite"));
	//select directory and file
	action.ClickButton(locator.getProperty("Logs.Dir"));
	action.WaitForObj(locator.getProperty("Logs.Files"));

	action.ClickButton(locator.getProperty("Logs.Files"));
	action.WaitForObj(locator.getProperty("Logs.Filter"));
	Thread.sleep(1000);
	//enter text for the filter
	action.entertext(locator.getProperty("Logs.Filter"), input.getProperty("UpdateSite"));
	action.ClickButton(locator.getProperty("Logs.Commit"));
	action.WaitForObj(locator.getProperty("Logs.Saveprofile"));
	Thread.sleep(1000);
	//Save profile
	action.DoubleClickButton(locator.getProperty("Logs.Saveprofile"));
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"), input.getProperty("UpdateSite"));
	Thread.sleep(1000);
}

@Test(description="Verification of Log harvester request editable page is showing",priority=2)
public void LogHarvestRequest() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equalsIgnoreCase(input.getProperty("UpdateSite"));
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
			Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Role.Edit"));
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Role.Edit"));
	action.VerifyTitle("Profile criteria edit");
	Thread.sleep(1000);
}

@Test(description="Verification of title Log harvester is view correctly",priority=3)
public void LogHarvestView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equalsIgnoreCase(input.getProperty("UpdateSite"));
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
	action.WaitToClick(locator.getProperty("Logs.view"));
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Logs.view"));
	action.VerifyTitle(locator.getProperty("Logs.ViewTitle"));
	//action.VerifyElementValue(locator.getProperty("Logs.ViewProfilename"), input.getProperty("UpdateSite"));
	Thread.sleep(1000);
}
@Test(description="Verification of Log harvester is Edit correctly",priority=4)
public void LogHarvestEdit() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equalsIgnoreCase(input.getProperty("UpdateSite"));
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
	action.WaitToClick(locator.getProperty("Role.Edit"));
	Thread.sleep(500);
	action.ClickButton(locator.getProperty("Role.Edit"));
	
	action.VerifyTitle("Profile criteria edit");
	action.ClearText(locator.getProperty("Logs.Profile"));
	action.entertext(locator.getProperty("Logs.Profile"), input.getProperty("UpdatTeam"));
	action.entertext(locator.getProperty("Logs.Filter"), input.getProperty("UpdatTeam"));
	action.ClickButton(locator.getProperty("Logs.Commit"));
	action.WaitForObj(locator.getProperty("Logs.Saveprofile"));	
	//Save profile
	action.DoubleClickButton(locator.getProperty("Logs.Saveprofile"));
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"), input.getProperty("UpdatTeam"));
	Thread.sleep(1000);
}
@Test(description="Verification of title Log harvester is Delete correctly",priority=5)
public void LogHarvestDelete() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Harvester"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equalsIgnoreCase(input.getProperty("UpdatTeam"));
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='harvest_profile_table:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Logs.Delete"));
	action.DoubleClickButton(locator.getProperty("Logs.Delete"));
	action.WaitForTitle(locator.getProperty("Profile_Delete_Confirmation"));
	action.DoubleClickButton(locator.getProperty("Logs.DeleteCnf"));
	action.WaitForTitle(locator.getProperty("Log_Harvester"));
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("UpdatTeam"));
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

