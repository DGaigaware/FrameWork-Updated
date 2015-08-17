package com.avaya.smgr.Tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class TenantLogin {
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

@Test(priority=1)
public void Loginwithtenantadmin() throws Exception
{
	//Click on Logoff button
	action.ClickButton(locator.getProperty("LogOff"));
	action.WaitForTitle(locator.getProperty("System_Manager"));
	action.VerifyTitle(locator.getProperty("System_Manager"));
	//Fill the Userid and password of tenant admin
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenantlogin1"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Tenantpass"));
	//Click on log On button
	action.ClickButton(locator.getProperty("LogOn"));
	Thread.sleep(2000);
	//Set the New password
	action.entertext(locator.getProperty("UserId"), input.getProperty("Newpass"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Newpass"));
	//Click on Change button
	action.ClickButton(locator.getProperty("Password.change"));
	action.WaitForTitle(locator.getProperty("Dashboard"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	
}

@Test(priority=2)
public void Verifypageelements() throws Exception
{
	action.driver.navigate().refresh();
	//Click on LogOff button
	action.ClickButton(locator.getProperty("LogOff"));
	action.WaitForTitle(locator.getProperty("System_Manager"));
	action.VerifyTitle(locator.getProperty("System_Manager"));
	//Pass the UserId and password
	action.entertext(locator.getProperty("UserId"), input.getProperty("Tenantlogin1"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Newpass"));
	//Click on logOn button
	action.ClickButton(locator.getProperty("LogOn"));
	action.VerifyTitle(locator.getProperty("Dashboard"));
	//Verify the page elements
	action.VerifyElementDisplay(locator.getProperty("Tblock"));
	action.VerifyElementDisplay(locator.getProperty("TSiteblock"));
	action.VerifyElementDisplay(locator.getProperty("TDeptblock"));
	action.VerifyElementDisplay(locator.getProperty("TTeamblock"));
	action.VerifyElementDisplay(locator.getProperty("UserDistribution"));
	action.VerifyElementDisplay(locator.getProperty("TUserLink"));
	action.VerifySelectcheckbox(locator.getProperty("Tenantnamechk1"));
	action.VerifydeSelectcheckbox(locator.getProperty("Tenantsitechk1"));
	action.VerifydeSelectcheckbox(locator.getProperty("Tenantsitechk2"));
	action.VerifydeSelectcheckbox(locator.getProperty("Tenantdeptchk1"));
	action.VerifydeSelectcheckbox(locator.getProperty("Tenantteamchk1"));
	action.VerifyEnableButton(locator.getProperty("Graphbtn"));
	action.SelectCheckBox(locator.getProperty("Tenantnamechk1"));
	Alert alert =action.driver.switchTo().alert();
    alert.accept();
    	
}

@Test(priority=3)
public void VerifyLevel1and2() throws Exception
{
	action.driver.navigate().refresh();
	//Verify the department and team displayed
	action.VerifyElementDisplay(locator.getProperty("Tenantdeptchk1"));
	action.VerifyElementDisplay(locator.getProperty("Tenantteamchk1"));
	//Select Site1
	action.SelectCheckBox(locator.getProperty("Tenantsitechk1"));
	Thread.sleep(1000);
	//Verify the department and team are not displayed
	action.VerifyNoElementdisplay(locator.getProperty("Tenantdeptchk1"));
	action.VerifyNoElementdisplay(locator.getProperty("Tenantteamchk1"));
	//Select the site2 
	action.SelectCheckBox(locator.getProperty("Tenantsitechk2"));
	Thread.sleep(1000);
	//Verify the department and team displayed
	action.VerifyElementDisplay(locator.getProperty("Tenantdeptchk1"));
	action.VerifyElementDisplay(locator.getProperty("Tenantteamchk1"));
	
}


@Test(priority=4) 
public void UserManagemntlink() throws Exception
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
	action.SwithchFrame("iframe0");
	Thread.sleep(2000);
	action.VerifyEnableButton(locator.getProperty("Users.Refresh"));
	action.VerifyEnableButton(locator.getProperty("Users.New"));
	action.SelectCheckBox(locator.getProperty("Tenantchk11"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Upr.refresh"));
	Thread.sleep(1000);
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("Tenantlogin1"));
}


@Test(priority=5)
public void NewTenantuser() throws Exception
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
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	//Select the Site from list
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"), input.getProperty("site1"));
	Thread.sleep(2000);
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
	Thread.sleep(3000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname7"),Keys.TAB);
	Thread.sleep(2000);
	//Click on the Commit Button
	action.ClickButton(locator.getProperty("Commit"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	//Click on Commit button
	action.ClickButton(locator.getProperty("Users.Commit"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
}

@Test(priority=6)
public void Deletetenantuser() throws Exception
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
	action.SwithchFrame("iframe0");
	action.SelectElementByLoginname(input.getProperty("UsersLoginname7"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	action.VerifyTitle(locator.getProperty("User_Delete_Confirmation"));
	action.ClickButton(locator.getProperty("Users.Deletecnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
}



@Test(description="Verify the elements of Public contact page",priority=7)
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


@Test(priority=8)
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

@Test(description="Verify the Error message on creating new shared Address",priority=9)
public void VerifyErrormessageonsharedAddress() throws Exception
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
