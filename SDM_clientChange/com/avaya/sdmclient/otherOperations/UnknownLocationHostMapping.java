package com.avaya.sdmclient.otherOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.vcenter.VCEdit;


public class UnknownLocationHostMapping {
	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	
	public void updateLoc(WebDriver driver,String inputIP) throws IOException, InterruptedException{
		WebElement table = driver.findElement(By.id("unknownHostGridPanel-body"));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		System.out.println(cells.size());
		String rowID = "";
		String cmbid = locator.getProperty("VCBulkEditLocCombo");
		String temp = "";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String scr = "var nl = document.querySelectorAll('[id^=\"combobox\"]');return nl;";
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
		
		tempEle = driver.findElement(By.id(rowID));
		WebElement ele = tempEle.findElement(By.xpath((".//*[local-name(.)='td']/div/img")));
		ele.click();
		
		ArrayList<WebElement> eles = (ArrayList<WebElement>) js.executeScript(scr);
		for(WebElement e : eles){
			System.out.println(e.getAttribute("id"));
			if(e.getAttribute("placeholder")!=null){
				if(e.getAttribute("placeholder").equals("Select Location")){
					System.out.println(e.getAttribute("id"));
					temp = e.getAttribute("id");
					break;
				}
				
			}
		
		}
		//rowLocationId-inputEl
		VCEdit.comboClickforvCenter(driver, temp, "Pune");
		
		
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
	
	
	@Test(description="Unknown Location Host Mapping",priority=0)
	
	public void unknownLocaHostMap() throws MyException, IOException, InterruptedException{
		obj.loginToSite(drive);
		
		obj.clickLinksOnLeftMenu(drive, "Unknown location host mapping");
		
		updateLoc(drive, "148.147.162.196");
		
		drive.findElement(By.id("submitBtn-btnIconEl")).click();
		
		Thread.sleep(3500);
		obj.confirmDialogBox(drive);
	}
}
