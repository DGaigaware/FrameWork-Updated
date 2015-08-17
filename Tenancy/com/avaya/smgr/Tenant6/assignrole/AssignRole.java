package com.avaya.smgr.Tenant6.assignrole;
/*
 * Test Case related to Roles UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignRole{
	boolean b=true,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	@BeforeMethod(alwaysRun=true)
	public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }
@Test(description="Verify that Role is assigned to the Tenant",priority=3)
public void TenantRoleAssign() throws Exception{
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	String RoleName=input.getProperty("Tenant.RoleName");
	action.SwithchFrame("iframe0");
	//action.driver.switchTo().frame("iframe1");
	//action.driver.switchTo().frame("iframe2");
	action.SelectElementByLoginname(input.getProperty("Tenant.Login"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	Thread.sleep(1000);
	WebElement Membership_Tab=action.driver.findElement(By.linkText(locator.getProperty("Membership_Tab")));
	Membership_Tab.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);

	Membership_Tab.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
	WebElement element1=action.driver.findElement(By.xpath(locator.getProperty("User.assignRole")));
	while(!(element1.isDisplayed())){
		action.ClickLink(locator.getProperty("Membership_Tab"));
		Thread.sleep(3000);

	}
	Thread.sleep(3000);
	action.ClickButton(locator.getProperty("User.assignRole"));
	action.WaitForTitle(locator.getProperty("Assign_Roles"));
	action.ClickButton(locator.getProperty("Users.Name"));
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("Users.Name"));
	Thread.sleep(2000);
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='tblAdditionalRoles_core_table_content']/tbody/tr"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='tblAdditionalRoles_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
    	if(sValue.contains(RoleName)){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='tblAdditionalRoles:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	Thread.sleep(3000);
	//Click on the Select button
	action.ClickButton(locator.getProperty("Users.AssingRole.Select"));
	Thread.sleep(5000);
	//Click on commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(5000);
}


@Test(description="Verify assignPassword to the Tenant admin",priority=1)
public void TenantChangePassword() throws Exception{
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.SwithchFrame("iframe0");
	//action.driver.switchTo().frame("iframe1");
	//action.driver.switchTo().frame("iframe2");
	action.SelectElementByLoginname(input.getProperty("Tenant.Login"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(5000);
    action.ClickButton(locator.getProperty("User.ChgPassword"));
    Thread.sleep(5000);
	action.entertext(locator.getProperty("Users.Password"), input.getProperty("TPassword"));
	action.entertext(locator.getProperty("Users.CnfPassword"), input.getProperty("TPassword"));
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(3000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	Thread.sleep(3000);	
}
@Test(description="Password change for Tenant admin",priority=2)
public void Loginwithtenantadmin1() throws Exception
{
	action.driver.navigate().refresh();
	//Click on Logoff button
	action.ClickButton(locator.getProperty("LogOff"));
	action.WaitForTitle(locator.getProperty("System_Manager"));
	//Fill the Userid and password of tenant admin
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Password"), input.getProperty("TPassword"));
	//Click on log On button
	action.ClickButton(locator.getProperty("LogOn"));
	Thread.sleep(2000);
	//Set the New password
	action.entertext(locator.getProperty("UserId"), input.getProperty("TChangePassword"));
	action.entertext(locator.getProperty("Password"), input.getProperty("TChangePassword"));
	//Click on Change button
	action.ClickButton(locator.getProperty("Password.change"));
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyElementValue(locator.getProperty("LoginUser"), input.getProperty("Tenant.Login"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	
}

	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
	}


}

