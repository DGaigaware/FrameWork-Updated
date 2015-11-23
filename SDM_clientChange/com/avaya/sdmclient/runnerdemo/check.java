package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.Settings;

public class check {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		Settings obj = new Settings();
		WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;

		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));

		drive.manage().window().maximize();
		drive.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		//driver.get("https://pdev55vm2.smgrdev.avaya.com");
		drive.get(locator.getProperty("SMGRURL"));
		
		drive.findElement(By.id("IDToken1")).sendKeys(locator.getProperty("UserName"));
	    drive.findElement(By.id("IDToken2")).sendKeys(locator.getProperty("PassWord"));
	    drive.findElement(By.xpath(locator.getProperty("Submit"))).click();
	    obj.debugLogging("Logged in successfully to SMGR: "+locator.getProperty("SMGRURL"), "Info");
	    
	    drive.findElement(By.linkText("Administrators")).click();
	    Thread.sleep(3000);
	    
	    drive.switchTo().frame("iframe0");

	    WebElement table = drive.findElement(By.name("leftnav"));

	    System.out.println(table.getAttribute("id"));
	    System.out.println(table.getText());
	    
	    drive.switchTo().frame(drive.findElement(By.name("leftnav")));
	    
		List<WebElement> cells = drive.findElements(By.xpath(".//*[local-name(.)='td']/a"));
		System.out.println(cells.size());

		obj.debugLogging("Entries on tree : "+cells.size(), "Info");
		
		for(WebElement e : cells)
		{
			
			if(e.getText().equals("Elements")){
				System.out.println(e.getText());
				e.click();
				break;
			}
			
		}
		
	}

}
