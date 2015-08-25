package com.avaya.sdmclient.location;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class Location {
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
	@Test(description="Adding Location")
	public void _AddLocation() throws IOException, InterruptedException {
		
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
		driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FAddress"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FCity"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FState"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FZIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FCountry"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='savenewlocation-btnInnerEl']")).click();
		logClass.info("Saved New Location");
		
		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Added a new Location");
		
	}
	
	
	@Test(description="Editing Location",priority=1)
	public void _EditLocation() throws IOException{
		
		logClass.startTestCase("Edit Location on SDM");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
	
		obj.findLocationInGrid(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='editlocation-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textareafield-1089-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textareafield-1089-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewAddress"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1090-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1090-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewCity"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1091-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1091-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewState"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1092-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1092-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewZIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1093-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1093-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewCountry"));
		
		driver.findElement(By.xpath(".//*[@id='saveeditlocation-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='saveeditlocation-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();
		logClass.info("Saved Location");
		
		logClass.endTestCase("Edited Location");
	}
	
	
	@Test(description="Deleting Location",priority=2)
	public void _DeleteLocation() throws IOException{
		
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
