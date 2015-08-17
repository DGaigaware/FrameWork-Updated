package com.avaya.smgr.sdm.swlib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class SwLib_Validations {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the Eror message when name is empty")
public void SwnameEmpty() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Enter the name. It is a mandatory field.");
}

@Test(description="Verify the Eror message when IP address field is empty")
public void SwIpEmpty() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Enter the IP address. It is a mandatory field.");
}
@Test(description="Verify the Eror message when Server path field is empty")
public void SwServerPathEmpty() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Enter the server secure path. It is a mandatory field.");
	
}
/*
@Test(description="Verify the Eror message when protocol is not selected")
public void SwlibProt() throws Exception
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
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "Select");
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Select a default protocol. it is a mandatory field.");
}*/
@Test(description="Verify the Eror message when SCp User name is empty")
public void SwlibSCPUser() throws Exception
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
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Enter SCP user name. It is a mandatory field.");	
	
}

@Test(description="Verify the Eror message when SCp password is empty")
public void SwlibSCPPass() throws Exception
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
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "Enter SCP password. It is a mandatory field.");	
	
}

@Test(description="Verify the Eror message when confirm SCP password is empty")
public void SwlibSCPCnfPass() throws Exception
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
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	action.ClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.ScpUserError"));
	action.VerifyElementValue(locator.getProperty("Lib.ScpUserError"), "The SCP password and retype SCP password do not match.");
	
}
@Test()
public void Verifyclearbtn() throws Exception
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
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.DoubleClickButton(locator.getProperty("Lib.Clearbtm"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyElementValue(locator.getProperty("Lib.Name"), "");
	action.VerifyElementValue(locator.getProperty("Lib.Ip"), "");
	action.VerifyElementValue(locator.getProperty("Lib.Path"), "");
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
