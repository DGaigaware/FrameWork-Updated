package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class VC2508 {

	
	public static void check(WebDriver driver,List<String> inputIP){
		
		WebElement table = driver.findElement(By.id("gridview-1369"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		System.out.println(cells.size()+"\n\n");
		for(int i=0;i<inputIP.size();i++)
		{
			for(WebElement e : cells)
		
			{	//System.out.println(e.getText());
				if(e.getText().trim().equals(inputIP.get(i)))
					{
						e.click();
						System.out.println("Added: "+e.getText());
					}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		List<String> inputIP = new ArrayList<String>();
		
		inputIP.add("148.147.162.16");
		inputIP.add("148.147.162.18");
		inputIP.add("148.147.162.120");
		
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
			driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			//driver.manage().window().maximize();
			
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
			Thread.sleep(9000);
			
			check(driver,inputIP);
			driver.findElement(By.id("manageHosts")).click();
			
			driver.findElement(By.id("gridcolumn-1515-titleEl")).click();
			driver.findElement(By.id("bulkLocationUpdateCombo-inputEl")).click();
			driver.findElement(By.id("bulkLocationUpdateCombo-inputEl")).sendKeys("Pune");
			Thread.sleep(1500);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(obj.selBoundList(driver))));
			obj.boundListSelect(driver, "Pune", obj.selBoundList(driver));
			
			driver.findElement(By.id("button-1350")).click();
			driver.findElement(By.id("commitHostsChangesBtn")).click();

			obj.confirmDialogBox(driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("refreshVMTreeId")));
			driver.findElement(By.id("refreshVMTreeId")).click();
	}

}
