package com.avaya.sdmclient.vcenter;

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
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class VCEdit {
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
					Thread.sleep(500);
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
		String cmbid = selCombo;
		Settings obj  = new Settings();
		driver.findElement(By.id(cmbid)).click();
		driver.findElement(By.id(cmbid)).clear();
		driver.findElement(By.id(cmbid)).sendKeys(input);
		Thread.sleep(2000);
		
		setup();
		WebElement element = driver.findElement(By.id((obj.selBoundListID(driver))));
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
	
	public static void updateVCHost(WebDriver driver,String inputIP,String location) throws IOException, InterruptedException{
		WebElement table = driver.findElement(By.id(locator.getProperty("VCManagedHostsGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		System.out.println(cells.size());
		String rowID = "";
		String cmbid = locator.getProperty("VCBulkEditLocCombo");
		
		WebElement tempEle;
		for(WebElement e : cells){
			System.out.println(e.getText()+e.getAttribute("id"));
			if(e.getText().contains(inputIP)){
				rowID = e.getAttribute("id");
				e.click();
				System.out.println(rowID);
				break;
			}
		}
		
		driver.findElement(By.id(cmbid)).click();
		driver.findElement(By.id(cmbid)).clear();
		driver.findElement(By.id(cmbid)).sendKeys("random");
		Thread.sleep(1500);
		
		tempEle = driver.findElement(By.id(rowID));
		WebElement ele = tempEle.findElement(By.xpath((".//*[local-name(.)='td']/div/img")));
		ele.click();
		
		//rowLocationId-inputEl
		comboClickforvCenter(driver, "rowLocationId-inputEl", location);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.querySelectorAll('[id^=\"roweditor\"]');return nl;";
		
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		String tempeleID = elem2.get(0).getAttribute("id");
		
		String sc2 = "var nl = document.getElementById(\""+tempeleID+"\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc2);
		
		for(WebElement e : elem){
			if(e.getText().equals("Update")){
				System.out.println(e.getAttribute("id"));
				e.click();
			}
		}
		
	}
	
	public static void bulkUpdate(WebDriver driver,List<String> IPs,String location) throws IOException, InterruptedException{
		WebElement table = driver.findElement(By.id(locator.getProperty("VCManagedHostsGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		String tempID = "";
		
		for(WebElement e : cells){
			for(String s : IPs){
				if(e.getText().contains(s)){
					tempID = e.getAttribute("id");
					WebElement ele = e.findElement(By.id(tempID)).findElement(By.xpath((".//*[local-name(.)='td']/div/div")));
					//e.findElement(By.xpath((".//*[@id='"+tempID+"']/td[0]/div/div"))).click();
					ele.click();
				}
			}
		}
		
		comboClickforvCenter(driver, locator.getProperty("VCBulkEditLocCombo"), location);
		confirmBulkUpdate(driver);
		
	}
	
	//public static void main(String[] args) throws InterruptedException, IOException, MyException {

	@Test(description="Editing vCenter",priority=0)
	public static void editVC() throws IOException, InterruptedException{
		Settings obj = new Settings();
		setup();
		
		List<String> inputIP = new ArrayList<String>();
		List<String> tempIPs = new ArrayList<String>();
		tempIPs.add("148.147.162.18");
		tempIPs.add("148.147.178.120");

		inputIP.add("148.147.162.78");

		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);

		logClass.startTestCase("Editing vCenter to given Location");

		obj.loginToSite(driver);
		Thread.sleep(2500);
		
		obj.clickMapvCenter(driver);
		logClass.info("Clicked on 'Map vCenter' ");
		Thread.sleep(2500);
		
		obj.findvCenterInGrid(driver, "148.147.162.168");
		driver.findElement(By.id("editVcenterBtn-btnEl")).click();
		Thread.sleep(9000);
		addHostsToManangedTable(driver,tempIPs);
		bulkUpdate(driver,tempIPs,"testLoc");
		driver.findElement(By.id((locator.getProperty("VCCommit")))).click();
		Thread.sleep(750);
		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("RefreshTree"))));
		driver.findElement(By.id(locator.getProperty("RefreshTree"))).click();
		Thread.sleep(11500);
		
		obj.clickMapvCenter(driver);
		logClass.info("Clicked on 'Map vCenter' ");
		Thread.sleep(2500);
		
		obj.findvCenterInGrid(driver, "148.147.162.168");
		driver.findElement(By.xpath(locator.getProperty("VCenterEdit"))).click();
		Thread.sleep(9000);
		addHostsToManangedTable(driver,inputIP);
		updateVCHost(driver, "148.147.162.78","Pune");
		driver.findElement(By.id((locator.getProperty("VCCommit")))).click();
		Thread.sleep(750);
		obj.confirmDialogBox(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("RefreshTree"))));
		driver.findElement(By.id(locator.getProperty("RefreshTree"))).click();
		Thread.sleep(11500);
		
		obj.clickMapvCenter(driver);
		logClass.info("Clicked on 'Map vCenter' ");
		Thread.sleep(2500);
		
		obj.findvCenterInGrid(driver, "148.147.162.168");
		driver.findElement(By.xpath(locator.getProperty("VCenterEdit"))).click();
		Thread.sleep(9000);
		selectAllHosts(driver);
		Thread.sleep(500);
		driver.findElement(By.id(locator.getProperty("VCArrowToRemove"))).click();
		driver.findElement(By.id((locator.getProperty("VCCommit")))).click();
		Thread.sleep(750);
		obj.confirmDialogBox(driver);
		Thread.sleep(2500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("RefreshTree"))));
		driver.findElement(By.id(locator.getProperty("RefreshTree"))).click();
		
		System.out.println("Sucessfully completed adding and removing hosts from vCenter");
		driver.quit();
	}

}
