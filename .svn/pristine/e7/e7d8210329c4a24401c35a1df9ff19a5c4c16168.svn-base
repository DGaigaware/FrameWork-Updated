package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class VerifyTeamoptions {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the team list options after selecting specific department") 
public void verifytenantteamoption() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	//Select the tenant
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"), input.getProperty("TName"));
	Thread.sleep(1000);
	//Select the site
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"), input.getProperty("Site2"));
	Thread.sleep(2000);
	//Select the department
	action.SelectFromdropDown(locator.getProperty("Users.deptlist"), input.getProperty("Dept1"));
	Thread.sleep(2000);
	//Verify the team options
	WebElement selectElement =action.driver.findElement(By.xpath(locator.getProperty("Users.teamlist")));
	Select select = new Select(selectElement);
	List<WebElement> options=select.getOptions();
	int n=options.size();
	Thread.sleep(2000);
	String[] exp ={"",input.getProperty("team1")};
	for(int i=0;i<n;i++)
	{
		Assert.assertTrue(options.get(i).getText().equals(exp[i]));
	}

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
