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

public class demo3108 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		boolean _Check;

		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		obj.goToSite(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"))){
			//addHost1();
			System.out.println("Adding Host");
			obj.goToSite(driver);
			logClass.info("Added Host as Location was not there beforehand.");
		}
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "VMName221")+"1");
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundList(driver));

		//1 - File Path; 3 - SW Library; 4 - URL
		switch(_default){
		case _default:
			driver.findElement(By.xpath(locator.getProperty("OVAFilePath"))).click();
			logClass.info("Choosen From File");

			driver.findElement(By.xpath(locator.getProperty("FilePathField"))).clear();
			driver.findElement(By.xpath(locator.getProperty("FilePathField"))).sendKeys(obj.readFromFile("input.txt", "SMFilePath"));
			driver.findElement(By.xpath(locator.getProperty("FilePathSubmit"))).click();
			logClass.info("File Path Given");
			Thread.sleep(2500);
			break;
		case _URL:
			driver.findElement(By.xpath(locator.getProperty("OVAURL"))).click();
			logClass.info("Choosen From URL");

			driver.findElement(By.xpath(locator.getProperty("URLInput"))).clear();
			driver.findElement(By.xpath(locator.getProperty("URLInput"))).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
			driver.findElement(By.xpath(locator.getProperty("URLSubmit"))).click();
			Thread.sleep(450);
			break;
		case _SWLib:
			driver.findElement(By.xpath(locator.getProperty("OVASWLib"))).click();
			logClass.info("Choosen From Software Library");

			driver.findElement(By.xpath(locator.getProperty("SWLibSelect"))).click();
			WebElement element = driver.findElement(By.id(locator.getProperty("boundlist-1543-listEl")));
			List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("x-boundlist-item")));
			for (WebElement e : tmp1 )
			{
				//System.out.println(e.getText()+ "\n Test \n");
				if(e.getText().contains("SM"))
				{
					System.out.println("\nSelected : \n"+e.getText());
					e.click();
				}
			}
			break;
		}

		//obj._errorBox(driver);
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);

		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));
		//Thread.sleep(250);
		_Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		obj.checkFailureOfHostCapacity(driver);

		obj.exec(!_Check);

		//removed

		obj.FillValues("inputsm.txt", obj.readFromFile("input.txt", "SMOVFPath"), driver);

		//driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

		driver.findElement(By.xpath(locator.getProperty("EULAAccept"))).click();
		logClass.info("Accepted EULA");

		Thread.sleep(9000);
		obj.findLocationOrHost(driver, "testHost");

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		obj.findVMForHost(driver, "testSM221");
		
		Thread.sleep(4500);

		driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		logClass.info("Checking Status Details");

		obj.waitForPresence(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

		System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driver, 1500, "VM Deployment Completed"));
		Thread.sleep(1000);
		//obj.StatusCheck(driver, "VM Deployment Completed", 20);
		obj.closeWindow(driver);
		Thread.sleep(5000);
	}

}
