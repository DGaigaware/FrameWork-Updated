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


public class TrapListener{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
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
public void TrapUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,TrapListener
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("TrapListener"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.Communinity"));
	action.entertext(locator.getProperty("Trap.Communinity"), input.getProperty("Uname"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.Communinity"));
	action.VerifyElementValue(locator.getProperty("Trap.Communinity"), input.getProperty("Uname"));
	
}

@Test(description="Enable of Edit and Done buttons",priority=1)
 public void TarpEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="Enable of commit and cancel buttons",priority=2)
public void TarpCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Verify that the title value",priority=2)
public void TarpTitle() throws Exception{
	//Verification of title "TrapListener"
	action.VerifyStringValue(locator.getProperty("TrapListener"));
	
}

@Test(description="Verify that the error message should display when password field is  empty",priority=3)
public void TrapPasswordEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.password"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.password.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.password.msg"), locator.getProperty("You_must_enter_a_value"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));
}

@Test(description="Verify that the error message should display when confirm password field is  empty",priority=3)
public void TrapCnfPasswordEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.CnfPassword"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.CnfPassword.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.CnfPassword.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when authentication field is  empty",priority=3)
public void TrapAuthEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.Communinity"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.Communinity.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.Communinity.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Community field is empty",priority=3)
public void TrapCommunEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.protocol"));
	action.ClickButton(locator.getProperty("Commit"));
	 
	action.WaitForObj(locator.getProperty("Trap.protocol.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.protocol.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Privacy password field is  empty",priority=3)
public void TrapPrivacyEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.PrivPassword"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.PrivPassword.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.PrivPassword.msg"), locator.getProperty("You_must_enter_a_value"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Confirm Privacy password field is  empty",priority=3)
public void TrapCnfPrivacyEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.CnfPrivPassword"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.CnfPrivPassword.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.CnfPrivPassword.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Privacy Protocol is empty",priority=3)
public void TrapAuthPrivacyEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.PrivProtocol"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.PrivProtocol.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.PrivProtocol.msg"), locator.getProperty("You_must_enter_a_value"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));
}

@Test(description="Verify that the error message should display when interval field is  empty",priority=3)
public void TrapIntervalEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("Trap.Interval"));
	action.entertext(locator.getProperty("Trap.protocol"), input.getProperty("Uname"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Trap.Interval.msg"));
	action.VerifyElementValue(locator.getProperty("Trap.Interval.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	Thread.sleep(500);
	boolean Accept1=action.isAlertPresent();
	action.alert(Accept1);
	action.ClickButton(locator.getProperty("Cancel"));
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
