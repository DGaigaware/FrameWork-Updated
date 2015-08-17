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


public class UserBulkImportProfile{
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
public void BeforeMethod() throws IOException, InterruptedException{

	
}
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved",groups={"Sanity"})
public void UsrBlkImportUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,SMGR Element Manager
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("User_BulkImport_Profile"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.MaxErrors"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Bulk.MaxErrors"), input.getProperty("TwoDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.VerifyElementValue(locator.getProperty("Bulk.MaxErrors"), input.getProperty("TwoDigitValue"));
}	

@Test(description="Enable of Edit and Done buttons",priority=1)
 public void UsrBlkImpExpEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="Enable of commit and cancel buttons",priority=2)
public void UsrBlkImpExpCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verifify the the title value",priority=2)
public void UsrBlkImpExpTitle() throws Exception{
	//Verification of title "User BulkImport Profile"
	action.VerifyStringValue(locator.getProperty("User_BulkImport_Profile"));
}


@Test(description="Verify that the error message should display when enable error is empty",priority=3)
public void UsrBlkImportEnablrError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.EnableError"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Bulk.EnableError.msg"));
	action.VerifyElementValue(locator.getProperty("Bulk.EnableError.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when default error is empty",priority=3)
public void UsrBlkImportDefaultError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.Default"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Bulk.Default.msg"));
	action.VerifyElementValue(locator.getProperty("Bulk.Default.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when max.no. of errors empty",priority=3)
public void UsrBlkMaxerror() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.MaxErrors"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Bulk.MaxErrors.msg"));
	action.VerifyElementValue(locator.getProperty("Bulk.MaxErrors.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when max.no. of errors and job are empty",priority=3)
public void UsrBlkMaxJobsrror() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.MaxJobs"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Bulk.MaxJobs.msg"));
	action.VerifyElementValue(locator.getProperty("Bulk.MaxJobs.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
}

@Test(description="Verify that the error message should display when default action is empty",priority=3)
public void UsrBlkImportDefaultAction() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Bulk.DefaultAction"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Bulk.DefaultAction.msg"));
	action.VerifyElementValue(locator.getProperty("Bulk.DefaultAction.msg"), locator.getProperty("You_must_enter_a_value"));
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
