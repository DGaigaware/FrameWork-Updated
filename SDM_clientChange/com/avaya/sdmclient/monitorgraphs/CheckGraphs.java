package com.avaya.sdmclient.monitorgraphs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;

public class CheckGraphs {
	
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	

	@Test(description="Check graphs for hosts",priority=0)
	public void checkGraphForHosts() throws IOException, InterruptedException, MyException {

		
		logClass.startTestCase("Check graphs for hosts");

		obj.loginToSite(driver);
		
		Thread.sleep(450);
		obj.clickLinksOnLeftMenu(driver, "Monitor Hosts");
		Thread.sleep(450);
		obj.findComboBoxForGraphs(driver, "graphViewForm", "Hosts:");

		obj.boundListSelect(driver, "148.147.162.196", obj.selBoundList(driver));
		Thread.sleep(250);
		
		driver.findElement(By.id("idGenerateGraphBtn-btnIconEl")).click();
		Thread.sleep(5250);
		
		obj.errorBox(driver, obj.checkError(driver));
		
		obj.takeScreenShotForDriver(driver);
		
	}
	
	@Test(description="Check graphs for VMs",priority=1)
	public void checkGraphForVMs() throws IOException, InterruptedException, MyException {

		logClass.startTestCase("Check graphs for VMs");

		obj.goHome(driver);
		//obj.loginToSite(driver);
		Thread.sleep(450);
		obj.clickLinksOnLeftMenu(driver, "Monitor VMs");
		
		//graphViewForm
		Thread.sleep(450);
		obj.findComboBoxForGraphs(driver, "graphViewForm", "Hosts:");

		obj.boundListSelect(driver, "148.147.162.196", obj.selBoundList(driver));
		Thread.sleep(250);

		obj.errorBox(driver, obj.checkError(driver));

		obj.findComboBoxForGraphs(driver, "graphViewForm", "Virtual machines:");

		obj.boundListSelect(driver, "pdev55vm2", obj.selBoundList(driver));
		Thread.sleep(250);
		
		driver.findElement(By.id("idGenerateGraphBtn-btnIconEl")).click();
		Thread.sleep(2250);
		obj.errorBox(driver, obj.checkError(driver));
		obj.takeScreenShotForDriver(driver);
	}
		
}
