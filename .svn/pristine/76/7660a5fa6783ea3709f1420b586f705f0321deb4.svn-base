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


public class Logsettings{
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
public void LogSettingsTitle() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Settings"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Settings"));
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Log_Settings"));
}
@Test(description="Verification of Attach Log sucessfully",priority=1)
public void LogsettingCreate() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Settings"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Settings"));
	action.ClickButton(locator.getProperty("LogSettings.logger"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Logsettings.Editbtn"));
	//Select first log
	action.DoubleClickButton(locator.getProperty("Logsettings.Editbtn"));
	//Click on the Edit button
	action.VerifyTitle(locator.getProperty("Edit_Logger"));
	//Click on the Attach button
	action.ClickButton(locator.getProperty("Logsettings.Attach"));
	Thread.sleep(500);
	action.WaitForTitle(locator.getProperty("Attach_Appender"));
	action.SelectFromdropDown(locator.getProperty("Logsettings.selectAtach"), "ADMINAUDIT");
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Edit_Logger"));
	action.VerifyaddedEntry(".//*[@id='panelGroup0_0_0']/table[2]/tbody/tr", "ADMINAUDIT");
	
}

@Test(description="Verification of Log settings is Edit correctly",priority=2)
public void LogsettingsEdit() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Settings"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Settings"));
	action.ClickButton(locator.getProperty("LogSettings.logger"));
	Thread.sleep(1000);

	action.WaitToClick(locator.getProperty("Logsettings.Editbtn"));

	//Select first log
	action.DoubleClickButton(locator.getProperty("Logsettings.Editbtn"));
	//Click on the Edit button
Thread.sleep(1000);
List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='log_appenders_table_1_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='log_appenders_table_1_core_table_content']/tbody/tr["+i+"]/td[2]/span")).getText();
				boolean b= str1.equalsIgnoreCase("ADMINAUDIT");
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='log_appenders_table_1:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
  
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
	action.WaitToClick(locator.getProperty("Logsettings.Edit"));
	action.ClickButton(locator.getProperty("Logsettings.Edit"));
	
	action.VerifyTitle(locator.getProperty("Edit_Appender"));
	Thread.sleep(1000);
}
@Test(description="Verification of title Log is Detach Sucessfully",priority=3)
public void LogsettingsDettachment() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Logs"));

	action.ClickLink(locator.getProperty("Log_Settings"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Log_Settings"));
	action.ClickButton(locator.getProperty("LogSettings.logger"));
	Thread.sleep(1000);
	action.WaitToClick(locator.getProperty("Logsettings.Editbtn"));

	//Select first log
	action.DoubleClickButton(locator.getProperty("Logsettings.Editbtn"));
	//Click on the Edit button
Thread.sleep(1000);
List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='log_appenders_table_1_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='log_appenders_table_1_core_table_content']/tbody/tr["+i+"]/td[2]/span")).getText();
				boolean b= str1.equalsIgnoreCase("ADMINAUDIT");
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='log_appenders_table_1:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
  
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
	action.WaitToClick(locator.getProperty("Logsettings.Dettach"));
	Thread.sleep(500);
	action.ClickButton(locator.getProperty("Logsettings.Dettach"));
	Thread.sleep(2000);
	action.VerifyDeleteEntry(".//*[@id='panelGroup0_0_0']/table[2]/tbody/tr", "ADMINAUDIT");

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

