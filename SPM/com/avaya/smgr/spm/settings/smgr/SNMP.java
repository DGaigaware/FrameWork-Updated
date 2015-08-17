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


public class SNMP{
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
@Test(description="Eanable of Edit and Done buttons",priority=1)
public void SNMPEditDoneEnableBtn() throws Exception{
	//1)Navigate to Configuration page, setting,SNMP
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("SNMP"));
	//action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("View_Profile"));
	action.SwithchFrame("iframe0");
	//Edit and Done buttons are enabled
	action.VerifyDisableButton(locator.getProperty("Edit"));
	action.VerifyDisableButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	
}
@Test(description="Verifify the the title value")
public void SNMPTitle() throws Exception{
	//Verification of title "SNMP"
	action.VerifyStringValue(locator.getProperty("SNMP"));
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
