package com.avaya.smgr.upr.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class SelectAll {
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

@Test(description="Verify the functionality of Select All Link")
public void Select_All_Test() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User provisioning rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	
	//Click on All link
	action.ClickLinkByxpath(locator.getProperty("All"));
	//Count the number of rows in upr table
	Thread.sleep(1000);
    List<WebElement> rows = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
    int numberofrows = rows.size();
    //Verify all UPR are selected
    for(int i=0;i<numberofrows;i++)
    {
    	action.VerifySelectcheckbox(locator.getProperty("Table1")+i+locator.getProperty("Bracket"));
    
    }
    
    action.ClickLinkByxpath(locator.getProperty("None"));
    //Verify all UPR are unchecked
    for(int i=0;i<numberofrows;i++)
    {
    	action.VerifydeSelectcheckbox(locator.getProperty("Table1")+i+locator.getProperty("Bracket"));
    
    }
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
