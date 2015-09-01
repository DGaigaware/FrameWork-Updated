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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class AdDemo25082015 {

	static Settings obj = new Settings();
	static WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	static Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}
	public static void demoAdd() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		boolean _Check;

		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("inputbsm.txt", "VMName225"));
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundList(driver));

		driver.findElement(By.xpath(locator.getProperty("OVAFilePath"))).click();
		logClass.info("Choosen From File");

		driver.findElement(By.xpath(locator.getProperty("FilePathField"))).clear();
		driver.findElement(By.xpath(locator.getProperty("FilePathField"))).sendKeys(obj.readFromFile("input.txt", "BSMFilePath"));
		driver.findElement(By.xpath(locator.getProperty("FilePathSubmit"))).click();
		logClass.info("File Path Given");
		Thread.sleep(2500);


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

		obj.FillValues("inputbsm.txt", "C:\\Users\\bshingala\\Downloads\\BSM-7.0.0.0.700007-e55-01_EXTRACT\\BSM-7.0.0.0.700007_OVF10.ovf", driver);

		driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

		driver.findElement(By.xpath(locator.getProperty("EULAAccept"))).click();
		logClass.info("Accepted EULA");

		Thread.sleep(9000);
		obj.findLocationOrHost(driver, "testHost");

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		obj.findVMForHost(driver, "testSM221");

		driver.findElement(By.linkText(locator.getProperty("Status Details  "))).click();
		logClass.info("Checking Status Details  ");

		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

		System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driver, 1500, "VM Deployment Completed"));
		Thread.sleep(1000);
		//obj.StatusCheck(driver, "VM Deployment Completed", 20);
		obj.closeWindow(driver);
		Thread.sleep(5000);
	}
	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		demoAdd();
	}

}
