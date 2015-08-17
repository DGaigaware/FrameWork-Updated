package com.avaya.smgr.spm.settings.smgr;
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


public class LoggingUI{
	boolean b;
	UserAction action =null;
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
public void LoggingUIUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Logging UI 
	action.ClickLink("Configurations");
	action.ClickLink("Settings");
	action.ClickLink("SMGR");
	action.ClickLink("Logging UI");
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Logging.TimeInterval"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Logging.TimeInterval"),input.getProperty("FourDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.TimeInterval"));
	action.VerifyElementValue(locator.getProperty("Logging.TimeInterval"), input.getProperty("FourDigitValue"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Enable of Edit and Done buttons",priority=1)
public void logggingEditDoneEnableBtn() throws Exception{
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}

@Test(description="Enable of commit and cancel buttons",priority=2)
public void loggingCommit() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Commit"));

}

@Test(description="Showing/Hiding behaviour of Logging Sevirity Levels",priority=3)
 public void LoggingSev() throws Exception{
	 //2) Click on  the Show button for the Alarm Severity Level
	action.ClickButton(locator.getProperty("GeneralProp"));
	Thread.sleep(2000);
	//3) Verify that  Logging Severity Level fields are hiding
    action.VerifyNoElementdisplay(locator.getProperty("Logging.Alert"));
    //4) Click on  the hide  button 
	action.ClickButton(locator.getProperty("GeneralProp"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.Alert"));
	//5)Logging Severity levels is displaying
    action.VerifyElementDisplay(locator.getProperty("Logging.Alert"));
 }
    

//Verification for the hiding/showing functionality for Auto Fresh
@Test(description="Showing/Hiding behavior of Auto Fresh",priority=4)
public void LoggingAuto() throws Exception{
	//2) Click on  the Expand  button for the Auto fresh
	action.ClickButton(locator.getProperty("IPOfficeProp"));
	Thread.sleep(2000);
    //3) Verify that  Auto Fresh fields are not showing
	action.VerifyNoElementdisplay(locator.getProperty("Logging.TimeInterval"));
	 //4) Click on  the Expand  button 
	action.ClickButton(locator.getProperty("IPOfficeProp"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.TimeInterval"));
	action.VerifyElementDisplay(locator.getProperty("Logging.TimeInterval"));
	Thread.sleep(1000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
   }
  
//Verification of Collapse all functionality
@Test(description="Collapse functionality of Logging UI",priority=5)
public void LogingCollapseAll() throws Exception{
	 //2) Click on  the Collapse button 
	action.ClickButton(locator.getProperty("CollapseAll"));
	Thread.sleep(2000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
	//3) Verify that all fields are hiding
    action.VerifyNoElementdisplay(locator.getProperty("Logging.ErrorRed"));
   	//action.VerifyNoElementdisplay(locator.getProperty("Logging.AutoFresh"));
	
   }

//Verification of expand all functionality for Logging UI
@Test(description="Expand functionality of Logging UI",priority=6)
public void ExpandAll() throws Exception{
	//2) Click on  the expand  button 
	action.ClickButton(locator.getProperty("ExpandAll"));
	Thread.sleep(2000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
	//3)verify elements are displaying
    action.VerifyElementDisplay(locator.getProperty("Logging.ErrorRed"));
   	//action.VerifyElementDisplay(locator.getProperty("Logging.AutoFresh"));
 }


//Verification of Name Logging UI
@Test(description="verification of Logging UI title",priority=7)
public void LoggingUItitle() throws Exception{
	action.VerifyStringValue("Logging UI");
	Accept=action.isAlertPresent();
	action.alert(Accept);
}


@Test(description="Verify that the error message should display when time field is empty",priority=8)
public void LoggingTimeEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Logging.TimeInterval"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.TimeInterval.msg"));
	action.VerifyElementValue(locator.getProperty("Logging.TimeInterval.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Commit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Error fields are empty",priority=9)
public void LoggingErrorEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClearText(locator.getProperty("Logging.ErrorRed"));
	action.ClearText(locator.getProperty("Logging.ErrorGreen"));
	action.ClearText(locator.getProperty("Logging.ErrorBlue"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.ErrorRed.msg"));
	action.VerifyElementValue(locator.getProperty("Logging.ErrorRed.msg"), locator.getProperty("red_component"));
	action.VerifyElementValue(locator.getProperty("Logging.ErrorGreen.msg"), locator.getProperty("green_component"));
	action.VerifyElementValue(locator.getProperty("Logging.ErrorBlue.msg"), locator.getProperty("blue_component"));
	action.ClickButton(locator.getProperty("Commit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when Notice fields are empty",priority=10)
public void LoggingNoticeEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Logging.NoticeRed"));
	action.ClearText(locator.getProperty("Logging.NoticeGreen"));
	action.ClearText(locator.getProperty("Logging.NoticeBlue"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.NoticeRed.msg"));
	action.VerifyElementValue(locator.getProperty("Logging.NoticeRed.msg"), locator.getProperty("red_component"));
	action.VerifyElementValue(locator.getProperty("Logging.NoticeGreen.msg"), locator.getProperty("green_component"));
	action.VerifyElementValue(locator.getProperty("Logging.NoticeBlue.msg"), locator.getProperty("blue_component"));
	action.ClickButton(locator.getProperty("Commit"));

}

@Test(description="Verify that the error message should display when Warning fields are empty",priority=11)
public void LoggingWarningEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Logging.WarnRed"));
	action.ClearText(locator.getProperty("Logging.WarnGreen"));
	action.ClearText(locator.getProperty("Logging.WarnBlue"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Logging.WarnRed.msg"));
	action.VerifyElementValue(locator.getProperty("Logging.WarnRed.msg"), locator.getProperty("red_component"));
	action.VerifyElementValue(locator.getProperty("Logging.WarnGreen.msg"), locator.getProperty("green_component"));
	action.VerifyElementValue(locator.getProperty("Logging.WarnBlue.msg"), locator.getProperty("blue_component"));
	action.ClickButton(locator.getProperty("Commit"));
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

