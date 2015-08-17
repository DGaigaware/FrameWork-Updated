package com.avaya.smgr.spm.settings.smgr;

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


public class Scheduler{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	boolean Accept;
	
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
public void SchedulerUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting SMGR, Scheduler
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Scheduler"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.Retry"));
	action.entertext(locator.getProperty("Scheduler.Retry"), input.getProperty("SingleDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.VerifyElementValue(locator.getProperty("Scheduler.Retry"), input.getProperty("SingleDigitValue"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}

@Test(description="enable of Edit and Done buttons",priority=1)
 public void SchedulerEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="enable of commit and cancel buttons",priority=2)
public void SchedulerCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Commit"));
}
@Test(description="verify that the title value",priority=3)
public void SchedulerTitle() throws Exception{
	//Verification of title "Scheduler"
	action.VerifyStringValue(locator.getProperty("Scheduler"));
	
}

@Test(description="Verify that the error message should display when number of try is empty",priority=4)
public void SchedulertryError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.Retry"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Scheduler.Retry.msg"));
	action.VerifyElementValue(locator.getProperty("Scheduler.Retry.msg"), locator.getProperty("You_must_enter_a_value"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when retry delay field  is empty",priority=4)
public void ScheduleRetryDelayError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.RetryDelay"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Scheduler.RetryDelay.msg"));
	action.VerifyElementValue(locator.getProperty("Scheduler.RetryDelay.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Verify that the error message should display when Initial Context is empty",priority=4)
public void ScheduleIntialError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.Initila"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Scheduler.Initila.msg"));
	action.VerifyElementValue(locator.getProperty("Scheduler.Initila.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Naming Convention is empty",priority=4)
public void ScheduleNamingError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.Naming"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Scheduler.Naming.msg"));
	action.VerifyElementValue(locator.getProperty("Scheduler.Naming.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Provide URL is empty",priority=4)
public void ScheduleProvideURLError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Scheduler.URL"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Scheduler.URL.msg"));
	action.VerifyElementValue(locator.getProperty("Scheduler.URL.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws Exception{
	  
	action.Screenshot(result, action);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
}
