package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public static void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}

	public static void addHostsToManangedTable(WebDriver driver,List<String> inputIP) throws IOException, InterruptedException{
		setup();
		
		for(int i=0;i<inputIP.size();i++)
		{
			WebElement table = driver.findElement(By.id(locator.getProperty("VCVMList")));
			List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='td']")));
			System.out.println(i+" No of Elements "+cells.size());
			
			for(WebElement e : cells)

			{	//System.out.println(e.getText());
				if(e.getText().trim().equals(inputIP.get(i)))
				{
					e.click();
					System.out.println("Added: "+e.getText());
					driver.findElement(By.id(locator.getProperty("VCArrowToAdd"))).click();
					Thread.sleep(1500);
					break;
				}
			}
		}
	}
	
	public static void selectAllHosts(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.getElementById(\"hostGridPanel\").querySelectorAll('[id^=\"headercontainer\"]');return nl;";
		
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		
		String tempID = elem2.get(0).getAttribute("id");
		System.out.println(tempID);
		
		String sc2 = "var nl = document.getElementById(\""+tempID+"\").querySelectorAll('[id^=\"gridcolumn\"]');return nl;";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc2);
		System.out.println(elem.size());
		
		String tempID1 = elem.get(0).getAttribute("id");
		System.out.println(tempID1);
		
		driver.findElement(By.id(tempID1)).click();
	}
	
	public static void confirmBulkUpdate(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.getElementById(\"hostGridPanel\").querySelectorAll('[id^=\"button\"]');return nl;";
		
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		
		for(WebElement e : elem2){
			//System.out.println(e.getText());
			if(e.getText().equals("Bulk Update")){
				System.out.println("Clicked bulk updated.");
				e.click();
				break;
			}
		}
	}
	
	public static void comboClickforvCenter(WebDriver driver, String selCombo,String input) throws IOException, InterruptedException{
		String cmbid = "bulkLocationUpdateCombo-inputEl";
		Settings obj  = new Settings();
		driver.findElement(By.id(cmbid)).click();
		driver.findElement(By.id(cmbid)).clear();
		driver.findElement(By.id(cmbid)).sendKeys(input);
		Thread.sleep(2000);
		
		setup();
		WebElement element = driver.findElement(By.id((obj.selBoundList(driver))));
		List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		for (WebElement e : tmp1 )
		{
			//System.out.println(e.getText()+ "\n Test \n");
			if(e.getText().equals(input))
			{
				System.out.println("Selected value for: "+selCombo+" "+input+" "+e.getText());
				e.click();
			}
		}
		//boundListSelect(driver, input, selBoundList(driver));
	}
	
	public static void main(String[] args) throws InterruptedException, IOException, MyException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		setup();
		List<String> inputIP = new ArrayList<String>();

		inputIP.add("148.147.162.16");
		inputIP.add("148.147.162.18");
		inputIP.add("148.147.178.120");
		inputIP.add("148.147.162.78");

		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));

		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		//driver.manage().window().maximize();

		logClass.startTestCase("Editing vCenter to given Location");

		//obj.goToSDMCliURL(driver);
		obj.loginToSite(driver);
		
		Thread.sleep(2500);
		obj.clickMapvCenter(driver);
		//driver.findElement(By.xpath(locator.getProperty("VCenterMap"))).click();
		logClass.info("Clicked on 'Map vCenter' ");
		Thread.sleep(2500);
		
		obj.findvCenterInGrid(driver, "148.147.162.168");

		//obj.errorBox(driver, obj.checkError(driver));

		driver.findElement(By.id("editVcenterBtn-btnEl")).click();
		Thread.sleep(9000);

		addHostsToManangedTable(driver,inputIP);
		
		selectAllHosts(driver);
		
		comboClickforvCenter(driver, "", "Pune");
		//driver.findElement(By.id(locator.getProperty("gridcolumn-1515-titleEl"))).click();
		/*driver.findElement(By.id(("bulkLocationUpdateCombo-inputEl"))).click();
		driver.findElement(By.id(("bulkLocationUpdateCombo-inputEl"))).sendKeys("Pune");
		Thread.sleep(1500);
		
		System.out.println(obj.selBoundList(driver));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id((obj.selBoundList(driver)))));
		Thread.sleep(1500);
		obj.boundListSelect(driver, "Pune", obj.selBoundList(driver));*/
		WebDriverWait wait = new WebDriverWait(driver, 15);
		Thread.sleep(500);
		confirmBulkUpdate(driver);
		
		//driver.findElement(By.id(locator.getProperty("button-1350"))).click();
		driver.findElement(By.id(("commitHostsChangesBtn"))).click();

		Thread.sleep(750);
		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("RefreshTree"))));
		driver.findElement(By.id(locator.getProperty("RefreshTree"))).click();
	}

}
