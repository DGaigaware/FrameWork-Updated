package com.avaya.smgr.upmtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utility.UserAction;

public class BulkEdit {
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
	@Test(description="Import users")
	public void ImportXMLDefaults() throws Exception{
		File imprt_XML = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\BulkEdit.xml");
		ImportFile(imprt_XML,locator.getProperty("Import.skip"));
	}

	@Test(description="Verify the  bulk edit functionality",priority=1)	
	public void Bulk_Edit() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select all users in the UPM table
		action.ClickButton(locator.getProperty("Users.all"));
		Thread.sleep(1000);
		//Click on More button,Bulk Edit Users Link
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));

		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		Thread.sleep(1000);
		//Edit the language and time
		action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
		Thread.sleep(500);
		action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
		Thread.sleep(500);
		//Click on Run now button
		action.ClickButton(locator.getProperty("Users.Runnow"));
		//Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		//Click on Commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}
	

		
	@Test(description="Verify Edited User",priority=2)
	public void Verify_Bulk_Edit() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select Checkbox
		action.SelectCheckBox(locator.getProperty("Checkbox0"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.View"))));
		//Click on view Button
		action.ClickButton(locator.getProperty("Users.View"));
		action.WaitForTitle(locator.getProperty("User_Profile_View"));
		//Click on Identity Tab
		action.ClickLinkByxpath(locator.getProperty("Identity"));
		Thread.sleep(2000);
		//Verify the language and Time
		String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(str1,input.getProperty("Danish"));

		String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
		Assert.assertEquals(input.getProperty("Danishtime"), str2);
	}
		

	@Test(description="Verify the various elements on Schedule Bulk Edit Job page",priority=3)
	public void Verify_Bulk_Editjob_Elements() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
	    Thread.sleep(1000);
		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));

		//Click on Status of bulk edit users
		action.ClickLink(locator.getProperty("Status_of_Bulk_Edit_Users_Jobs"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		//Verify the buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Commit"));
		action.VerifyDisableButton(locator.getProperty("ViewJob"));
		action.VerifyDisableButton(locator.getProperty("CancelJob"));
		action.VerifyDisableButton(locator.getProperty("DeleteJob"));
		//Select the Job
		action.SelectCheckBox(locator.getProperty("BulkEditorJobchk0"));
		Thread.sleep(1000);
		//verify the buttons are enabled
		action.VerifyEnableButton(locator.getProperty("ViewJob"));
		action.VerifyEnableButton(locator.getProperty("DeleteJob"));
		
	}
	@Test(description="Verify the various elements on user bulk edit page",priority=4)	
	public void Verify_Elements() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.VerifyTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			//Select the users from table
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			action.WaitForObj(locator.getProperty("Upr.checkbox1"));
			
			//Verify the elements state
			action.VerifydeSelectcheckbox(locator.getProperty("Upr.checkbox1"));
			action.VerifydeSelectcheckbox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);

	}


		@Test(description="Verify the View Function of Bulk Edit job",priority=5)
	public void View_Bulkedit_Job() throws Exception
	{
		boolean b1,b2;
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		
		//Click on More button
		 Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));
		
		action.ClickLink(locator.getProperty("Status_of_Bulk_Edit_Users_Jobs"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		action.VerifyTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		action.SelectCheckBox(locator.getProperty("BulkEditorJobchk0"));
		Thread.sleep(1000);
		//Click on View button
		action.ClickButton(locator.getProperty("ViewJob"));
		action.WaitForTitle(locator.getProperty("Bulk_Edit_Job_Details"));
		//Verify the buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Refreshbtn1"));
		action.VerifyEnableButton(locator.getProperty("Backbtn1"));
		action.VerifyEnableButton(locator.getProperty("Refreshbtn2"));
		action.VerifyEnableButton(locator.getProperty("Backbtn2"));
		//Verify the records
		String Str1=action.driver.findElement(By.xpath(locator.getProperty("TotalRecords"))).getText();
		String Str2=action.driver.findElement(By.xpath(locator.getProperty("Successrecords"))).getText();
		String Str3=action.driver.findElement(By.xpath(locator.getProperty("FailedRecord"))).getText();
		 b1=Str1.equals(Str2);
		Assert.assertTrue(b1);
		b2=Str3.equals("0");
		Assert.assertTrue(b2);
	}

		
	@Test(description="Verify the Success Records",priority=6)
	public void Verify_Success_Record() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on More button
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));
		action.ClickLink(locator.getProperty("Status_of_Bulk_Edit_Users_Jobs"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		action.SelectCheckBox(locator.getProperty("BulkEditorJobchk0"));
		Thread.sleep(1000);
		//Click on View button
		action.DoubleClickButton(locator.getProperty("ViewJob"));
		action.WaitForTitle(locator.getProperty("Bulk_Edit_Job_Details"));
		//Click on panel down Arrow
		action.driver.findElement(By.cssSelector(".show_panel_arrow")).click();
		
		action.VerifyStringValue("Description");
		//Click on panel Up Arrow
		action.driver.findElement(By.cssSelector(".show_panel_arrow")).click();
		
	}



	@Test(description="Verify the Deletion of Bulk Edit job",priority=7)
	public void Delete_BulkEdit_Jobs() throws Exception
		{	
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.VerifyTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.SwithchFrame("iframe0");
			
			//Click on More button
			action.ClickButton(locator.getProperty("Users.More"));
			action.ClickLink(locator.getProperty("Status_of_Bulk_Edit_Users_Jobs"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			//Select checkbox
			action.SelectCheckBox(locator.getProperty("BulkEditorJobchkAll"));
			Thread.sleep(500);
			//click on delete button
			action.DoubleClickButton(locator.getProperty("DeleteJob"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			//click on commit button
			action.ClickButton(locator.getProperty("Commit"));
			action.WaitForTitle(locator.getProperty("User_Management"));
		}
	

	@Test(description="Verify the Bulk edit job get deleted from table",priority=8)
	public void Verify_Delete_Job() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");

		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));

		action.ClickLink(locator.getProperty("Status_of_Bulk_Edit_Users_Jobs"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		Thread.sleep(1000);
		action.VerifyElementValue(locator.getProperty("Bulkeditjobchkbox"), "No Records found");
		Thread.sleep(1000);
	}


	@Test(description="Verify the cancelation of Bulk edit job",priority=9)
	public void Cancel_Bulk_Edit_Jobs() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select all the users
		action.ClickButton(locator.getProperty("Users.all"));
		Thread.sleep(1000);
		//action.ClickLinkByxpath(locator.getProperty("All"));
		
		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));
		
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		//Select language and time
		action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
		action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
		//click on Run Now button
		//Click on cancel button
	}
 
	
	@Test(description="Verify the Assignment of SM to multiple user throgh bulk edit",priority=10)	
	public void Assign_SM_With_Bulk_Edit() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Select bulk edit user link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			//Select SM check box 
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select SM from SM list
			action.SelectFromdropDown(locator.getProperty("Upr.smlist"), input.getProperty("SM_Pune"));
			Thread.sleep(1000);
			//Select location from list
			action.SelectFromdropDown_Index(locator.getProperty("Upr.smlisthl"), 1);
			Thread.sleep(1000);
			//Click on Identity link
			action.DoubleClickButton(locator.getProperty("Identity"));
			action.WaitForObj(locator.getProperty("Identity"));
			//Select SIP domain from list
			action.SelectFromdropDown(locator.getProperty("UPR.SIP"), input.getProperty("Sipdomain"));
			Thread.sleep(2000);
			//Click on edit link
			action.DoubleClickButton(locator.getProperty("Editcmf"));
			//enter com profile password
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("cnfcomprofpass"))));
			action.entertext(locator.getProperty("Upr.comprofilepass"), "Avaya1123$");
			action.entertext(locator.getProperty("Upr.confirmpass"), "Avaya1123$");
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			Thread.sleep(1000);
			//Click on Refresh button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("TableRefresh"))));
			action.ClickButton(locator.getProperty("TableRefresh"));
			//Click on Commit button
			action.DoubleClickButton(locator.getProperty("Commit"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Select one user
			action.ClickButton(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			//click on Edit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Edit"))));
			action.DoubleClickButton(locator.getProperty("Users.Edit"));
			action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
			action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
			Thread.sleep(1000);
			//Verify SM is assigned to the user
			action.VerifySelectcheckbox(locator.getProperty("Users.SMCheckBox"));
			Thread.sleep(500);
			
	}


	@Test(description="Verify the update of old SM to new SM for multiple users",priority=11)	
	public void Bulk_Edit_SM_Element() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			//Select SM check box
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select SM from list
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.smlist"))));
			Select dropdown = new Select(action.driver.findElement(By.xpath(locator.getProperty("Upr.smlist"))));
			dropdown.selectByValue("0");
			//action.SelectFromdropDown_Index(locator.getProperty("Upr.smlist"),1);
			Thread.sleep(1000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			//Click on Commit button
			action.ClickButton(locator.getProperty("Commit"));
            action.WaitForTitle(locator.getProperty("User_Management"));
          //Select one user
            action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Select one user
			action.ClickButton(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			//Click on edit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Edit"))));
			action.DoubleClickButton(locator.getProperty("Users.Edit"));
			action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
			action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
			Thread.sleep(1000);
			//verify  SM check box selected
			action.VerifySelectcheckbox(locator.getProperty("Users.SMCheckBox"));
			Thread.sleep(1000);
			//Verify the new SM assigned to the user
			//action.VerifyElementValue(locator.getProperty("Users.smlist"), input.getProperty("sm123"));
			String str1=action.driver.findElement(By.xpath(locator.getProperty("Users.smlist"))).getAttribute("value");
			Assert.assertEquals(str1,input.getProperty("sm4"));
			
	}

	
	@Test(description="Verify the Assignment of CM to multiple user throgh bulk edit",priority=12)	
	public void Assign_CM_With_Bulk_Edit() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);

			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on Bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select the CM checkbox
			action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);
			//Select new profile checkbox
			action.SelectCheckBox(locator.getProperty("Newprofilecmchk"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.cmlist"))));
			//Select CM from list
			action.SelectFromdropDown(locator.getProperty("Upr.cmlist"), input.getProperty("cm29"));
			Thread.sleep(4000);
			//Select Template from list
			action.SelectFromdropDown(locator.getProperty("Upr.templatelist"), input.getProperty("Template"));
			Thread.sleep(2000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			//Click on Commit button
			action.ClickButton(locator.getProperty("Commit"));			
			//Select one user
            action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Select one user
			action.ClickButton(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			//Click on edit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Edit"))));
			action.DoubleClickButton(locator.getProperty("Users.Edit"));
			action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
			action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
			Thread.sleep(1000);
			//verify  SM check box selected
			action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
			Thread.sleep(1000);
			
	
	}

	@Test(description="Verify the Change of old template to new template for multiple users",priority=13)	
	public void Bulk_Edit_CM_Element() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select Check box
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on Bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select the CM checkbox
			action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);
			//Select template from list
			Select dropdown = new Select(action.driver.findElement(By.xpath(locator.getProperty("Upr.templatelist"))));
			dropdown.selectByValue("1");
			//action.SelectFromdropDown_Index(locator.getProperty("Upr.templatelist"), 3);
			Thread.sleep(5000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			Thread.sleep(2000);
			//Click on refresh button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("TableRefresh"))));
			action.ClickButton(locator.getProperty("TableRefresh"));
			//Click on Commit button
			action.DoubleClickButton(locator.getProperty("Commit"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			//Select one user
            action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Select one user
			action.ClickButton(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			//Click on Edit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Edit"))));
			action.DoubleClickButton(locator.getProperty("Users.Edit"));
			action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
			action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
			Thread.sleep(1000);
			//Verify CM checkbox selected
			action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
			Thread.sleep(100);
			//Verify new template assigned to user
			String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("Upr.templatelist")))).getFirstSelectedOption().getText();
			Assert.assertEquals(str, input.getProperty("Template"));
	}
	
	
	@Test(description="Verify the scheduling of bulk edit job successfully.",priority=14)	
	public void Schedule_Bulk_Edit_Job() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select Check box
		action.ClickButton(locator.getProperty("Users.all"));
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));
        //Click on bulk edit users link
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		Thread.sleep(1000);
		//Edit the language and time
		action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
		action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
		//Click on Schedule button
		action.DoubleClickButton(locator.getProperty("Users.Schedule"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		//Set the time 
		String min=action.driver.findElement(By.xpath(locator.getProperty("Bulk.minute"))).getAttribute("value");
		int minute=Integer.parseInt(min);
		minute+=1;
		String str=String.valueOf(minute);
		action.ClearText(locator.getProperty("Bulk.minute"));
		action.entertext(locator.getProperty("Bulk.minute"), str);
		Thread.sleep(5000);
		//Click on Schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		Thread.sleep(1000);
		//Verify the status of bulk edit job
		String state=action.driver.findElement(By.xpath(locator.getProperty("Bulkeditstatus"))).getText();
		Assert.assertEquals(state, "PENDINGEXECUTION");
		while(action.driver.findElement(By.xpath(locator.getProperty("Bulkeditstatus"))).getText().equals("RUNNING")||action.driver.findElement(By.xpath(locator.getProperty("Bulkeditstatus"))).getText().equals("PENDINGEXECUTION"))
		{
			action.ClickButton(locator.getProperty("TableRefresh"));
			Thread.sleep(2000);
		}
		//verify status is completed
		action.VerifyElementValue(locator.getProperty("Bulkeditstatus"), "COMPLETED");
		//Click on Commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}

	
	@Test(description="Verify CM and SM is unassigned  successfully",priority=15)
	public void Unassign_CMSM() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("Checkbox0"));
        Thread.sleep(1000);
		action.WaitForObj(locator.getProperty("Users.Edit"));
		action.ClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		action.DeselectCheckBox(locator.getProperty("Upr.smcheckbox1"));
		Thread.sleep(500);
		action.DeselectCheckBox(locator.getProperty("Upr.cmcheckbox2"));
		Thread.sleep(1000);
		//Click on Commit Button and verify title
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.VerifyTitle(locator.getProperty("User_Management"));
        }
		
	
	
	@Test(description="Verify the Assignment of CM to multiple user throgh bulk edit",priority=16)	
	public void Assign_SM_CM_With_Bulk_Edit() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select Check box
			//Select Check box
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on Bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select SM from SM list
			action.SelectFromdropDown(locator.getProperty("Upr.smlist"), input.getProperty("SM_Pune"));
			Thread.sleep(2000);
			//Select location from list
			action.SelectFromdropDown_Index(locator.getProperty("Upr.smlisthl"), 1);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select the CM checkbox
			action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);
			//Select new profile checkbox
			action.SelectCheckBox(locator.getProperty("Newprofilecmchk"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.cmlist"))));
			//Select CM from list
			action.SelectFromdropDown(locator.getProperty("Upr.cmlist"), input.getProperty("cm29"));
			Thread.sleep(4000);
			//Select Template from list
			action.SelectFromdropDown(locator.getProperty("Upr.templatelist"), input.getProperty("Template"));
			Thread.sleep(2000);
			//Click on Identity link
			action.DoubleClickButton(locator.getProperty("Identity"));
			//Select SIP domain from list
			action.SelectFromdropDown(locator.getProperty("UPR.SIP"), input.getProperty("Sipdomain"));
			Thread.sleep(2000);
			//Click on edit link
			action.DoubleClickButton(locator.getProperty("Editcmf"));
			//enter com profile password
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("cnfcomprofpass"))));
			action.entertext(locator.getProperty("Upr.comprofilepass"), "Avaya1123$");
			action.entertext(locator.getProperty("Upr.confirmpass"), "Avaya1123$");
			Thread.sleep(1000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
			Thread.sleep(1000);
			//Click on refresh button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("TableRefresh"))));
			action.ClickButton(locator.getProperty("TableRefresh"));
			//Click on Commit button
			action.DoubleClickButton(locator.getProperty("Commit"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickButton(locator.getProperty("Users.all"));
			Thread.sleep(1000);
			//Select user
			action.ClickButton(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			//Click on Edit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Edit"))));
			action.DoubleClickButton(locator.getProperty("Users.Edit"));
			action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
			action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
			Thread.sleep(1000);
			//verify that CM is assigned to user
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("Upr.cmcheckbox2"))));
			action.VerifySelectcheckbox(locator.getProperty("Upr.smcheckbox1"));
			action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
			Thread.sleep(500);
			action.DoubleClickButton(locator.getProperty("Users.Commit"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			Thread.sleep(500);
			
			}
			
	
	private void ImportFile(File imprt_Excel,String Record) throws FileNotFoundException, IOException, Exception {
		action.RefreshPage();
		String Excel=String.valueOf(imprt_Excel);
		String File_Name = FilenameUtils.getBaseName(Excel);
		//Navigate to UPM, Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on  the more drop down button
		action.ClickButton(locator.getProperty("UPM.moreAcns"));
		action.WaitForObj(locator.getProperty("UPM.importUsers"));
		action.ClickButton(locator.getProperty("UPM.importUsers"));
		action.WaitForTitle(locator.getProperty("Import_Users_title"));
		action.ClickButton(Record);
		action.WaitForObj(locator.getProperty("import.Browse"));
		action.entertext(locator.getProperty("import.Browse"), Excel);
		action.WaitForObj(locator.getProperty("ImportBtn"));
		action.ClickButton(locator.getProperty("ImportBtn"));
		action.WaitForObj(locator.getProperty("Import.Status"));
		action.ClickButton(locator.getProperty("Import.Status"));
		action.WaitForObj(locator.getProperty("Import.Status.Result"));
		action.VerifyElementValue(locator.getProperty("Import.Status.Result"), "Import job"+" "+File_Name+".xml"+" "+"is"+" "+"scheduled");
		Thread.sleep(1000);
		while(action.driver.findElement(By.xpath(locator.getProperty("Import.Status.Process"))).getText().equals("RUNNING")||action.driver.findElement(By.xpath(locator.getProperty("Import.Status.Process"))).getText().equals("PENDING EXECUTION")){
			action.ClickButton(locator.getProperty("Import.Refresh"));
			Thread.sleep(2000);


		}
		action.VerifyElementValue(locator.getProperty("Import.Status.Process"), "SUCCESSFUL");
		action.VerifyElementValue(locator.getProperty("Import.PercCompleted"), "100");
		Thread.sleep(2000);
	}

	
 
	public String ReadExcel(File imprt_Excel) throws FileNotFoundException, IOException, Exception {
		//
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(imprt_Excel);
		    String login = "";
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("tns:user");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
					Element eElement = (Element) nNode;
					login=eElement.getElementsByTagName("loginName").item(0).getTextContent();
			}
			return login;
		}
		public String ReadExcelMerge(File imprt_Excel) throws FileNotFoundException, IOException, Exception {
		//
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(imprt_Excel);
		    String login = "";
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("tns:user");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
					Element eElement = (Element) nNode;
					login=eElement.getElementsByTagName("newLoginName").item(0).getTextContent();
			}
			return login;
		}
		public String PartialReadExcel(File imprt_Excel) throws FileNotFoundException, IOException, Exception {
		//
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(imprt_Excel);
		    String login = "";
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("delta:userDelta");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
					Element eElement = (Element) nNode;
					login=eElement.getElementsByTagName("loginName").item(0).getTextContent();
			}
			return login;
		}

	
@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException
{
	  
	action.Screenshot(result, action);
}

@AfterClass
	  public void Close() throws IOException, InterruptedException
    {
		action.logout(action);
	}
	

}
