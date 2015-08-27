package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class VC2508 {
	static Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}

	public static void check(WebDriver driver,List<String> inputIP){

		WebElement table = driver.findElement(By.id(locator.getProperty("gridview-1369")));
		List<WebElement> cells = table.findElements(By.xpath(locator.getProperty(".//*[local-name(.)='td']")));
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
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		Thread.sleep(1500);
		driver.findElement(By.xpath(locator.getProperty("VCenterMap"))).click();
		logClass.info("Clicked on 'Map vCenter' ");

		obj.findvCenterInGrid(driver, "148.147.162.168");

		obj.errorBox(driver, obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("VCenterEdit"))).click();
		Thread.sleep(9000);

		check(driver,inputIP);
		driver.findElement(By.id(locator.getProperty("manageHosts"))).click();

		driver.findElement(By.id(locator.getProperty("gridcolumn-1515-titleEl"))).click();
		driver.findElement(By.id(locator.getProperty("bulkLocationUpdateCombo-inputEl"))).click();
		driver.findElement(By.id(locator.getProperty("bulkLocationUpdateCombo-inputEl"))).sendKeys("Pune");
		Thread.sleep(1500);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty(obj.selBoundList(driver)))));
		obj.boundListSelect(driver, "Pune", obj.selBoundList(driver));

		driver.findElement(By.id(locator.getProperty("button-1350"))).click();
		driver.findElement(By.id(locator.getProperty("commitHostsChangesBtn"))).click();

		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("refreshVMTreeId"))));
		driver.findElement(By.id(locator.getProperty("refreshVMTreeId"))).click();
	}

}
