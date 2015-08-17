package com.avaya.smgr.spm.settings.smgr;

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


public class SMGRElmtMgr{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
	Properties input=null;
	public WebDriver driver;
	boolean Accept;
	
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
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved")
public void SMGRMgrUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting, SMGR,SMGR Element Manager
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("SMGR_Element_Manager"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.DiskAllocated"));
	action.entertext(locator.getProperty("SMGRElmtMgr.DiskAllocated"), input.getProperty("TwoDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.DiskAllocated"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DiskAllocated"), input.getProperty("TwoDigitValue"));
}
@Test(description="enable of Edit and Done buttons",priority=1)
 public void SMGRMgrEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
}
@Test(description="enable of commit and cancel buttons",priority=2)
public void SMGRMgrCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="verify that the title value",priority=3)
public void SMGRMgrTitle() throws Exception{
	//Verification of title "SMGR Element Manager"
	action.VerifyStringValue(locator.getProperty("SMGR_Element_Manager"));
	Thread.sleep(1000);

}


@Test(description="Verify that the errror message should display when Backup Directory and DB Utilities are empty",priority=4)
public void SMGRBackupError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.Backup"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.DatabaseUtil"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.Backup.Msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.Backup.Msg"), locator.getProperty("You_must_enter_a_value"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DatabaseUtil.Msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when DB user Password and Confirm Password are empty",priority=4)
public void SMGRDBSuperError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.DatabaseSuper"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.CnfDatabaseSuper"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.DatabaseSuper.Msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DatabaseSuper.Msg"), locator.getProperty("You_must_enter_a_value"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.CnfDatabaseSuper.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when DB Super user and Job URL are empty",priority=4)
public void SMGRDBSuperJobError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.DbSuper"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.JobInterface"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.DbSuper.msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DbSuper.msg"), locator.getProperty("You_must_enter_a_value"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.JobInterface.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
}
@Test(description="Verify that the error message should display when Remote Util and Scheduler URL are empty",priority=4)
public void SMGRRemoScedError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.RemoteUtil"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.SchedulerURL"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.RemoteUtil.Msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.RemoteUtil.Msg"), locator.getProperty("You_must_enter_a_value"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.SchedulerURL.msg"), locator.getProperty("You_must_enter_a_value"));
	action.ClickButton(locator.getProperty("Cancel"));
	
}

@Test(description="Verify that the error message should display when Data base port and SCP port are empty",priority=4)
public void SMGRDBPortSCPPortError() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.DbPort"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.DbScpPort"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.DbPort.msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DbPort.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DbScpPort.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
}

@Test(description="Verify that the error message should display when disk space threshold and allocated  are empty",priority=4)
public void SMGRDiskSpace() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.DiskAllocated"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.DiskSpaceThreshold"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.DiskAllocated.msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DiskAllocated.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.DiskSpaceThreshold.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));

}

@Test(description="Verify that the error message should display when Max back and Retention are empty",priority=4)
public void SMGRMaxBackupReten() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.MaxBackup"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.MaxDataRetention"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.MaxBackup.msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.MaxBackup.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.MaxDataRetention.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));


}

@Test(description="Verify that the error message should display when Max size and Max transaction are empty",priority=4)
public void SMGRMaxsizeTran() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value 
	action.ClearText(locator.getProperty("SMGRElmtMgr.MaxSize"));
	action.ClearText(locator.getProperty("SMGRElmtMgr.MaxTrans"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("SMGRElmtMgr.MaxSize.msg"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.MaxSize.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.VerifyElementValue(locator.getProperty("SMGRElmtMgr.MaxTrans.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Cancel"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws Exception{
	  
	action.Screenshot(result, action);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);

}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
	action.logout(action);
	}
}
