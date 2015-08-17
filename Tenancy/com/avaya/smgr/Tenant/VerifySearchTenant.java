package com.avaya.smgr.Tenant;

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


public class VerifySearchTenant {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the Search operation of tenant")
public void searchtenant() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	//Verify the buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Tsearchbtn"));
	action.VerifyEnableButton(locator.getProperty("TClearsearch"));
	action.ClearText(locator.getProperty("Searchtenant"));
	//Enter tenant name in search box
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("TName"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	//Verify the Tenant search result
	String Str=action.driver.findElement(By.xpath(locator.getProperty("Tenantnameabspath"))).getText();
	Assert.assertEquals(Str, input.getProperty("TName"));
	//Click on Clear button
	action.ClickButton(locator.getProperty("TClearsearch"));
	//Enter tenant name which does not exist
	action.entertext(locator.getProperty("Searchtenant"), input.getProperty("TName1"));
	//Click on Search button
	action.ClickButton(locator.getProperty("Tsearchbtn"));
	//verify the following message
	action.VerifyStringValue("No Data Found ...");
	
}


@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}
}
