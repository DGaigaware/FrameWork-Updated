package com.avaya.smgr.upmtest;

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


public class FilterTest {
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
	
@Test(description="Verify the Filter Functionality")
public void Filter_by_Login_Name() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on Filter link
	action.ClickLinkByxpath(locator.getProperty("Filter"));
	Thread.sleep(1000);
	//enter login name to filter
	action.entertext(locator.getProperty("Loginnamefilter"), input.getProperty("UsersLoginname2"));
	
	//Click on Apply link
	action.ClickLinkByxpath(locator.getProperty("Filter.Apply"));
	Thread.sleep(1000);
    action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
	//Calculate the number of rows
	List<WebElement> rows =action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	boolean b=false;
	for(int i=3;i<=numberofrows+2;i++)
	{
		//Verify the rows of table
		String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
		b=str1.startsWith(input.getProperty("UsersLoginname2"));
			if(b==false)
			{
				Assert.assertTrue(b);
				break;
			}
	}
	
    if(b==true)
	{
		Assert.assertTrue(b);
	}
    //Click on Disable filter button
    action.ClickLinkByxpath(locator.getProperty("Disablefilter"));
    Thread.sleep(1000);
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
