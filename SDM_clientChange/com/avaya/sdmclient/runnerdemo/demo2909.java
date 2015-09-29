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

public class demo2909 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		

		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));

		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		//obj.goHome(driver);

		obj.loginToSite(driver);
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		obj.chooseTab(driver, "Virtual Machines");
		obj.findVMForHost(driver,"sm3");

		//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		
		obj.findMoreActionsButton(driver);
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		driver.switchTo().activeElement();

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);
		
		obj.chooseLink(driver, "sm3");
		//driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("RefreshVM")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));

		if(driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		//Added
		Thread.sleep(5000);
		
		obj.chooseLink(driver, "sm3");
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "VM Refresh Completed"));
		obj.StatusCheck(driver, "VM Refresh Completed", 20);
		// Uptill Now
		Thread.sleep(2500);
		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}

}
