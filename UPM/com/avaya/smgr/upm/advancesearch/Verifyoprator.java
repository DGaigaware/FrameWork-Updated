package com.avaya.smgr.upm.advancesearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Verifyoprator {
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

@Test(description="Verify the And operator for same user",priority=13)
public void Verify_And_Operator_with_Same_User() throws Exception
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
	Thread.sleep(500);
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(2000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLoginname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	
	//Click on Plus sign button
	action.ClickButton(locator.getProperty("Plusbtn"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("AndOrlist"))));
	WebElement AndORlist=action.driver.findElement(By.xpath(locator.getProperty("AndOrlist")));
	AndORlist.sendKeys(org.openqa.selenium.Keys.CONTROL);
	//Thread.sleep(1000);
	//Select And From Drop down list
	action.SelectFromdropDown(locator.getProperty("AndOrlist"), "And");
	//Select First name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist1"), locator.getProperty("First_Name"));
	Thread.sleep(500);
	action.entertext(locator.getProperty("Seachvalue1"), input.getProperty("SearchFirstname"));
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue1")), input.getProperty("SearchFirstname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	//Verify the login name and first name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	action.Verify_Add_Thirdcolumn(input.getProperty("SearchFirstname"));
	//Click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the And operator for different user",priority=14)
public void verify_And_Operator_Diff_users() throws Exception
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
	Thread.sleep(500);
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLoginname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	//Click on Plus sign button
	action.ClickButton(locator.getProperty("Plusbtn"));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("AndOrlist"))));
	//Thread.sleep(1000);
	WebElement AndORlist=action.driver.findElement(By.xpath(locator.getProperty("AndOrlist")));
	AndORlist.sendKeys(org.openqa.selenium.Keys.CONTROL);
	//Thread.sleep(1000);
	//Select And From Drop down list
	action.SelectFromdropDown(locator.getProperty("AndOrlist"), "And");
	Thread.sleep(500);
	//Select Display Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist1"), locator.getProperty("Display_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue1"), input.getProperty("Falsename"));
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue1")), input.getProperty("Falsename")));
	Thread.sleep(500);
	//Click on Search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Secondrow"))));
	//Verify No Record Found in table
	action.VerifyElementValue(locator.getProperty("Secondrow"), "No Records found");
	//Thread.sleep(1000);
	//Click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the OR operator for same user",priority=15)
public void Verify_OR_Operator_with_Same_User() throws Exception
{
	action.driver.navigate().refresh();
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	Thread.sleep(500);
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLoginname"));
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	//Click on Plus sign button
	action.ClickButton(locator.getProperty("Plusbtn"));
	//Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("AndOrlist"))));
	WebElement AndORlist=action.driver.findElement(By.xpath(locator.getProperty("AndOrlist")));
	AndORlist.sendKeys(org.openqa.selenium.Keys.CONTROL);
    Thread.sleep(1000);
	//Select Or From Drop down list
	action.SelectFromdropDown(locator.getProperty("AndOrlist"), "Or");
	Thread.sleep(500);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Columnnamelist1"))));
	//Select First name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist1"), locator.getProperty("First_Name"));
	Thread.sleep(500);
	action.entertext(locator.getProperty("Seachvalue1"), input.getProperty("SearchFirstname"));
	Thread.sleep(500);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue1")), input.getProperty("SearchFirstname")));
	
	//Click on Search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1500);
	//verify the Login name or First name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	Thread.sleep(1500);
	action.Verify_Add_Thirdcolumn(input.getProperty("SearchFirstname"));
	//Click on reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the OR operator for Different user",priority=16)
public void Verify_OR_Operator_Diff_User() throws Exception
{
	action.driver.navigate().refresh();
	WebDriverWait wait=new WebDriverWait(action.driver,20);
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	Thread.sleep(500);
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLoginname"));
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	
	//Click on Plus sign button
	action.ClickButton(locator.getProperty("Plusbtn"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("AndOrlist"))));
	Thread.sleep(500);
	WebElement AndORlist=action.driver.findElement(By.xpath(locator.getProperty("AndOrlist")));
	AndORlist.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(500);
	//Select Or From Drop down list
	action.SelectFromdropDown(locator.getProperty("AndOrlist"), "Or");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Columnnamelist1"))));
	//Select Display Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist1"), locator.getProperty("Display_Name"));
	Thread.sleep(500);
	action.entertext(locator.getProperty("Seachvalue1"), input.getProperty("Falsename"));
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue1")), input.getProperty("Falsename")));
	Thread.sleep(1000);
	
	//Click on Search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	//verify the Login name or Display name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	//Click on reset button
	action.ClickButton(locator.getProperty("Resettable"));
    //Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}

}
