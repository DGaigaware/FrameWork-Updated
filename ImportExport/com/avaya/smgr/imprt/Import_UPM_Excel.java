package com.avaya.smgr.imprt;
/*
 * Imported Excel related functionality

 */

import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class Import_UPM_Excel{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
	Properties input=null;
	Properties export=null;
	public WebDriver driver;
	static Properties prop = new Properties();
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }
/*
 * Import functionality on the UPM page
 */

@Test(description="Verify that import Users is showing under UPM more dropdown")
 public void ImportUpmdropdown() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	//Verify that import Users is showing under UPM more drop down
	action.VerifyElementValue(locator.getProperty("UPM.importUsers"), locator.getProperty("Import_Users"));
	Thread.sleep(1000);
}
@Test(description="Verify that import Users title is showing correctly when click on the UPM more dropdown")
public void ImportUpmTitle() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	//Verify that import Users title is showing correctly when click on the UPM more dropdown
	action.VerifyTitle(locator.getProperty("Import_Users_title"));
	Thread.sleep(1000);
}
@Test(description="Verify that done and import buttons are enabled correctly")
public void ImportUpmBtns() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Verify that import Users title is showing correctly when click on the UPM more drop down
	action.VerifyEnableButton(locator.getProperty("import.doneTop"));
	action.VerifyEnableButton(locator.getProperty("ImportBtn"));
	Thread.sleep(1000);
}
@Test(description="Verify that Collapse All Functionality")
public void ImportCollapse() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the collapse button
	action.ClickButton(locator.getProperty("Coolapse"));
	Thread.sleep(1000);
	//verify that all the elements are hide
	action.VerifyNoElementdisplay(locator.getProperty("import.Browse"));
	action.VerifyNoElementdisplay(locator.getProperty("Import.skip"));
	action.VerifyNoElementdisplay(locator.getProperty("ImportBtn"));
	action.VerifyNoElementdisplay(locator.getProperty("Import.table"));
	Thread.sleep(1000);
}
@Test(description="Verify that Collapse All Functionality for File Selection")
public void ImportCollapseFile() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the File selection show button
	action.ClickButton(locator.getProperty("Import.Collapse1"));
	Thread.sleep(1000);
	//Verify that Collapse All Functionality for File Selection
	action.VerifyNoElementdisplay(locator.getProperty("import.Browse"));
	Thread.sleep(1000);
}
@Test(description="Verify that Collapse for the General")
public void ImportCollapseGeneral() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the General show button
	action.ClickButton(locator.getProperty("Import.Collapse2"));
	Thread.sleep(1000);
	//Verify that Collapse for the General
	action.VerifyNoElementdisplay(locator.getProperty("Import.skip"));
	Thread.sleep(1000);
}
@Test(description="Verify that Collapse for the Job Schedule")
public void ImportCollapseJobSched() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the Job Schedule
	action.ClickButton(locator.getProperty("Import.Collapse3"));
	Thread.sleep(1000);
	//Verify that Collapse for the Job Schedule
	action.VerifyNoElementdisplay(locator.getProperty("ImportBtn"));
	Thread.sleep(1000);
}
@Test(description="Verify that Collapse for the Manage job")
public void ImportCollapseFileSelection() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the Manage job
	action.ClickButton(locator.getProperty("Import.Collapse4"));
	Thread.sleep(1000);
	//Verify that Collapse for the Manage job
	action.VerifyNoElementdisplay(locator.getProperty("Import.table"));
	Thread.sleep(1000);
}
@Test(description="Verify that Expand All Functionality")
public void ImportExpand() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Click on the collapse button
	action.ClickButton(locator.getProperty("Coolapse"));
	action.ClickButton(locator.getProperty("Import.Expand"));
	Thread.sleep(1000);
	//verify that all the elements are hide
	action.VerifyElementDisplay(locator.getProperty("import.Browse"));
	action.VerifyElementDisplay(locator.getProperty("Import.skip"));
	action.VerifyElementDisplay(locator.getProperty("ImportBtn"));
	action.VerifyElementDisplay(locator.getProperty("Import.table"));
	Thread.sleep(1000);
}
@Test(description="Verify that Browse button is enabled")
public void ImportBrowse() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Verify that Browse button is enabled
	action.VerifyEnableButton(locator.getProperty("import.Browse"));
	Thread.sleep(1000);
}
@Test(description="Verify that error message when click on import button without File upload")
public void ImportErrorFileNOtFound() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	//Verify that Browse button is enabled
	action.ClickButton(locator.getProperty("ImportBtn"));
	action.WaitForObj(locator.getProperty("Import.FileUpload"));
	action.VerifyElementValue(locator.getProperty("Import.FileUpload"),"No file to upload");
	Thread.sleep(1000);
}

