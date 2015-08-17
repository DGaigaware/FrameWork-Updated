package com.avaya.smgr.geo.primary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.upmsetup.Upmsetup;

public class Primaryheartbitstatus {
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
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
	
	public void MovetoGRHealth() throws Exception
	 {
		
		 action.driver.navigate().refresh();
		 //Click on Geographic Redundancy, GR_Health 
		 action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		 action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("navigatetree"))));
		 
		 Thread.sleep(1000);
		 action.ClickLink(locator.getProperty("GR_Health"));
		 action.WaitForTitle(locator.getProperty("GR_Health"));
		 action.VerifyTitle(locator.getProperty("GR_Health"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(1000);
	 }
	
	@Test(description="Verify the File Replication link on Heartbeat Status")
	public void File_Replication() throws Exception
	{
		MovetoGRHealth();
		//Click on heart beat status link 
		action.ClickLinkByxpath(locator.getProperty("Heartbeatstatus"));
		action.WaitForTitle(locator.getProperty("GR_Heartbeat"));
		action.VerifyTitle(locator.getProperty("GR_Heartbeat"));
		//verify Done button is Enabled
		action.VerifyEnableButton(locator.getProperty("Donestatus"));
		//Click on View Graph link of File Replication
		action.ClickLinkByxpath(locator.getProperty("FileHeartbeat"));
		Thread.sleep(1000);
		//Verify the Service name
		action.VerifyElementValue(locator.getProperty("Servicename"), "Service Name File Replication");
		Thread.sleep(1000);
		//Verify the Buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Editdate"));
		action.VerifyEnableButton(locator.getProperty("Autorefreshgraph"));
	}
	
	
	@Test(description="Verify the Application System link on Heartbeat Status")
	public void Application() throws Exception
	{
		MovetoGRHealth();
		//Click on heart beat status link 
		action.ClickLinkByxpath(locator.getProperty("Heartbeatstatus"));
		action.WaitForTitle(locator.getProperty("GR_Heartbeat"));
		action.VerifyTitle(locator.getProperty("GR_Heartbeat"));
		//verify Done button is Enabled
		action.VerifyEnableButton(locator.getProperty("Donestatus"));
		//Click on View Graph link of Application System health
		action.ClickLinkByxpath(locator.getProperty("AppHeartbeat"));
		Thread.sleep(1000);
		//Verify the Service name
		action.VerifyElementValue(locator.getProperty("Servicename"), "Service Name Application System Health");
		Thread.sleep(1000);
		//Verify the Buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Editdate"));
		action.VerifyEnableButton(locator.getProperty("Autorefreshgraph"));
		//Thread.sleep(1000);
	}
	
	@Test(description="Verify the Database Replication link on Heartbeat Status")
	public void Database_Replication() throws Exception
	{
		MovetoGRHealth();
		//Click on heart beat status link 
		action.ClickLinkByxpath(locator.getProperty("Heartbeatstatus"));
		action.WaitForTitle(locator.getProperty("GR_Heartbeat"));
		action.VerifyTitle(locator.getProperty("GR_Heartbeat"));
		//verify Done button is Enabled
		action.VerifyEnableButton(locator.getProperty("Donestatus"));
		//Click on View Graph link of Database
		action.ClickLinkByxpath(locator.getProperty("DatabaseHeartbeat"));
		Thread.sleep(1000);
		//Verify the Service name
		//String str=action.driver.findElement(By.xpath(locator.getProperty("Servicename"))).getText();
		//System.out.println(str);
		//boolean b=str.equals("Service Name Database Replication");
		action.VerifyElementValue(locator.getProperty("Servicename"), "Service Name Database Replication");
		Thread.sleep(1000);
		//Verify the Buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Editdate"));
		action.VerifyEnableButton(locator.getProperty("Autorefreshgraph"));
	}
	
	@Test(description="Verify the System health link on Heartbeat Status")
	public void System_Replication() throws Exception
	{
		MovetoGRHealth();
		//Click on heart beat status link 
		action.ClickLinkByxpath(locator.getProperty("Heartbeatstatus"));
		action.WaitForTitle(locator.getProperty("GR_Heartbeat"));
		action.VerifyTitle(locator.getProperty("GR_Heartbeat"));
		//verify Done button is Enabled
		action.VerifyEnableButton(locator.getProperty("Donestatus"));
		//Click on View Graph link of System Health
		action.ClickLinkByxpath(locator.getProperty("SystemHeartbeat"));
		Thread.sleep(1000);
		//Verify the Service name
		action.VerifyElementValue(locator.getProperty("Servicename"), "Service Name System Health");
		Thread.sleep(1000);
		//Verify the Buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Editdate"));
		action.VerifyEnableButton(locator.getProperty("Autorefreshgraph"));
	}
	
	@Test(description="Verify the Directory Replication link on Heartbeat Status")
	public void Directory_Replication() throws Exception
	{
		MovetoGRHealth();
		//Click on heart beat status link 
		action.ClickLinkByxpath(locator.getProperty("Heartbeatstatus"));
		action.WaitForTitle(locator.getProperty("GR_Heartbeat"));
		action.VerifyTitle(locator.getProperty("GR_Heartbeat"));
		//verify Done button is Enabled
		action.VerifyEnableButton(locator.getProperty("Donestatus"));
		//Click on View Graph link of Directory Health
		action.ClickLinkByxpath(locator.getProperty("DirectoryHeartbeat"));
		Thread.sleep(1000);
		//Verify the Service name
		action.VerifyElementValue(locator.getProperty("Servicename"), "Service Name Directory Replication");
		Thread.sleep(1000);
		//Verify the Buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Editdate"));
		action.VerifyEnableButton(locator.getProperty("Autorefreshgraph"));
	}
	
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
		action.logout(action);
	}

	


}
