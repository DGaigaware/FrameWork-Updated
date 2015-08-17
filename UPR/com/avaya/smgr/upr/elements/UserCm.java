package com.avaya.smgr.upr.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class UserCm {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the Creation of new user by assigning Upr with CM element",enabled=false)
public void Verify_UPR() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	//Select the Upr from uprlist
	action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"), input.getProperty("upr1"));
	action.ClickButton(locator.getProperty("Users.Uprchangeok"));
	Thread.sleep(25000);
	//Click on Communication profile
	action.ClickLink(locator.getProperty("Communication_Profile"));
	Thread.sleep(5000);
	//verify the CM Element fields as per specified in Upr
	action.VerifySelectcheckbox(locator.getProperty("Users.Cmendcheckbox"));
	String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("Upr.cmlist")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str1,input.getProperty("cm29"));
	String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("Upr.templatelist")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str2,input.getProperty("Template"));
	action.VerifySelectcheckbox(locator.getProperty("Users.delonunassignck"));
	action.VerifySelectcheckbox(locator.getProperty("Users.overrideck"));
	
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass
public void Close() throws IOException, InterruptedException{
	action.logout(action);
}
}
