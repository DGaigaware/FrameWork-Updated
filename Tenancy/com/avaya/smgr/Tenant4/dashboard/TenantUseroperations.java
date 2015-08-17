package com.avaya.smgr.Tenant4.dashboard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class TenantUseroperations {
	boolean b=false,match=false;;
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
	action.login(input.getProperty("Tenant.Login"),input.getProperty("Password"),action);
}

@Test(description="Verify the Error message when required field is empty",priority=1)
public void VerifyErrormsg() throws Exception
{
action.driver.navigate().refresh();
//Click on User Management,Manage Users
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
Thread.sleep(1000);
action.ClickLink(locator.getProperty("Manage_Users"));
action.SwithchFrame("iframe0");
//Click on New Button
action.ClickButton(locator.getProperty("Users.New"));
action.ClickButton(locator.getProperty("Users.Commit"));
Thread.sleep(1000);
//Verify the following Error message displayed
action.VerifyStringValue("Last Name should not be left blank.");
action.VerifyStringValue("First Name should not be left blank.");
action.VerifyStringValue("Login Name should not be left blank.");

}

@Test(description="Verify that user is created successfully",priority=2)
public void NewUser() throws Exception
{
	
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	//Check enabled buttons
	Thread.sleep(3000);

	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Firstname=action.driver.findElement(By.xpath(locator.getProperty("Firstname")));
	Firstname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Loginname=action.driver.findElement(By.xpath(locator.getProperty("Loginname")));
	Loginname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("User.Loginname"),Keys.TAB);
	Thread.sleep(3000);
	//Click on the Commit Button
	//action.DoubleClickButton(locator.getProperty("Commit"));
	//action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	//action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(3000);
	action.Verify_Add_Fifthcolumn(input.getProperty("User.Loginname"));
	Thread.sleep(1000);
}

@Test(description="verify the UPM page element buttons",priority=3)
public void Verifypageelements() throws Exception
{
action.driver.navigate().refresh();
//Click on User Management,Manage Users
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
action.VerifyTitle(locator.getProperty("User_Management"));
action.ClickLink(locator.getProperty("Manage_Users"));
action.SwithchFrame("iframe0");
//Verify the buttons
action.VerifyDisableButton(locator.getProperty("Users.View"));
action.VerifyDisableButton(locator.getProperty("Users.Edit"));
action.VerifyDisableButton(locator.getProperty("Users.Duplicate"));
action.VerifyDisableButton(locator.getProperty("Users.Delete"));
//verify New button is enabled
action.VerifyEnableButton(locator.getProperty("Users.New"));
//Select User
action.SelectElementByLoginname(input.getProperty("User.Loginname"));
Thread.sleep(3000);
//verify the buttons are enabled
action.VerifyEnableButton(locator.getProperty("Users.View"));
action.VerifyEnableButton(locator.getProperty("Users.Edit"));
action.VerifyEnableButton(locator.getProperty("Users.Duplicate"));
action.VerifyEnableButton(locator.getProperty("Users.Delete"));
}

@Test(description="Verify User edited successfully",priority=4)
public void Edituser() throws Exception
{
action.driver.navigate().refresh();
//Click on User Management,Manage Users
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
action.VerifyTitle(locator.getProperty("User_Management"));
action.ClickLink(locator.getProperty("Manage_Users"));
action.SwithchFrame("iframe0");
Thread.sleep(1000);
action.SelectElementByLoginname(input.getProperty("User.Loginname"));
WebDriverWait wait = new WebDriverWait(action.driver, 60);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
action.ClickButton(locator.getProperty("Users.Edit"));
action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
action.DoubleClickButton(locator.getProperty("Identity"));
Thread.sleep(3000);
//Edit the display name
action.ClearText(locator.getProperty("Displayname"));
action.entertext(locator.getProperty("Displayname"), input.getProperty("Uname"));

action.DoubleClickButton(locator.getProperty("Users.Commit"));
action.WaitForTitle(locator.getProperty("User_Management"));
Thread.sleep(1000);
action.ClickButton(locator.getProperty("Users.Edit"));
action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
action.DoubleClickButton(locator.getProperty("Identity"));
Thread.sleep(3000);
boolean b=action.driver.findElement(By.xpath(locator.getProperty("Displayname"))).getAttribute("value").equalsIgnoreCase(input.getProperty("Uname"));
Assert.assertTrue(b);
Thread.sleep(1000);
}


@Test(description="Verify user is view successfully",priority=5)
public void Viewuser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	action.SelectElementByLoginname(input.getProperty("User.Loginname"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	action.DoubleClickButton(locator.getProperty("Users.View"));
	action.WaitForTitle(locator.getProperty("User_Profile_View"));
	action.VerifyTitle(locator.getProperty("User_Profile_View"));
	action.DoubleClickButton(locator.getProperty("Identity"));
	action.WaitForObj(locator.getProperty("Displayname"));
	boolean b=action.driver.findElement(By.xpath(locator.getProperty("Displayname"))).getAttribute("value").equalsIgnoreCase(input.getProperty("Uname"));
	Assert.assertTrue(b);
	Thread.sleep(1000);
	
}


@Test(description="Verify Duplicate user creation",priority=6)
public void DuplicateUser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	action.SelectElementByLoginname(input.getProperty("User.Loginname"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	action.DoubleClickButton(locator.getProperty("Users.Duplicate"));
	action.WaitForTitle(locator.getProperty("User_Profile_Duplicate"));
	action.VerifyTitle(locator.getProperty("User_Profile_Duplicate"));
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	//Check enabled buttons
	Thread.sleep(2000);

	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Firstname=action.driver.findElement(By.xpath(locator.getProperty("Firstname")));
	Firstname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Loginname=action.driver.findElement(By.xpath(locator.getProperty("Loginname")));
	Loginname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("DuplicateUser.Loginname"),Keys.TAB);
	Thread.sleep(2000);
	//Click on the Commit Button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.Verify_Add_Fifthcolumn(input.getProperty("DuplicateUser.Loginname"));
	Thread.sleep(1000);

	
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);


}
@AfterClass(alwaysRun=true)
public void logout() throws IOException, InterruptedException{

	action.logout(action);

}

}
