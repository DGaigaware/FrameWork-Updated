package com.avaya.smgr.RTS.ManageServicabilityAgents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class ManageServicabilityAgents{
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
@Test(description="Verification of title Manage Servicability is showing correctly")
public void SNMPV3UserTitle() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Verification of title Manage Servicability is showing correctly
	action.VerifyTitle(locator.getProperty("SNMPv3_User_Profiles"));
}
@Test(description="Verification for Enabled for New button and Disabled for  Edit, View and Delete Buttons")
public void SNMPV3UserBtnsStates() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Verification for Enabled for New button and Disabled for Edit, View and Delete Buttons
	action.VerifyEnableButton(locator.getProperty("New"));
	action.VerifyDisableButton(locator.getProperty("Delete"));
	action.VerifyDisableButton(locator.getProperty("Update"));
	action.VerifyDisableButton(locator.getProperty("View"));
	}
@Test(description="Verify that the added SNMP User Profile successfully",groups={"Sanity"})
public void SNMPV3UserAdd() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the new button
	action.ClickButton(locator.getProperty("New"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Name"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.PrivPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.CnfPrivPwd"), input.getProperty("Password"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles"));
	//Verify that the added SNMP User Profile successfully
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"),input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles"));
	}
@Test(description="Verify that SNMP V3 User profile is updated",dependsOnMethods="SNMPV3UserAdd",groups={"Sanity"})
public void SNMPV3UserUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent ,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	action.ClickButton(locator.getProperty("Update"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.SelectFromdropDown(locator.getProperty("GSNMP.Auth"),"SHA");
	Thread.sleep(1000);
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("GSNMP.CnfPrivPwd"), input.getProperty("Password"));
	Thread.sleep(1000);
	//Click on the commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"),input.getProperty("SHA"));
	//Verify that SNMP V3 User profile is updated
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles"));
}

@Test(description="Verify that SNMP V3 User profile is View only",dependsOnMethods="SNMPV3UserAdd",groups={"Sanity"})
public void SNMPV3UserView() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent ,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("View"));
	//Verify that SNMP V3 User profile is View only
	action.VerifyReadOnlyDisplayed(locator.getProperty("GSNMP.User"));
	Thread.sleep(1000);
}
@Test(description="SNMP v3 Profile is Deleted",dependsOnMethods="SNMPV3TargetDelete",groups={"Sanity"})
public void SNMPV3UserDelete() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent ,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	//Thread.sleep(2000);
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	//Click on the Delete button
	action.ClickButton(locator.getProperty("Delete"));
	//action.WaitForText("SNMP Access Profiles");
	action.WaitForTitle("User Profile Delete Confirmation");
	action.VerifyStringValue("User Profile Delete Confirmation");
	//Verify the button is enable for Cancel and Delete
	action.VerifyEnableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyEnableButton(locator.getProperty("Cancel_Cnf"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles")); 
	//Verify the profile is deleted
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"),input.getProperty("UpdatedName"));
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles")); 
}
/*
 * Test Cases Related to SNMP Target Profiles
 */
@Test(description="Verification of title SNMP Target Profiles is showing correctly")
public void SNMPV3TargetTitle() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Verification of title SNMP Target Profiles is showing correctly
	action.VerifyTitle(locator.getProperty("SNMP_Target_Profiles"));
}
@Test(description="Verification for Enabled for New button and Disabled for  Edit, View and Delete Buttons")
public void SNMPV3TargetBtnsStates() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Verification for Enabled for New button and Disabled for  Edit, View and Delete Buttons
	action.VerifyEnableButton(locator.getProperty("New"));
	action.VerifyDisableButton(locator.getProperty("Delete"));
	action.VerifyDisableButton(locator.getProperty("Update"));
	action.VerifyDisableButton(locator.getProperty("View"));
	}
@Test(description="Verify that the added SNMP Target Profile successfully",dependsOnMethods="SNMPV3UserAdd",groups={"Sanity"})
public void SNMPV3TargetAdd() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on  the new button and Fill the Required mandatory details
	action.ClickButton(locator.getProperty("New"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.entertext(locator.getProperty("Taget.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Target.IP"), input.getProperty("Ip"));
	action.ClearText(locator.getProperty("Target.Port"));
	action.entertext(locator.getProperty("Target.Port"), input.getProperty("port"));
	Thread.sleep(1000);
	//Attach the SNMPv3 user profile
	action.ClickLink(locator.getProperty("Attach/Detach_User_Profile"));
	action.ClickButton(locator.getProperty("Target.Refresh"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Target.profile"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Target.Assign"));
	Thread.sleep(1000);
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	//Verify that the added SNMP Target Profile successfully
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"),input.getProperty("Name"));
}
@Test(description="Verify that SNMP Targer profile is updated",dependsOnMethods="SNMPV3TargetAdd",groups={"Sanity"})
public void SNMPV3TargetUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Update"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.ClearText(locator.getProperty("Target.Port"));
	Thread.sleep(1000);
	action.entertext(locator.getProperty("Target.Port"), input.getProperty("port"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify that SNMP Targer profile is updated
	action.VerifyaddedEntry(locator.getProperty("GSNMP.Table"),input.getProperty("port"));
	Thread.sleep(1000);
}

@Test(description="Verify that SNMP Targer profile is View only",dependsOnMethods="SNMPV3TargetAdd",groups={"Sanity"})
public void SNMPV3TargetView() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("View"));
	//Verify that SNMP Targer profile is View only
	action.VerifyReadOnlyDisplayed(locator.getProperty("Taget.Name"));
	Thread.sleep(1000);
}
@Test(description="SNMP v3 Target Profile is Deleted",dependsOnMethods="SNMPV3TargetUpdate",groups={"Sanity"})
public void SNMPV3TargetDelete() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Select first item in the table
	//action.ClickButton(locator.getProperty("FirstItem"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr"+"["+i+"]"+"/td[2]")).getText();
		if(input.getProperty("Name").contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	Thread.sleep(2000);
	//Click on the Delete button
	action.ClickButton(locator.getProperty("Delete"));
	//action.WaitForText("SNMP Access Profiles");
	action.WaitForTitle(locator.getProperty("Target_Profile_Delete_Confirmation"));
	action.VerifyStringValue(locator.getProperty("Target_Profile_Delete_Confirmation"));
	//Verify the button is enable for Cancel and Delete
	action.VerifyEnableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyEnableButton(locator.getProperty("Cancel_Cnf"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles")); 
	//Verify the profile is deleted
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"),input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles")); 
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

