package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;


public class hostOp {
		
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	
	@Test(description="Adding Host to given Location")
	public void AddHost() throws IOException, InterruptedException{

		logClass.startTestCase("Adding Host to given Location");

		obj.goHome(driver);
		//obj.loginToSite(driver);
		
		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"))){
			driver.navigate().refresh();
			obj.logOut(driver);
			//AddLocation();
			System.out.println("Adding Location");
			logClass.info("Location was not there. Adding it and pausing current thread.");
			obj.goHome(driver);
			//obj.loginToSite(driver);
			logClass.info("Added Location as Location was not there beforehand.");
		}

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(driver, "input.properties", "AddHost");
		Thread.sleep(250);
		
		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("SaveHost")));
		
		driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		obj.confirmDialogBox(driver);
		Thread.sleep(4500);
		
		obj.refreshItems(driver, "AddHost");
		Thread.sleep(1500);
		
		//obj.checkSuccess(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);
		
		//Adding second Host
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");
		Thread.sleep(250);			
	
		obj.findIDandFillValues(driver, "input.properties", "AddHost1");
		Thread.sleep(250);
		
		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("SaveHost")));
		
		driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		obj.confirmDialogBox(driver);
		Thread.sleep(4500);
		
		obj.refreshItems(driver, "AddHost");
		Thread.sleep(1500);
		
		//obj.checkSuccess(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Successfully");
	}

	@Test(description="Editing Host to given Location",priority=1)
	public void EditHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Editing Host to given Location");

		obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();
		//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

		obj.selectLocforEditHost(driver);
		Thread.sleep(250);

		obj.findIDandFillValues(driver, "input.properties", "EditHost");
		Thread.sleep(250);
		
		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("SaveHostEdit")));

		driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		Thread.sleep(2500);
		
		obj.refreshItems(driver, "EditHost");
		
		logClass.endTestCase("Edited Host Successfully");

	}
	
	@Test(description="Operations to check is the host is AVP or not and perform operations for AVP Host",priority=2)

	public void AVPHostOperations() throws IOException, InterruptedException, MyException{
		
		logClass.startTestCase("Checking if the host is AVP or not... (ANd performing operations for the same)");

		obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));
		
		driver.findElement(By.id("chgNetwkParamBtn")).click();
		if(obj.checkError(driver)){
			obj.errorBox(driver, obj.checkError(driver));
		}
		else{
			System.out.println("AVP host...");
			System.out.println("Changing network parameters..");
			obj.changeNetworkParamsHost(driver,"input.properties","ChangeNetWorkParams");
		}
		
		driver.findElement(By.id("chgPassBtn")).click();
		if(obj.checkError(driver)){
			obj.errorBox(driver, obj.checkError(driver));
		}
		else{
			System.out.println("AVP host...");
			System.out.println("Changing password..");
			obj.changePassWordHost(driver,"input.properties","UpdatePwdHost");
		}

		driver.findElement(By.id("updateEsxiHost")).click();
		if(obj.checkError(driver)){
			obj.errorBox(driver, obj.checkError(driver));
		}
		else{
			System.out.println("AVP host...");
			System.out.println("Updating AVP Host..");
			obj.updateESXiHost(driver,"input.properties","UpdateHost");
		}
	}

	@Test(description="Deleting Host to given Location",priority=3)

	public void DeleteHost() throws IOException, InterruptedException{
		logClass.startTestCase("Deleting Host to given Location");

		obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationCity:"));

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("HostDelete")));
		
		driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Host");
	}

}
