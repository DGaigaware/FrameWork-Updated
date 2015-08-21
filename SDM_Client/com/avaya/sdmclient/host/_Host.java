package com.avaya.sdmclient.host;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.avaya.sdmclient.*;
public class _Host {
	_Settings obj = new _Settings();

	WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
	
	//public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	
	@Test(description="Adding Host to given Location")	
	public void addHost() throws IOException, InterruptedException{
	
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Adding Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
				
		obj._findLocationOrHost(driver, "testLoc");

		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
		
		driver.findElement(By.xpath(".//*[@id='newHostBtn_-btnInnerEl']")).click();
		logClass.info("Adding new Host");
		
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostPwd"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='saveNewHost']")).click();
	
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();

		logClass.endTestCase("Added Host Succesfully");
	}		
		
	@Test(description="Editing Host to given Location",priority=1)
		public void _EditHost() throws IOException, InterruptedException{
		
		logClass.startTestCase("Editing Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
	
		obj._findHostInGrid(driver, "148.147.162.196");
		
		driver.findElement(By.xpath(".//*[@id='editHostBtn-btnInnerEl']")).click();
		
		System.out.println("\n\n\n");
		
		driver.findElement(By.xpath(".//*[@id='combobox-1137-inputEl']")).click();
		obj._addToList();
		
		obj._boundListSelect(driver, obj._readFromFile("input.txt", "NewLoc"), 4);
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
	    driver.findElement(By.linkText(linkText)).click();*/
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostName"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostPwd"));
		Thread.sleep(250);
		
		driver.findElement(By.xpath(".//*[@id='saveOnEditHost-btnInnerEl']")).click();
		logClass.endTestCase("Edited Host Succesfully");
		
	}
	
	@Test(description="Deleting Host to given Location",priority=2)
		
		public void _DeleteHost() throws IOException{
			logClass.startTestCase("Deleting Host to given Location");
			
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
			logClass.info("Clicked on VM management");
			
			obj._findLocationOrHost(driver, "testLoc");
		
			obj._findHostInGrid(driver, obj._readFromFile("input.txt", "NewHostName"));
			
			driver.findElement(By.xpath(".//*[@id='deleteHostBtn-btnInnerEl']")).click();

			obj._confirmDialogBox(driver);
			
			logClass.endTestCase("Deleted Host");
		}

}
