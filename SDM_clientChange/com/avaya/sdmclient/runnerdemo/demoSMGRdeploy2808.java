package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

import utility.UserAction;

public class demoSMGRdeploy2808 {
	

	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParserConfigurationException, SAXException, MyException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		Properties locator = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		
		logClass.startTestCase("Adding VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"))){
			//addHost1();
			System.out.println("Adding Host");
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
			logClass.info("Added Host as Location was not there beforehand.");
		}
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewHostIP"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "VMSMGRName"));
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "server", obj.selBoundList(driver));

		//1 - File Path; 3 - SW Library; 4 - URL
		
			driver.findElement(By.xpath(locator.getProperty("OVASWLib"))).click();
			logClass.info("Choosen From Software Library");

			driver.findElement(By.xpath(locator.getProperty("SWLibSelect"))).click();
			
			obj.boundListSelect(driver, "SMGR", obj.selBoundList(driver));
			
			Thread.sleep(1500);
			
		obj.errorBox(driver,obj.checkError(driver));
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);

		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));
		//Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		obj.checkFailureOfHostCapacity(driver);


		//removed

		obj.FillValues("inputsmgr.txt", "C:\\Program Files\\Avaya\\SDMClient\\SDM_API\\SMGR-7.0.0.0.16266-e55-43.ovf", driver,"","");

		driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();
	}

}
