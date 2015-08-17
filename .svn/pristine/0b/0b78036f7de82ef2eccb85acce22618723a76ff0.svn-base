package com.avaya.smgr.upm.advancesearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import utility.UserAction;


public class Searchuser {
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

@Test(description="Verify the Creation of new user",priority=1)
public void Create_User() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	//Fill the required details 
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("SearchLastname"),Keys.TAB);
	Thread.sleep(500);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("SearchLastname")));
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("SearchFirstname"),Keys.TAB);
	Thread.sleep(500);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")), input.getProperty("SearchFirstname")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
	Thread.sleep(500);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("SearchLoginname"),Keys.TAB);
	Thread.sleep(500);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.New"))));
	//Verify that user is added to the table
	action.Verify_Add_Fifthcolumn(input.getProperty("SearchLoginname"));
}

@Test(description="Verify the searching by dispaly name",priority=2)
public void Search_with_Display_Name_Equals() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Display Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Display_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("Displayname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("Displayname")));
	Thread.sleep(500);
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the display name present in table
	action.Verify_Add_Forthcolumn(input.getProperty("Displayname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify that display name is not displaying when searching by not Equal Operator",priority=2)
public void Search_with_Display_Name_Not_Equals() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Display Name from drop down list
	
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Display_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "1");
	
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("Displayname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("Displayname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Secondrow"))));
	Thread.sleep(500);
	action.Verify_Delete_Fifthcolumn(input.getProperty("Displayname"));
	
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify that display name is displaying when searching by starts with and display name",priority=2)
public void Search_with_Display_Name_Starts_With() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select from list
	
	//Select Display Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Display_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "2");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "ab");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "ab"));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the display name present in table
	action.Verify_Add_Forthcolumn(input.getProperty("Displayname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the searching by dispaly name",priority=2)
public void Search_with_Display_Name_Ends_With() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	
	//Select Display Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Display_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "3");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "cd");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "cd"));
	
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the display name present in table
	action.Verify_Add_Forthcolumn(input.getProperty("Displayname"));
	//click on Reset button
	action.DoubleClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}


@Test(description="Verify the searching by last name",priority=3)
public void Search_with_Last_Name_Equals() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Last Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Last_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLastname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLastname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the Last name present in table
	action.Verify_Add_Secondcolumn(input.getProperty("SearchLastname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify that Correct User Displayed in table after searching user by Last name and not Equals combination",priority=3)
public void Search_with_Last_Name_Not_Equals() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Last Name from drop down list
	
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Last_Name"));
	Thread.sleep(1000);
    action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "1");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLastname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLastname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	//Verify the Last name present in table
	action.Verify_Delete_Secondcolumn(input.getProperty("SearchLastname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify that Correct User Displayed in table after searching user by last name and Starts with combination",priority=3)
public void Search_with_Last_Name_Start_with() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Last Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Last_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "2");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "ab");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "ab"));
	//Click on search button//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the Last name present in table
	action.Verify_Add_Secondcolumn(input.getProperty("SearchLastname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify that Correct User Displayed in table after searching user by last name and Ends  with combination",priority=3)
public void Search_with_Last_Name_Ends_With() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Last Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Last_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "3");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "bc");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "bc"));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the Last name present in table
	action.Verify_Add_Secondcolumn(input.getProperty("SearchLastname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}




@Test(description="Verify that Correct User Displayed in table after searching user by first name and Equals combination",priority=4)
public void Search_with_First_Name() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select First Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("First_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchFirstname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchFirstname")));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the First name present in table
	action.Verify_Add_Thirdcolumn(input.getProperty("SearchFirstname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}


@Test(description="Verify that  User is not Displaying in table after searching user by first name and not Equals combination",priority=4)
public void Search_with_First_Name_Not_Equals() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select First Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("First_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "1");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchFirstname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchFirstname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	//Verify the First name present in table
	action.Verify_Delete_Thirdcolumn(input.getProperty("SearchFirstname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}
@Test(description="Verify that Correct User Displayed in table after searching user by First name and Starts with combination",priority=4)
public void Search_with_First_Name_Starts_With() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select First Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("First_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "2");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "aac");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "aac"));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the First name present in table
	action.Verify_Add_Thirdcolumn(input.getProperty("SearchFirstname"));
	//click on Reset button
	action.DoubleClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}
@Test(description="Verify that Correct User Displayed in table after searching user by first  name and Ends  with combination",priority=4)
public void Search_with_First_Name_Ends_With() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select First Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("First_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "3");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), "cd");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "cd"));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Thread.sleep(1000);
	//Verify the First name present in table
	action.Verify_Add_Thirdcolumn(input.getProperty("SearchFirstname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}


@Test(description="Verify the searching by login name",priority=5)
public void Search_with_Login_Name_Equal() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"), input.getProperty("SearchLoginname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	//Verify the Login name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the searching by login name",priority=5)
public void Search_with_Login_Name_NotEqual() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "1");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"),input.getProperty("SearchLoginname"));
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), input.getProperty("SearchLoginname")));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	//Verify the Login name present in table
	action.Verify_Delete_Fifthcolumn(input.getProperty("SearchLoginname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the searching by login name",priority=5)
public void Search_with_Login_Name_StartsWith() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "2");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"),"aace");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "aace"));
	//Click on search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	//Verify the Login name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}

@Test(description="Verify the searching by login name",priority=5)
public void Search_with_Login_Name_EndsWith() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(1000);
	action.SelectFromdropDownbyValue(locator.getProperty("Operatorlist"), "3");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Seachvalue"),"ac.com");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "ac.com"));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	//Verify the Login name present in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("SearchLoginname"));
	//click on Reset button
	action.ClickButton(locator.getProperty("Resettable"));
	//Click on Close button
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Closebtn"));
}



@Test(description="Verify the searching by Startwith Character",priority=6) 
public void Verify_Startwith_Search() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(500);
	//Select Starts With from drop down list
	action.SelectFromdropDown(locator.getProperty("Operatorlist"), "Starts With");
	Thread.sleep(500);
	//Enter search value as aaa
	String str2=input.getProperty("SearchLoginname");
	String str3=str2.substring(0, 3);
	action.entertext(locator.getProperty("Seachvalue"), str3);
	//Click on Search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	//Select ALL From drop down list
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Pagesizelist"))));
	action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
	//Thread.sleep(1000);
	//Calculate the number of rows
	List<WebElement> rows =action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	boolean b=false;
	for(int i=2;i<=numberofrows+1;i++)
	{
		//Verify the login name starts with 'u'
		String str1=action.driver.findElement(By.xpath(locator.getProperty("Table.Row")+"["+i+"]/td[5]")).getText();
		b=str1.startsWith(str3);
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
    //Click on close button
    Thread.sleep(1000);
    action.DoubleClickButton(locator.getProperty("Closebtn"));
    
}


@Test(description="Verify the searching by Ends with Character",priority=7) 
public void Verify_Endswith_Search() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(500);
	//Select Ends With from drop down list
	action.SelectFromdropDown(locator.getProperty("Operatorlist"), "Ends With");
	Thread.sleep(500);
	
	action.entertext(locator.getProperty("Seachvalue"), "ac.com");
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Seachvalue")), "ac.com"));
	//Click on search button
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Pagesizelist"))));
	//Select ALL From drop down list
	action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
	Thread.sleep(1000);
	//Calculate the number of rows
	List<WebElement> rows =action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	boolean b=false;
	for(int i=2;i<=numberofrows+1;i++)
	{
		//Verify the login name Ends With 's'
		String str1=action.driver.findElement(By.xpath(locator.getProperty("Table.Row")+"["+i+"]/td[5]")).getText();
		b=str1.endsWith("ac.com");
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
  //Click on close button
    Thread.sleep(1000);
    action.DoubleClickButton(locator.getProperty("Closebtn"));
    
}

@Test(description="Verify the searching by Conatins ",priority=8)
public void Verify_Contains_Search() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//click on Advance Search Link
	action.ClickLink(locator.getProperty("Advanced_Search"));
	Thread.sleep(500);
	//Select Login Name from Drop down list
	action.SelectFromdropDown(locator.getProperty("Columnnamelist"), locator.getProperty("Login_Name"));
	Thread.sleep(500);
	//Select Ends With from drop down list
	action.SelectFromdropDown(locator.getProperty("Operatorlist"), "Contains");
	Thread.sleep(500);
	//Enter search value as 'use'
	action.entertext(locator.getProperty("Seachvalue"), "ac");
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("AdvanceSearch"));
	Thread.sleep(500);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Pagesizelist"))));
	//Select ALL From drop down list
	action.SelectFromdropDown(locator.getProperty("Pagesizelist"), locator.getProperty("SelectAll"));
	Thread.sleep(1000);
	//Calculate the number of rows
	List<WebElement> rows =action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	boolean b=false;
	for(int i=2;i<=numberofrows+1;i++)
	{
		//Verify the login name contains With 'ac'
		
		String str1=action.driver.findElement(By.xpath(locator.getProperty("Table.Row")+"["+i+"]/td[5]")).getText();
		b=str1.contains("ac");
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
  //Click on close button
    Thread.sleep(1000);
    action.DoubleClickButton(locator.getProperty("Closebtn"));
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
