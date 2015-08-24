package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

import com.avaya.sdmclient._Settings;
import com.avaya.sdmclient.logClass;

public class finalDynamic {

	public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		_Settings obj = new _Settings();
		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		
		obj._addToList();
		boolean _Check;

		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='newVM_-btnInnerEl']")).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).clear();
		//driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));	
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewVMName")+"1");
		logClass.info("Given Name");
		Thread.sleep(250);
		
		obj._errorBox(driver,obj._checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		Thread.sleep(250);
		obj._errorBox(driver,obj._checkError(driver));
		
		obj._boundListSelect(driver, "data", obj._selBoundList(driver));
		
		//1 - File Path; 3 - SW Library; 4 - URL 

					driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
					logClass.info("Choosen From File");
		
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj._readFromFile("input.txt", "BSMFilePath"));
					driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
					logClass.info("File Path Given");
					Thread.sleep(2500);
			
		//obj._errorBox(driver);

		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj._boundListSelect(driver, "Profile 1", obj._selBoundList(driver));
		//Thread.sleep(250);
		_Check = obj._checkError(driver);
		obj._errorBox(driver,obj._checkError(driver));
	
		driver.findElement(By.xpath(".//*[@id='gridcolumn-1221-textEl']")).click();
		obj._checkFailureOfHostCapacity(driver);
		
		obj._exec(!_Check);
		
		obj.FillValues("", "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf", driver);
	}

}
