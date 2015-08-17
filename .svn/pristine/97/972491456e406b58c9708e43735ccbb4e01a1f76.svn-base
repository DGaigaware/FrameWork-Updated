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


public class PrivateContact {
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
	

@Test(description="Verify Error message on required fields empty",priority=1)
public void Verify_Error_Msg() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(500);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contacts tab
	action.ClickLinkByxpath(locator.getProperty("Contacts"));
	//Click on private contact tab
	action.ClickButton(locator.getProperty("Privatecnttab"));
	//Click on New button
	action.ClickButton(locator.getProperty("NewContact"));
	action.WaitForTitle(locator.getProperty("New_Private_Contact"));
	action.VerifyTitle(locator.getProperty("New_Private_Contact"));
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Commit"));
	//Verify following error message
	action.VerifyStringValue("Last Name should not be left blank.");
	//Enter last name
	action.EntertextUsingKey(locator.getProperty("contactLastName"), "abc1",Keys.TAB);
	Thread.sleep(2000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Commit"));
	
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Commit"));
    }
	//Verify following error message
	action.VerifyStringValue("First Name should not be left blank.");
	
}

@Test(description="Verify creation of new private contact",priority=2)
public void Add_Private_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(500);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	//action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contacts tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	Thread.sleep(2000);
	//Click on private contact tab
	action.ClickButton(locator.getProperty("Privatecnttab"));
	Thread.sleep(1000);
	//Click on New button
	action.DoubleClickButton(locator.getProperty("NewContact"));
	action.WaitForTitle(locator.getProperty("New_Private_Contact"));
	action.VerifyTitle(locator.getProperty("New_Private_Contact"));
	action.VerifyEnableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Cancel"));
	//Fill the required details
	action.EntertextUsingKey(locator.getProperty("contactLastName"), input.getProperty("Clastname"),Keys.TAB);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("contactLastNameAscii")),input.getProperty("Clastname")));
	action.EntertextUsingKey(locator.getProperty("contactFirstname"), input.getProperty("Cfirstname"),Keys.TAB);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("contactFirstnameAscii")),input.getProperty("Cfirstname")));
	Thread.sleep(1000);
	//Click on Commit button
	
	action.DoubleClickButton(locator.getProperty("Commit"));
    action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Users.Commit"));
    }
	
	//action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
}
	
@Test(description="Verify newly added private contact",priority=3)
public void Verify_Added_Private_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");

	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	//action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contacts tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	//Click on private contact tab
	action.ClickButton(locator.getProperty("Privatecnttab"));
	Thread.sleep(1000);
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("privatecontactchbox")));
	int n = rows1.size();
	int b=0;
	//Assert.assertEquals(noofrows, 1);
	for(int i=0;i<n;i++)
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='privateContactsTable:"+i+":tbl_inputText_firstName']")).getText();
	   if(str.equals(input.getProperty("Cfirstname")))
	   {
		   Assert.assertTrue(true);
		    b=1;
		   break;
	   }
	}
		
	if(b==0)
	{
		Assert.assertTrue(false);
	}
}


@Test(description="Verify Edit private contact",priority=4)
public void Edit_Private_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contacts Tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	//Click on private contact tab
	action.ClickButton(locator.getProperty("Privatecnttab"));
	Thread.sleep(1000);
	action.VerifyDisableButton(locator.getProperty("EditpvtContact"));
	action.VerifyDisableButton(locator.getProperty("DeletepvtContact"));
	//Select contact
	action.SelectCheckBox(locator.getProperty("Pvtcontactchk0"));
	Thread.sleep(1000);
	//Click on edit button
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("EditpvtContact"))));
	action.DoubleClickButton(locator.getProperty("EditpvtContact"));
	action.WaitForTitle(locator.getProperty("Edit_Private_Contact"));
	action.VerifyTitle(locator.getProperty("Edit_Private_Contact"));
	action.VerifyEnableTextbox(locator.getProperty("contactLastName"));
	action.VerifyEnableTextbox(locator.getProperty("contactFirstname"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Contactlanglist"), input.getProperty("Danish"));
	Thread.sleep(500);
	//Click on Add button
	action.DoubleClickButton(locator.getProperty("Commit"));
	//handle exception
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Commit"));
    }
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	//handle exception
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Users.Commit"));
    }
	
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
}

@Test(description="Verify Edited private contact",priority=5)
public void Verify_Edit_Private_Contact() throws Exception
{
	 action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
    Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contacts tab
	action.DoubleClickButton(locator.getProperty("Contacts"));	
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Privatecnttab"));
	action.VerifyDisableButton(locator.getProperty("EditpvtContact"));
	action.VerifyDisableButton(locator.getProperty("DeletepvtContact"));
	//Select private contact
	action.SelectCheckBox(locator.getProperty("Pvtcontactchk0"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("EditpvtContact"))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("EditpvtContact"));
	action.WaitForTitle(locator.getProperty("Edit_Private_Contact"));
	action.VerifyTitle(locator.getProperty("Edit_Private_Contact"));
	//Verify Edited private contact 
	Thread.sleep(1000);
    String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Contactlanglist")))).getFirstSelectedOption().getText();
    Assert.assertEquals(str,input.getProperty("Danish"));
    Thread.sleep(500);
    action.DoubleClickButton(locator.getProperty("Cancel"));	
  //handle exception
    
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Cancel"));
    }
	

    action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
}

@Test(description="Verify delete private contact",priority=6)
public void Delete_Private_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(500);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click Contacts Tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	action.ClickButton(locator.getProperty("Privatecnttab"));
	Thread.sleep(2000);
	action.VerifyDisableButton(locator.getProperty("DeletepvtContact"));
	//Select private contact
	action.SelectCheckBox(locator.getProperty("Pvtcontactchk0"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("DeletepvtContact"))));
	//Click on delete button
	action.DoubleClickButton(locator.getProperty("DeletepvtContact"));
	action.WaitForTitle(locator.getProperty("Contact_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Contact_Delete_Confirmation"));
	//Click on Delete confirm button
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(500);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	//handle exception
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(3000);
        action.DoubleClickButton(locator.getProperty("Users.Commit"));
    }
	
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
}
	


@Test(description="Verify deleted private contact",priority=7)
public void Verify_Delete() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select user
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(500);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(2000);
	//Click on Contacts tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	//Click on private contact tab
	action.ClickButton(locator.getProperty("Privatecnttab"));
	
	String str=action.driver.findElement(By.xpath(".//*[@id='privateContactsTable_core_table_content']/tbody/tr[2]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertTrue(true);
	
	}
	else
	{
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("privatecontactchbox")));
	int n = rows1.size();
	int flag=0;
	//Assert.assertEquals(noofrows, 1);
	for(int i=0;i<n;i++)
	{
		String str1=action.driver.findElement(By.xpath(".//*[@id='privateContactsTable:"+i+":tbl_inputText_firstName']")).getText();
	   boolean b=str1.equals(input.getProperty("Cfirstname"));
		if(b)
	   {
		   Assert.assertFalse(b);
		    flag=1;
		   break;
	   }
	}
		
	if(flag==0)
	{
		Assert.assertFalse(b);
	}
	}
	
}

	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{	
	action.Screenshot(result, action);
	action.logout(action);
	}
	
	

}
