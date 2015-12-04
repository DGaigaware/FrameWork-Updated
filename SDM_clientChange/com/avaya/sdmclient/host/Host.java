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
import com.avaya.sdmclient.location.driverGetter;
public class Host extends Location {
	Settings obj = new Settings();
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	//WebDriver driver = driverGetter.driver;
	@Test(description="Adding Host to given Location",priority=3)
	public void addHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Adding Host to given Location");

		obj.goToSDMCliURL(getDriver());

		if(!obj.checkPresenceOfLocationOrHostOrVM(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"))){
			getDriver().navigate().refresh();
			obj.debugLogging("Adding Location as location was not already added ...", "Info");
			obj.debugLogging("Pausing the current thread .. ", "Info");
			Location loc = new Location();
			loc.addLocation();
			obj.debugLogging("Location added successfully. Now resuming the current thread .. ", "Info");
			obj.goToSDMCliURL(getDriver());
		}

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(getDriver(), "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");

		obj.clickButtonxPath(getDriver(), locator.getProperty("New-Host"));
		obj.debugLogging("Adding new host .. ", "Info");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(getDriver(), "input.properties", "AddHost");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
	
		obj.clickButtonxPath(getDriver(), locator.getProperty("SaveHost"));
		Thread.sleep(1250);
		
		obj.confirmDialogBox(getDriver());
		
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(getDriver(), "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");
		
		obj.refreshItems(getDriver(), "AddHost");
		Thread.sleep(1500);
		
		obj.chooseLink(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"), "Host", "Status Details");
		obj.waitForPresenceOfElement(getDriver(), By.id(locator.getProperty("vmDeployStatus")));
		
		obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), getDriver(), 50, "Host Create/Update Completed"),"Info");
		obj.StatusCheck(getDriver(), "Host Create/Update Completed", 20);
		
		//Adding second host
		
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));

		obj.chooseTab(getDriver(), "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");

		obj.clickButtonxPath(getDriver(), locator.getProperty("New-Host"));
		obj.debugLogging("Adding new host .. ", "Info");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(getDriver(), "input.properties", "AddHost1");
		Thread.sleep(250);
		obj.debugLogging("Filled values .. ", "Info");
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("SaveHost"));
		Thread.sleep(1250);
		
		obj.confirmDialogBox(getDriver());
		
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(getDriver(), "Hosts");
		obj.debugLogging("Navigated to Host tab.", "Info");
		
		obj.refreshItems(getDriver(), "AddHost");
		Thread.sleep(1500);
		
		obj.chooseLink(getDriver(), obj.readFromFile("input.properties", "AddHost1HostName:"), "Host", "Status Details");
		obj.waitForPresenceOfElement(getDriver(), By.id(locator.getProperty("vmDeployStatus")));
		
		obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), getDriver(), 50, "Host Create/Update Completed"),"Info");
		obj.StatusCheck(getDriver(), "Host Create/Update Completed", 20);

		obj.debugLogging("Hosts added successfully .. ", "Info");
		logClass.endTestCase("Added Host Successfully");
	}

	@Test(description="Editing Host to given Location",priority=4)
	public void editHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Editing Host to given Location");
		
		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(getDriver(), "Hosts");

		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		Thread.sleep(1250);
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("EditHost"));
		//drive.findElement(By.xpath(locator.getProperty("EditHost"))).click();
		//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

//		obj.selectLocforEditHost(drive);
//		Thread.sleep(250);

		obj.findIDandFillValues(getDriver(), "input.properties", "EditHost");
		Thread.sleep(250);
		
		obj.checkFocus(getDriver(), By.xpath(locator.getProperty("SaveHostEdit")));

		getDriver().findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		obj.errorBox(getDriver(), obj.checkError(getDriver()));
		Thread.sleep(2500);
		
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(getDriver(), "Hosts");
		Thread.sleep(1250);
		logClass.info("In 'Host' Tab");
		
		obj.refreshItems(getDriver(), "EditHost");

		logClass.endTestCase("Edited Host Successfully");

	}

	@Test(description="Deleting Host to given Location",priority=5)

	public void deleteHost() throws IOException, InterruptedException, MyException{
		logClass.startTestCase("Deleting Host to given Location");

		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddLocationName:"));

		obj.findHostInGrid(getDriver(), obj.readFromFile("input.properties", "AddHost1HostName:"));

		obj.checkFocus(getDriver(), By.xpath(locator.getProperty("HostDelete")));

		getDriver().findElement(By.xpath(locator.getProperty("HostDelete"))).click();

		obj.confirmDialogBox(getDriver());

		logClass.endTestCase("Deleted Host");
		//driver.quit();
	}

}
