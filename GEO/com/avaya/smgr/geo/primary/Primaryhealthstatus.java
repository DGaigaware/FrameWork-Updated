package com.avaya.smgr.geo.primary;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import utility.UserAction;

public class Primaryhealthstatus {
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
 public void Move_to_GR_Health() throws Exception
 {
	 action.driver.navigate().refresh();
	 //Click on Geographic Redundancy,GR Health
	 action.ClickLink(locator.getProperty("Geographic_Redundancy"));
	 action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
	 Thread.sleep(1000);
	 action.ClickLink(locator.getProperty("GR_Health"));
	 action.WaitForTitle(locator.getProperty("GR_Health"));
	 action.VerifyTitle(locator.getProperty("GR_Health"));
	 action.SwithchFrame("iframe0");
	 Thread.sleep(1000);
 }
	
@Test(description="Verify the File Replication link on GR Health page")
    public void File_Replication() throws Exception
    {
	Move_to_GR_Health();
	//Click on View Details link of File Replication
	action.ClickLinkByxpath(locator.getProperty("FileReplication"));
	DateFormat dateFormat = new SimpleDateFormat("MMMM");
	String str=action.driver.findElement(By.xpath(locator.getProperty("FilerepStatus"))).getText();
    Date date = new Date();
	boolean b=str.contains(dateFormat.format(date));
	Assert.assertTrue(b);
	Thread.sleep(1000);
    }

@Test(description="Verify the Database Replication link on GR Health page")
   public void Database_Replication_Date() throws Exception
   {
	Move_to_GR_Health();
  //Click on View Graph link of Database Replication
    action.ClickLinkByxpath(locator.getProperty("DatabaseReplication"));
    Thread.sleep(1000);
    //Verify buttons are enabled
    action.VerifyEnableButton(locator.getProperty("Editdate"));
    action.VerifyEnableButton(locator.getProperty("Refreshgraph"));
    //Click on Edit date button
    action.DoubleClickButton(locator.getProperty("Editdate"));
    WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Generategraph"))));
    //Verify buttons are enabled
    action.VerifyEnableButton(locator.getProperty("Generategraph"));
    action.VerifyEnableButton(locator.getProperty("Editdatecancel"));
    //verify error message on invalid date
    action.ClearText(locator.getProperty("Startday"));
    action.entertext(locator.getProperty("Startday"), "abc");
    action.ClickButton(locator.getProperty("Generategraph"));
    Thread.sleep(1000);
    action.VerifyStringValue("Invalid date");
    action.ClearText(locator.getProperty("Startday"));
    action.entertext(locator.getProperty("Startday"), "32");
    action.ClickButton(locator.getProperty("Generategraph"));
    Thread.sleep(1000);
    action.VerifyStringValue("Invalid date");
    Thread.sleep(1000);
    //Verify error message on date greater than current date
    action.ClearText(locator.getProperty("Startday"));
    action.entertext(locator.getProperty("Startday"), "3");
    Thread.sleep(1000);
    action.ClearText(locator.getProperty("Endyear"));
    action.entertext(locator.getProperty("Endyear"), "2016");
    action.ClickButton(locator.getProperty("Generategraph"));
    Thread.sleep(1000);
    action.VerifyStringValue("Graph end sate must not be greater than current date.");
    Thread.sleep(1000);
    //Verify error message on greater start date than end date
    action.ClearText(locator.getProperty("Startday"));
    action.entertext(locator.getProperty("Startday"), "30");
    Thread.sleep(1000);
    action.ClearText(locator.getProperty("Endyear"));
    action.entertext(locator.getProperty("Endyear"), "2015");
    action.ClickButton(locator.getProperty("Generategraph"));
    Thread.sleep(1000);
    action.VerifyStringValue("Graph start date must be less than the graph end date");
    Thread.sleep(1000);
   }

@Test(description="Verify the Database Replication link on GR Health page")
	public void Database_Replication_Time() throws Exception
	{
	Move_to_GR_Health();
	//Click on View Graph link of Database Replication
	 action.ClickLinkByxpath(locator.getProperty("DatabaseReplication"));
	 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Editdate"))));
	 action.DoubleClickButton(locator.getProperty("Editdate"));
	 Thread.sleep(1000);
	 //verify hours field
	 action.ClearText(locator.getProperty("Starthour"));
	 action.entertext(locator.getProperty("Starthour"), "ab");
	 action.DoubleClickButton(locator.getProperty("Generategraph"));
	 Thread.sleep(1000);
	 action.VerifyStringValue("Invalid Time; value for hour must be numeric.");
	//verify Minutes field by entering invalid details
	 action.ClearText(locator.getProperty("Starthour"));
	 action.entertext(locator.getProperty("Starthour"), "5");
	 action.ClearText(locator.getProperty("Startminute"));
	 action.entertext(locator.getProperty("Startminute"), "as");
	 action.ClickButton(locator.getProperty("Generategraph"));
	 Thread.sleep(1000);
	 //Verify error message
	 action.VerifyStringValue("Invalid Time; value for minute(s) must be numeric.");
	 //Verify AM PM
	 action.SelectFromdropDown(locator.getProperty("Startampm"), "AM");
	 action.ClearText(locator.getProperty("Starthour"));
	 action.entertext(locator.getProperty("Starthour"), "13");
	 action.ClearText(locator.getProperty("Startminute"));
	 action.entertext(locator.getProperty("Startminute"), "52");
	 action.ClickButton(locator.getProperty("Generategraph"));
	 Thread.sleep(1000);
	//Verify error message
	 action.VerifyStringValue("Invalid Time; value for hour must be on or between 1 and 12.");
	 //Verify 24Hr
	 action.SelectFromdropDown(locator.getProperty("Startampm"), "24Hr");
	 action.ClearText(locator.getProperty("Starthour"));
	 action.entertext(locator.getProperty("Starthour"), "24");
	 action.ClearText(locator.getProperty("Startminute"));
	 action.entertext(locator.getProperty("Startminute"), "52");
	 action.ClickButton(locator.getProperty("Generategraph"));
	 Thread.sleep(1000);
	//Verify error message
	 action.VerifyStringValue("Invalid Time; value for hour must be on or between 0 and 23.");
	 
	 action.SelectFromdropDown(locator.getProperty("Startampm"), "24Hr");
	 action.ClearText(locator.getProperty("Starthour"));
	 action.entertext(locator.getProperty("Starthour"), "5");
	 action.ClearText(locator.getProperty("Endminute"));
	 action.entertext(locator.getProperty("Endminute"), "62");
	 action.ClickButton(locator.getProperty("Generategraph"));
	 Thread.sleep(1000);
	//Verify error message
	 action.VerifyStringValue("Invalid Time; value for minute(s) must be between 0 and 59.");
	 
	}


@Test(description="Verify the Directory Replication link on GR Health page")
	public void Directory_Replication() throws Exception
	{
	Move_to_GR_Health();
	 //Click on View Graph link of Directory Replication
	 action.ClickLinkByxpath(locator.getProperty("DirectoryReplication"));
	 Thread.sleep(1000);
	 WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Editdate"))));
	 //Verify buttons are enabled
	 action.VerifyEnableButton(locator.getProperty("Editdate"));
	 action.VerifyEnableButton(locator.getProperty("Refreshgraph"));
	 //Click on Edit date button
	 action.DoubleClickButton(locator.getProperty("Editdate"));
	 Thread.sleep(1000);
	 //Verify buttons are enabled
	 action.VerifyEnableButton(locator.getProperty("Generategraph"));
	 action.VerifyEnableButton(locator.getProperty("Editdatecancel"));
	}

@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException
        {  
	action.Screenshot(result, action);
	action.logout(action);
		}
	
		
	}
