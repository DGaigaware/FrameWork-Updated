package com.avaya.smgr.Tenant6.users;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class TenantUseroperations {
	boolean b=false,match=false;;
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
	action.login(input.getProperty("Tenant.Login"),input.getProperty("TChangePassword"),action);
   }


@Test(description="Create the user using Tenant Login",priority=1)
public void Tenantusercreation() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(5000);
	action.SelectFromdropDown(locator.getProperty("Users.deptlist"),input.getProperty("UpdateDept"));
	Thread.sleep(5000);
	action.SelectFromdropDown(locator.getProperty("Users.teamlist"),input.getProperty("UpdatTeam"));
	Thread.sleep(3000);
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("User.Loginname"),Keys.TAB);
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	Thread.sleep(2000);
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.Verify_Add_Fifthcolumn(input.getProperty("Loginname"));
}
@Test(description="Edit the user using Tenant Login",priority=2)
public void Tenantuserupdation() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.SwithchFrame("iframe0");
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(2000);
	action.ClearText(locator.getProperty("Lastname"));
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UpdatedName"),Keys.TAB);
	Thread.sleep(3000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//action.Verify_Add_Secondcolumn(Columnvalue);(input.getProperty("Loginname"));

}
@Test(description="Edit the CM for user using Tenant Login",priority=3,enabled=false)
public void TenantuseraddCm() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.SwithchFrame("iframe0");
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
    action.ClickButton(locator.getProperty("Upr.checkbox2"));
    Thread.sleep(1000);
    action.SelectFromdropDown_Index(locator.getProperty("Upr.cmlist"),1);
	Thread.sleep(5000);
	action.SelectFromdropDown_Index(locator.getProperty("Upr.templatelist"),2);
	Thread.sleep(5000);

	action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//action.Verify_Add_Secondcolumn(Columnvalue);(input.getProperty("Loginname"));
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifySelectcheckbox(locator.getProperty("Users.Cmendcheckbox"));
	Thread.sleep(2000);
}
@Test(description="Edit the SM for user using Tenant Login",priority=4,enabled=false)
public void TenantuseraddSM() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	Thread.sleep(1000);
	action.SwithchFrame("iframe0");
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
    action.ClickButton(locator.getProperty("Upr.checkbox2"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	//action.Verify_Add_Secondcolumn(Columnvalue);(input.getProperty("Loginname"));
	action.SelectElementByLoginname(input.getProperty("Tenant.Login"));
	Thread.sleep(1000);
    action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifySelectcheckbox(locator.getProperty("Users.SMCheckBox"));
	Thread.sleep(2000);
}

@Test(description="Soft delete the user",priority=5)
public void Deletetenantuser() throws Exception
{
	action.RefreshPage();
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.driver.switchTo().defaultContent();
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	action.Verify_Delete_Fifthcolumn(input.getProperty("Loginname"));
}

/*

@Test(description="Verify the elements of Public contact page",priority=7,enabled=false)
public void verifyElements() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Public contacts
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	//Click on Public Contact Link
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	action.SwithchFrame("iframe0");
	//Verify the View,Edit,Delete Buttons are disabled
	action.VerifyDisableButton(locator.getProperty("public_view"));
	action.VerifyDisableButton(locator.getProperty("EditpvtContact"));
	action.VerifyDisableButton(locator.getProperty("DeletepvtContact"));
	//Verify the New,refresh buttons are enabled
	action.VerifyEnableButton(locator.getProperty("NewContact"));
	action.VerifyEnableButton(locator.getProperty("Public_Refresh"));
}


@Test(priority=8,enabled=false)
public void VerifyErrormsgonpubliccontact() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickButton(locator.getProperty("LogOff"));
	action.WaitForTitle(locator.getProperty("System_Manager"));
	action.VerifyTitle(locator.getProperty("System_Manager"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenantlogin1"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Newpass"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Public_Contacts"));
	action.WaitForTitle(locator.getProperty("Public_Contacts"));
	action.VerifyTitle(locator.getProperty("Public_Contacts"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("NewContact"));
	Thread.sleep(3000);
    action.VerifyStringValue("You don't have sufficient permissions to perform the requested operation. Please contact the administrator in case you need to have these permissions");	
    
}

@Test(description="Verify the Error message on creating new shared Address",priority=9,enabled=false)
public void VerifyErrormessageonsharedAddress() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickButton(locator.getProperty("LogOff"));
	action.WaitForTitle(locator.getProperty("System_Manager"));
	action.VerifyTitle(locator.getProperty("System_Manager"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenant.Login"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Shared_Addresses"));
	action.WaitForTitle(locator.getProperty("Shared_Address"));
	action.VerifyTitle(locator.getProperty("Shared_Address"));
	action.SwithchFrame("iframe0");
    action.ClickButton(locator.getProperty("NewContAdd"));
    action.WaitForTitle(locator.getProperty("Add_Address"));
	action.VerifyTitle(locator.getProperty("Add_Address"));
	//Fill the required details
	action.entertext(locator.getProperty("Addressname"), input.getProperty("Addname1"));
	action.SelectFromdropDown(locator.getProperty("AddressType"), "Home");
	
	//Click on Commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Add_Address"));
	action.VerifyTitle(locator.getProperty("Add_Address"));
	action.VerifyStringValue("Authorization error");
    
}

public void Verifydropdownvalue(String locator, String Expected) throws InterruptedException {
	boolean match=false;
	 WebElement dropdown = action.driver.findElement(By.xpath(locator));  
	 Select select = new Select(dropdown);  

	 List<WebElement> options = select.getOptions();  
	 for(int i = 0; i < options.size(); i++)
	 {
		 String actual=options.get(i).getText();
		 if(actual.equals(Expected)){
			 Assert.assertEquals(actual, Expected);
	    match=true;
	    break;
		 }
	 }
	 Assert.assertTrue(match);


}
*/
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);

}


}
