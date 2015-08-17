package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignRole {
	
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
	
	@Test(description="Assign role to user while creating user without passwaord",priority=1)
	public void Assign_Role_without_Password() throws Exception
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
		action.VerifyTitle(locator.getProperty("New_User_Profile"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		 Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		 Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
	    Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname2"),Keys.TAB);
		Thread.sleep(1000);
		//Click on Membership tab
		action.ClickLinkByxpath(locator.getProperty("Membership"));
		Thread.sleep(2000);
		action.ClickButton(locator.getProperty("User.assignRole"));
		action.WaitForTitle(locator.getProperty("Assign_Roles"));
		//Verify the select button is disabled
		action.VerifyDisableButton(locator.getProperty("Users.AssingRole.Select"));
		//verify cancel button enabled
		action.VerifyEnableButton(locator.getProperty("Users.AssingRole.Cancel"));
		//Select Role to assign
		action.SelectCheckBox(locator.getProperty("Users.AssingRole.Checkbox1"));
		action.SelectCheckBox(locator.getProperty("Users.AssingRole.Checkbox9"));
		//Click on select button
		action.ClickButton(locator.getProperty("Users.AssingRole.Select"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		action.VerifyTitle(locator.getProperty("New_User_Profile"));
		Thread.sleep(1000);
		//Click on commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		Thread.sleep(1000);
		//Verify the error Message occured
		action.VerifyStringValue("Password is required");	
	}

@Test(description="Assign role to user while creating user with password",priority=2,groups={"Sanity"})
	public void Assign_Role_with_Password() throws Exception
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
		action.VerifyTitle(locator.getProperty("New_User_Profile"));
		
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		Thread.sleep(500);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(500);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
        Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname2"),Keys.TAB);
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Users.Password"),input.getProperty("pass1"),Keys.TAB);
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Users.CnfPassword"),input.getProperty("pass1"),Keys.TAB);
		Thread.sleep(1000);
		//Click on Membership tab
		action.ClickLinkByxpath(locator.getProperty("Membership"));
		Thread.sleep(1000);
		//click on Assign role button
		action.DoubleClickButton(locator.getProperty("User.assignRole"));
		action.WaitForTitle(locator.getProperty("Assign_Roles"));
		action.VerifyDisableButton(locator.getProperty("Users.AssingRole.Select"));
		action.VerifyEnableButton(locator.getProperty("Users.AssingRole.Cancel"));
		//Select Role to Assign
		action.SelectCheckBox(locator.getProperty("Users.AssingRole.Checkbox1"));
		action.SelectCheckBox(locator.getProperty("Users.AssingRole.Checkbox9"));
		//Click on select button
		action.DoubleClickButton(locator.getProperty("Users.AssingRole.Select"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		action.VerifyTitle(locator.getProperty("New_User_Profile"));
		//Click on Commit button
		//action.DoubleClickButton(locator.getProperty("Commit"));		
		//action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(1000);
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		Thread.sleep(500);
		action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginname2"));
		Thread.sleep(500);
	}

@Test(description="Verify assigned role is added into table",priority=3)
	public void Verify_Added_Role() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User from table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname2"));
	    Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(1000);
		action.ClickLinkByxpath(locator.getProperty("Membership"));
		List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("RollCheckbox")));
		int noofrows = rows1.size();
		Assert.assertEquals(noofrows, 2);
	}

@Test(description="Verify Unasssign role from user",priority=4)
	public void Unassign_Role() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User From table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname2"));
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(1000);
		//Click on Membership tab
		action.DoubleClickButton(locator.getProperty("Membership"));
		Thread.sleep(1000);
		//Select the role to unassign
		action.SelectCheckBox(locator.getProperty("Users.Roletableck1"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Unassign"))));
		//Click on UnAssign button
		action.DoubleClickButton(locator.getProperty("Users.Unassign"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		//verify the rows of table
		Thread.sleep(1000);
		List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("RollCheckbox")));
		int noofrows = rows1.size();
		Assert.assertEquals(noofrows, 1);
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
}


}
