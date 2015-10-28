package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;

public class deleteVM {
	

	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}

	@Test(description="Deleting VM to given Location and Host",priority=14)
	
	public void DeleteVM() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Delete VM to given Location and Host");

		String shortVMName = "CMM";
		
		//obj.goHome(drive);

		obj.loginToSite(drive);
		
		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1550);
		obj.findVMForHost(drive, "test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));
		obj.clickButtonxPath(drive, locator.getProperty("VMDelete"));
		//drive.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

		obj.confirmDialogBox(drive);

		logClass.endTestCase("Deleted VM successfully");
		
		//obj.maintainedList(driver, "combobox-1238-inputEl");
		drive.quit();
		
}
}