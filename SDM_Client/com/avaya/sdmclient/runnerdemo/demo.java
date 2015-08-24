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

public class demo {

	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		_Settings obj = new _Settings();
		obj._addToList();
		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";
		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		//driver.manage().window().maximize();
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "VM Management");
		
		obj._findLocationInGrid(driver, obj._readFromFile("input.txt", "NewHostEditLoc"));
		
		driver.findElement(By.xpath(".//*[@id='deletelocation-btnInnerEl']")).click();	
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Deleted Location");
	}

}
