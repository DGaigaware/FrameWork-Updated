package com.avaya.smgr.workassgnment.assmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import com.avaya.smgr.Worksetup.*;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Resource {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	WorkSetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
   	setup=new WorkSetup();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the page elements of Assignment management page")
public void Verify_Page() throws Exception
{
	action.RefreshPage();
	action.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Assign.resourcechk"))));
	//verify resource button selected
	Thread.sleep(1000);
	action.VerifySelectcheckbox(locator.getProperty("Assign.resourcechk"));
	Thread.sleep(2000);
	//Verify remaining buttons are disabled
	action.VerifydeSelectcheckbox(locator.getProperty("Assign.resourceAccount"));
	action.VerifydeSelectcheckbox(locator.getProperty("Assign.resourceSource"));
	action.VerifydeSelectcheckbox(locator.getProperty("Assign.resourceoverall"));	
	//action.VerifyDisableButton(locator.getProperty("RNext"));
	Thread.sleep(1000);
}

@Test(description="Verify the creation of user with work assignment profile",priority=1)
public void New_User_with_WAS() throws Exception
{
	
action.driver.navigate().refresh();
action.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Click on User Management,Manage Users
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
action.VerifyTitle(locator.getProperty("User_Management"));
action.ClickLink(locator.getProperty("Manage_Users"));
action.SwithchFrame("iframe0");
//Click on New Button
action.DoubleClickButton(locator.getProperty("Users.New"));
action.WaitForTitle(locator.getProperty("New_User_Profile"));
//Enter the last name,First name,Login name
action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
Thread.sleep(500);
WebDriverWait wait=new WebDriverWait(action.driver,60);
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
Thread.sleep(500);
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
Thread.sleep(1000);
action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginnameWS"),Keys.TAB);
Thread.sleep(1000);
//Click on Communication profile link
action.DoubleClickButton(locator.getProperty("Tent.AdminLink"));
Thread.sleep(1000);
//Select the work assignment
action.SelectCheckBox(locator.getProperty("Upr.cmcheckbox3"));
wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("Upr.cmcheckbox3"))));
Thread.sleep(1000);
//Create Communication address
action.DoubleClickButton(locator.getProperty("NewCP"));
Thread.sleep(1000);
//Select work assignment from list
action.SelectFromdropDown(locator.getProperty("CPTypelist"), "Work Assignment");
Thread.sleep(2000);
//enter handle
action.entertext(locator.getProperty("CPHandle"), "abc");
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("CPHandle")),"abc"));
Thread.sleep(1000);
//Enter domain
action.entertext(locator.getProperty("CP_Domainname"),"avaya.com");
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("CP_Domainname")),"avaya.com"));
//Click on Add button
action.DoubleClickButton(locator.getProperty("CPAdd"));
Thread.sleep(1000);
//Select Resource Account
action.SelectCheckBox(locator.getProperty("EWAchk1"));
Thread.sleep(2000);
//Enter source in textbox
action.entertext(locator.getProperty("EWASource"), input.getProperty("UsersLoginnameWS"));
Thread.sleep(2000);
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("EWASource")),input.getProperty("UsersLoginnameWS")));
//Click on Commit button
action.DoubleClickButton(locator.getProperty("Users.Commit"));
action.WaitForTitle(locator.getProperty("User_Management"));
Thread.sleep(1000);
action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginnameWS"));
Thread.sleep(1000);
}


@Test(description="Verify the creation of Catagory and attribute",priority=2)
public void Add_Category_Atrribute() throws Exception
{
action.RefreshPage();
action.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Navigate to work assignment
action.ClickLink(locator.getProperty("Work_Assignment"));
action.WaitForTitle(locator.getProperty("Work_Assignment"));
action.ClickLink(locator.getProperty("Attribute_Management"));
//verify page title
action.WaitForTitle(locator.getProperty("Attribute_Management"));
action.VerifyTitle(locator.getProperty("Attribute_Management"));
action.SwithchFrame("iframe0");
WebDriverWait wait = new WebDriverWait(action.driver, 60);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Attribute.CatAdd"))));
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.CatAdd"));
action.entertext(locator.getProperty("Attribute.Newcat"), "Sport");
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.Cataddbtn"));
Thread.sleep(1000);
//verify message
action.VerifyStringValue("Add Category Sport successful!");
//Enter Category name
setup.VerifyAddCatagory(action, "Sport");
action.DeselectCheckBox(locator.getProperty("Attribute.CatChk1"));
Thread.sleep(1000);
action.VerifyDisableButton(locator.getProperty("Attribute.AttrAdd"));
Thread.sleep(1000);
action.SelectCheckBox(locator.getProperty("Attribute.CatChk")+"Sport']");
Thread.sleep(1000);
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.AttrAdd"));
//Enter attribute name
action.entertext(locator.getProperty("Attribute.NewAttr"), "Cricket");
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.Attraddbtn"));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Attribute.AttrAdd"))));
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.AttrAdd"));
//Enter attribute name
action.entertext(locator.getProperty("Attribute.NewAttr"), "Football");
//Click on Add button
action.ClickButton(locator.getProperty("Attribute.Attraddbtn"));
//Verify added attribute in table
Thread.sleep(1000);
setup.VerifyAddAttribute(action, "Cricket");
Thread.sleep(1000);
setup.VerifyAddAttribute(action, "Football");
}


