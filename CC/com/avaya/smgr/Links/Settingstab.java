package com.avaya.smgr.Links;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Settingstab {
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
//This test cases need to element as verification of build no
	/*
@Test(priority=1,description="Verify Build number from about tab")
public void BuildInfo() throws Exception {
	
    action.WaitForTitle(locator.getProperty("Dashboard"));
    action.VerifyTitle(locator.getProperty("Dashboard"));
    //Click on Setting tab and Test for About link
    action.ClickLinkByxpath(locator.getProperty("Settingtab"));
    Thread.sleep(1000);
	action.ClickLink("About");
	Thread.sleep(1000);
	String str=action.driver.findElement(By.xpath(locator.getProperty("BuildNo"))).getText();
	
	Assert.assertEquals(str, input.getProperty("Buildinfo"));
}
*/
	
@Test(priority=2,description="Verify the Functionality of about, help, password options in Setting tab")
	  public void SettingsTab_Test() throws Exception {
	
			action.RefreshPage();
	        action.WaitForTitle(locator.getProperty("Dashboard"));
	        action.VerifyTitle(locator.getProperty("Dashboard"));
	      
	        //Click on Setting tab and Test for About link
		    action.ClickLinkByxpath(locator.getProperty("Settingtab"));
		    Thread.sleep(1000);
			action.ClickLink("About");
			
			//Click on details Link
			action.ClickLink("Details");
			Thread.sleep(1000);
			action.VerifyStringValue("Operating System - CentOS release 6.5");
			action.ClickLink("Third Party Terms for CentOS");
			Thread.sleep(1000);
			//Click on Third party Terms for CentOS
			action.VerifyStringValue("Third Party Terms for CentOS");
			action.ClickLinkByxpath(locator.getProperty("closeaboutwindow"));
			
			
			//Click on Setting tab and Test for Help link
			String parentHandle = action.driver.getWindowHandle(); 
		
			action.ClickLinkByxpath(locator.getProperty("Settingtab"));
			Thread.sleep(1000);
			action.ClickLink(locator.getProperty("Help"));
			
           // Switch to help page
			for (String winHandle : action.driver.getWindowHandles())
			{
			    action.driver.switchTo().window(winHandle); 
			}
			
			Thread.sleep(2000);
			action.WaitForTitle("Help");
			action.VerifyStringValue("Help");
			//Close help page
			action.driver.close(); 
			// Switch to Dashboard
			action.driver.switchTo().window(parentHandle);
			
			//Click on Setting tab and Test for  change password
			action.ClickLinkByxpath(locator.getProperty("Settingtab"));
			action.ClickLink("Change Password");
			Thread.sleep(1000);
			action.VerifyStringValue("Password");
			//Close the password window
			action.ClickLinkByxpath(locator.getProperty("closechangepasswindow"));
			action.driver.switchTo().window(parentHandle);
		
	  }
	
	@Test(priority=3,description="Verifying that the user is able to view Help page")
	public void Help_Test()throws Exception
	{
		 action.RefreshPage();
		//Click on User Management,manage users link 
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		String parentHandle = action.driver.getWindowHandle();
		action.SwithchFrame("iframe0");
		Thread.sleep(2000);
		//Click on help link
		action.ClickLinkByxpath(locator.getProperty("Helplink"));
	  
		//Switch to help page
		for (String winHandle : action.driver.getWindowHandles())
		{
		    action.driver.switchTo().window(winHandle); 
		}
		//Thread.sleep(4000);
		action.WaitForTitle("Administering Avaya Aura� System Manager");
		action.VerifyTitle("Administering Avaya Aura� System Manager");
		//Close help page	
		action.driver.close(); 
		action.driver.switchTo().window(parentHandle);
		
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
