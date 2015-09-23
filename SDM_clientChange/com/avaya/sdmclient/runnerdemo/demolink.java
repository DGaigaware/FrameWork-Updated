package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class demolink {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		obj.findVMForHost(driver, "sm3");

		driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		driver.switchTo().activeElement();

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.txt", "CustomerName"));

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.txt", "CustPwd"));

		obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);
		
		//obj.chooseLink(driver, "sm3");
		
		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		WebElement el = null;
		//System.out.println(cells.size()+"\n\n");
		String id = "";
		
		
		for(WebElement e : cells)
		{	System.out.println("RTest "+e.getText());
			if(e.getText().contains("sm3"))
				{
					el = e;
					id = e.getAttribute("id");
					System.out.println("Row ID: "+id);
				}
			
				//e.findElement(By.linkText("Status Details")).click();
			System.out.println("After");
			//Thread.sleep(4500);
			//System.out.println("Dep "+e.findElement(By.className("deployinprogress")).getText());
			
			//System.out.println(e.findElement(By.tagName("href")));
		}
		driver.findElement(By.id(id)).findElement(By.className("deployinprogress")).click();
		//el.findElement(By.className("deployinprogress")).click();
		
		/*JavascriptExecutor js = (JavascriptExecutor)driver;
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
		
		driver.findElement(By.id("combobox-1238-inputEl")).click();
		driver.findElement(By.id("combobox-1238-inputEl")).sendKeys("SM-7.0.0.0.700007-e55-01.ova");
		obj.maintainedList(driver,"combobox-1238-inputEl");*/
		
		settingsForConcThreads aa = new settingsForConcThreads();
		List<String> a = aa.readOVAs();
		for(String s : a)
			System.out.println(s);
	}

}
