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


public class AlarmManagementService{
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
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved",groups={"Sanity"})
public void alarmMgmtUpdate() throws Exception{
	//Navigate to Configuration page, setting,Agent Management 
	action.ClickLink("Configurations");
	action.ClickLink("Settings");
	action.ClickLink(locator.getProperty("Alarm_Management_Service"));
	action.ClickLink(locator.getProperty("Alarm_Management"));
	action.SwithchFrame("iframe0");
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Alrm.EmailFrwd"));
	action.entertext(locator.getProperty("Alrm.EmailFrwd"), input.getProperty("TrueValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForObj(locator.getProperty("Alrm.EmailFrwd"));
	action.VerifyElementValue(locator.getProperty("Alrm.EmailFrwd"), input.getProperty("TrueValue"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	//action.WaitForTitle(locator.getProperty("SPMHome_Screen"));
}
@Test(description="Verify that the title is showing correctly",groups={"Sanity"},priority=1)
 public void alarmMgmtServiceTitle() throws Exception{
	//Verify the Alarm Management is showing in the page.
	action.VerifyStringValue(locator.getProperty("Alarm_Management"));
}
@Test(description="Verify that the Edit and Done buttons are enabled",priority=2)
public void alarmEditDoneEnableBtn() throws Exception{
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);

}
@Test(description="Verify that the commit and cancel buttons are enabled",priority=3)
public void alarmMgmtService() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.ClickButton(locator.getProperty("Done"));
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
