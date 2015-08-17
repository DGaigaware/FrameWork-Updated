package com.avaya.smgr.gls.roles;
/*
 * Test Case related to Roles UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Createusers{
	boolean b=true,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	private static final String IDEN_Roles_Grid = "//*[@class='jstree-closed']";
	private static final String IDEN_Roles_Sysadmin = ".//*[@id='node_System_white-space_Administrator']/ins";

@BeforeClass(alwaysRun=true)
public void BeforeClasssetup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	
 }
@BeforeMethod(alwaysRun=true)
	public void setup() throws IOException, InterruptedException{
		
   }

@Test(description="Verify that user is created by using Role successfully",priority=2,groups={"Sanity"})
public void GLSBUserCreate() throws Exception{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	String RoleName=input.getProperty("UpdatedName");
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Firstname=action.driver.findElement(By.xpath(locator.getProperty("Firstname")));
	Firstname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
	Thread.sleep(1000);	
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("Role.user"),Keys.TAB);
	Thread.sleep(2000);
	action.entertext(locator.getProperty("Users.Password"), input.getProperty("Role.user"));
	action.entertext(locator.getProperty("Users.CnfPassword"), input.getProperty("Role.user"));
	action.ClickLinkByxpath(locator.getProperty("Membership"));
	action.WaitForObj(locator.getProperty("User.assignRole"));
	action.ClickButton(locator.getProperty("User.assignRole"));
	action.WaitForTitle(locator.getProperty("Assign_Roles"));
	action.ClickButton(locator.getProperty("Users.Name"));
	Thread.sleep(2000);
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='tblAdditionalRoles_core_table_content']/tbody/tr"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='tblAdditionalRoles_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
    	if(sValue.contains(RoleName)){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='tblAdditionalRoles:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	//Click on the Select button
	action.ClickButton(locator.getProperty("Users.AssingRole.Select"));
	action.WaitForObj(locator.getProperty("Users.Commit"));
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
}
@Test(description="Verification login succcessfully when login with user Credentials",priority=3,groups={"Sanity"})
public void GLSCRolesUser() throws Exception{
	action.RefreshPage();
	action.ClickButton(locator.getProperty("LogOff"));
	String EmailId=input.getProperty("Loginname");
	//Navigate to SMGR,Groups & Roles,Roles
	action.entertext(locator.getProperty("UserId"), input.getProperty("Role.user"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Role.user"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.WaitForObj(locator.getProperty("UserId"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("Password.change"));
	action.WaitForObj(locator.getProperty("LoginUser"));
	 String Username=action.driver.findElement(By.xpath(locator.getProperty("LoginUser"))).getText();
	 b=Username.contains(input.getProperty("Role.user"));
	 Assert.assertTrue(b);

}

@Test(description="Verify that enable links are showing for created user",priority=3,groups={"Sanity"})
public void GLSECreateUser() throws Exception{
	action.RefreshPage();
	action.ClickButton(locator.getProperty("LogOff"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Role.user"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.WaitForObj(locator.getProperty("LoginUser"));
		//Navigate to SMGR,User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	String RoleName=input.getProperty("UpdatedName");
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	//Enter the last name,First name,Login name
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Firstname=action.driver.findElement(By.xpath(locator.getProperty("Firstname")));
	Firstname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
	Thread.sleep(1000);	
	WebElement Loginname=action.driver.findElement(By.xpath(locator.getProperty("Loginname")));
	Loginname.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(2000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("Role.Createuser"),Keys.TAB);
	Thread.sleep(2000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
	action.Verify_Add_Fifthcolumn(input.getProperty("Role.Createuser"));
	Thread.sleep(500);
	
}

@Test(description="Verify User edited successfully",priority=5)
public void Edit_User() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickButton(locator.getProperty("LogOff"));
	action.entertext(locator.getProperty("UserId"), input.getProperty("Role.user"));
	action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("LogOn"));
	action.WaitForObj(locator.getProperty("LoginUser"));
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select User from table
	action.SelectElementByLoginname(input.getProperty("Role.Createuser"));
	Thread.sleep(1000);

	WebDriverWait wait = new WebDriverWait(action.driver, 80);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.View"))));
	//action.DoubleClickButton(locator.getProperty("Users.Edit"));
	WebElement element=action.driver.findElement(By.xpath(locator.getProperty("Users.View")));
	element.sendKeys(org.openqa.selenium.Keys.CONTROL);
	action.DoubleClickButton(locator.getProperty("Users.View"));
	action.DoubleClickButton(locator.getProperty("Edit"));
	//Click on Edit button
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(500);
	//Click on Identity tab
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(500);
	//Edit the language and time
	action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
	Thread.sleep(1000);
	action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
	//Click on the Commit Button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Profile_View"));
	//Click on Identity tab
	action.ClickLink(locator.getProperty("Identity_Tab"));
	Thread.sleep(1000);
	//verify the edited TimeZone and language 
	String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str1,input.getProperty("Danish"));
	Thread.sleep(1000);
	String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
	Assert.assertEquals(input.getProperty("Danishtime"), str2);
}

@Test(description="Verify Duplicate user creation",priority=6)
	public void Duplicate_User() throws Exception
	{
		action.driver.navigate().refresh();
		action.driver.navigate().refresh();
		action.ClickButton(locator.getProperty("LogOff"));
		action.entertext(locator.getProperty("UserId"), input.getProperty("Role.user"));
		action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForObj(locator.getProperty("LoginUser"));
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("Role.Createuser"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Duplicate"))));
		//Click on Duplicate button
		action.DoubleClickButton(locator.getProperty("Users.Duplicate"));
		action.WaitForTitle(locator.getProperty("User_Profile_Duplicate"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LangDropdown"))));
		//Verify the timezone and language are set
		String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(str1,input.getProperty("Danish"));
		Thread.sleep(1000);
		String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(input.getProperty("Danishtime"), str2);		
		
	}

@Test(description="Verify Delete user from table",priority=7)
	public void Delete_User() throws Exception
	{
		action.driver.navigate().refresh();
		action.ClickButton(locator.getProperty("LogOff"));
		action.entertext(locator.getProperty("UserId"), input.getProperty("Role.user"));
		action.entertext(locator.getProperty("Password"), input.getProperty("Password"));
		action.ClickButton(locator.getProperty("LogOn"));
		action.WaitForObj(locator.getProperty("LoginUser"));
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User From table
		action.SelectElementByLoginname(input.getProperty("Role.Createuser"));
		Thread.sleep(500);
		//Click on delete Button
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Delete"))));
		action.ClickButton(locator.getProperty("Users.Delete"));
		action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
		//Click on Delete Button
		action.ClickButton(locator.getProperty("Delete_Cnf"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		//Click User Management link
		action.RefreshPage();
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Verify User get deleted from table
		action.Verify_Delete_Fifthcolumn(input.getProperty("Role.Createuser"));
		Thread.sleep(500);
	}

	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	}
	@AfterClass(alwaysRun=true)
	public void close() throws IOException, InterruptedException{
	  
		action.logout(action);

	}

}

