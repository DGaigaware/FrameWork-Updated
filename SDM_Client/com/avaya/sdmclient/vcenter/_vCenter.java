package com.avaya.sdmclient.vcenter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.avaya.sdmclient.*;
public class _vCenter {
	_Settings obj = new _Settings();

	WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
	@Test(description="Adding vCenter to given Location")
	public void _AddvCenter() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Adding vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "NewLocation");
		
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		driver.findElement(By.xpath(".//*[@id='newVcenterBtn-btnInnerEl']")).click();
		logClass.info("Adding vCenter");
		
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).sendKeys(obj._readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).sendKeys(obj._readFromFile("input.txt", "usernameVC"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).sendKeys(obj._readFromFile("input.txt", "passwordVC"));
				
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='findHostBtn-btnIconEl']")).click();
		
		obj._errorBox(driver,obj._checkError(driver));
		
		logClass.endTestCase("Added vCenter Succesfully");
		
	}

	@Test(description="Editing vCenter to given Location",priority=1)
	public void _EditvCenter() throws InterruptedException, IOException{
		
		logClass.startTestCase("Editing vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		obj._findVCenterInGrid(driver, "148.147.162.168");
		
		obj._errorBox(driver, obj._checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='editVcenterBtn-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).sendKeys(obj._readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).sendKeys(obj._readFromFile("input.txt", "usernameVC"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).sendKeys(obj._readFromFile("input.txt", "passwordVC"));
		Thread.sleep(450);
		
		driver.findElement(By.xpath(".//*[@id='findHostBtn-btnIconEl']")).click();
		logClass.endTestCase("Edited vCenter Successfully");
	}
	
	@Test(description="Deleting Host to given Location",priority=2)
	public void _DeletevCenter() throws IOException, InterruptedException{
		
		logClass.startTestCase("Deleting vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		obj._findVCenterInGrid(driver, obj._readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='deleteVcenterBtn-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1006-btnIconEl']")).click();
		
		logClass.endTestCase("Deleted Successfully");
	}
}
