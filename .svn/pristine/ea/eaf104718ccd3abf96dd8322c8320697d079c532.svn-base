package com.avaya.smgr.RTS.ManageServicabilityAgents;
/*ManageServicabilityAgents validations
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


public class ManageServicabilityAgents_Validations{
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
public void ScreenshptSetup() throws IOException, InterruptedException{
}
@Test(description="Verification of error message where User is empty")
public void SNMPV3UserError() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, Manage Servicability agent,SNMPV3 User Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("New"));
	action.WaitForObj(locator.getProperty("GSNMP.User"));	
	//Fill the mandatory details Except User name
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message when user is empty
	action.VerifyStringValue("User Name is null");
}
@Test(description="Verification of error message where Auth Pass is empty")
public void SNMPV3AuthPassError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent,SNMPV3 User Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("New"));
	//Fill the mandatory details Except Authorisation password
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message where  Authentication pass word is empty
	action.VerifyStringValue("Confirm Authentication Password is null.");
	action.VerifyStringValue("Authentication Password is null.");

}
@Test(description="Verification error message where Priv and Confirm Priv Password is empty")
public void SNMPV3PrivError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent,SNMPV3 User Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the new button
	action.ClickButton(locator.getProperty("New"));
	//Fill the required details
	//Make sure Priv and Confirm Priv Password is empty
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.ClickButton(locator.getProperty("Commit"));
	//action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	Thread.sleep(1000);
	//Verification error message where Priv and Confirm Priv Password is empty	
	action.VerifyStringValue("Confirm Privacy Password is null.");
	action.VerifyStringValue("Privacy Password is null.");
	}
@Test(description="Verification of error message where Auth Pass and Cnf AuthPass are Missmatch")
public void SNMPV3AuthPassMissMatchError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent,SNMPV3 User Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("User_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message where  Auth Pass and Cnf AuthPass is Mismatch
	action.VerifyStringValue("Authentication Password and Confirm Authentication password is not matching. - Please enter non null and valid values.");
}
@Test(description="Verification of error message where Priv and Confirm Priv Password is mismatch")
public void SNMPV3PrivMismatch() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent,SNMPV3 User Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMPv3_User_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMPv3_User_Profiles"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.entertext(locator.getProperty("GSNMP.PrivPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	action.entertext(locator.getProperty("GSNMP.CnfPrivPwd"), input.getProperty("Uname"));
	action.ClickButton(locator.getProperty("Commit"));
	//action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	Thread.sleep(1000);
	//Verify error message when user is empty
	action.VerifyStringValue("Privacy Password and Confirm Privacy password is not matching.");
	}
//Error Messages related to SNMP Target profiles
@Test(description="Verification of error message where User is empty")
public void SNMPTargetUserError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, RTS,Manage Servicability agent,SNMP Target Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("New"));
	//Fill the mandatory details, Make sure User should empty
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.ClearText(locator.getProperty("Taget.Name"));
	action.entertext(locator.getProperty("Target.IP"), input.getProperty("Ip"));
	action.ClearText(locator.getProperty("Target.Port"));
	action.entertext(locator.getProperty("Target.Port"), input.getProperty("port"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message when user is empty
	action.VerifyStringValue("Name is invalid");
	Thread.sleep(1000);
}
@Test(description="Verification of error message where IP is empty")
public void SNMPTargetIpError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, RTS,Manage Servicability agent,SNMP Target Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the new button
	action.ClickButton(locator.getProperty("New"));
	//Fill the mandatory details, Make sure IP should empty
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.ClearText(locator.getProperty("Taget.Name"));
	action.entertext(locator.getProperty("Taget.Name"), input.getProperty("Name"));
	action.ClearText(locator.getProperty("Target.IP"));
	action.ClearText(locator.getProperty("Target.Port"));
	action.entertext(locator.getProperty("Target.Port"), input.getProperty("port"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verification of error message where IP is empty	
	action.VerifyStringValue("IP Address is invalid");
	Thread.sleep(1000);
}@Test(description="Verification of error message where Port is empty")
public void SNMPTargetPortError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, RTS,Manage Servicability agent,SNMP Target Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("New"));
	//Fill the mandatory details, Make sure Port should empty
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.ClearText(locator.getProperty("Taget.Name"));
	action.entertext(locator.getProperty("Taget.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Target.IP"), input.getProperty("Ip"));
	action.ClearText(locator.getProperty("Target.Port"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message when port is empty
	action.VerifyStringValue("Port is invalid");
	Thread.sleep(1000);
}
@Test(description="Verification of Error message where SNMPV3 User Profile is not attached")
public void SNMPTargetAttachUserError() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, RTS,Manage Servicability agent,SNMP Target Profile
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("SNMP_Target_Profiles"));
	action.WaitForTitle(locator.getProperty("SNMP_Target_Profiles"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("New"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	action.ClearText(locator.getProperty("Taget.Name"));
	action.entertext(locator.getProperty("Taget.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Target.IP"), input.getProperty("Ip"));
	action.ClearText(locator.getProperty("Target.Port"));
	action.entertext(locator.getProperty("Target.Port"), input.getProperty("port"));
	action.WaitForTitle(locator.getProperty("New_Target_Profile"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message when user is empty
	action.VerifyStringValue("No User profile is attached to the target. Please attach the user profile.");
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

