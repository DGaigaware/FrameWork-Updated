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

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class cHost2608 {

	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));

	//public static void main(String[] args) throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}

	@Test(description="Adding VM to given Location")
	public void AddVMSuite() throws InterruptedException, IOException, ParserConfigurationException, SAXException {

		boolean _Check;

		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";

		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		System.out.println("Before");

		if(obj.checkLocationOrHost(driver, "148.147.162.175"))
		{
			addHost();
			System.out.println("Adding Host");
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
			logClass.info("Clicked on VM management");
		}

		System.out.println("Host added Successfully");
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "VMName221"));
		logClass.info("Given Name");
		Thread.sleep(250);
	}

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

		logClass.endTestCase("Added Host Succesfully");
	}


}
