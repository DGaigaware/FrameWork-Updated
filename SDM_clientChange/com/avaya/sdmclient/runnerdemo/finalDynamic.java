package com.avaya.sdmclient.runnerdemo;

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
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class finalDynamic {
	static Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}
	public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));

		boolean _Check;

		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, "testHost");

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "NewVMName")+"1");
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundList(driver));

		//1 - File Path; 3 - SW Library; 4 - URL

		driver.findElement(By.xpath(locator.getProperty("OVAFilePath"))).click();
		logClass.info("Choosen From File");

		driver.findElement(By.xpath(locator.getProperty("FilePathField"))).clear();
		driver.findElement(By.xpath(locator.getProperty("FilePathField"))).sendKeys(obj.readFromFile("input.txt", "BSMFilePath"));
		driver.findElement(By.xpath(locator.getProperty("FilePathSubmit"))).click();
		logClass.info("File Path Given");
		Thread.sleep(2500);

		//obj._errorBox(driver);

		driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);

		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));
		//Thread.sleep(250);
		_Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		obj.checkFailureOfHostCapacity(driver);

		obj.exec(!_Check);

		obj.FillValues("", "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf", driver);
	}

}
