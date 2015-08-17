package com.avaya.smgr.upr.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class RefreshUPRtable {
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

@Test(description="Verify the functionality of refresh button of UPR table")
public void Refersh_Test() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User provisioning rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Count the number of rows in upr table
	List<WebElement> rows = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
    int numberofrows = rows.size();
    //Click on New button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
	//Fill up the required fields
	action.entertext(locator.getProperty("Uprname"),input.getProperty("upr5"));
	//Click on commit
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on refresh button
	action.ClickLinkByxpath(locator.getProperty("Upr.refresh"));
	//Verify number of rows increase by one
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int noofrows = rows1.size();
	Assert.assertEquals(noofrows, numberofrows+1);
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
