package com.avaya.smgr.spm.settings;
/*
 Agent Management UI Related Test case
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AgentMgmtService{
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException{
		action = new UserAction();		locator=new Properties();
	   	input=new Properties();
	    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
@BeforeMethod(alwaysRun=true)
public void ScreenshotSetup() throws IOException, InterruptedException{
}
@Test(description="Verify that the title is showing correctly")
 public void agentMgmtService() throws Exception{
	//Navigate to Configuration page, setting,Agent Management 
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Agent_Management_Service"));
	action.ClickLink(locator.getProperty("Agent_Management"));
	action.VerifyStringValue(locator.getProperty("Agent_Management"));
}
@Test(description="Verify that the Edit and Done buttons are enabled")
public void agentEditDoneEnableBtn() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Agent Management 
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Agent_Management_Service"));
	action.ClickLink(locator.getProperty("Agent_Management"));
	action.SwithchFrame("iframe0");
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the commit and cancel buttons are enabled")
public void agentCommCancelEnableBtn() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Agent Management 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Agent_Management_Service"));
	action.ClickLink(locator.getProperty("Agent_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.ClickButton(locator.getProperty("Done"));
	action.WaitForTitle(locator.getProperty("SPMHome_Screen"));
}
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved",groups={"Sanity"})
public void agentMgmtUpdate() throws Exception{
	//Navigate to Configuration page, setting,Agent Management 
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Agent_Management_Service"));
	action.ClickLink(locator.getProperty("Agent_Management"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Edit the value in the Enterprise heart beat threshold field
	action.ClearText(locator.getProperty("Agent.EnterpriseHrtThrshold"));
	action.entertext(locator.getProperty("Agent.EnterpriseHrtThrshold"), input.getProperty("ThreeDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Agent.EnterpriseHrtThrshold"));	
	action.VerifyElementValue(locator.getProperty("Agent.EnterpriseHrtThrshold"), input.getProperty("ThreeDigitValue"));
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
