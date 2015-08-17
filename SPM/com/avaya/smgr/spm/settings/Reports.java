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
import org.testng.annotations.Test;

import utility.UserAction;


public class Reports{
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
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved",groups={"Sanity"})
public void ReportsUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Agent Management 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Reports"));
	action.ClickLink(locator.getProperty("Configuration"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Reports.Periodic"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Reports.Periodic"), input.getProperty("TwoDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.VerifyElementValue(locator.getProperty("Reports.Periodic"), input.getProperty("TwoDigitValue"));
}
@Test(description="Verify that the Edit and Done buttons are enabled",priority=1)
public void ReportsEditDoneEnableBtn() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,Reports 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Reports"));
	action.ClickLink(locator.getProperty("Configuration"));
	action.SwithchFrame("iframe0");
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));

}
@Test(description="Verify that the commit and cancel buttons are enabled",priority=2)
public void ReportsMgmtService() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.ClickButton(locator.getProperty("Done"));
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
