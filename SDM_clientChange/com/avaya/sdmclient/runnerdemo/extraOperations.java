package com.avaya.sdmclient.runnerdemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class extraOperations {

	static Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		logClass.startTestCase("Editing Host to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();

		driver.findElement(By.xpath(locator.getProperty("HostNameEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostNameEdit"))).sendKeys(obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("HostIPEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostIPEdit"))).sendKeys(obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("HostUserNameEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostUserNameEdit"))).sendKeys(obj.readFromFile("input.txt", "username175"));

		driver.findElement(By.xpath(locator.getProperty("HostPassWordEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostPassWordEdit"))).sendKeys(obj.readFromFile("input.txt", "password175"));
		Thread.sleep(250);

		driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		//obj.errorBox(driver, obj.checkError(driver));

		//obj.confirmDialogBox(driver);
		Thread.sleep(3500);

		obj.checkSuccess(driver, obj.readFromFile("input.txt", "HostName175"));

		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Succesfully");
	}

}
