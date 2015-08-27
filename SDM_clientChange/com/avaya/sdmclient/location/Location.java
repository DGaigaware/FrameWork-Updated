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

public class Location {
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}
	@Test(description="Adding Location")
	public void _AddLocation() throws IOException, InterruptedException {

		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		logClass.startTestCase("Add a new Location on SDM");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		driver.findElement(By.xpath(locator.getProperty("LocationAdd"))).click();
		logClass.info("Adding new Location");

		driver.findElement(By.xpath(locator.getProperty("LocationName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationName"))).sendKeys(obj.readFromFile("input.txt", "NewLocation"));

		driver.findElement(By.xpath(locator.getProperty("LocationAddress"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationAddress"))).sendKeys(obj.readFromFile("input.txt", "FAddress"));

		driver.findElement(By.xpath(locator.getProperty("LocationCity"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationCity"))).sendKeys(obj.readFromFile("input.txt", "FCity"));

		driver.findElement(By.xpath(locator.getProperty("LocationState"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationState"))).sendKeys(obj.readFromFile("input.txt", "FState"));

		driver.findElement(By.xpath(locator.getProperty("LocationZIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationZIP"))).sendKeys(obj.readFromFile("input.txt", "FZIP"));

		driver.findElement(By.xpath(locator.getProperty("LocationCountry"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationCountry"))).sendKeys(obj.readFromFile("input.txt", "FCountry"));

		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("LocationSave"))).click();
		logClass.info("Saved New Location");

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Added a new Location");

	}


	@Test(description="Editing Location",priority=1)
	public void _EditLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Edit Location on SDM");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationInGrid(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("LocationEdit"))).click();

		driver.findElement(By.xpath(locator.getProperty("LocationAddressEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationAddressEdit"))).sendKeys(obj.readFromFile("input.txt", "NewAddress"));

		driver.findElement(By.xpath(locator.getProperty("LocationCityEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationCityEdit"))).sendKeys(obj.readFromFile("input.txt", "NewCity"));

		driver.findElement(By.xpath(locator.getProperty("LocationStateEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationStateEdit"))).sendKeys(obj.readFromFile("input.txt", "NewState"));

		driver.findElement(By.xpath(locator.getProperty("LocationZIPEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationZIPEdit"))).sendKeys(obj.readFromFile("input.txt", "NewZIP"));

		driver.findElement(By.xpath(locator.getProperty("LocationCountryEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("LocationCountryEdit"))).sendKeys(obj.readFromFile("input.txt", "NewCountry"));

		driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();
		driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();

		driver.switchTo().activeElement();
		driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
		logClass.info("Saved Location");

		logClass.endTestCase("Edited Location");
	}


	@Test(description="Deleting Location",priority=2)
	public void _DeleteLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Delete Location on SDM");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "VM Management");

		obj.findLocationInGrid(driver, obj.readFromFile("input.txt", "Location"));

		driver.findElement(By.xpath(locator.getProperty("LocationDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Location");
	}

}
