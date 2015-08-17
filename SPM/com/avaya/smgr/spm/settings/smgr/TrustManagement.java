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


public class TrustManagement{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
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
public void TrustMgmtUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,TrustManagement
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Trust_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("TrustMgmt.Threshold"));
	action.entertext(locator.getProperty("TrustMgmt.Threshold"), input.getProperty("TwoDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.VerifyElementValue(locator.getProperty("TrustMgmt.Threshold"), input.getProperty("TwoDigitValue"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}

@Test(description="Enable Edit and Done buttons",priority=1)
 public void TrustMgmtEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));

}
@Test(description="Enable of commit and cancel buttons",priority=2)
public void TrustMgmtCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(2000);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);

}
@Test(description="Verify the title value",priority=2)
public void TrustMgmtTitle() throws Exception{
	action.VerifyStringValue(locator.getProperty("Trust_Management"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}

@Test(description="Verify that the error message should display when alarm Certificate is empty",priority=3)
public void TrustMgmtCertEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("TrustMgmt.Alarm"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("TrustMgmt.Alarm.msg"));
	action.VerifyElementValue(locator.getProperty("TrustMgmt.Alarm.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Auto Renew is empty",priority=3)
public void TrustMgmtAutoRenewEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("TrustMgmt.AutoRenew"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("TrustMgmt.AutoRenew.msg"));
	action.VerifyElementValue(locator.getProperty("TrustMgmt.AutoRenew.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Threshold is empty",priority=3)
public void TrustThresholdEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("TrustMgmt.Threshold"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("TrustMgmt.Threshold.msg"));
	action.VerifyElementValue(locator.getProperty("TrustMgmt.Threshold.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Pref is empty",priority=3)
public void TrustThresholdprefEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("TrustMgmt.Pref"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("TrustMgmt.Pref.msg"));
	action.VerifyElementValue(locator.getProperty("TrustMgmt.Pref.msg"), locator.getProperty("Integer_value_is_out_of_range"));
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
