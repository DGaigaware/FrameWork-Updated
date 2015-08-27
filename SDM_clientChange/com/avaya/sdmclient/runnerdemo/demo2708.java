package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class demo2708 {
	static Settings obj = new Settings();
	static WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\bshingala\\Desktop\\xprev.properties"));


			//Thread.sleep(5000);
			logClass.startTestCase("Refresh VM to given Location and Host");

			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(prop.getProperty("VM-Management"))).click();
			logClass.info("Clicked on VM management");

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));

			driver.findElement(By.xpath(prop.getProperty("VM-Tab"))).click();

			obj.findVMForHostT(driver, "testBSM225");

			driver.findElement(By.xpath(prop.getProperty("VMMoreAction"))).click();
			Thread.sleep(500);
			driver.findElement(By.xpath(prop.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("VMReEstConnUN"))));
			driver.switchTo().activeElement();

			driver.findElement(By.xpath(prop.getProperty("VMReEstConnUN"))).clear();
			driver.findElement(By.xpath(prop.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.txt", "CustomerName"));

			driver.findElement(By.xpath(prop.getProperty("VMReEstConnPw"))).clear();
			driver.findElement(By.xpath(prop.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.txt", "CustPwd"));

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("VMReEstConnConf"))));
			Thread.sleep(250);
			driver.findElement(By.xpath(prop.getProperty("VMReEstConnConf"))).click();

			Thread.sleep(5000);
			driver.findElement(By.linkText(prop.getProperty("Status Details"))).click();
			obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("RefreshVM"))));

			if(driver.findElement(By.xpath(prop.getProperty("RefreshVM"))).isEnabled())
				driver.findElement(By.xpath(prop.getProperty("RefreshVM"))).click();
			//Added
			Thread.sleep(5000);
			WebElement table1 = driver.findElement(By.id(prop.getProperty("gridview-1190")));
			List<WebElement> cells1 = table1.findElements(By.xpath(prop.getProperty(".//*[local-name(.)='td']")));
			for(WebElement e : cells1)

			{
				if(e.getText().trim().contains("..."))
				{
					System.out.println("next"+e.getText());
					e.findElement(By.linkText(prop.getProperty("Status Details"))).click();
				}

			}
			System.out.println(obj.fluentWait(By.id(prop.getProperty("vmDeployStatus")), driver, 50, "VM Refresh Completed"));
			obj.StatusCheck(driver, "VM Refresh Completed", 20);
			// Uptill Now
			Thread.sleep(2500);
			//obj.StatusCheck(driver, "VM Refresh Completed", 50);
			logClass.endTestCase("VM refreshed Successfully");
		}

}
