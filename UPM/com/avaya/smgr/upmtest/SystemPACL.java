package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class SystemPACL {
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

@Test(description="Verify page elements of System presence ACL page",priority=1)
public void Verify_Page_Elements() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	//Verify Buttons
	action.VerifyDisableButton(locator.getProperty("EditACL"));
	action.VerifyDisableButton(locator.getProperty("DeleteACL"));
	action.VerifyEnableButton(locator.getProperty("NewACL"));
	action.VerifyEnableButton(locator.getProperty("RefreshACL"));
	//Select All ACL
	
	action.ClickLink(locator.getProperty("SelectAl"));
	Thread.sleep(2000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("ACLChk0"))));
	//verify checkbox
	action.VerifySelectcheckbox(locator.getProperty("ACLChk0"));
	Thread.sleep(2000);
	action.ClickLink(locator.getProperty("SelectNone"));
	Thread.sleep(1000);
	action.VerifydeSelectcheckbox(locator.getProperty("ACLChk0"));
	Thread.sleep(500);
	//action.SelectCheckBox(locator.getProperty("ACLChk0"));
	//Thread.sleep(2000);
	//action.VerifyEnableButton(locator.getProperty("EditACL"));
}

@Test(description="Verify Creation of new ACL",priority=2)
public void New_SPACL() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("NewACL"));
	Thread.sleep(1000);

	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	//wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("Actionlist"))));
	//Select Blick from dropdown
	//action.SelectFromdropDown_Index(locator.getProperty("Actionlist") , 0);
	//Click on Save button
	action.DoubleClickButton(locator.getProperty("SaveAcl"));
	Thread.sleep(1000);
		
}


@Test(description="Verify added New ACL",priority=3)
public void Verify_Added_SPACL() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	//Verify number of rows increases
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("Aclchkboxname")));
	int n = rows1.size();
	Assert.assertEquals(2, n);
	
}


@Test(description="Verify Edit System presence ACL",priority=4)
public void Edit_SPACL() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	//Select ACL to edit
	action.SelectCheckBox(locator.getProperty("ACLChk1"));
	Thread.sleep(4000);
	WebDriverWait  wait=new  WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(action.driver.findElement(By.xpath(locator.getProperty("EditACL")))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("EditACL"));
	
	//Edit Action to Allow
	action.SelectFromdropDown_Index(locator.getProperty("Actionlist") , 2);
	//Click on Save button
	action.ClickButton(locator.getProperty("SaveAcl"));

	action.ClickButton(locator.getProperty("EditACL"));
	Thread.sleep(1000);
	//Verify Edit perform successfully
	String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("Actionlist")))).getFirstSelectedOption().getText();
	
	Assert.assertEquals("Confirm",str1);
}


@Test(description="Verify Delete System presence ACL",priority=5)
public void Delete_SPACL() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	//Select ACL
	action.SelectCheckBox(locator.getProperty("ACLChk1"));
	Thread.sleep(4000);
	WebDriverWait  wait=new  WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.elementToBeClickable(action.driver.findElement(By.xpath(locator.getProperty("DeleteACL")))));
	//Click on Delete button
	action.DoubleClickButton(locator.getProperty("DeleteACL"));
    Thread.sleep(2000);
	action.VerifyEnableButton(locator.getProperty("NewACL"));
		
}


@Test(description="Verify Deleted System presence ACL ",priority=6)
public void Verify_Deleted_SPACL() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,System Presence ACLs
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("System_Presence_ACLs"));
	action.WaitForTitle(locator.getProperty("Presence_ACL"));
	action.VerifyTitle(locator.getProperty("Presence_ACL"));
	action.SwithchFrame("iframe0");
	//Verify the number of of rows decreases
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("Aclchkboxname")));
	int n = rows1.size();
	Assert.assertEquals(1, n);
	
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
