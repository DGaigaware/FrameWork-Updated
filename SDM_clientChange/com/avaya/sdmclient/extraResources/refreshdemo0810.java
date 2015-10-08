package com.avaya.sdmclient.extraResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class refreshdemo0810 {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		Settings obj = new Settings();
		WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		String shortVMName = "SM";
		
		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		obj.loginToSite(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		obj.findVMForHost(drive,"test"+shortVMName);

		//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		
		obj.findMoreActionsButton(drive);
		Thread.sleep(500);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		drive.switchTo().activeElement();

		obj.refreshVMValues(drive, shortVMName, "inputsm.properties");
		
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
		Thread.sleep(250);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);
		
		//Adding code
		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.chooseTab(drive, "Virtual Machines");

		obj.chooseLink(drive, "test"+shortVMName);
		obj.StatusCheck(drive, "VM Trust Establishment Completed",50);

		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));
		Thread.sleep(1500);
		if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		//Added
		Thread.sleep(5000);
		
		obj.chooseLink(drive, "test"+shortVMName);
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "VM Refresh Completed"));
		obj.StatusCheck(drive, "VM Refresh Completed", 20);
		// Uptill Now
		Thread.sleep(2500);
		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}

}
