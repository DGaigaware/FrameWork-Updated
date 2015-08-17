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


public class Shutdown{
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
public void ShutdownUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,SMGR, shutdown 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Shutdown"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();	action.alert(Accept);	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Shutdown.GracePer"));
	//action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Shutdown.GracePer"));
	action.entertext(locator.getProperty("Shutdown.GracePer"), input.getProperty("number1"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(2000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.VerifyElementValue(locator.getProperty("Shutdown.GracePer"), input.getProperty("number1"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	
}
@Test(description="Enable of Edit and Done buttons",priority=1)
 public void shutdownEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="enable of commit and cancel buttons",priority=2)
public void shutdownCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	boolean Accept=action.isAlertPresent();	action.alert(Accept);	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Commit"));
}
@Test(description="verify that the title value",priority=3)
public void shutdownTitle() throws Exception{
	action.VerifyStringValue(locator.getProperty("Shutdown"));
}


@Test(description="Verify that the error message should display when Grace period field is empty",priority=3)
public void ShutdownAutoError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();	action.alert(Accept);	
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Shutdown.GracePer"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();	action.alert(Accept);	
	action.WaitForObj(locator.getProperty("Shutdown.GracePer.msg"));	
	action.VerifyElementValue(locator.getProperty("Shutdown.GracePer.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));

}


@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws Exception{
	  
	action.Screenshot(result, action);
	boolean Accept=action.isAlertPresent();	action.alert(Accept);}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		//action logout=new action();
		action.logout(action);
	}
}
