package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class testDemo {

	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
			locator=new Properties();
			locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		
		boolean _Check;

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		//obj.goHome(driver);
		obj.loginToSite(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"))){
			//addHost();
			System.out.println("Adding Host");
			obj.goHome(driver);
			logClass.info("Added Host as Location was not there beforehand.");
		}
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "VMName221"));
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundList(driver));
		Thread.sleep(2500);
		
		obj.comboClick(driver, "combobox-1235","SMGR_DEFAULT_LOCAL");
		Thread.sleep(2500);
		
		obj.comboClick(driver, "combobox-1238", "US-7.0.0.0.0.11-e55-01_OVF10.ova");
		Thread.sleep(2500);
		
		/*driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);
		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));*/

		_Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		obj.checkFailureOfHostCapacity(driver);

		obj.exec(!_Check);

		//removed

		obj.FillValues("inputsm.txt", obj.readFromFile("input.txt", "USFP"), driver);
	}

}
