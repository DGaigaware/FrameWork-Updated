package com.avaya.smgr.workassgnment.attributeproperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Basic {

	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the page title")
public void Page_Title() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.VerifyTitle(locator.getProperty("Work_Assignment"));
	action.SwithchFrame("iframe0");
	
	action.VerifyStringValue(locator.getProperty("Assignment_Management"));
	action.VerifyStringValue(locator.getProperty("Attribute_Management"));
	action.VerifyStringValue(locator.getProperty("Property_Management"));	
	//verify the help link in table
	for(int i=1;i<=3;i++)
	{
		String str1=action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr["+i+"]/td[3]")).getText();
		Assert.assertEquals(str1, locator.getProperty("Help"));
	}	
}


@Test(description="Verify the Help page")
public void Verify_Help_Page() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.SwithchFrame("iframe0");

	String parentHandle = action.driver.getWindowHandle();
	action.ClickLink(locator.getProperty("Help"));
	// Switch to help page
	for (String winHandle : action.driver.getWindowHandles())
	{
	    action.driver.switchTo().window(winHandle); 
	}
	//verify the title of help page
	action.WaitForTitle(locator.getProperty("Avaya_Work_Assignment_Snap_in_online_help"));
	action.VerifyTitle(locator.getProperty("Avaya_Work_Assignment_Snap_in_online_help"));
	//Close help page
	action.driver.close(); 
	// Switch to work assignment page
	action.driver.switchTo().window(parentHandle);
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
