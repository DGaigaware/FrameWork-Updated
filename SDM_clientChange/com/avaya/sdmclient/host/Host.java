package com.avaya.sdmclient.host;

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
import com.avaya.sdmclient.*;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.location.Location;
public class Host {
	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	@Test(description="Adding Host to given Location",priority=3)
	public void addHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Adding Host to given Location");

		obj.goToSDMCliURL(driver);

		if(!obj.checkPresenceOfLocationOrHostOrVM(driver, obj.readFromFile("input.properties", "AddLocationName:"))){
			driver.navigate().refresh();
			obj.debugLogging("Adding Location as location was not already added ...", "Info");
			obj.debugLogging("Pausing the current thread .. ", "Info");
			Location loc = new Location();
			loc.addLocation();
			obj.debugLogging("Location added successfully. Now resuming the current thread .. ", "Info");
			obj.goToSDMCliURL(driver);
		}

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");

		obj.clickButtonxPath(driver, locator.getProperty("New-Host"));
		obj.debugLogging("Adding new host .. ", "Info");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(driver, "input.properties", "AddHost");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
	
		obj.clickButtonxPath(driver, locator.getProperty("SaveHost"));
		Thread.sleep(1250);
		
		obj.confirmDialogBox(driver);
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");
		
		obj.refreshItems(driver, "AddHost");
		Thread.sleep(1500);
		
		obj.chooseLink(driver, obj.readFromFile("input.properties", "AddHostHostName:"), "Host", "Status Details");
		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"),"Info");
		obj.StatusCheck(driver, "Host Create/Update Completed", 20);
		
		//Adding second host
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.chooseTab(driver, "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");

		obj.clickButtonxPath(driver, locator.getProperty("New-Host"));
		obj.debugLogging("Adding new host .. ", "Info");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(driver, "input.properties", "AddHost1");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(driver, locator.getProperty("SaveHost"));
		Thread.sleep(1250);
		
		obj.confirmDialogBox(driver);
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");
		
		obj.refreshItems(driver, "AddHost");
		Thread.sleep(1500);
		
		obj.chooseLink(driver, obj.readFromFile("input.properties", "AddHost1HostName:"), "Host", "Status Details");
		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"),"Info");
		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		obj.debugLogging("Hosts added successfully .. ", "Info");
		logClass.endTestCase("Added Host Successfully");
	}

	@Test(description="Editing Host to given Location",priority=4)
	public void editHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Editing Host to given Location");
		
		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");

		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		Thread.sleep(1250);
		
		obj.clickButtonxPath(driver, locator.getProperty("EditHost"));
		//drive.findElement(By.xpath(locator.getProperty("EditHost"))).click();
		//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

//		obj.selectLocforEditHost(drive);
//		Thread.sleep(250);

		obj.findIDandFillValues(driver, "input.properties", "EditHost");
		Thread.sleep(250);
		
		obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHostEdit")));

		driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		Thread.sleep(2500);
		
		obj.refreshItems(driver, "EditHost");

		logClass.endTestCase("Edited Host Successfully");

	}

	@Test(description="Deleting Host to given Location",priority=5)

	public void deleteHost() throws IOException, InterruptedException, MyException{
		logClass.startTestCase("Deleting Host to given Location");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHost1HostName:"));

		obj.checkFocus(driver, By.xpath(locator.getProperty("HostDelete")));

		driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Host");
		driver.quit();
	}

}
