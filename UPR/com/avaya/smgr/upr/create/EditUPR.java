package com.avaya.smgr.upr.create;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
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
import org.testng.annotations.Test;

import com.avaya.smgr.Tsetup.Tenantsetup;

import utility.UserAction;


public class EditUPR {
	boolean b;
	UserAction action =null;
	Tenantsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the Edit UPR operation",groups={"Sanity"})
public void Edit_UPR() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));

action.SwithchFrame("iframe0");
//Select one Upr from table
setup.SelectUPR(action,input.getProperty("uprcmsm2"));

WebDriverWait wait = new WebDriverWait(action.driver,60);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
//click on Edit button and verify the title of the page
action.DoubleClickButton(locator.getProperty("Users.Edit"));
action.WaitForTitle(locator.getProperty("Edit_User_Provisioning_Rule"));

//Verify the buttons are enabled 
action.VerifyEnableButton(locator.getProperty("Commit"));
action.VerifyEnableButton(locator.getProperty("Cancel"));
//Verify the uprname field is  enabled
action.VerifyEnableTextbox(locator.getProperty("Uprname"));
action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Korean"));
action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Ktime"));
//Click on cancel button and wait for title of the page
action.DoubleClickButton(locator.getProperty("Commit"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));

}

@Test(description="Verify the Edited UPR",groups={"Sanity"})
public void Verify_Edited_Upr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Select one Upr from table
	setup.SelectUPR(action,input.getProperty("uprcmsm2"));
	Thread.sleep(1000);
	//Wait until button is enabled
	WebDriverWait wait = new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//click on Edit button and verify the title of the page
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("Edit_User_Provisioning_Rule"));
	
	//Verify the edit operation successfully
    String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
    Assert.assertEquals(str1,input.getProperty("Korean"));
    Thread.sleep(1000);
    String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
    Assert.assertEquals(input.getProperty("Ktime"), str2);
    action.ClickButton(locator.getProperty("Users.Schedulebtm"));
    action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	
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
