package com.avaya.smgr.upm.advancesearch;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Basic {
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

@Test(description="Verify user management Page title on the advance search page",priority=1)
public void Verify_Page_Title() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//Verify the text present on the page
	action.VerifyStringValue(locator.getProperty("User_Management"));	
}
@Test(description="Verify the Page elements",priority=2)
public void Verify_Page_Elements() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Verify the buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Clearbtn"));	
	action.VerifyEnableButton(locator.getProperty("Plusbtn"));	
	action.VerifyEnableButton(locator.getProperty("Closebtn"));	
	action.VerifyEnableButton(locator.getProperty("AdvanceSearch"));
	action.VerifyDisableButton(locator.getProperty("Minusbtn"));
	action.VerifyEnableTextbox(locator.getProperty("Seachvalue"));
}

@Test(description="Verify the functionality of Clear button",priority=3)
public void Verify_Clear_Button() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Enter text in search field
	action.entertext(locator.getProperty("Seachvalue"), "admin123");
	//Click on clear button
	action.ClickButton(locator.getProperty("Clearbtn"));
	Thread.sleep(1000);
	//verify the search box get cleared
	action.VerifyElementValue(locator.getProperty("Seachvalue"), "");
	action.ClickButton(locator.getProperty("Closebtn"));
	
}

@Test(description="Verify the functionality of Close button",priority=4)
public void Verify_Close_Button() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Closebtn"))));
	//verify close button displayed on the page
	action.VerifyElementDisplay(locator.getProperty("Closebtn"));
	//Click on Close button
	action.ClickButton(locator.getProperty("Closebtn"));
	Thread.sleep(1000);
	//Verify that buttons are disappeared after clicking close button
	action.VerifyNoElementdisplay(locator.getProperty("Seachvalue"));
	//action.VerifyNoElementdisplay(locator.getProperty("Plusbtn"));	
}

@Test(description="Verify the functionality of Plus-minus button",priority=5)
public void VerifyPlus_Minus_Button() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Click on plus sign button
	action.ClickButton(locator.getProperty("Plusbtn"));
	//verify  buttons displayed on the page
	action.VerifyElementDisplay(locator.getProperty("Plusbtn1"));
	action.VerifyElementDisplay(locator.getProperty("Minusbtn1"));
	//Click on Minus sign button
	action.ClickButton(locator.getProperty("Minusbtn1"));
	//Verify that buttons are disappeared after clicking Minus button
	action.VerifyNoElementdisplay(locator.getProperty("Plusbtn1"));
	//action.VerifyNoElementdisplay(locator.getProperty("Minusbtn1"));	
}


@Test(description="Verify the Advance search link",priority=6)
public void Verify_Advance_Search_Link() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Closebtn"))));
	//verify elements are displayed on the page
	action.VerifyElementDisplay(locator.getProperty("Closebtn"));
	action.VerifyElementDisplay(locator.getProperty("Closebtn"));
	action.VerifyElementDisplay(locator.getProperty("Seachvalue"));
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	Thread.sleep(1000);
	////verify elements are not displayed on the page
	//action.VerifyNoElementdisplay(locator.getProperty("Closebtn"));
	action.VerifyNoElementdisplay(locator.getProperty("AdvanceSearch"));
	//action.VerifyNoElementdisplay(locator.getProperty("Seachvalue"));
	//action.VerifyNoElementdisplay(locator.getProperty("Plusbtn"));
	
}

@Test(description="Verify the search for non existing user",priority=7)
public void Verify_No_Record() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("Falsename"));
	//Thread.sleep(1000);
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Secondrow"))));
	Thread.sleep(500);
	//Verify the No record present in table
	action.VerifyElementValue(locator.getProperty("Secondrow"), "No Records found");
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	Thread.sleep(1000);
	//Click on Close button
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}
@AfterClass
public void logout() throws IOException, InterruptedException{
	  
	action.logout(action);
}

}
