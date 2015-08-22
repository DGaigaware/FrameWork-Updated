package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient._Settings;
import com.avaya.sdmclient.logClass;

public class demo {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		_Settings obj = new _Settings();
		obj._addToList();
		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";
		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		//driver.manage().window().maximize();
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
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys("testSM22111");
		logClass.info("Given Name");
		Thread.sleep(250);
		
		obj._errorBox(driver,obj._checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		Thread.sleep(250);
		obj._errorBox(driver,obj._checkError(driver));
		
		obj._boundListSelect(driver, "data", obj._selBoundList(driver));
		
		Thread.sleep(250);
		
		driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
		logClass.info("Choosen From File");

		driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj._readFromFile("input.txt", "FilePath"));
		driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
		logClass.info("File Path Given");
		Thread.sleep(500);

		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj._boundListSelect(driver, "Profile 1", obj._selBoundList(driver));
		//Thread.sleep(250);

		obj._errorBox(driver,obj._checkError(driver));
	
		driver.findElement(By.xpath(".//*[@id='gridcolumn-1221-textEl']")).click();
		obj._checkFailureOfHostCapacity(driver);
		
		
		obj._boundListSelect(driver, "2,500", obj._selBoundList(driver));
		
		//System.out.println(obj._AutoFill(driver, "hostname"));
		
		//driver.findElement(By.id(obj._AutoFill(driver, "hostname"))).sendKeys("SMGR");
		
		System.out.println("abc \n");
		obj._AutoFillIP(driver, "ipfsnetmask0","255.255.255.0");
		
		obj._AutoFill(driver, "timezone");
		
		//driver.findElement(By.id(obj._AutoFill(driver, "timezone"))).click();
		
		obj._boundListSelect(driver, "Asia/Kolkata", obj._selBoundList(driver));
		
	}

}
