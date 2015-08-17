package com.avaya.smgr.spm.dataRetention;
/*
 * Data Retention UI related Test case
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


public class DataRetentionUITestCase{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
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
public void ScreenshptSetup() throws IOException, InterruptedException{
}	
	
@Test(description="Verify that the title is showing correctly",groups={"Sanity"})
public void AgedAlarmPurgeRule() throws Exception{
	//Navigate to configuration
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Data_Retention"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("FirstItem"));
	//2) update the alarm purged the value
	action.WaitForTitle(locator.getProperty("Data_Retention"));
	//3)Verify the Data Retention page is displaying successfully
	action.VerifyStringValue(locator.getProperty("Data_Retention"));
}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		//action logout=new action();
		action.logout(action);
	}
}
