package com.avaya.smgr.Tenant;

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


public class EditTeam {
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

@Test(description="Verify the Edit team operation of tenant")
public void Editteam() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	action.ClickElement(locator.getProperty("Tenanttree3"));
	Thread.sleep(1000);
	action.ClickElement(locator.getProperty("Tenanttree4"));
	Thread.sleep(1000);
	action.ClickElement(locator.getProperty("Tenanttree5"));
	Thread.sleep(1000);
	//Click on Team
	action.ClickLink(input.getProperty("team1"));
	Thread.sleep(2000);
	//Click on Edit button
	action.ClickButton(locator.getProperty("Edit"));
	//Edit team and Click on Commit button
	action.ClearText(locator.getProperty("teamdesc"));
	action.entertext(locator.getProperty("teamdesc"), "Team");
	action.ClickButton(locator.getProperty("Tenantadd"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	Thread.sleep(2000);
	action.VerifyStringValue("team1 is updated successfully. Refresh the tree to see updated node.");
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