@Test(description="Verify that the error message when trying for import .xml type for import type .Xlsx")
public void ImportExceError() throws Exception{
	action.RefreshPage();
	File imprt_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_XML_Replace_Partial.xml");
	String Excel=String.valueOf(imprt_Excel);
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	action.ClickButton(locator.getProperty("Import.Excel"));
	action.WaitForObj(locator.getProperty("import.Browse"));
	action.entertext(locator.getProperty("import.Browse"), Excel);
	action.WaitForObj(locator.getProperty("ImportBtn"));
	action.DoubleClickButton(locator.getProperty("ImportBtn"));
	action.WaitForObj(locator.getProperty("import.Browse"));
	action.VerifyElementValue(locator.getProperty("Import.FileUpload"),"Import File Type option selected is Xlsx. Cannot import Xml file.");
	Thread.sleep(2000);

}

@Test(description="Verify that the error message when trying for import .Xlsx type for import type .Xml")
public void ImportXMLError() throws Exception{
	action.RefreshPage();
	File imprt_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Merge.xlsx");
	String Excel=String.valueOf(imprt_Excel);
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on  the more drop down button
	action.ClickButton(locator.getProperty("UPM.moreAcns"));
	action.WaitForObj(locator.getProperty("UPM.importUsers"));
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	action.WaitForTitle(locator.getProperty("Import_Users_title"));
	action.ClickButton(locator.getProperty("Import.merge.xml"));
	action.WaitForObj(locator.getProperty("import.Browse"));
	action.entertext(locator.getProperty("import.Browse"), Excel);
	action.WaitForObj(locator.getProperty("ImportBtn"));
	action.ClickButton(locator.getProperty("ImportBtn"));
	action.WaitForObj(locator.getProperty("import.Browse"));
	action.VerifyElementValue(locator.getProperty("Import.FileUpload"),"Import File Type option selected is Xml. Cannot import Xlsx file.");
	Thread.sleep(1000);
	
}

@Test(description="Verify that import of the User sucessfully and skip the user if already exists",priority=1,groups={"Sanity"})
public void ImportExcelDefaults() throws Exception{
	File imprt_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel.xlsx");
	ImportFile(imprt_Excel,locator.getProperty("Import.skipExcel"));
}

@Test(description="Verify that import Users is showing under Users table and skip the user if already exists",priority=2,groups={"Sanity"})
public void ImportExcelVerify() throws Exception{
	action.RefreshPage();
	//File imprt_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel.xlsx");
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Cell cellvalue=ReadExcel(imprt_Excel);
	//String loginname=cellvalue.getStringCellValue();
	//Need implement by using .xlxs
	//Verify that import Users is showing under UPM table
	action.Verify_Add_Fifthcolumn(input.getProperty("Loginname"));
	Thread.sleep(1000);
}

@Test(description="Verify that Performed import Excel of the User sucessfully Using Merge",priority=3,groups={"Sanity"})
public void ImportExcelMerge() throws Exception{
	File imprt_Excel_Merge = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Merge.xlsx");
	ImportFile(imprt_Excel_Merge,locator.getProperty("Import.merge"));
}
@Test(description="Verify that Merge the existing Login Name to new login Name for the user Using Merge",priority=4,groups={"Sanity"})
public void ImportExcelMergeVerify() throws Exception{
	action.RefreshPage();
	//File imprt_Excel_Merge = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Merge.xlsx");

	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Cell cellvalue=ReadExcelMerge(imprt_Excel_Merge);
	//String loginname=cellvalue.getStringCellValue();
	//Need implement by using .xlxs
	//Verify that import Users is showing under UPM table
	action.Verify_Add_Fifthcolumn(input.getProperty("Loginname1"));
	Thread.sleep(1000);
	
    }

@Test(description="Verify that import User using Delete",priority=5,groups={"Sanity"})
public void ImportExcelDelete() throws Exception{
	File imprt_Excel_Delete = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Delete.xlsx");
	ImportFile(imprt_Excel_Delete,locator.getProperty("Import.Delete"));
}

@Test(description="Verify that Deleted Users is not showing under UPM table",priority=6,groups={"Sanity"})
public void ImportExcelDeleteVerify() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//File imprt_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Delete.xlsx");
	//Cell cellvalue=ReadExcel(imprt_Excel);
	//String loginname=cellvalue.getStringCellValue();
	//Need implement by using .xlxs
	//Verify that import Users is showing under UPM table
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("Loginname1"));
	//action.Verify_Delete_Fifthcolumn(loginname);
	Thread.sleep(1000);
 }

@Test(description="Verify that import User with CM using Import skip",priority=7,groups={"Sanity"})
public void Import_CM_Excel() throws Exception{
	File imprt_CM_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_CM.xlsx");
	ImportFile(imprt_CM_Excel,locator.getProperty("Import.skipExcel"));

}

@Test(description="Verify that import User with CM profile is showing",priority=8,groups={"Sanity"})
public void Import_CM_Excel_Verify() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	//File imprt_CM_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\exportfile_1.xlsx");
	//Cell cellvalue=ReadExcel(imprt_CM_Excel);
	//String loginname=cellvalue.getStringCellValue();
	
	//select by login name
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("Users.Edit"));
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForObj(locator.getProperty("Upr.cmcheckbox2"));
	//Verify that import Users with CM profile is showing under UPM table
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));
	Thread.sleep(1000);
}

