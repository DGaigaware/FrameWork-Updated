package com.avaya.sdmclient.host;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.avaya.sdmclient.*;
public class Host {
	Settings obj = new Settings();

	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
	//public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	
	@Test(description="Adding Host to given Location")	
	public void addHost() throws IOException, InterruptedException{
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Adding Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
				
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"));

		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
		
		driver.findElement(By.xpath(".//*[@id='newHostBtn_-btnInnerEl']")).click();
		logClass.info("Adding new Host");
		
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostPwd"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='saveNewHost']")).click();
	
		obj.confirmDialogBox(driver);

		logClass.endTestCase("Added Host Succesfully");
	}		
		
	@Test(description="Editing Host to given Location",priority=1)
		public void _EditHost() throws IOException, InterruptedException{

		logClass.startTestCase("Editing Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
	
		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='editHostBtn-btnInnerEl']")).click();
		
		//System.out.println("\n\n\n");
		
		driver.findElement(By.xpath(".//*[@id='combobox-1137-inputEl']")).click();
		
		Thread.sleep(250);
		obj.boundListSelect(driver, obj.readFromFile("input.txt", "NewHostEditLoc"), obj.selBoundList(driver));
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
	    driver.findElement(By.linkText(linkText)).click();*/
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostName"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewHostPwd"));
		Thread.sleep(250);
		
		driver.findElement(By.xpath(".//*[@id='saveOnEditHost-btnInnerEl']")).click();
		
		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited Host Successfully");
		
	}
	
	@Test(description="Deleting Host to given Location",priority=2)
		
		public void _DeleteHost() throws IOException{
			logClass.startTestCase("Deleting Host to given Location");
			
			driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
			driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
			logClass.info("Clicked on VM management");
			
			obj.findLocationOrHost(driver, "testLoc");
		
			obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostName"));
			
			driver.findElement(By.xpath(".//*[@id='deleteHostBtn-btnInnerEl']")).click();

			obj.confirmDialogBox(driver);
			
			logClass.endTestCase("Deleted Host");
		}

}
