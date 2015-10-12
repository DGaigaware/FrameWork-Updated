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

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, MyException {
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
		obj.chooseTab(drive, "Virtual Machines");
		obj.findVMForHost(drive,"test"+shortVMName);
		
		obj.findMoreActionsButton(drive);
		Thread.sleep(500);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();
		Thread.sleep(500);
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		drive.switchTo().activeElement();

		obj.refreshVMValues(drive, shortVMName, "inputsm.properties");
		Thread.sleep(750);
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
		Thread.sleep(250);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
		
		Thread.sleep(1000);
		obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+shortVMName, 5, true,10);
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
		Thread.sleep(1500);
		if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		Thread.sleep(5000);
		
		obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+ shortVMName, 50, true,10);		
		
		Thread.sleep(2500);
		logClass.endTestCase("VM refreshed Successfully");
	}

}
