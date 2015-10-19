package com.avaya.sdmclient.vcenter;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.*;
import com.avaya.sdmclient.extraResources.MyException;
public class vCenter {
	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	@Test(description="Adding vCenter to given Location")
	public void _AddvCenter() throws IOException, InterruptedException, MyException {
		// TODO Auto-generated method stub


		List<String> inputIP = new ArrayList<String>();

		inputIP.add("148.147.162.16");
		inputIP.add("148.147.162.18");
		inputIP.add("148.147.162.120");

		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		logClass.startTestCase("Adding vCenter to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "NewLocation");

		driver.findElement(By.xpath(locator.getProperty("VCenterMap"))).click();
		logClass.info("Clicked on 'Map vCenter' ");

		driver.findElement(By.xpath(locator.getProperty("VCenterAdd"))).click();
		logClass.info("Adding vCenter");

		driver.findElement(By.xpath(locator.getProperty("VCenterIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterIP"))).sendKeys(obj.readFromFile("input.txt", "vCenterIP"));

		driver.findElement(By.xpath(locator.getProperty("VCenterUserName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterUserName"))).sendKeys(obj.readFromFile("input.txt", "usernameVC"));

		driver.findElement(By.xpath(locator.getProperty("VCenterPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterPw"))).sendKeys(obj.readFromFile("input.txt", "passwordVC"));

		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("VCenterFindHost"))).click();

		obj.errorBox(driver,obj.checkError(driver));
		Thread.sleep(9000);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("VCVMList"))));

		obj.check(driver,inputIP);
		driver.findElement(By.id(locator.getProperty("manageHosts"))).click();

		driver.findElement(By.id(locator.getProperty("gridcolumn-1515-titleEl"))).click();
		driver.findElement(By.id(locator.getProperty("bulkLocationUpdateCombo-inputEl"))).click();
		driver.findElement(By.id(locator.getProperty("bulkLocationUpdateCombo-inputEl"))).sendKeys("Pune");
		Thread.sleep(1500);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty(obj.selBoundList(driver)))));
		obj.boundListSelect(driver, "Pune", obj.selBoundList(driver));

		driver.findElement(By.id(locator.getProperty("button-1350"))).click();
		driver.findElement(By.id(locator.getProperty("commitHostsChangesBtn"))).click();

		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("refreshVMTreeId"))));
		driver.findElement(By.id(locator.getProperty("refreshVMTreeId"))).click();

		logClass.endTestCase("Added vCenter Succesfully");

	}

	@Test(description="Editing vCenter to given Location",priority=1)
	public void _EditvCenter() throws InterruptedException, IOException, MyException{

		logClass.startTestCase("Editing vCenter to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(locator.getProperty("VCenterMap"))).click();
		logClass.info("Clicked on 'Map vCenter' ");

		obj.findvCenterInGrid(driver, "148.147.162.168");

		obj.errorBox(driver, obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("VCenterEdit"))).click();

		driver.findElement(By.xpath(locator.getProperty("VCenterIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterIP"))).sendKeys(obj.readFromFile("input.txt", "vCenterIP"));

		driver.findElement(By.xpath(locator.getProperty("VCenterUserName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterUserName"))).sendKeys(obj.readFromFile("input.txt", "usernameVC"));

		driver.findElement(By.xpath(locator.getProperty("VCenterPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VCenterPw"))).sendKeys(obj.readFromFile("input.txt", "passwordVC"));
		Thread.sleep(450);

		driver.findElement(By.xpath(locator.getProperty("VCenterFindHost"))).click();
		logClass.endTestCase("Edited vCenter Successfully");
	}

	@Test(description="Deleting Host to given Location",priority=2)
	public void _DeletevCenter() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Deleting vCenter to given Location");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(locator.getProperty("VCenterMap"))).click();
		logClass.info("Clicked on 'Map vCenter' ");

		obj.findvCenterInGrid(driver, obj.readFromFile("input.txt", "vCenterIP"));

		driver.findElement(By.xpath(locator.getProperty("VCenterDelete"))).click();

		driver.switchTo().activeElement();
		driver.findElement(By.xpath(locator.getProperty("ConfButton1"))).click();

		logClass.endTestCase("Deleted Successfully");
	}
}
