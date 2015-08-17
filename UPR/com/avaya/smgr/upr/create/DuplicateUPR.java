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


public class DuplicateUPR {
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

@Test(description="Verify the Duplicate Button",groups={"Sanity"}) 
public void Duplicate_UPR() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));

action.SwithchFrame("iframe0");
action.VerifyDisableButton(locator.getProperty("Users.Duplicate"));
//Select one Upr from table
setup.SelectUPR(action,input.getProperty("uprcmsm2"));
action.WaitToClick(locator.getProperty("Users.Duplicate"));
//click on Duplicate button and verify the title of the page
action.DoubleClickButton(locator.getProperty("Users.Duplicate"));
//Enter name of UPR
action.entertext(locator.getProperty("Uprname"),input.getProperty("uprcmsm3"));

////verify the Language correctly displaying
//String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
//Assert.assertEquals(str1,input.getProperty("Danish"));
//Thread.sleep(1000);
////verify the Time correctly displaying
//String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
//Assert.assertEquals(input.getProperty("Danishtime"), str2);
action.DoubleClickButton(locator.getProperty("Commit"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
Thread.sleep(1000);
setup.VerifyUprname(action,input.getProperty("uprcmsm3"));

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
