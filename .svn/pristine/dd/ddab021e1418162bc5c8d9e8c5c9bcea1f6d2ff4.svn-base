package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class DeleteUser {
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

@Test(description="Verify Delete user from table",priority=1)
	public void Delete_User() throws Exception
	{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	//Fill the required details
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UserLastname"),Keys.TAB);
	WebDriverWait wait=new WebDriverWait(action.driver,20);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UserLastname")));
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UserFirstname"),Keys.TAB);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UserFirstname")));
	Thread.sleep(1000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UserLoginname"),Keys.TAB);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//Select User From table
	action.SelectElementByLoginname(input.getProperty("UserLoginname"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Delete"))));
	//Click on delete Button
	action.DoubleClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	//Click on Delete Button
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	}

@Test(description="Verify Deleted user",priority=2)
   public void verify_Deleted_User() throws Exception
   {
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on More button
	action.ClickButton(locator.getProperty("Users.More"));
	//Click on Show Deleted user link
	action.ClickLink(locator.getProperty("Show_Deleted_Users"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	//Verify the Deleted user added in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("UserLoginname"));
	Thread.sleep(1000);
   }

@Test(description="Verify Restore oprattion on deleted user",priority=3)
  public void Verify_Restore_Deleted_User() throws Exception
  {
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on More button
	action.ClickButton(locator.getProperty("Users.More"));
	//Click on Show Deleted user link
	action.ClickLink(locator.getProperty("Show_Deleted_Users"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	action.VerifyDisableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyDisableButton(locator.getProperty("Users.Restore"));
	//Select user
	action.SelectElementByLoginnamewithoutfilter(input.getProperty("UserLoginname"));
	Thread.sleep(1000);
	WebDriverWait wait=new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Restore"))));
	//Click on restore button
	action.DoubleClickButton(locator.getProperty("Users.Restore"));
	action.WaitForTitle(locator.getProperty("User_Restore_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Restore_Confirmation"));
	//Click on restore confirm button
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("Users.Commit"))));
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	
  }

@Test(description="Verify Restore User successfully",priority=4)
public void Verify_Restored_User() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Verify that User is restored in table
	action.Verify_Add_Fifthcolumn(input.getProperty("UserLoginname"));
	Thread.sleep(1000);
}
   
@Test(description="Verify Deletion of user permanantly",priority=5)
public void Delete_User_Permanently() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//action.SelectCheckBox(locator.getProperty("Checkbox0"));
	action.SelectElementByLoginname(input.getProperty("UserLoginname"));
	Thread.sleep(500);
	WebDriverWait wait=new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Delete"))));
	//Click on delete Button
	action.DoubleClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Delete_Confirmation"));
	//Click on Delete Button
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//Click on More button
	action.ClickButton(locator.getProperty("Users.More"));
	//Click on Show Deleted user link
	action.ClickLink(locator.getProperty("Show_Deleted_Users"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	action.VerifyDisableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyDisableButton(locator.getProperty("Users.Restore"));
	//Select user from table
	action.SelectElementByLoginnamewithoutfilter(input.getProperty("UserLoginname"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyEnableButton(locator.getProperty("Users.Restore"));
	//Click on delete button
	action.DoubleClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Delete_Confirmation"));
	//Click on Delete confirm button
	action.DoubleClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
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
