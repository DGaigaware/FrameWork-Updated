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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.upmsetup.Upmsetup;

public class Standaloneprimary {

	boolean b;
	UserAction action =null;
	Upmsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
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
	
	@Test(description="Convert the primary server to standalone mode",priority=1)
	public void Convert_To_Standalone() throws Exception
	{
		//Navigate to Geographic_Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Convert to stand alone button
		action.ClickButton(locator.getProperty("Convertostandalonebtn"));
		Thread.sleep(1000);
		WebElement deletecnfm=action.driver.findElement(By.xpath(locator.getProperty("Geodeletecnfrmwindow")));
		deletecnfm.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on OK button to confirm
		action.ClickButton(locator.getProperty("GeoDeletecnfm"));
		Thread.sleep(600000);
		//handle alert 
		if(action.isAlertPresent())
		{
	        action.driver.switchTo().alert();
	        action.driver.switchTo().alert().accept();	
	    }
		action.RefreshPage();
		Thread.sleep(100000);
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
	}
	
	@Test(description="Verify the primary server converted to standalone mode",priority=2)
	public void Verify_Status() throws Exception
	{
		action.RefreshPage();
		//Navigate to Geographic_Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		action.VerifyEnableButton(locator.getProperty("Configure"));
		//Verify no server details
		action.VerifyStringValue("No primary server configured");
		action.VerifyStringValue("No secondary server configured");
	}
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException
	{
		action.Screenshot(result, action);
	}

	@AfterClass
	public void Close() throws IOException, InterruptedException
	{
		action.logout(action);
	}
}
