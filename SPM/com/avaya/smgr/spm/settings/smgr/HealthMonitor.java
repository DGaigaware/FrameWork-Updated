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


public class HealthMonitor{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
	Properties input=null;
	public WebDriver driver;
	Boolean Accept;
	
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
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved", groups={"Sanity"})
public void HealthMonUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Health Monitor 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("HealthMonitor"));
	action.SwithchFrame("iframe0");
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Health.Interval"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Health.Interval"), input.getProperty("TwoDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.DoubleClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Health.Interval"));
	action.VerifyElementValue(locator.getProperty("Health.Interval"), input.getProperty("TwoDigitValue"));
}
@Test(description="Enable of Edit and Done buttons", priority=1)
 public void HealthMonitorEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Enable of commit and cancel buttons", priority=2)	
public void HealthMonitorCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(2000);
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.ClickButton(locator.getProperty("Done"));

}
@Test(description="Verify that the title value", priority=3)
public void HealthMonitorTitle() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	//Verification of Commit and Cancel buttons should be enable
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of title "HealthMonitor"
	action.VerifyStringValue(locator.getProperty("HealthMonitor"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.ClickButton(locator.getProperty("Done"));
}


@Test(description="Verify that the error message should display when Health Monitor Interval field is empty", priority=4)
public void HealthMonIntEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Health.Interval"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Health.Interval.msg"));
	action.VerifyElementValue(locator.getProperty("Health.Interval.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the errror message should display when Health Monitor Retention field is empty", priority=5)
public void HealthMonRetEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Health.Retention"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Health.Interval.msg"));
	action.VerifyElementValue(locator.getProperty("Health.Retention.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Health Monitor Retries field is empty", priority=6)
public void HealthMonRetriesEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Healt.Retries"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Health.Interval.msg"));
	action.VerifyElementValue(locator.getProperty("Healt.Retries.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
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
