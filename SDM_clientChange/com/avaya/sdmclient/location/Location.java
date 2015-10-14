package com.avaya.sdmclient.location;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;

public class Location {
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	@Test(description="Adding Location",priority=0)
	public void AddLocation() throws IOException, InterruptedException, MyException {

		logClass.startTestCase("Add a new Location on SDM");

		obj.loginToSite(driver);

		driver.findElement(By.xpath((locator.getProperty("LocationAdd")))).click();
		logClass.info("Adding new Location");

		obj.findIDandFillValues(driver, "input.properties", "AddLocation");
		Thread.sleep(250);
		
		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("LocationSave")));
		
		driver.findElement(By.xpath(locator.getProperty("LocationSave"))).click();
		logClass.info("Saved New Location");

		obj.errorBox(driver, obj.checkError(driver));
		obj.refreshItems(driver, "AddLocation");
		
		logClass.endTestCase("Added a new Location");

	}

	
	@Test(description="Editing Location",priority=1)
	public void EditLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Edit Location on SDM");

		obj.goHome(driver);

		obj.findLocationInGrid(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("LocationEdit"))).click();

		obj.findIDandFillValues(driver, "input.properties", "EditLocation");
		
		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("LocationSaveEdit")));
		
		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("LocationSaveEdit")));
		
		//driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();
		driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();

		driver.switchTo().activeElement();
		driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
		obj.refreshItems(driver, "EditLocation");
		logClass.info("Saved Location");

		logClass.endTestCase("Edited Location");
	}


	@Test(description="Deleting Location",priority=2)
	public void DeleteLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Delete Location on SDM");

		obj.goHome(driver);

		obj.findLocationOrHost(driver, "VM Management");

		obj.findLocationInGrid(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("LocationDelete")));
		
		driver.findElement(By.xpath(locator.getProperty("LocationDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Location");
	}
}
