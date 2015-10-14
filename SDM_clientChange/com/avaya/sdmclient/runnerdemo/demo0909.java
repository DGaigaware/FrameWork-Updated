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
import com.avaya.sdmclient.extraResources.MyException;

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

		/*System.out.println("Starting to edit VM "+ VMName);
		logClass.info("Starting to edit VM "+ VMName);
		String shortVMName = obj.shortVMName(VMName);*/
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(driver, "Virtual Machines");
		obj.findVMForHost(driver, "sm3");
		
		driver.findElement(By.xpath(locator.getProperty("EditVM"))).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);

		//driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

		obj.editVMchooseFPorFQDN(driver, "FQDN");
		driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

		obj.editVM(driver,"null","null");
		
		/*driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).sendKeys(IP);

		driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).sendKeys(shortVMName+"edited");*/

		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("VMEditSave")));
		driver.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited VM Successfully");
	}

}
