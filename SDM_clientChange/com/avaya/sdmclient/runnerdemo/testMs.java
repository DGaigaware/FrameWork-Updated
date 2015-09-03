package com.avaya.sdmclient.runnerdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class testMs {
	
	public void adLoc() throws InterruptedException, IOException{
		Settings obj = new Settings();
		
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
 			
			
			logClass.startTestCase("Add a new Location on SDM");
			
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
			logClass.info("Clicked on VM management");
			
			driver.findElement(By.xpath(".//*[@id='addnewlocation-btnInnerEl']")).click();
			logClass.info("Adding new Location");
			
			driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewLocation"));
			
			driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).sendKeys(obj.readFromFile("input.txt", "Address"));
		
			driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).sendKeys(obj.readFromFile("input.txt", "City"));
			
			driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).sendKeys(obj.readFromFile("input.txt", "State"));
			
			driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).sendKeys(obj.readFromFile("input.txt", "ZIP"));
			
			driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).clear();
			driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).sendKeys(obj.readFromFile("input.txt", "Country"));
			
			Thread.sleep(250);
			driver.findElement(By.xpath(".//*[@id='savenewlocation-btnInnerEl']")).click();
			logClass.info("Saved New Location");
			
			logClass.endTestCase("Added a new Location");
			
		}
	
	public void editLoc() throws IOException, InterruptedException{
		Settings obj = new Settings();
		
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
				
				logClass.startTestCase("Delete Location on SDM");
				
				driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
				driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
				logClass.info("Clicked on VM management");
				
				obj.findLocationOrHost(driver, "VM Management");
				
				obj.findLocationInGrid(driver, obj.readFromFile("input.txt", "Location"));
				
				driver.findElement(By.xpath(".//*[@id='deletelocation-btnInnerEl']")).click();	
				
				obj.confirmDialogBox(driver);
				
				logClass.endTestCase("Deleted Location");
 		}		
}
