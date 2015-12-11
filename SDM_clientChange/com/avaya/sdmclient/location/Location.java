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
	static Settings obj = new Settings();
	private static WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	//WebDriver driver = driverGetter.driver;
	//Add location
	@Test(description="Adding Location",priority=0)
	public void addLocation() throws IOException, InterruptedException, MyException {
		
		logClass.startTestCase("Add a new Location on SDM");

		obj.goToSDMCliURL(getDriver());

		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationAdd"));
		obj.debugLogging("Adding new Location .. ", "Info");

		// Find all ID and fill values for Location
		obj.findIDandFillValues(getDriver(), "input.properties", "AddLocation");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationSave"));
		obj.debugLogging("Checking if any errors are occurring or not .. ", "Info");

		//obj.confirmDialogBox(driver);
		Thread.sleep(450);
		obj.debugLogging("Location added successfully .. ", "Info");
		//obj.refreshItems(drive, "AddLocation");
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationAdd"));
		obj.debugLogging("Adding new Location .. ", "Info");

		// Find all ID and fill values for Location
		obj.findIDandFillValues(getDriver(), "input.properties", "AddLocation1");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationSave"));
		Thread.sleep(450);
		obj.debugLogging("Location added successfully .. ", "Info");

		obj.errorBox(getDriver(), obj.checkError(getDriver()));
		logClass.endTestCase("Added a new Location");

	}

	// Edit location	
	@Test(description="Editing Location",priority=1)
	public void editLocation() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Edit Location on SDM");
		obj.goToSDMCliURL(getDriver());
		
		obj.findLocationInGrid(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationEdit"));
		
		// Find all ID and then fill values for editing location
		obj.findIDandFillValues(getDriver(), "input.properties", "EditLocation");
		obj.waitForPresenceOfElement(getDriver(), By.xpath(locator.getProperty("LocationSaveEdit")));

		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationSaveEdit"));
		Thread.sleep(450);
		obj.confirmDialogBox(getDriver());
		obj.debugLogging("Location edited successfully .. ", "Info");

		logClass.endTestCase("Edited Location");
	}

	// Delete location
	@Test(description="Deleting Location",priority=2)
	public void deleteLocation() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Delete Location on SDM");

		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), "VM Management");
		obj.findLocationInGrid(getDriver(), obj.readFromFile("input.properties", "AddLocation1Name:"));

		obj.clickButtonxPath(getDriver(), locator.getProperty("LocationDelete"));
		Thread.sleep(450);
		obj.confirmDialogBox(getDriver());
		Thread.sleep(2500);
		//check whether location is present or not
		if(!obj.checkPresenceOfLocationOrHostOrVM(getDriver(), obj.readFromFile("input.properties", "AddLocation1Name:")))
			obj.debugLogging("Location deleted successfully .. ", "Info");
		else
			obj.debugLogging("Error occurred while deleting location.\nPlease Check screenshot.", "Error");

		logClass.endTestCase("Deleted Location");
		//driver.quit();
	}
	/**
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public static void setDriver(WebDriver driver) {
		Location.driver = driver;
	}

	
}
