package com.avaya.sdmclient.runnerdemo;

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

public class demolink {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		obj.findVMForHost(driver, "sm3");

		driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		driver.switchTo().activeElement();

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.txt", "CustomerName"));

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.txt", "CustPwd"));

		obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);
		
		obj.chooseLink(driver, "sm3");
	}

}