@Test(description="Verify the Elements of resource details page",priority=3)
public void Verify_Resource_Deatils_Page() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.VerifySelectcheckbox(locator.getProperty("Assign.resourcechk"));
	action.SelectCheckBox(locator.getProperty("Resourcechk1"));
	action.VerifyEnableButton(locator.getProperty("RNext"));
	action.ClickButton(locator.getProperty("RNext"));
	//Verify title of page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	action.VerifyTitle(locator.getProperty("Assignment_Resource_Details"));
	//Verify buttons are enabled
	action.VerifyEnableButton(locator.getProperty("AttrCommitcontbtn"));
	action.VerifyEnableButton(locator.getProperty("AttrCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("Attrcancelbtn"));
	//Verify Three tabs
	action.VerifyElementValue(locator.getProperty("Attributetab"), "Attributes");
	action.VerifyElementValue(locator.getProperty("Propertytab"), "Properties");
	action.VerifyElementValue(locator.getProperty("Strategytab"), "Strategies");
	
}


@Test(description="Verify the Commit button of Resource detail page ",priority=4)
public void Verify_AttrCommit_Button() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Resourcechk1"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	//Verify title of page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Attributetree"))));
	action.ClickButton(locator.getProperty("Attributetree"));
	//Select Attribute
	WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("Sportchk1")));
	ele.click();
	//action.SelectCheckBox(locator.getProperty("Sportchk1"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Selectedattrlist"), "Sport-->Cricket,Football");
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("AttrCommitbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("RNext"))));
	//Click on Next Button
	action.DoubleClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	Thread.sleep(1000);
	//Verify attributes gets committed to resource
	action.VerifyElementValue(locator.getProperty("Selectedattrlist"), "Sport-->Cricket,Football");
}


@Test(description="Verify the Commit&Continue and Cancel buttons of Resource detail page ",priority=5)
public void Verify_AttrCommitContinue_Cancel_Button() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Resourcechk1"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	//Verify title of page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));

	//Select Attribute
	WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("Crickchk")));
	ele.click();
	//action.DeselectCheckBox(locator.getProperty("Crickchk"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Selectedattrlist"), "Sport-->Football");
	action.WaitToClick(locator.getProperty("AttrCommitcontbtn"));
	action.ClickButton(locator.getProperty("AttrCommitcontbtn"));
	Thread.sleep(1000);
	//Stay on same page
	action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Assignment_Resource_Details"));
	//Verify attributes gets committed to resource
	action.VerifyElementValue(locator.getProperty("Selectedattrlist"), "Sport-->Football");
	//Verify Cancel button
	action.ClickButton(locator.getProperty("Attrcancelbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
}


@Test(description="Verify the Property tab of Resource deatils page",priority=6)
public void Verify_Property_Tab() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Resourcechk1"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	//Verify title of page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	Thread.sleep(1000);
	//Click on properties tab
	action.ClickLinkByxpath(locator.getProperty("Propertytab"));
	//Verify buttons
	action.VerifyEnableButton(locator.getProperty("ViewChangebtn"));
	action.VerifyEnableButton(locator.getProperty("PropCommitconbtn"));
	action.VerifyEnableButton(locator.getProperty("PropCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("PropCancelbtn"));
	//Change default value of property
	action.SelectCheckBox(locator.getProperty("Propertychk1"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("Propertychk1"))));
	action.ClearText(locator.getProperty("Defaultvaluetext"));
	action.entertext(locator.getProperty("Defaultvaluetext"), "5");
	action.ClickButton(locator.getProperty("PropCommitconbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Assignment_Resource_Details"));
	
	//Verify Commit button
	Thread.sleep(1000);
	action.ClearText(locator.getProperty("Defaultvaluetext"));
	action.entertext(locator.getProperty("Defaultvaluetext"), "4");
	action.ClickButton(locator.getProperty("PropCommitbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Click on Next Button
	action.DoubleClickButton(locator.getProperty("RNext"));
	Thread.sleep(1000);
	//Verify attributes gets committed to resource
	action.ClickLinkByxpath(locator.getProperty("Propertytab"));
	Thread.sleep(1000);
	String str=action.driver.findElement(By.xpath(locator.getProperty("Defaultvaluetext"))).getAttribute("value");
	Assert.assertEquals("4", str);	
	action.VerifySelectcheckbox(locator.getProperty("Propertychk1"));
	Thread.sleep(1000);
	//verify Cancel button
	action.ClickButton(locator.getProperty("PropCancelbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
}


@Test(description="Verify the Strategy tab of Resource deatils page",priority=7)
public void Verify_Strategy_Tab() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Resourcechk1"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	//Verify title of page
	action.WaitForTitle(locator.getProperty("Assignment_Resource_Details"));
	Thread.sleep(1000);
	//Click on properties tab
	action.ClickLinkByxpath(locator.getProperty("Strategytab"));
	Thread.sleep(1000);
	//Verify buttons
	action.VerifyEnableButton(locator.getProperty("StratCommitconbtn"));
	action.VerifyEnableButton(locator.getProperty("StratCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("StratCancelbtn"));
	//Verify Commit and Continue button
	Thread.sleep(1000);
	action.SelectRadioButton(locator.getProperty("Strategychk1"));
	action.ClickButton(locator.getProperty("StratCommitconbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Assignment_Resource_Details"));
	//Verify the Commit button
	action.SelectRadioButton(locator.getProperty("Strategychk2"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("StratCommitbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Click on Next Button
	action.DoubleClickButton(locator.getProperty("RNext"));
	Thread.sleep(1000);
	//Verify attributes gets committed to resource
	action.ClickLinkByxpath(locator.getProperty("Strategytab"));
	Thread.sleep(1000);
	action.VerifySelectcheckbox(locator.getProperty("Strategychk2"));
	//Verify Cancel button
	action.ClickButton(locator.getProperty("StratCancelbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
}



}
