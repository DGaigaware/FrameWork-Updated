package com.avaya.smgr.workassgnment.assmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.Nullable;
import org.w3c.dom.Document;

import utility.UserAction;

import com.avaya.smgr.Worksetup.WorkSetup;
import com.google.common.base.Predicate;

public class SourceDeatails {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	WorkSetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
   	setup=new WorkSetup();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the elements of Account source page")
public void Verify_Account_Source() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//Verify Element not displayed before selecting resource
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Assign.resourceSource"))));
	action.VerifyNoElementdisplay(locator.getProperty("Acc.Sourcechk"));
	action.SelectCheckBox(locator.getProperty("Assign.resourceSource"));
	//Verify Element displayed after selecting resource
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Acc.Sourcechk"))));
	action.VerifyElementDisplay(locator.getProperty("Acc.Sourcechk"));
	Thread.sleep(1000);
	//Verify Element value
	action.VerifyElementValue(locator.getProperty("Acc.Sourcetable"), "avaya.com");
	action.VerifyDisableButton(locator.getProperty("RNext"));
	action.SelectCheckBox(locator.getProperty("Acc.Sourcechk"));
	action.VerifyEnableButton(locator.getProperty("RNext"));

}


@Test(description="Verify the Source Property tab of Account source page")
public void Account_Source_Property() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Assign.resourceSource"))));
	action.SelectCheckBox(locator.getProperty("Assign.resourceSource"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Acc.Sourcechk"))));
	action.SelectCheckBox(locator.getProperty("Acc.Sourcechk"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("RNext"))));
	action.ClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Source_Assignment_Details"));
	action.VerifyTitle(locator.getProperty("Source_Assignment_Details"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Acc.SourceCommitContbtn"))));
	action.VerifyEnableButton(locator.getProperty("Acc.SourceCommitContbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.SourceCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.SourceCancel"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Acc.SoourcePropchk"))));
	//verify commit and continue button
	action.SelectCheckBox(locator.getProperty("Acc.SoourcePropchk"));
	//Thread.sleep(1000);
	action.SelectFromdropDown(locator.getProperty("Acc.Defaultlist"), "Resource Adapter Present");
	action.ClickButton(locator.getProperty("Acc.SourceCommitContbtn"));
	//Thread.sleep(1000);
	action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Source_Assignment_Details"));
	//Verify Commit button
	action.SelectFromdropDown(locator.getProperty("Acc.Defaultlist"), "No Resource Adapter Present");
	action.ClickButton(locator.getProperty("Acc.SourceCommitbtn"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Thread.sleep(1000);
	action.ClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Source_Assignment_Details"));
	String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Acc.Defaultlist")))).getFirstSelectedOption().getText();
    Assert.assertEquals(str, "No Resource Adapter Present");
	//Thread.sleep(1000);
	//Verify Cancel button
	action.ClickButton(locator.getProperty("Acc.SourceCancel"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
}


@Test(description="Verify the elements of Overall business page")
public void Verify_Overall_Business() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Assign.resourceoverall"))));
	Thread.sleep(500);
	//action.VerifyNoElementdisplay(locator.getProperty("Acc.BusinessPropChk"));
	action.SelectCheckBox(locator.getProperty("Assign.resourceoverall"));
	Thread.sleep(5000);
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Acc.BusinessPropChk"))));
	action.VerifyEnableButton(".//*[@id='button']");
	//action.VerifyEnableButton(locator.getProperty("Acc.BusCommitContbtn"));
	//action.VerifyEnableButton(locator.getProperty("Acc.BusCommitbtn"));
	//action.VerifyEnableButton(locator.getProperty("Acc.BusCancelbtn"));
	Thread.sleep(5000);
	action.SelectCheckBox(locator.getProperty("Acc.BusinessPropChk"));
	//Verify Commit and Continue button
	action.entertext(locator.getProperty("Acc.Businessdefaultvalue"), "4");
	
	action.ClickButton(locator.getProperty("Acc.BusCommitContbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Verify Commit button
	action.ClickButton(locator.getProperty("Acc.BusCommitbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("No changes done on the property assignment");
	action.ClearText(locator.getProperty("Acc.Businessdefaultvalue"));
	action.entertext(locator.getProperty("Acc.Businessdefaultvalue"), "7");
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Acc.BusCommitbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("The system saved propeties on the selected business successfully.");
	//Verify value gets Commited
	action.SelectCheckBox(locator.getProperty("Assign.resourceoverall"));
	Thread.sleep(1000);
	String str=action.driver.findElement(By.xpath(locator.getProperty("Acc.Businessdefaultvalue"))).getAttribute("value");
	Assert.assertEquals("7", str);	
	
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
