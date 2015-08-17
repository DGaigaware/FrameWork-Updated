package com.avaya.smgr.RTS.ElementTypeAccess;
/*
 * Element type access validations UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class ElementTypeAccess_Validations{
	boolean b;
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
@BeforeMethod(alwaysRun=true)
public void Beforemethodsetup() throws IOException, InterruptedException{
}
@Test(description="Verification of error message where Name is empty")
public void ElmtAccessTypeNameEmpty() throws Exception{
	//Navigate to SMGR,RTS,Element type access
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	//Fill the Mandatory details and Make sure Name field is empty
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Log"), input.getProperty("Name"));
	action.entertext(locator.getProperty("ElementTypeAcess.Password"), input.getProperty("Password"));
	action.entertext(locator.getProperty("ElementTypeAcess.CnfPassword"), input.getProperty("Password"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	Thread.sleep(2000);
	//Verification of error message where Name is empty
	action.VerifyStringValue("Name:Value Required.");
	Thread.sleep(1000);
}
@Test(description="Verification of error message where Login name is empty")
public void ElmtAccessTypeLoginEmpty() throws Exception{
	//Navigate to SMGR,RTS,Element type access
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	//Fill the Mandatory details and Make sure Login Name field is empty
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Password"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.CnfPassword"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	Thread.sleep(1000);
	//Verification of error message where Login name is empty
	action.VerifyStringValue("Name:Value Required.");
	Thread.sleep(1000);

}
@Test(description="Verification of error message where password is empty")
public void ElmtAccessTypePasswordEmpty() throws Exception{
	//Navigate to SMGR,RTS,Element type access
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	//Fill the Mandatory details and Make sure Password field is empty
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Log"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.CnfPassword"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	Thread.sleep(1000);
	//Verification of error message where password is empty
	action.VerifyStringValue("Password:Value Required.");
	Thread.sleep(1000);

}
@Test(description="Verification of error message where Confirm password is empty")
public void ElmtAccessTypeCnfPasswordEmpty() throws Exception{
	//Navigate to SMGR,RTS,Element type access
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle("Element Type Access");
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the new button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	//Fill the Mandatory details and Make sure Confirm Password field is empty
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Log"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Password"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	Thread.sleep(1000);
	//Verification of error message where Confirm password is empty
	action.VerifyStringValue("Confirm Password:Value Required.");
	Thread.sleep(1000);

}
@Test(description="Verification of error message where password and Cnf Password is mismatch")
public void ElmtAccessTypeMismatchPassword() throws Exception{
	//Navigate to SMGR,RTS,Element type access
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle("Element Type Access");
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	//Fill the Mandatory details and Make sure password and Cnf Password is mismatch
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("ElementTypeAcess.Log"), input.getProperty("Name"));
	action.entertext(locator.getProperty("ElementTypeAcess.Password"), input.getProperty("Password"));
	action.entertext(locator.getProperty("ElementTypeAcess.CnfPassword"), input.getProperty("Name"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	Thread.sleep(1000);
	//Verification of error message where password and Cnf Password is mismatch
	action.VerifyStringValue("Mismatch in Password and Confirm Password.");
	Thread.sleep(1000);

}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}



  @AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
 }

