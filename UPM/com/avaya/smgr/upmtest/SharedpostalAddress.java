package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import com.avaya.smgr.upmsetup.Upmsetup;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class SharedpostalAddress {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Upmsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		setup=new com.avaya.smgr.upmsetup.Upmsetup();
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}
	
@Test(description="Verify the Creation of shared address",priority=1)
public void Create_Shared_Address() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Shared_Addresses"));
	action.WaitForTitle(locator.getProperty("Shared_Address"));
	action.VerifyTitle(locator.getProperty("Shared_Address"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("NewContAdd"));
	action.WaitForTitle(locator.getProperty("Add_Address"));
	action.VerifyTitle(locator.getProperty("Add_Address"));
	
	action.entertext(locator.getProperty("Addressname"), input.getProperty("Adname3"));
	action.SelectFromdropDown(locator.getProperty("AddressType"), "Home");
	Thread.sleep(500);
	action.entertext(locator.getProperty("Locality"), "Noyda");
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Contactdetailbtn"))));
	action.ClickButton(locator.getProperty("Contactdetailbtn"));
	action.entertext(locator.getProperty("businessphone"), "5555");
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Shared_Address"));
	action.VerifyTitle(locator.getProperty("Shared_Address"));
}
	

@Test(description="Verify Addition of poastal address through shared address",priority=2)
public void Add_Postal_Address() throws Exception
{
	
	action.driver.navigate().refresh();
	//Click on User Management,Public Contacts
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	action.SwithchFrame("iframe0");
	//Click on new button
	action.ClickButton(locator.getProperty("NewContact"));
	action.WaitForTitle(locator.getProperty("New_Public_Contact"));
	action.VerifyTitle(locator.getProperty("New_Public_Contact"));
	//Fill up the required details
	action.EntertextUsingKey(locator.getProperty("contactLastName"), "aaabc",Keys.TAB);
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("contactLastNameAscii")),"aaabc"));
	action.EntertextUsingKey(locator.getProperty("contactFirstname"), "asd",Keys.TAB);
	Thread.sleep(500);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("contactFirstnameAscii")),"asd"));
	Thread.sleep(500);
	//Click on  Choose Shared Address
	action.DoubleClickButton(locator.getProperty("SharedAddbtn"));
	action.WaitForTitle(locator.getProperty("Choose_Address"));
	action.VerifyTitle(locator.getProperty("Choose_Address"));
	
	action.SwithchFrame("iframe0");
	//Select shared address
	setup.AccessSecondcolumn(action,input.getProperty("Adname3"));
	Thread.sleep(2000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.AssingRole.Select"))));
	//Click on Select button
	action.DoubleClickButton(locator.getProperty("Users.AssingRole.Select"));
	action.WaitForTitle(locator.getProperty("New_Public_Contact"));
	action.VerifyTitle(locator.getProperty("New_Public_Contact"));
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Commit"));
	
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
}

@Test(description="Verify added postal address",priority=3)
public void Verify_Added_Address() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Public Contacts
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	action.SwithchFrame("iframe0");
	action.SelectCheckBox(locator.getProperty("publicChk0"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("EditpvtContact"));
	action.WaitForTitle(locator.getProperty("Edit_Public_Contact"));
	action.VerifyTitle(locator.getProperty("Edit_Public_Contact"));
	
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("Contctaddbyname")));
	int n = rows1.size();
	int b=0;
	//Assert.assertEquals(noofrows, 1);
	for(int i=0;i<n;i++)
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='contactPostalAddress:"+i+":tbl_inputText_addressName']")).getText();
	   if(str.equals(input.getProperty("Adname3")))
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
@Test(description="Verify the Deletion of public contact",priority=4)
public void Delete_Public_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Public Contacts
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	action.SwithchFrame("iframe0");
	//Select Checkbox
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("publicChk0"))));
	action.SelectCheckBox(locator.getProperty("publicChk0"));
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("DeletepvtContact"))));
	Thread.sleep(3000);
	//Click on delete button
	action.DoubleClickButton(locator.getProperty("DeletepvtContact"));
	action.WaitForTitle(locator.getProperty("Contact_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("Contact_Delete_Confirmation"));
	//Click on Delete button to confirm
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));	
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
