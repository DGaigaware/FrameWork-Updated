package com.avaya.sdmclient.vcenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.avaya.sdmclient.*;
public class vCenter {
	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	@Test(description="Adding vCenter to given Location")
	public void _AddvCenter() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		List<String> inputIP = new ArrayList<String>();
		
		inputIP.add("148.147.162.16");
		inputIP.add("148.147.162.18");
		inputIP.add("148.147.162.120");
		
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Adding vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, "NewLocation");
		
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		driver.findElement(By.xpath(".//*[@id='newVcenterBtn-btnInnerEl']")).click();
		logClass.info("Adding vCenter");
		
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).sendKeys(obj.readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).sendKeys(obj.readFromFile("input.txt", "usernameVC"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).sendKeys(obj.readFromFile("input.txt", "passwordVC"));
				
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='findHostBtn-btnIconEl']")).click();
		
		obj.errorBox(driver,obj.checkError(driver));
		Thread.sleep(9000);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gridview-1369")));
		
		obj.check(driver,inputIP);
		driver.findElement(By.id("manageHosts")).click();
		
		driver.findElement(By.id("gridcolumn-1515-titleEl")).click();
		driver.findElement(By.id("bulkLocationUpdateCombo-inputEl")).click();
		driver.findElement(By.id("bulkLocationUpdateCombo-inputEl")).sendKeys("Pune");
		Thread.sleep(1500);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(obj.selBoundList(driver))));
		obj.boundListSelect(driver, "Pune", obj.selBoundList(driver));
		
		driver.findElement(By.id("button-1350")).click();
		driver.findElement(By.id("commitHostsChangesBtn")).click();

		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("refreshVMTreeId")));
		driver.findElement(By.id("refreshVMTreeId")).click();
		
		logClass.endTestCase("Added vCenter Succesfully");
		
	}
/*
	@Test(description="Editing vCenter to given Location",priority=1)
	public void _EditvCenter() throws InterruptedException, IOException{
		
		logClass.startTestCase("Editing vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		obj.findVCenterInGrid(driver, "148.147.162.168");
		
		obj.errorBox(driver, obj.checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='editVcenterBtn-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).sendKeys(obj.readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).sendKeys(obj.readFromFile("input.txt", "usernameVC"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).sendKeys(obj.readFromFile("input.txt", "passwordVC"));
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
		
		obj.findVCenterInGrid(driver, obj.readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='deleteVcenterBtn-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1006-btnIconEl']")).click();
		
		logClass.endTestCase("Deleted Successfully");
	}*/
}
