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

public class demo0909 {
	
	public static String comboID(WebDriver driver,String select){
		String returnID = "";
		String sc1 = "var nl = document.getElementById(\"confignewvm\").querySelectorAll('[id^=\"combobox\"]'); return nl;";
		
		JavascriptExecutor exec = (JavascriptExecutor) driver;
		List<WebElement> allPanels = (List<WebElement>) exec.executeScript(sc1);
		System.out.println(allPanels.size());
		
		for(WebElement e : allPanels){
			//System.out.println(e.getAttribute("id"));
			if(e.getAttribute("id").contains("labelEl") && e.getText().equals(select)){
				System.out.println(e.getText());
				System.out.println(e.getAttribute("id"));
				returnID = e.getAttribute("id").replace("labelEl", "inputEl");
				System.out.println(returnID);
			}
		}
		return returnID;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ParserConfigurationException, SAXException, MyException {
		
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator =new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		// TODO Auto-generated method stub
		boolean _Check;

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");
		
		
		obj.FillValues("", obj.chooseOVF("AES-7.0.0.0.0.13.20150629-e50-00.ova"), driver, "148.147.162.221", "test");
/*
		//obj.goHome(driver);
		obj.loginToSite(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"))){
			//addHost();
			System.out.println("Adding Host");
			obj.goHome(driver);
			logClass.info("Added Host as Location was not there beforehand.");
		}
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.properties", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.properties", "VMName221"));
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
		
		obj.comboClick(driver, "combobox-1238", "CM-Simplex-07.0.0.0.441-e55-0.ova");
		Thread.sleep(2500);
		
		driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);
		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));

		//comboID(driver, "Select Software Library:");
		//obj.selectFP(driver, "CM");
		obj.comboClick(driver, "Select Software Library:","SMGR_DEFAULT_LOCAL");
		Thread.sleep(2500);
		obj.comboClick(driver, "Select OVAs:", "CMM-07.0.0.0.440-e55-0.ova");
		Thread.sleep(2500);
		_Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		obj.checkFailureOfHostCapacity(driver);

		obj.exec(!_Check);
		
		obj.FillValues("inputsm.properties", obj.readFromFile("input.properties", "CMFPS"), driver,"148.147.162.221","abc");
		
		//obj.findIDandFillValuesForVM(driver, "input.properties", "AddVM");
*/	}

}
