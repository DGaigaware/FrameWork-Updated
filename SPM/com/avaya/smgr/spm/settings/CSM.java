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


public class CSM{
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
public void csmMgmtUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,CSM 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Communication_System_Management"));
	action.ClickLink(locator.getProperty("Configuration"));
	action.SwithchFrame("iframe0");
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value
	action.ClearText(locator.getProperty("TP.Clean"));
	//action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("TP.Clean"), input.getProperty("SingleDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForObj(locator.getProperty("TP.Clean1"));
	action.VerifyElementValue(locator.getProperty("TP.Clean1"), input.getProperty("SingleDigitValue"));
	Thread.sleep(1000);
}
@Test(description="Verify that the title is showing correctly",groups={"Sanity"},priority=1)
public void csmtitle() throws Exception{
	action.SwithchFrame("iframe0");
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.DoubleClickButton(locator.getProperty("Done"));
}
@Test(description="Verify that the Edit and Done buttons are enabled",priority=2)
public void csmMgmtService() throws Exception{
	action.SwithchFrame("iframe0");
	action.DoubleClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.DoubleClickButton(locator.getProperty("Done"));

}
	
@Test(description="Verify that the Showing/Hiding behaviour for the General Properties",priority=3)
 public void csmGen() throws Exception{
	action.SwithchFrame("iframe0");
	 //2) Click on  the Show button for the General Properties
	action.ClickButton(locator.getProperty("GeneralProp"));
	//3) Verify that  General Properties fields are hiding
    action.VerifyNoElementdisplay(locator.getProperty("GP.Applicn"));
//	action.WaitForTitle(locator.getProperty("View_Profile"));
    //4) Click on  the hide  button 
	action.ClickButton(locator.getProperty("GeneralProp"));
    action.VerifyElementDisplay(locator.getProperty("GP.Applicn"));
    }
//Verification for the hiding/showing functionality under Telephony Properties
@Test(description="Verify that the Showing/Hiding behaviour for Telephony Properties",priority=4)
public void csmtable() throws Exception{
//	//1)Navigate to Configuration page, setting,CSM
	action.SwithchFrame("iframe0");
	  //2) Click on  the Expand  button for the Telephony properties
	action.ClickButton(locator.getProperty("IPOfficeProp"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
    //3) Verify that  Telephony Properties fields are not showing
	action.VerifyNoElementdisplay(locator.getProperty("TP.Clean"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Prepopulate"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Incremental"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.MaxforTab"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Maxforsingle"));

	//action.WaitForTitle(locator.getProperty("View_Profile"));
	 //4) Click on  the Expand  button 
	action.ClickButton(locator.getProperty("IPOfficeProp"));
    //5) Verify that  Telephony Properties fields are showing
	action.VerifyElementDisplay(locator.getProperty("TP.Clean"));
	action.VerifyElementDisplay(locator.getProperty("TP.Prepopulate"));
	action.VerifyElementDisplay(locator.getProperty("TP.Incremental"));
	action.VerifyElementDisplay(locator.getProperty("TP.MaxforTab"));
	action.VerifyElementDisplay(locator.getProperty("TP.Maxforsingle"));
   }
//Verification of Collapse all functionality
@Test(description="Verification for the Collapse functionality",priority=5)
public void csmCollapseAll() throws Exception{
	action.SwithchFrame("iframe0");
	 //2) Click on  the Collapse button 
	action.ClickButton(locator.getProperty("CollapseAll"));
	//3) Verify that all fields are hiding
    action.VerifyNoElementdisplay(locator.getProperty("GP.Applicn"));
    
	action.VerifyNoElementdisplay(locator.getProperty("TP.Clean"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Prepopulate"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Incremental"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.MaxforTab"));
	action.VerifyNoElementdisplay(locator.getProperty("TP.Maxforsingle"));
   }
//Verification of expand all functionality for CSM
@Test(description="Verify thate the Expand functionality",priority=6)
public void csmExpandAll() throws Exception{
	 //2) Click on  the Collapse all button for the General Properties
	action.ClickButton(locator.getProperty("ExpandAll"));
	//3) Click on  the hide  button 
	action.ClickButton(locator.getProperty("ExpandAll"));
	action.VerifyElementDisplay(locator.getProperty("GP.Applicn"));
    
	action.VerifyElementDisplay(locator.getProperty("TP.Clean"));
	action.VerifyElementDisplay(locator.getProperty("TP.Prepopulate"));
	action.VerifyElementDisplay(locator.getProperty("TP.Incremental"));
	action.VerifyElementDisplay(locator.getProperty("TP.MaxforTab"));
	action.VerifyElementDisplay(locator.getProperty("TP.Maxforsingle"));
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
