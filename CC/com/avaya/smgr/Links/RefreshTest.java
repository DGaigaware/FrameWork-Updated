package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class RefreshTest {
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
	
@Test(description="Verifying links on Common Console are not lost due to various action performed")
	public void Links_Test() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Groups & Roles,Groups
		action.ClickLink(locator.getProperty("Groups_&Roles"));
		action.WaitForTitle(locator.getProperty("Groups_and_Roles"));
		String st1=action.driver.getWindowHandle();
		action.ClickLink(locator.getProperty("Groups"));
		action.WaitForTitle(locator.getProperty("Group_Management"));
		action.SwithchFrame("iframe0");
		//Click on Advance Search
		action.ClickLink(locator.getProperty("Advanced_Search"));
		action.entertext(locator.getProperty("inputtext"),"OperationsGroup");
		//Click on Search Button
		action.ClickButton(locator.getProperty("Searchbtn"));
		Thread.sleep(1000);
		action.VerifyElementValue(locator.getProperty("Firstrow"), "OperationsGroup");
		//Click on Close Button
		action.ClickButton(locator.getProperty("Closebtn"));
		action.WaitForTitle(locator.getProperty("Group_Management"));
		action.VerifyStringValue(locator.getProperty("Advanced_Search"));
		action.driver.switchTo().window(st1);
		//Click on Resources Link
		action.ClickLink(locator.getProperty("Resources"));
		action.WaitForTitle(locator.getProperty("Resources"));
		action.SwithchFrame("iframe0");
		//Click on Advance Search
		action.ClickLink(locator.getProperty("Advanced_Search"));
		//Click on Close Button
		action.ClickButton(locator.getProperty("Closebtn"));
		action.WaitForTitle(locator.getProperty("Resources"));
		action.VerifyStringValue(locator.getProperty("Advanced_Search"));
		action.driver.switchTo().window(st1);
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
