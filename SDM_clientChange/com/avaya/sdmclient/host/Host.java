package com.avaya.sdmclient.host;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.avaya.sdmclient.*;
public class Host {
	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	//public static void main(String[] args) throws IOException, InterruptedException {
	// TODO Auto-generated method stub

	@Test(description="Adding Host to given Location")
	public void addHost() throws IOException, InterruptedException{

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);

		logClass.startTestCase("Adding Host to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"));

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");

		driver.findElement(By.xpath(locator.getProperty("HostName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostName"))).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("HostIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostIP"))).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("HostUserName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostUserName"))).sendKeys(obj.readFromFile("input.txt", "NewHostUser"));

		driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).sendKeys(obj.readFromFile("input.txt", "NewHostPwd"));

		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Added Host Succesfully");
	}

	@Test(description="Editing Host to given Location",priority=1)
	public void _EditHost() throws IOException, InterruptedException{

		logClass.startTestCase("Editing Host to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();

		//System.out.println("\n\n\n");

		driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

		Thread.sleep(250);
		obj.boundListSelect(driver, obj.readFromFile("input.txt", "NewHostEditLoc"), obj.selBoundList(driver));
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator.getProperty(linkText)));
driver.findElement(By.linkText(locator.getProperty(linkText)).click();*/
		driver.findElement(By.xpath(locator.getProperty("HostNameEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostNameEdit"))).sendKeys(obj.readFromFile("input.txt", "NewHostName"));

		driver.findElement(By.xpath(locator.getProperty("HostIPEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostIPEdit"))).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("HostUserNameEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostUserNameEdit"))).sendKeys(obj.readFromFile("input.txt", "NewHostUser"));

		driver.findElement(By.xpath(locator.getProperty("HostPassWordEdit"))).clear();
		driver.findElement(By.xpath(locator.getProperty("HostPassWordEdit"))).sendKeys(obj.readFromFile("input.txt", "NewHostPwd"));
		Thread.sleep(250);

		driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited Host Successfully");

	}

	@Test(description="Deleting Host to given Location",priority=2)

	public void _DeleteHost() throws IOException, InterruptedException{
		logClass.startTestCase("Deleting Host to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "testLoc");

		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostName"));

		driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Host");
	}

}
