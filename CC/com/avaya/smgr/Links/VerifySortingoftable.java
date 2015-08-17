package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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


public class VerifySortingoftable {
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
	
@Test(priority=1,description="Verifying sorting (Ascending) behavior with data selected in table ")
	public void Verify_Sort_Ascending()throws Exception
	{
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		String str1=action.driver.getWindowHandle();
		action.SwithchFrame("iframe0");
		//Click on last name link
		action.ClickLink(locator.getProperty("Last_Name"));
		//Thread.sleep(3000);
		//action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
		Thread.sleep(1000);
		//Click on login name and sort by Ascending order
		action.ClickLink(locator.getProperty("Login_Name"));
		action.ClickLinkByxpath(locator.getProperty("Users.loginname"));
		Thread.sleep(1000);
		action.ClickLinkByxpath(locator.getProperty("Users.loginname"));
		Thread.sleep(1000);
		//Define the list to store the column values
		
		List<String> displayedNames = new ArrayList<String>();
	    List<String> SortedNames = new ArrayList<String>();
	    Thread.sleep(1000);
	    WebElement tableType=action.driver.findElement(By.xpath(locator.getProperty("rowpoint")));
	    Thread.sleep(1000);
	    List<WebElement>rowElmt=tableType.findElements(By.xpath(locator.getProperty("columnpoint")));
	    String getData;
	    Thread.sleep(1000);
	    //Store Values in  two list displayedNames and SortedNames
	   // for(int i=2;i<rowElmt.size();i++)
	    for(int i=2;i<=10;i++)
	    {
	        getData=rowElmt.get(i).getText();
	        displayedNames.add(getData);
	        SortedNames.add(getData);
	    }
	    
	   //Print Column values
	    System.out.println(displayedNames);
	    Thread.sleep(1000);
	    List<String> sortingOperation = displayedNames;
	    //Sort Column values
	    Collections.sort(sortingOperation);
	    //Compare Actual values with expected values 
	    Assert.assertEquals(SortedNames, sortingOperation); 
	    action.driver.switchTo().window(str1);
	}


@Test(priority=2,description="Verifying sorting (Descending) behavior with data selected in table ")
public void Verify_Sort_Descending()throws Exception
{
	//Click on User Management,Manage Users
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	String str1=action.driver.getWindowHandle();
	action.SwithchFrame("iframe0");
	//Click on last name link
	action.ClickLink(locator.getProperty("Last_Name"));
	Thread.sleep(1000);
	//action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
	//Thread.sleep(2000);
	//Click on login name and sort by Ascending order
	action.ClickLink(locator.getProperty("Login_Name"));
	action.ClickLinkByxpath(locator.getProperty("Users.loginname"));
	//Define the list to store the column values
	List<String> displayedNames = new ArrayList<String>();
    List<String> SortedNames = new ArrayList<String>();
    Thread.sleep(1000);
    WebElement tableType=action.driver.findElement(By.xpath(locator.getProperty("rowpoint")));
    Thread.sleep(1000);
    List<WebElement>rowElmt=tableType.findElements(By.xpath(locator.getProperty("columnpoint")));
    String getData;
    Thread.sleep(1000);
    //Store Values in  two list displayedNames and SortedNames
   // for(int i=2;i<rowElmt.size();i++)
   for(int i=2;i<=10;i++)
    {
        getData=rowElmt.get(i).getText();
        displayedNames.add(getData);
        SortedNames.add(getData);
    }
    
   //Print Column values
    System.out.println(displayedNames);
    Thread.sleep(1000);
    List<String> sortingOperation = displayedNames;
    //Sort Column values
    Collections.sort(sortingOperation);
    Collections.reverse(sortingOperation);
    //Compare Actual values with expected values 
    Assert.assertEquals(SortedNames, sortingOperation); 
    action.driver.switchTo().window(str1);
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
