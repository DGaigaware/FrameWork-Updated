package com.avaya.smgr.workassgnment.assmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

import com.avaya.smgr.Worksetup.WorkSetup;

public class UserManagement {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	WorkSetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
   	setup=new WorkSetup();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify Resource details buttons of work assignment in user management ")
public void Verify_Resourcedetails() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select WA User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginnameWS"));
	Thread.sleep(1000);
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox3"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("User.Resourcedetail"))));
	//Verify Reource Details button
	action.DoubleClickButton(locator.getProperty("User.Resourcedetail"));
	//Switch to Assignment Management page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	action.VerifyTitle(locator.getProperty("Assignment_Resource_Details"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Attrcancelbtn"))));
	Thread.sleep(1000);
	
}

@Test(description="Verify Account details buttons of work assignment in user management ")
public void Verify_Accountdetails() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	//Select WA User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginnameWS"));
	Thread.sleep(1000);
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox3"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("User.Resourcedetail"))));
	//Verify Account Details button
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("User.AccDetail"))));
	action.DoubleClickButton(locator.getProperty("User.AccDetail"));
	//Switch to  Account Assignment Details page
	action.WaitForTitle(locator.getProperty("Account_Assignment_Details"));
	action.VerifyTitle(locator.getProperty("Account_Assignment_Details"));
	Thread.sleep(500);
	action.VerifyElementDisplay(locator.getProperty("Attributetab"));
	action.VerifyElementDisplay(locator.getProperty("Propertytab"));
	Thread.sleep(500);
}

@Test(description="Verify Source buttons of work assignment in user management ")
public void Verify_Sourcedetails() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select WA User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginnameWS"));
	Thread.sleep(1000);
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox3"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("User.Resourcedetail"))));
	
	//Verify Source Details button
	action.DoubleClickButton(locator.getProperty("User.SourceDetail"));
	//Switch to  Source Assignment Details page
	action.WaitForTitle(locator.getProperty("Source_Assignment_Details"));
    action.VerifyTitle(locator.getProperty("Source_Assignment_Details"));
    action.VerifyElementDisplay(locator.getProperty("Attributetab"));
	
	
}

	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException
	{
		  
		action.Screenshot(result, action);
		
	}
	
	@AfterClass
	public void Close() throws IOException, InterruptedException
	{
		action.logout(action);
	}


}
