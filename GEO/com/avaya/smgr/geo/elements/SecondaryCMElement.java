package com.avaya.smgr.geo.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.GeoSetup.Geosetup;

public class SecondaryCMElement {
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
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Activate Secondary server",priority=1)
	public void Activate_Secondary() throws Exception
	{
		
		//Click on Geographic Redundancy,GR Health
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.SwithchFrame("iframe0");
		//Click on Activate secondary button
		action.ClickButton(locator.getProperty("ActivateSecbtn"));
		Thread.sleep(1000);
		action.driver.switchTo().frame("activateUIpopup");
		Thread.sleep(1000);
		//Click on Ok button
		action.ClickButton(locator.getProperty("Activationcnfrm"));
		//Wait for 15 minutes
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
		//Login
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
		Thread.sleep(2000);
	}

	@Test(description="Verify the Status is Unmanaged before Manage",priority=2)
	public void Verify_Status_before_Manage() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		 //Select the CM element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on Get Current status button
		 action.ClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(80000);
		 //Verify Status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Unmanaged");
		 Thread.sleep(2000);
	}
	
	
	
	@Test(description="Verify the Manage CM Successfully",priority=3)
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
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		//Select the CM element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 Thread.sleep(1000);
		 //Click on More button
		 action.ClickButton(locator.getProperty("Users.More"));
		 Thread.sleep(1000);
		 //Select manage button
		 action.ClickLink(locator.getProperty("Manage"));
		 Thread.sleep(60000);
		 //Verify status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Secondary");
		 Thread.sleep(80000);
	}
	
	@Test(description="Verify the Status is Secondary after Manage",priority=4)
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
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		//Select the CM element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		//Click on Get Current status button
		 action.DoubleClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(2000);
		 //Verify Status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Secondary");
		 action.VerifyElementValue(locator.getProperty("Reachable"), "Yes");
		 Thread.sleep(80000);
	}


	
	@Test(description="Verify the Manage CM Successfully",priority=5)
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
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 //Select CM Element
		 Thread.sleep(2000);
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebElement more=action.driver.findElement(By.xpath(locator.getProperty("Users.More")));
		 more.sendKeys(org.openqa.selenium.Keys.CONTROL);
		 //Click on More button
		 action.ClickButton(locator.getProperty("Users.More"));
		 Thread.sleep(1000);
		 //Click on Unmanage link
		 action.ClickLink(locator.getProperty("Unmanage"));
		 Thread.sleep(80000);
		 //Verify Status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Unmanaged");
		 Thread.sleep(2000);
	}
	
	@Test(description="Verify the Status is Unmanaged after UnManage",priority=6)
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
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		 //Select CM Element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 Thread.sleep(2000);
		 //Click on Get current status button
		 action.DoubleClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(2000);
		 //Verify The status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Unmanaged");
		 action.VerifyElementValue(locator.getProperty("Reachable"), "Yes");
		 Thread.sleep(1000);
	}
	
	@Test(description="Deactivate the secondary server",priority=7)
	public void Deactivate_Secondary() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy, GR_Health
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.SwithchFrame("iframe0");
		//Click on Deactivate secondary
		action.DoubleClickButton(locator.getProperty("Geodeactivate"));
		Thread.sleep(1000);
		action.driver.switchTo().frame("deactivationUIDiv");
		Thread.sleep(1000);
		//Click on ok button to confirm
		action.ClickButton(locator.getProperty("deactivationcnfrm"));
		//Wait for 15 minutes to deactivate
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
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException
	{
		  
		action.Screenshot(result, action);
		action.logout(action);
	}

	
}
