package com.avaya.smgr.sdm.swlib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.avaya.smgr.sdmsetup.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Operations {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Sdmsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	setup=new com.avaya.smgr.sdmsetup.Sdmsetup(); 
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the Software library page elements")
public void Verifypage() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	//Verify buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Lib.New"));
	action.VerifyEnableButton(locator.getProperty("Lib.Managefile"));
	//verify buttons are disabled
	action.VerifyDisableButton(locator.getProperty("Lib.View"));
	action.VerifyDisableButton(locator.getProperty("Lib.Edit"));
	action.VerifyDisableButton(locator.getProperty("Lib.Delete"));
	//Verify New library page
	action.DoubleClickButton(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	//Verify buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Lib.Commit"));
	action.VerifyEnableButton(locator.getProperty("Lib.Clear"));
	action.VerifyEnableButton(locator.getProperty("Lib.Cancel"));
	action.VerifyEnableButton(locator.getProperty("Lib.Commitbtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Clearbtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Cancelbtm"));
}
@Test(description="Verify the creation of new Software library",priority=1)
public void CreateLibrary() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("LName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip2"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	setup.VerifyAdd(action, input.getProperty("LName"));
	Thread.sleep(1000);
}



@Test(description="Verify the Software library viewed successfully",priority=2)
public void ViewLibrary() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	setup.SelectLibrary(action, input.getProperty("LName"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.View"))));
	action.ClickButton(locator.getProperty("Lib.View"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyEnableButton(locator.getProperty("Lib.View.Edit"));
	action.VerifyEnableButton(locator.getProperty("Lib.View.Done"));
	action.VerifyDisableTextbox(locator.getProperty("Lib.Path"));
	action.VerifyDisableTextbox(locator.getProperty("Lib.Name"));
	action.VerifyDisableCheckbox(locator.getProperty("Lib.lspchk"));	
	action.DoubleClickButton(locator.getProperty("Lib.View.Done"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
}

@Test(description="Verify the Edit Software library operation",priority=3)
public void EditLibrary() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	setup.SelectLibrary(action, input.getProperty("LName"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.Edit"))));
	action.ClickButton(locator.getProperty("Lib.Edit"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	//verify buttons
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Commit"));
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Reset"));
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Cancel"));
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Commitbtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Resetbtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Edit.Cancelbtm"));
	
	action.ClearText(locator.getProperty("Lib.Name"));	
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));

	action.DoubleClickButton(locator.getProperty("Lib.Edit.Commit"));
	action.WaitForObj(locator.getProperty("Lib.New"));

	action.WaitForTitle(locator.getProperty("Software_Library"));
	setup.VerifyAdd(action, input.getProperty("FName"));
	Thread.sleep(1000);
}

@Test(description="Verify the deletion of Software library",priority=4)
public void DeleteLibrary() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	setup.SelectLibrary(action, input.getProperty("FName"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.Delete"))));
	action.ClickButton(locator.getProperty("Lib.Delete"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	//verify buttons
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Now"));
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Schedule"));
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Cancel"));
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Nowbtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Schedulebtm"));
	action.VerifyEnableButton(locator.getProperty("Lib.Delete.Cancelbtm"));
	
	action.DoubleClickButton(locator.getProperty("Lib.Delete.Now"));
	action.WaitForObj(locator.getProperty("Lib.New"));
	action.DoubleClickButton(locator.getProperty("Lib.Refresh"));
	Thread.sleep(2000);
	setup.VerifydeleteLibrary(action, input.getProperty("FName"));
	Thread.sleep(1000);
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}


}
