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


public class Inventory{
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
public void InventoryUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting,Agent Management 
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Configuration"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Inv.Record1"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Inv.Record1"), input.getProperty("Tenthvalue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForObj(locator.getProperty("Inv.Record1"));
	action.VerifyElementValue(locator.getProperty("Inv.Record1"), input.getProperty("Tenthvalue"));
}
@Test(description="Verify that the Edit and Done buttons are enabled",priority=1)
public void inventoryEditbtn() throws Exception{
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Edit and Done buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));

}
@Test(description="Verify that the commit and cancel buttons are enabled",priority=2)
public void inventoryCommitbts() throws Exception{
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
	
@Test(description="Hiding functionality of Inventory UI",priority=3)
 public void InventoryHiding() throws Exception{
	//3) Verify that  General Properties fields are hiding
    action.VerifyElementDisplay(locator.getProperty("Inv.Record1"));
    action.VerifyElementDisplay(locator.getProperty("Inv.Record2"));
    action.VerifyElementDisplay(locator.getProperty("Inv.Record3"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("View_Profile"));
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
