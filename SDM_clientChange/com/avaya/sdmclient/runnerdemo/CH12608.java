package com.avaya.sdmclient.runnerdemo;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class CH12608 {
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}

	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	@Test(description="Adding Host to given Location",priority=3)
	public void addHost1() throws IOException, InterruptedException{

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);

		logClass.startTestCase("Adding Host to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"))){
			//_AddLocation();
			System.out.println("Adding Location");
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
			logClass.info("Added Location as Location was not there beforehand.");
		}

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"));

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");

		driver.findElement(By.xpath(locator.getProperty("HostName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostName"))).sendKeys(obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("HostIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostIP"))).sendKeys(obj.readFromFile("input.txt", "HostIP175"));

		driver.findElement(By.xpath(locator.getProperty("HostUserName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostUserName"))).sendKeys(obj.readFromFile("input.txt", "username175"));

		driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).sendKeys(obj.readFromFile("input.txt", "password175"));

		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		obj.confirmDialogBox(driver);

		Thread.sleep(2500);

		obj.checkSuccess(driver, obj.readFromFile("input.txt", "HostName175"));

		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Succesfully");
	}
}
