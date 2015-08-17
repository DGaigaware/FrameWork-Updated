package com.avaya.smgr.spm.settings;
//
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


public class EventProcessor{
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
public void ScreenshptSetup() throws IOException, InterruptedException{
}
@Test(description="Verify that the Edit and Done buttons are enabled",groups={"Sanity"})
public void eventEditDoneEnableBtn() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,Event Processor Service 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Event_Processor_Service"));
	action.ClickLink(locator.getProperty("Event_Processor"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("View_Profile"));
	//Verification of Edit and Done buttons should be enable
	action.VerifyDisableButton(locator.getProperty("Edit"));
	action.VerifyDisableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the title is showing correctly")
public void eventprocessor() throws Exception{
	//Verification of title "Event processor"
	action.VerifyStringValue(locator.getProperty("Event_Processor"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
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
