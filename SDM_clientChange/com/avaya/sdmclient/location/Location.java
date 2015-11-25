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
	public void addLocation() throws IOException, InterruptedException, MyException {

		logClass.startTestCase("Add a new Location on SDM");

		obj.goToSDMCliURL(driver);

		obj.clickButtonxPath(driver, locator.getProperty("LocationAdd"));
		obj.debugLogging("Adding new Location .. ", "Info");

		obj.findIDandFillValues(driver, "input.properties", "AddLocation");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(driver, locator.getProperty("LocationSave"));
		obj.debugLogging("Checking if any errors are occurring or not .. ", "Info");

		//obj.confirmDialogBox(driver);
		Thread.sleep(450);
		obj.debugLogging("Location added successfully .. ", "Info");
		//obj.refreshItems(drive, "AddLocation");
		
		obj.clickButtonxPath(driver, locator.getProperty("LocationAdd"));
		obj.debugLogging("Adding new Location .. ", "Info");

		obj.findIDandFillValues(driver, "input.properties", "AddLocation1");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(driver, locator.getProperty("LocationSave"));
		Thread.sleep(450);
		obj.debugLogging("Location added successfully .. ", "Info");

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Added a new Location");

	}


	@Test(description="Editing Location",priority=1)
	public void editLocation() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Edit Location on SDM");
		obj.goToSDMCliURL(driver);
		
		obj.findLocationInGrid(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.clickButtonxPath(driver, locator.getProperty("LocationEdit"));
		
		obj.findIDandFillValues(driver, "input.properties", "EditLocation");
		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("LocationSaveEdit")));

		obj.clickButtonxPath(driver, locator.getProperty("LocationSaveEdit"));
		Thread.sleep(450);
		obj.confirmDialogBox(driver);
		obj.debugLogging("Location edited successfully .. ", "Info");

		logClass.endTestCase("Edited Location");
	}


	@Test(description="Deleting Location",priority=2)
	public void deleteLocation() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Delete Location on SDM");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, "VM Management");
		obj.findLocationInGrid(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.clickButtonxPath(driver, locator.getProperty("LocationDelete"));
		//obj.confirmDialogBox(driver);
		
		if(!obj.checkPresenceOfLocationOrHostOrVM(driver, obj.readFromFile("input.properties", "AddLocationName:")))
			obj.debugLogging("Location deleted successfully .. ", "Info");
		else
			obj.debugLogging("Error occurred while deleting location.\nPlease Check screenshot.", "Error");

		logClass.endTestCase("Deleted Location");
		driver.quit();
	}

	
}
