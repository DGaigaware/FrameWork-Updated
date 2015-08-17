package com.avaya.smgr.upm.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class UserOperation {
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

	
	@Test(description="Verify the Error message on required field is empty")
	public void Verify_Error_AllField() throws Exception
	{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LastnameErrmsg"))));
	//Verify the following Error message displayed
	action.VerifyStringValue("Last Name should not be left blank.");
	action.VerifyStringValue("First Name should not be left blank.");
	action.VerifyStringValue("Login Name should not be left blank.");
	
	}
	
	
	@Test(description="Verify the Error message on required field is empty")
	public void Verify_Error_LastNameField() throws Exception
	{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Firstname"))));
	//Fill up the required fields
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
	Thread.sleep(1000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname"),Keys.TAB);
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LastnameErrmsg"))));
	//Verify the following Error message displayed on Last name Empty
	action.VerifyStringValue("Last Name should not be left blank.");
	}
	
	@Test(description="Verify the Error message on required field is empty")
	public void Verify_Error_FirstNameField() throws Exception
	{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Firstname"))));
	//Fill up the required fields
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
	Thread.sleep(1000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname"),Keys.TAB);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Loginname")),input.getProperty("UsersLoginname")));
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("FirstnameErrmsg"))));
	//Verify the following Error message displayed
	action.VerifyStringValue("First Name should not be left blank.");
	}
	
@Test(description="Verify new user creation")
	public void Verify_Error_LoginNameField() throws Exception
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
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Check enabled buttons
		action.VerifyEnableButton(locator.getProperty("Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancel"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancelbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Commitbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Continue"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		Thread.sleep(1000);
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LoginnameErrmsg"))));
		//Verify the following Error message displayed
		action.VerifyStringValue("Login Name should not be left blank.");
	}
	
	
@Test(description="Verify new user creation",priority=2)
	public void New_User() throws Exception
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
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Check enabled buttons
		action.VerifyEnableButton(locator.getProperty("Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancel"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancelbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Commitbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Continue"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname"),Keys.TAB);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Loginname")),input.getProperty("UsersLoginname")));
		//Click on Commit button
		Thread.sleep(1000);
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		Thread.sleep(1000);
		action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginname"));
		Thread.sleep(500);
	}

	@Test(description="Verify new user creation",priority=3)
	public void Verify_Error_Duplicateuser() throws Exception
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
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Check enabled buttons
		action.VerifyEnableButton(locator.getProperty("Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Commit"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancel"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancelbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Commitbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Continue"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname"),Keys.TAB);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Loginname")),input.getProperty("UsersLoginname")));
		//Click on Commit button
		Thread.sleep(1000);
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginerrmsgbox"))));
		//Verify the following Error message displayed
		action.VerifyStringValue("user with this loginname already exists ( maybe the user you are adding has been soft deleted )");
		Thread.sleep(1000);
	}

@Test(description="verify the UPM page elements",priority=4)
public void Verify_Page_Elements() throws Exception
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
	//Select User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginname"));
	Thread.sleep(1000);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//verify the buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Users.View"));
	action.VerifyEnableButton(locator.getProperty("Users.Edit"));
	action.VerifyEnableButton(locator.getProperty("Users.Duplicate"));
	action.VerifyEnableButton(locator.getProperty("Users.Delete"));
}

@Test(description="Verify User edited successfully",priority=5)
public void Edit_User() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	//Select User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginname"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(500);
	//Click on Identity tab
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(500);
	//Edit the language and time
	action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
	Thread.sleep(1000);
	action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
	//Click on the Commit Button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Identity tab
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(1000);
	//verify the edited TimeZone and language 
	String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str1,input.getProperty("Danish"));
	Thread.sleep(1000);
	String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
	Assert.assertEquals(input.getProperty("Danishtime"), str2);
}


@Test(description="Verify user viewed successfully",priority=6)
	public void View_User() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select user
		action.SelectElementByLoginname(input.getProperty("UsersLoginname"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.View"))));
		//Click on View Button
		action.ClickButton(locator.getProperty("Users.View"));
		action.WaitForTitle(locator.getProperty("User_Profile_View"));
		action.VerifyTitle(locator.getProperty("User_Profile_View"));
		//Click on Identity tab
		action.ClickLinkByxpath(locator.getProperty("Identity"));
		Thread.sleep(1000);
		//Verify the text box are read only
		action.VerifyReadOnlyDisplayed(locator.getProperty("Lastname"));
		action.VerifyReadOnlyDisplayed(locator.getProperty("Firstname"));
		Thread.sleep(500);
		
	}


@Test(description="Verify Duplicate user creation",priority=7)
	public void Duplicate_User() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname"));
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Duplicate"))));
		//Click on Duplicate button
		action.DoubleClickButton(locator.getProperty("Users.Duplicate"));
		action.WaitForTitle(locator.getProperty("User_Profile_Duplicate"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LangDropdown"))));
		//Verify the timezone and language are set
		String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(str1,input.getProperty("Danish"));
		Thread.sleep(1000);
		String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(input.getProperty("Danishtime"), str2);		
		
	}

@Test(description="Verify Delete user from table",priority=8)
	public void Delete_User() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User From table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname"));
		Thread.sleep(1000);
		//Click on delete Button
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Delete"))));
		action.DoubleClickButton(locator.getProperty("Users.Delete"));
		action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
		//Click on Delete Button
		action.ClickButton(locator.getProperty("Delete_Cnf"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		//Click User Management link
		action.RefreshPage();
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Verify User get deleted from table
		action.Verify_Delete_Fifthcolumn(input.getProperty("UsersLoginname"));
		Thread.sleep(500);
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
