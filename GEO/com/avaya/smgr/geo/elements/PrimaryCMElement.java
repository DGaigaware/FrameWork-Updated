package com.avaya.smgr.geo.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.GeoSetup.Geosetup;

public class PrimaryCMElement {
	boolean b;
	UserAction action =null;
	Geosetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		setup=new com.avaya.smgr.GeoSetup.Geosetup();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Verify Enable replication completed suceesfully",priority=1) 
	public void Enable_Replication() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Enable button
		action.ClickButton(locator.getProperty("Geoenable"));
		//Wait until Disable button Enabled
		WebDriverWait wait = new WebDriverWait(action.driver, 180000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Geodisable"))));
		//Verify Status is Enabled
		action.VerifyElementValue(locator.getProperty("Replstatuscolumn"), "Enabled");
	}

	@Test(description="Manage CM Element in primary",priority=2)
	public void Manage_CM() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(1000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(1000);
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
	      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on More button
		 action.ClickButton(locator.getProperty("Users.More"));
		 Thread.sleep(1000);
		 //Click on manage link
		 action.ClickLink(locator.getProperty("Manage"));
		 //Wait for some times
		 Thread.sleep(75000);
		 //Verify Status is Primary.
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Primary");
		 Thread.sleep(1000);
	}
		
	@Test(description="Verify the staus is 'Manage' after managing the element",priority=3)
	public void Verify_Status_after_Manage() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(1000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(1000);
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on Get Current status button
		 action.DoubleClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(75000);
		 //Verify the Status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Primary");
		 action.VerifyElementValue(locator.getProperty("Reachable"), "Yes");
		 Thread.sleep(1000);
	}
	
	
	
	@Test(description="UnManage CM Element in primary",priority=4)
	public void Unmanage_CM() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(1000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(1000);
		 //Select CM Element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on More button
		 action.ClickButton(locator.getProperty("Users.More"));
		 Thread.sleep(1000);
		 //Click on Unmanage button
		 action.ClickLink(locator.getProperty("Unmanage"));
		 Thread.sleep(75000);
		 //Verify the status is unmanaged
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Unmanaged");
		 Thread.sleep(1000);
	}
	
	@Test(description="Verify the staus is 'Unmanage' after Unmanaging the element",priority=5)
	public void Verify_Status_after_Unmanage() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(1000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(1000);
		 //Select CM Element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on Get Current status button
		 action.DoubleClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(60000);
		 //verify the status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Unmanaged");
		 action.VerifyElementValue(locator.getProperty("Reachable"), "Yes");
		 Thread.sleep(1000);
	}
		
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException
	{
		action.Screenshot(result, action);
		action.logout(action);
	}

	

}
