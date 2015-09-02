package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class demo0109 {
	static Settings obj = new Settings();
	
	public static String getViewFrame(WebDriver driver,String input){
		List<String> types = new ArrayList<>();
		String returnStr = "";
		
		types.add("newlocationview");
		types.add("editlocationview");
		types.add("host_new");
		types.add("host_edit");
		
		if(input.equals("AddLocation"))
			returnStr = types.get(0);
		else if(input.equals("EditLocation"))
			returnStr = types.get(1);
		else if(input.equals("AddHost"))
			returnStr = types.get(2);
		else if(input.equals("EditHost"))
			returnStr = types.get(3);
		
		return returnStr;
	}
	
	public static void findIDandFillValues(WebDriver driver,String filename,String input) throws IOException{
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//String script = "var nl = Ext.getBody().dom.querySelectorAll('[id^=\"text\"]'); return nl;";
		
		String sc = "var nl = document.getElementById(\""+getViewFrame(driver, input)+"\").getElementsByTagName(\"input\"); return nl;";
		String returnID = "";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println("Before");
		System.out.println(elem.size());
		
		for(WebElement e : elem){
			
			System.out.println("ID: "+e.getAttribute("id")+" Attrib "+e.getAttribute("readonly"));
		}
		
		for(WebElement e : elem){
			if(e.getAttribute("readonly")==null){
			System.out.println("Attribb"+e.getAttribute("readonly"));
			WebElement el = driver.findElement(By.id(e.getAttribute("id").replace("input", "label")));
			System.out.println(el.getText());
			returnID = e.getAttribute("id");
			System.out.println(returnID);
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(obj.readFromFile(filename, input+el.getText().replace("*", "").replace(" ", "")));
			}
		}
		
		System.out.println("After");
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		logClass.startTestCase("Add a new Location on SDM");

		obj.loginToSite(driver);

		driver.findElement(By.xpath((locator.getProperty("LocationAdd")))).click();
		logClass.info("Adding new Location");

		obj.findIDandFillValues(driver, "input.txt", "AddLocation");
		
		
		
		Thread.sleep(250);
		
		obj.checkFocus(driver, By.xpath(locator.getProperty("LocationSave")));
		
		//driver.findElement(By.xpath(locator.getProperty("LocationSave"))).click();
		/*logClass.info("Saved New Location");

		obj.errorBox(driver, obj.checkError(driver));
		obj.refreshItems(driver, "AddLocation");
		
		logClass.endTestCase("Added a new Location");*/
		
		//obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHost")));
		//driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		/*obj.confirmDialogBox(driver);
		Thread.sleep(1500);
		
		obj.refreshItems(driver, "AddHost");
		Thread.sleep(1500);
		obj.checkSuccess(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		obj.waitForPresence(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Succesfully");
		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationName:"))){
			driver.navigate().refresh();
			obj.logOut(driver);
			//AddLocation();
			System.out.println("Adding Location");
			obj.goHome(driver);
			logClass.info("Added Location as Location was not there beforehand.");
		}

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationName:"));

		driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");

		Thread.sleep(250);	
		
		//findIDandFillValues(driver, "input.txt", "AddHost");
		*/
		//obj.refreshItems(driver, "AddHost");
		System.out.println("refreshed");
		
	}

}
