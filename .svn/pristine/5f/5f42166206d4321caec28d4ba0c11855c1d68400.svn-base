package com.avaya.smgr.RTS.SubnetConfiguration;
/*
 * Test case Related to Subnet Configuration UI
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class SubnetConfiguration_Validations{
	boolean b;
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
@BeforeMethod(alwaysRun=true)
@Test(description="Verification of error message where subnet name is empty")
public void SubnetConfigNameEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,SubnetConfiguration
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Subnet_Configuration"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Subnet.New"));
	//Name as blank and Fill the required details 
	action.WaitForTitle(locator.getProperty("Subnet_Configuration"));
	action.entertext(locator.getProperty("Subnet.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Subnet.Mask"), input.getProperty("Mask"));
	action.ClickButton(locator.getProperty("Subnet.Save"));
	Thread.sleep(2000);
	//Verification of error message where subnet name is empty
	action.VerifyStringValue("Input parameter Discovery Name is Null is Invalid/Null.");
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Subnet_Configuration"));
}
@Test(description="Verification of error message where IP is empty")
public void SubnetConfigIpEmpty() throws Exception{
	action.RefreshPage();
	//Navigate to RTS,SubnetConfiguration
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Subnet_Configuration"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Subnet.New"));
	//IP as blank and Fill the required details 
	action.WaitForTitle(locator.getProperty("Subnet_Configuration"));
	action.entertext(locator.getProperty("Subnet.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Subnet.Mask"), input.getProperty("Mask"));
	action.ClickButton(locator.getProperty("Subnet.Save"));
	Thread.sleep(2000);
	//Verification of error message where IP is empty 
	action.VerifyStringValue("Input parameter Ip Address cannot be null  is Invalid/Null.");
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Subnet_Configuration"));
}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}
@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
 }

