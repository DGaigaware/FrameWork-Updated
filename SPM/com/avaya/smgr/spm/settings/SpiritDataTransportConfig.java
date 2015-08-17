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


public class SpiritDataTransportConfig{
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
public void SpiritTransConfigUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Agent Management 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SPIRIT"));
	action.ClickLink(locator.getProperty("Data_Transport_Config"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("DataTransport.proxyport"));
	action.entertext(locator.getProperty("DataTransport.proxyport"), input.getProperty("FourDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.VerifyElementValue(locator.getProperty("DataTransport.proxyport"), input.getProperty("FourDigitValue"));
}
@Test(description="Verify that the Edit and Done buttons are enabled",priority=1)
public void SpiritEditDoneEnableBtn() throws Exception{
	action.WaitForTitle(locator.getProperty("View_Profile"));
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the commit and cancel buttons are enabled",priority=2)
public void spiritCommCancelEnableBtn() throws Exception{
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}

@Test(description="Verify that the title is showing correctly")
public void SpiritTransConfig() throws Exception{
	action.VerifyStringValue(locator.getProperty("Data_Transport_Config"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
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
