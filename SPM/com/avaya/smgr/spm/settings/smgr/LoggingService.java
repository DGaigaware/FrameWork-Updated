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


public class LoggingService{
	boolean b;
	static UserAction action =null;
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
public void LoggingServiceUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Logging Service 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Logging_Service"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Logging.MaxAllowed"));
	action.entertext(locator.getProperty("Logging.MaxAllowed"), input.getProperty("SingleDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.VerifyElementValue(locator.getProperty("Logging.MaxAllowed"), input.getProperty("SingleDigitValue"));
}

@Test(description="Eanable of Edit and Done buttons",priority=1)
 public void LoggingServiceEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="Eanable of commit and cancel buttons",priority=2)
public void LoggingServiceCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Commit"));
}
@Test(description="Verifify the the title value",priority=8)
public void LoggingServiceTitle() throws Exception{
	//Verification of title "Logging Service"
	action.VerifyStringValue(locator.getProperty("Logging_Service"));

}

@Test(description="Verify that the errror message should display when Max. Time interval to wait field is empty",priority=3)
public void LoggingServiceTimeInterval() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Loggserv.Maxtime"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyStringValue(locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
}@Test(description="Verify that the errror message should display when Directory path for harvested generateXMLForSDMClient is empty",priority=4)
public void LoggingServiceDirPathEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Loggserv.Dir"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyStringValue(locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Commit"));

}
@Test(description="Verify that the errror message should display when No. of lines is empty",priority=5)
public void LoggingServiceNoOfLines() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Loggserv.NoLines"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyStringValue(locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Commit"));

}
@Test(description="Verify that the errror message should display when Logging.MaxAllowed field is empty",priority=6)
public void LoggingServiceMaxAllowed() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Logging.MaxAllowed"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyStringValue(locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Commit"));
}
@Test(description="Verify that the errror message should display when Max. Time interval to wait field is empty",priority=7)
public void LoggingServiceNoFiles() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Loggserv.NoFiles"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyStringValue(locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Commit"));
}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	//action.logout(action);

}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
	action.logout(action);
	}
}
