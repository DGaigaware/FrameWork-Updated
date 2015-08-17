package com.avaya.smgr.upr.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.smgr.Tsetup.Tenantsetup;

import utility.UserAction;


public class CMUPR {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Tenantsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException,Exception
    {
	action = new UserAction();
	locator=new Properties();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
    }

@Test(description="Verify the error message on blank template",priority=1)
public void Verify_Error_Msg() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User provisioning rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	//Fill up the required fields
	action.ClearText(locator.getProperty("Uprname"));
	action.entertext(locator.getProperty("Uprname"), input.getProperty("upr1"));
	//Click on Communication Profile
	action.ClickLink(locator.getProperty("Communication_Profile"));
	//Select CM Checkbox and fill up required fields
	action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Upr.cmlist"),input.getProperty("cm29"));
	Thread.sleep(2000);
	WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("Upr.exttypecheckbox")));
	while(!(ele.isSelected()))
			{
	action.SelectCheckBox(locator.getProperty("Upr.exttypecheckbox"));
			}
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.exttextbox"))));
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Commit"));
	//action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
	action.VerifyTitle(locator.getProperty("New_User_Provisioning_Rule"));
	Thread.sleep(1000);
    action.VerifyStringValue("Please select a Template -it is a mandatory field.");
}


@Test(description="Verify the creation of UPR with CM Element",priority=2,groups={"Sanity"})
public void UPR_with_CM() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User provisioning rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	//Fill up the required fields
	action.ClearText(locator.getProperty("Uprname"));
	action.entertext(locator.getProperty("Uprname"), input.getProperty("upr1"));
	action.entertext(locator.getProperty("Upr.comprofilepass"), input.getProperty("Compassword"));
	Thread.sleep(500);
	action.entertext(locator.getProperty("Upr.confirmpass"), input.getProperty("Compassword"));
	Thread.sleep(500);
	action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
	Thread.sleep(500);
	action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
	Thread.sleep(500);
	//Click on Communication Profile
	action.DoubleClickButton(locator.getProperty("Tent.AdminLink"));
	//Select CM Checkbox and fill up required fields
	action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Upr.cmlist"),input.getProperty("cm29"));
	WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("Upr.exttypecheckbox")));
	while(!(ele.isSelected()))
			{
	action.SelectCheckBox(locator.getProperty("Upr.exttypecheckbox"));
			}
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.exttextbox"))));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Upr.exttextbox"), input.getProperty("upr-Extension-Range"));
	action.SelectFromdropDown(locator.getProperty("Upr.templatelist"), input.getProperty("Template"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Upr.securitytypelist"),input.getProperty("Blank"));
	Thread.sleep(3000);
	//Click on Commit Button and verify title
	action.DoubleClickButton(locator.getProperty("Commit"));
	//action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	Thread.sleep(1000);
	setup.VerifyUprname(action,input.getProperty("upr1"));
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
