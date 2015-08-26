package com.avaya.sdmclient.runnerdemo;
import com.avaya.sdmclient.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class extraOperations {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		logClass.startTestCase("Editing Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
	
		obj.findHostInGrid(driver, obj.readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='editHostBtn-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "username175"));
		
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).sendKeys(obj.readFromFile("input.txt", "password175"));
		Thread.sleep(250);
		
		driver.findElement(By.xpath(".//*[@id='saveOnEditHost-btnInnerEl']")).click();
		
		//obj.errorBox(driver, obj.checkError(driver));
		
		//obj.confirmDialogBox(driver);
		Thread.sleep(3500);
		
		obj.checkSuccess(driver, obj.readFromFile("input.txt", "HostName175"));
		
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
		System.out.println(obj.fluentWait(By.id("vmDeployStatus"), driver, 50, "Host Create/Update Completed"));
		
		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Succesfully");
    }

}
