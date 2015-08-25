package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class test12508 {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
	
	driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
	
	obj.findVMForHost(driver, "testSM221");
	
	driver.findElement(By.linkText("Status Details")).click();
	logClass.info("Checking Status Details");
	
	WebDriverWait wait = new WebDriverWait(driver, 3000);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
	driver.switchTo().activeElement();
	System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
	
	System.out.println(obj.fluentWaitCloseOpen(By.id("vmDeployStatus"), driver, 1500, "VM Deployment Completed"));
	
	//obj.StatusCheck(driver, "VM Deployment Completed", 20);
	}
}
