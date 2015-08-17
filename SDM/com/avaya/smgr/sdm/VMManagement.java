package com.avaya.smgr.sdm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class VMManagement {
	
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the VM Management location is created successfully",priority=1)
public void VMNameCreation() throws Exception
{
	
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("VM_Management"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.SwithchFrame("iframe0");
	WebDriverWait wait = new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.getProperty("VM.Loc.New"))));
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.New"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.getProperty("VM.Loc.Name"))));
	action.entertext(locator.getProperty("VM.Loc.Name1"), input.getProperty("UpdateSite"));
	action.entertext(locator.getProperty("VM.Loc.address1"), input.getProperty("UpdateSite"));
	action.entertext(locator.getProperty("VM.Loc.city1"), input.getProperty("UpdateSite"));
	action.entertext(locator.getProperty("VM.Loc.State1"), input.getProperty("UpdateSite"));
	action.entertext(locator.getProperty("VM.Loc.Zip1"), input.getProperty("port"));
	action.entertext(locator.getProperty("VM.Loc.Country1"), input.getProperty("UpdateSite"));
	Thread.sleep(2000);
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.Save"))).click();
	Thread.sleep(2000);
	action.driver.findElement(By.xpath(locator.getProperty("VM.Loc.Refresh1"))).click();
	Thread.sleep(2000);
	boolean b=action.driver.findElement(By.xpath(locator.getProperty("VM.Loc.City.Locname1"))).getText().equals( input.getProperty("UpdateSite"));
	Assert.assertTrue(b);
	
}


@Test(description="Verify the VM Management location is Edit successfully",priority=2)
public void VMNameEdit() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("VM_Management"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.SwithchFrame("iframe0");
	WebDriverWait wait = new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.getProperty("VM.Loc.New"))));
	//Click on the First Check box
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.FirstCheckBox1"))).click();
	Thread.sleep(100);
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.Edit"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.getProperty("VM.Loc.City.Edit"))));
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.City.Edit"))).clear();
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.City.Edit"))).sendKeys(input.getProperty("UpdatTeam"));
	wait.until(ExpectedConditions.elementToBeClickable(By.id(locator.getProperty("VM.Loc.Save.Edit"))));
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.Save.Edit"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.getProperty("VM.Loc.Save.Edit.OK"))));
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.Save.Edit.OK"))).click();
	Thread.sleep(500);
	action.driver.findElement(By.xpath(locator.getProperty("VM.Loc.Refresh1"))).click();
	Thread.sleep(1000);
	//boolean b=action.driver.findElement(By.xpath(locator.getProperty("VM.Loc.City.Summary1"))).getText().equals( input.getProperty("UpdatTeam"));
	//Assert.assertTrue(b);
	
}


@Test(description="Verify the VM Management location is Delete successfully",priority=3)
public void VMNameDelete() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("VM_Management"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.SwithchFrame("iframe0");
	
	//Click on the First Check box
	action.driver.findElement(By.id(locator.getProperty("VM.Loc.FirstCheckBox1"))).click();
	action.WaitForObj(locator.getProperty("VM.Loc.Delete1"));
	//click on the new button
	action.ClickButton(locator.getProperty("VM.Loc.Delete1"));
	Thread.sleep(2000);
	action.DoubleClickButton(locator.getProperty("VM.Loc.Save.Delete.Yes1"));
	action.WaitForObj(locator.getProperty("VM.LOC.Save.Delete.OK1"));
	action.DoubleClickButton(locator.getProperty("VM.LOC.Save.Delete.OK1"));
	//action.WaitForObj(locator.getProperty("VM.LOC.entry"));
	
	//action.VerifyDeleteEntry(locator.getProperty("VM.LOC.entry"), (input.getProperty("UpdateSite")));
	Thread.sleep(1000);
}


@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}




}