@Test(description="Verify that import User with existing CM and new SM using Complete Merge",priority=9,groups={"Sanity"})
public void Import_CM_SM_Excel() throws Exception{
	File Import_CM_SM_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_SM_CM_Merge.xlsx");
	ImportFile(Import_CM_SM_Excel,locator.getProperty("Import.merge"));

}
@Test(description="Verify that import Users with existing CM is showing for Complete merge",priority=10,groups={"Sanity"})
public void Import_CM1_Excel_Verify() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	
	////File Import_CM_SM_Excel = new File(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_SM_CM_Merge.xlsx");
	//Cell cellvalue=ReadExcel(Import_CM_SM_Excel);
	//String loginname=cellvalue.getStringCellValue();
	
	//select by login name
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	action.WaitToClick(locator.getProperty("Users.Edit"));
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForObj(locator.getProperty("Upr.cmcheckbox2"));
	//Verify that import Users with CM profile is showing under UPM table
	action.VerifySelectcheckbox(locator.getProperty("Upr.cmcheckbox2"));

	//Need implement by using .xlxs
	//Verify that import Users is showing under UPM table
	Thread.sleep(1000);
}

@Test(description="Verify that import Users with SM profile is showing for Complete merge",priority=11,groups={"Sanity"})
public void Import_SM_Excel_Verify() throws Exception{
	action.RefreshPage();
	//Navigate to UPM, Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//select by login name
	action.SelectElementByLoginname(input.getProperty("Loginname"));
	Thread.sleep(500);

	action.WaitToClick(locator.getProperty("Users.Edit"));
	
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForObj(locator.getProperty("Users.SMCheckBox"));
	//Verify that import Users with CM profile is showing under UPM table
	action.VerifySelectcheckbox(locator.getProperty("Users.SMCheckBox"));

	//Need implement by using .xlxs
	//Verify that import Users is showing under UPM table
	Thread.sleep(2000);
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
	//Select Merge 
	action.ClickButton(locator.getProperty("UPM.importUsers"));
	//Thread.sleep(3000);
	action.WaitForTitle(locator.getProperty("Import_Users_title"));

	//action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickButton(locator.getProperty("Import.Excel"));
	action.WaitForObj(Record);
	
	//WebElement element=action.driver.findElement(By.xpath(locator.getProperty(Record)));
	//element.sendKeys(org.openqa.selenium.Keys.CONTROL);
	action.ClickButton(Record);
	action.WaitForObj(locator.getProperty("import.Browse"));
	action.entertext(locator.getProperty("import.Browse"), Excel);
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("ImportBtn"));
	action.WaitForObj(locator.getProperty("Import.Status"));
	action.ClickButton(locator.getProperty("Import.Status"));
	action.WaitForObj(locator.getProperty("Import.Status.Result"));
	action.VerifyElementValue(locator.getProperty("Import.Status.Result"), "Import job"+" "+File_Name+".xlsx"+" "+"is"+" "+"scheduled");
	Thread.sleep(1000);
	while(action.driver.findElement(By.xpath(locator.getProperty("Import.Status.Process"))).getText().equals("RUNNING")||action.driver.findElement(By.xpath(locator.getProperty("Import.Status.Process"))).getText().equals("PENDING EXECUTION")){
		action.ClickButton(locator.getProperty("Import.Refresh"));
		Thread.sleep(1000);


	}
	action.VerifyElementValue(locator.getProperty("Import.Status.Process"), "SUCCESSFUL");
	action.VerifyElementValue(locator.getProperty("Import.PercCompleted"), "100");
	Thread.sleep(1000);
}
private Cell ReadExcel(File imprt_Excel) throws FileNotFoundException, IOException, Exception {
//	FileInputStream fsIP= new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Delete.xlsx"); //Read the spreadsheet that needs to be updated
	InputStream fsIP = new FileInputStream(imprt_Excel);
	XSSFWorkbook wb = new XSSFWorkbook(fsIP);//Access the workbook
	XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
	Cell cell = null; // declare a Cell object
	cell = worksheet.getRow(10).getCell(4);// Access the second cell in second row to update the value
	System.out.println("Root element :" +cell);
	//cell.setCellValue("OverRide Last Name"); // Get current cell value value and overwrite the value
	fsIP.close();
	return cell;
}
private Cell ReadExcelMerge(File imprt_Excel) throws FileNotFoundException, IOException, Exception {
//	FileInputStream fsIP= new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Excel_Delete.xlsx"); //Read the spreadsheet that needs to be updated
	InputStream fsIP = new FileInputStream(imprt_Excel);
	XSSFWorkbook wb = new XSSFWorkbook(fsIP);//Access the workbook
	XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
	Cell cell = null; // declare a Cell object
	cell = worksheet.getRow(10).getCell(5);// Access the second cell in second row to update the value
	System.out.println("Root element :" +cell);
	//cell.setCellValue("OverRide Last Name"); // Get current cell value value and overwrite the value
	fsIP.close();
	return cell;
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

