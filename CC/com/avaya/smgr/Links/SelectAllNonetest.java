package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class SelectAllNonetest {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
	
@Test(description="Verify Select All and Un-Select All fields works Properly for all SMGR Pages")
	public void Select_Test()throws Exception
	{
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		String str1=action.driver.getWindowHandle();
		action.SwithchFrame("iframe0");
		//Click on All Link
		action.driver.findElement(By.xpath(locator.getProperty("All"))).click();
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("Selectallcheckbox"))));
	    //Thread.sleep(5000);
		action.WaitForTitle(locator.getProperty("User_Management"));
		//Verify All CheckBox Selected 
		Thread.sleep(1000);
		action.VerifySelectcheckbox(locator.getProperty("Selectallcheckbox"));
		
		 List<WebElement> rows = action.driver.findElements(By.name("table_1:_s"));
		    int numberofrows = rows.size();
		    for(int i=0;i<numberofrows;i++)
		    {
		    	action.VerifySelectcheckbox(".//*[@id='table_1:"+i+"']");
		    
		    }
		Thread.sleep(1000);
	    //Click on None link in manage users page
	    action.driver.findElement(By.xpath(locator.getProperty("None"))).click();
		action.WaitForTitle(locator.getProperty("User_Management"));
		//Verify All Check box unchecked
		Thread.sleep(1000);
		action.VerifydeSelectcheckbox(locator.getProperty("Selectallcheckbox"));
		
	    for(int i=0;i<numberofrows;i++)
	    {
	    	action.VerifydeSelectcheckbox(".//*[@id='table_1:"+i+"']");
	    
	    }
		action.driver.switchTo().window(str1); 
	}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass
	public void Close() throws IOException, InterruptedException
        {
		action.logout(action);
		}

}
