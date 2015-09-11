package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class hostOpOther {

	public static void main(String[] args) throws IOException, InterruptedException {

		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;

		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));

		logClass.startTestCase("Testing Other Operations");

		obj.goToSite(driver);

		obj.findLocationOrHost(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		//obj.findIDandFillValues(driver, "input.txt", "AddHost");
		driver.findElement(By.id("chgNetwkParamBtn")).click();
		obj.errorBox(driver, obj.checkError(driver));
		
		driver.findElement(By.id("chgPassBtn")).click();
		obj.errorBox(driver, obj.checkError(driver));
		
		driver.findElement(By.id("updateEsxiHost")).click();
		obj.errorBox(driver, obj.checkError(driver));

		
	}

}
