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


public class NewUserCreation {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException,Exception
    {
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
    }

@Test(description="Verify Upr By creating new user",enabled=false)
public void Verify_UPR_with_New_User()
{
try
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
//Select the upr from upr list
action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"), input.getProperty("uprcmsm1"));
action.ClickButton(locator.getProperty("Users.Uprchangeok"));
Thread.sleep(3000);
//Verify the language and time for specified upr is correct
String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
Assert.assertEquals(str1,input.getProperty("Danish"));
Thread.sleep(3000);
String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("Danish")))).getFirstSelectedOption().getText();
Assert.assertEquals(input.getProperty("Danishtime"), str2);
action.ClickButton(locator.getProperty("Users.Cancel"));

}
catch(Exception e)
 {
 }
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
