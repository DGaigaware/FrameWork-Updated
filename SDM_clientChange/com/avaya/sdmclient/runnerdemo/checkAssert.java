package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;

public class checkAssert {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, MyException {
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		String shortVMName = "SM";
		
		logClass.startTestCase("Restart VM to given Location and Host");

		obj.loginToSite(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.chooseTab(driver, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(driver,"test"+shortVMName);

		obj.clickButtonxPath(driver, locator.getProperty("VMRestart"));
		
		Thread.sleep(1500);
		obj.confirmDialogBox(driver);
		
		Thread.sleep(2500);
		
		obj.checkCompletion(driver, "test"+shortVMName, 0, 18, 4);
	}

}
