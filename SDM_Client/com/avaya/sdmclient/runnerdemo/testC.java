package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class testC {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
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
			
			obj.findLocationOrHost(driver, "testHost");
			
			driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
			
			WebElement table = driver.findElement(By.id("gridview-1190"));
			List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
			//System.out.println(cells.size()+"\n\n");
		
			for(WebElement e : cells)
			{	System.out.println(e.getText());
				
			}
				
			obj.findVMForHost(driver, "testSM22111");
			/*
			 * Working Fine with Refresh and Polling on Window check
			 * 
			 * 
			 * driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).click();
			
			
			Thread.sleep(5000);
			WebElement table1 = driver.findElement(By.id("gridview-1190"));
			List<WebElement> cells1 = table1.findElements(By.xpath(".//*[local-name(.)='td']"));
			for(WebElement e : cells1)
				
			{	
				if(e.getText().trim().contains("..."))
				{
					System.out.println("next"+e.getText());
					e.findElement(By.linkText("Status Details")).click();
				}
				
			}
			
			System.out.println(obj.fluentWait(By.id("vmDeployStatus"), driver, 50, "VM Refresh Completed"));
			obj._StatusCheck(driver, "VM Refresh Completed", 20);*/
			driver.findElement(By.xpath(".//*[@id='stopvm-btnInnerEl']")).click();
			obj.confirmDialogBox(driver);

			
			WebElement tablet = driver.findElement(By.id("gridview-1190"));
			List<WebElement> cellst = tablet.findElements(By.xpath(".//*[local-name(.)='td']"));
			//System.out.println(cells.size()+"\n\n");
			Thread.sleep(60000);
			for(WebElement e : cellst)
			{	//System.out.println(e.getText());
				if(e.getText().trim().equals("VM_STOP:COMPLETED"))
					{
					e.getText();
					System.out.println("Success");
					}
			}
	
			}
			
	

}
