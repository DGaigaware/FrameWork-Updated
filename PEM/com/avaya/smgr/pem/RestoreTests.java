package com.avaya.smgr.pem;

/*
 Contains tests for SMGR Restore functionality.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class RestoreTests {
	boolean b;
	static UserAction action = null;
	Properties locator = null;
	Properties input = null;
	public WebDriver driver;

	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException {
		
	}

	@BeforeMethod(alwaysRun=true)
	public void ScreenshptSetup() throws IOException, InterruptedException {
		action = new UserAction();
		locator = new Properties();
		input = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir")+ "\\Third Party\\objectRepository\\OR.properties"));
		input.load(new FileInputStream(System.getProperty("user.dir")+ "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"), action);
	}

	@Test(description = "Verification for the Restore title is showing correctly.")
	public void RestoreTitle() throws Exception {
		action.RefreshPage();
		// Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		// Click on the Restore button
		action.ClickButton(locator.getProperty("PEM.Restore"));
		// Verification for the Restore title is showing correctly.
		action.VerifyTitle(locator.getProperty("Restore_title"));
	}

	@Test(description = "Verification for the Local Restore is performed successfully",groups={"Sanity"})
	public void LocalRestore() throws Exception {
		action.RefreshPage();
		// Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		// Click on the Restore button
		action.ClickButton(locator.getProperty("PEM.Restore"));
		action.WaitForTitle(locator.getProperty("Restore_title"));
		// Enter Restore file name
		WebElement sel = action.driver.findElement(By.xpath(locator.getProperty("PEM.Restore.SelectFile")));
		List<WebElement> lists = sel.findElements(By.tagName("option"));
		    for(WebElement element: lists)  
		    {
		        if(element.getText().contains(input.getProperty("LocakBackup"))){
		        	Select dropdown = new Select(action.driver.findElement(By.xpath(locator.getProperty("PEM.Restore.SelectFile"))));
		    		dropdown.selectByVisibleText(element.getText());
		    		Thread.sleep(1000);

		        	
		        }
		    }
		// Click on the Restore	 button
		action.ClickButton(locator.getProperty("Edit"));
		action.WaitForTitle(locator.getProperty("Restore_Confirmation"));

		//Verify the Continue and Cancel buttons are enabled
		action.VerifyEnableButton(locator.getProperty("PEM.Restore.Continue"));
		action.VerifyEnableButton(locator.getProperty("Cancel_Cnf"));
		//Click on the continue button
		action.ClickButton(locator.getProperty("PEM.Restore.Continue"));
		Thread.sleep(60000);
		action.driver.switchTo().defaultContent();
		action.RefreshPage();
		Thread.sleep(2000);
		action.RefreshPage();
		Thread.sleep(1800000);
		action.RefreshPage();
		action.driver.switchTo().defaultContent();
		
		while(action.driver.findElement(By.xpath(locator.getProperty("SMGR.Error1"))).isDisplayed()){
			action.RefreshPage();
			Thread.sleep(30000);

		}
		action.driver.switchTo().defaultContent();
		action.login(input.getProperty("UserId"),input.getProperty("Password"), action);
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		action.Verify_Add_Fifthcolumn(input.getProperty("PEM.Loginname"));
		Thread.sleep(1000);
		
	}
	
@Test(description="Verification for the Home page is displaying when click on the cancel")
public void ScheduledLocalRestoreCancel() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, PEM
	action.ClickLink(locator.getProperty("Backup_and_Restore"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("PEM.Restore"));
	action.WaitForObj(locator.getProperty("PEM.FileName"));
	//Click on the Cancel button
	action.ClickButton(locator.getProperty("Cancel"));
	action.WaitForTitle(locator.getProperty("Backup_and_Restore"));
	//Verification for the Home page is displaying when click on the cancel
	action.VerifyTitle(locator.getProperty("Backup_and_Restore"));
	}
@Test(description="Verification for the Remote Restore is performed successfully")
public void RemoteRestore() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, PEM
	action.ClickLink(locator.getProperty("Backup_and_Restore"));
	action.SwithchFrame("iframe0");
	//Click on the Restore button
	action.ClickButton(locator.getProperty("PEM.Restore"));
	Thread.sleep(2000);	
	//Click on the Remote Radio button
	action.ClickButton(locator.getProperty("PEM.Remote"));
	//Switch to Parameterized Restore
	action.ClickLink(locator.getProperty("Parameterized_Restore"));
	//Fill the Required details
	action.entertext(locator.getProperty("PEM.Remote.ip"),input.getProperty("Ip"));
	action.entertext(locator.getProperty("PEM.Remote.UserName"),input.getProperty("Uname"));
	action.entertext(locator.getProperty("PEM.Remote.Password"),input.getProperty("Password"));
	action.entertext(locator.getProperty("PEM.Restore.File"),input.getProperty("BackupName"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Restore_Confirmation"));
	/*
	 * We are not click on the Restore continue button because it will take 45 min.
	 */
	}

	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException,
			InterruptedException {
		action.Screenshot(result, action);
		action.logout(action);
	}

	@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException {
		
	}
}
