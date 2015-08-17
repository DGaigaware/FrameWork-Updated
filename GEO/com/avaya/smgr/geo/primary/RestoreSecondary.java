package com.avaya.smgr.geo.primary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.upmsetup.Upmsetup;

public class RestoreSecondary {
	boolean b;
	UserAction action =null;
	Upmsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		setup=new com.avaya.smgr.upmsetup.Upmsetup();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Verify creation of user in Secondary to verify replication",priority=1)
	public void New_User() throws Exception
	{
		
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on New Button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		Thread.sleep(500);
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(500);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("SecReplicaLogname"),Keys.TAB);
		Thread.sleep(1000);
		//Click on the Commit Button
		//action.DoubleClickButton(locator.getProperty("Commit"));
		//action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		//action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		Thread.sleep(1000);
		action.Verify_Add_Fifthcolumn(input.getProperty("SecReplicaLogname"));
	}
	
	@Test(description="Deactivate the secondary server",priority=2)
	public void Deactivate_Secondary() throws Exception
	{
		action.driver.navigate().refresh();
		action.logout(action);
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Click on Geographic Redundancy, GR_Health
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.SwithchFrame("iframe0");
		//Click on Deactivate secondary
		action.ClickButton(locator.getProperty("Geodeactivate"));
		Thread.sleep(1000);
		action.driver.switchTo().frame("deactivationUIDiv");
		Thread.sleep(1000);
		//Click on Ok button to confirm
		action.DoubleClickButton(locator.getProperty("deactivationcnfrm"));
		Thread.sleep(600000);
		action.RefreshPage();
		Thread.sleep(75000);
		action.RefreshPage();
		 WebDriverWait wait = new WebDriverWait(action.driver,240000);
		 wait.until(ExpectedConditions.titleContains(locator.getProperty("System_Manager")));
		action.RefreshPage();
		Thread.sleep(3000);
		WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("ErrorBox")));
		while(ele.isDisplayed())
		{
			action.RefreshPage();
			Thread.sleep(60000);
		}
		action.driver.quit();
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="verify the Deactivate  secondary server successfully.",priority=3)
	public void Verify_Status() throws Exception
	{
		action.driver.navigate().refresh();
		//verify status is standby
		action.VerifyElementValue(locator.getProperty("GeoSecondaryserverstatus"), "Standby");
		//Click on Geographic Redundancy,GR_Health 
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.SwithchFrame("iframe0");
		//verify Activate secondary button is enabled
		action.VerifyEnableButton(locator.getProperty("ActivateSecbtn"));
	}

	@Test(description="Restore Secondary server to primary",priority=4)
	public void Restore() throws Exception
	{
		action.RefreshPage();
		action.logout(action);
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Navigate to Geographic_Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Restore button
		action.ClickButton(locator.getProperty("GeoRestore"));
		Thread.sleep(2000);
		//Focus on Pop up window
		WebElement Details=action.driver.findElement(By.xpath(locator.getProperty("RecoveryWindow")));
		Details.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//WebDriverWait wait = new WebDriverWait(action.driver, 60);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("RecoverSecondary"))));
		//Click on Secondary sever button
		action.DoubleClickButton(locator.getProperty("RecoverSecondary"));
		WebElement recovercnfm=action.driver.findElement(By.xpath(locator.getProperty("RecoveryWindowcnfrm")));
		recovercnfm.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on OK button to confirm
		action.DoubleClickButton(locator.getProperty("Restorecnfrm"));
		//Wait for some time to handle pop up
		Thread.sleep(700000);
		//handle alert 
			if(action.isAlertPresent())
			{
		        action.driver.switchTo().alert();
		        action.driver.switchTo().alert().accept();	
		    }
		//Wait for 15 minutes
			action.RefreshPage();
			Thread.sleep(75000);
			action.RefreshPage();
			 WebDriverWait wait = new WebDriverWait(action.driver,240000);
			 wait.until(ExpectedConditions.titleContains(locator.getProperty("System_Manager")));
			action.RefreshPage();
			Thread.sleep(3000);
			WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("ErrorBox")));
			while(ele.isDisplayed())
			{
				action.RefreshPage();
				Thread.sleep(60000);
			}
			
		action.driver.quit();
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		Thread.sleep(1000);
		action.RefreshPage();
	}

	@Test(description="Verify creation of user in primary to verify replication",priority=5)
	public void Verify_Restore() throws Exception
	{
		
		action.driver.navigate().refresh();
		action.logout(action);
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		Thread.sleep(500);
		action.ClickButton(locator.getProperty("Upr.refresh"));
		Thread.sleep(2000);
		//Verify user is restored from secondary
		action.Verify_Add_Fifthcolumn(input.getProperty("SecReplicaLogname"));
		Thread.sleep(1000);
		
	}
	
	@Test(description="Verify Restore operation completed",priority=6,enabled=false)
	public void Verify_Restore_Status() throws Exception
	{
		action.driver.navigate().refresh();
		action.logout(action);
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Verify status of restore operation
		WebDriverWait wait = new WebDriverWait(action.driver,60);	   
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("Georestoremsg"))));
	    b=action.driver.findElement(By.xpath(locator.getProperty("Georestoremsg"))).getText().contains("Restore data is completed");
	   	Assert.assertTrue(b);
	   	Thread.sleep(1000);
				
	}

	@Test(description="Verify the Alarm of activation",priority=7)
	public void Verify_Alarmon_Primary() throws Exception
	{ 
		int flag=0;
	    action.RefreshPage();
	    action.logout(action);
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Click on Events,Alarming
		action.ClickLink(locator.getProperty("Events"));
		action.WaitForTitle(locator.getProperty("Events"));
		action.ClickLink(locator.getProperty("Alarms"));
		action.WaitForTitle(locator.getProperty("Alarming"));
		action.SwithchFrame("iframe0");
		for(int i=2;i<=10;i++)
		{
			String str=action.driver.findElement(By.xpath(".//*[@id='table_core_table_content']/tbody/tr["+i+"]/td[7]")).getText();
		   boolean b=str.equals("Peer AppServer Node not Reachable");
		   if(b)
		   {
			   Assert.assertTrue(b);
			   flag=1;
			   break;
		   }
		}
		
		if(flag==0)
		{
			Assert.assertTrue(b);
		}	
	}
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
		action.logout(action);
	}


}
