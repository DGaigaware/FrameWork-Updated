package com.avaya.smgr.sdm.swlib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;



import com.avaya.smgr.sdmsetup.Sdmsetup;

public class Create {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Sdmsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	setup=new com.avaya.smgr.sdmsetup.Sdmsetup(); 
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Create new software library with SFTP protocol",priority=1)
public void CreateLibrarywithSFTP() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("FName"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SFTP");
	Thread.sleep(2000);

	action.DoubleClickButton(locator.getProperty("Lib.Sftptab"));
	action.WaitForObj(locator.getProperty("Lib.Sftpusername"));
	action.entertext(locator.getProperty("Lib.Sftpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Sftppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Sftpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commitbtm"));
	action.WaitForObj(locator.getProperty("Lib.New"));
	setup.VerifyAdd(action, input.getProperty("FName"));
	Thread.sleep(1000);
		
}


@Test(description="Create new software library with FTP protocol",priority=2)
public void CreateLibrarywithFTP() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("Danish"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip1"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "FTP");
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Lib.Ftptab"));
	action.WaitForObj(locator.getProperty("Lib.ftpusername"));
	action.entertext(locator.getProperty("Lib.ftpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.ftppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.ftpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	setup.VerifyAdd(action, input.getProperty("Danish"));
	Thread.sleep(1000);
}


@Test(description="Create new software library with SCP,SFTP and FTP protocol",priority=3)
public void CreateLibrarywithAll() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Lib.New"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.entertext(locator.getProperty("Lib.Name"), input.getProperty("Avaya"));
	action.entertext(locator.getProperty("Lib.Ip"), input.getProperty("Ip2"));
	action.entertext(locator.getProperty("Lib.Path"), input.getProperty("Uname"));
	action.SelectFromdropDown(locator.getProperty("Lib.Protocollist"), "SCP");
	
	action.DoubleClickButton(locator.getProperty("Lib.Scptab"));
	action.entertext(locator.getProperty("Lib.Scpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Scppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Scpcnfpass"), input.getProperty("Password"));
	
	action.DoubleClickButton(locator.getProperty("Lib.Sftptab"));
	action.WaitForObj(locator.getProperty("Lib.Sftpchk"));
	action.SelectCheckBox(locator.getProperty("Lib.Sftpchk"));
	Thread.sleep(2000);
	action.entertext(locator.getProperty("Lib.Sftpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.Sftppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.Sftpcnfpass"), input.getProperty("Password"));
	
	action.DoubleClickButton(locator.getProperty("Lib.Ftptab"));
	action.WaitForObj(locator.getProperty("Lib.Ftpchk"));
	action.SelectCheckBox(locator.getProperty("Lib.Ftpchk"));
	Thread.sleep(2000);
	action.entertext(locator.getProperty("Lib.ftpusername"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Lib.ftppassword"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Lib.ftpcnfpass"), input.getProperty("Password"));
	action.DoubleClickButton(locator.getProperty("Lib.Commit"));
	action.WaitForObj(locator.getProperty("Lib.New"));
	setup.VerifyAdd(action, input.getProperty("Avaya"));
	Thread.sleep(2000);
}

@Test(description="Verify deletion of software library with SFTP protocol",priority=4)
public void DeleteLibrary() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	//action.ClickButton(locator.getProperty("Lib.delete.first"));
	 List<WebElement> rows = action.driver.findElements(By.xpath(locator.getProperty("Lib.tab")));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=3;i<=numberofrows+2;i++)
			{
				String str11=action.driver.findElement(By.xpath(locator.getProperty("Lib.Colname")+"["+i+"]/td[2]")).getText();
				boolean b1= str11.equalsIgnoreCase(input.getProperty("Avaya"));
				if(b1)
				{
					action.driver.findElement(By.xpath(locator.getProperty("Lib.item")+(i-2)+"']")).click();
					flag=1;
					break;
				}
			}
			
			if(flag==0){
				Assert.assertTrue(false);
			}
			Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.Delete"))));
	action.ClickButton(locator.getProperty("Lib.Delete"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.DoubleClickButton(locator.getProperty("Lib.Delete.Now"));
	action.WaitToClick(locator.getProperty("Lib.Refresh"));
	action.DoubleClickButton(locator.getProperty("Lib.Refresh"));
	Thread.sleep(2000);
	action.DoubleClickButton(locator.getProperty("Lib.Refresh"));
	Thread.sleep(2000);
	setup.VerifydeleteLibrary(action, input.getProperty("Avaya"));
	Thread.sleep(2000);
	
}


@Test(description="Verify deletion of  new software library with FTP protocol",priority=4)
	public void DeleteLibraryFTP() throws Exception
	{
		action.driver.navigate().refresh();
		action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
		action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
		action.ClickLink(locator.getProperty("Software_Library_Management"));
		action.WaitForTitle(locator.getProperty("Software_Library"));
		action.VerifyTitle(locator.getProperty("Software_Library"));
		action.SwithchFrame("iframe0");
		//action.ClickButton(locator.getProperty("Lib.delete.first"));
		 List<WebElement> rows = action.driver.findElements(By.xpath(locator.getProperty("Lib.tab")));
			int numberofrows = rows.size();
			int flag=0;
				for(int i=3;i<=numberofrows+2;i++)
				{
					String str11=action.driver.findElement(By.xpath(locator.getProperty("Lib.Colname")+"["+i+"]/td[2]")).getText();
					boolean b1= str11.equalsIgnoreCase(input.getProperty("Danish"));
					if(b1)
					{
						action.driver.findElement(By.xpath(locator.getProperty("Lib.item")+(i-2)+"']")).click();
						flag=1;
						break;
					}
				}
				if(flag==0)
				{
					Assert.assertTrue(b);
				}
				Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.Delete"))));
		action.ClickButton(locator.getProperty("Lib.Delete"));
		Thread.sleep(1000);
		action.WaitForTitle(locator.getProperty("Software_Library"));
		action.DoubleClickButton(locator.getProperty("Lib.Delete.Now"));
		action.WaitToClick(locator.getProperty("Lib.Refresh"));
		action.driver.switchTo().defaultContent();
		action.ClickLink(locator.getProperty("Software_Library_Management"));
		action.WaitForTitle(locator.getProperty("Software_Library"));
		action.SwithchFrame("iframe0");
		action.DoubleClickButton(locator.getProperty("Lib.Refresh"));
		Thread.sleep(2000);
		setup.VerifydeleteLibrary(action, input.getProperty("Danish"));
		Thread.sleep(2000);
		
		
		
}


@Test(description="Verify deletion of software library with SCP,SFTP and FTP protocol",priority=4)
public void DeleteLibrarySCP() throws Exception
{
	action.driver.navigate().refresh();
	action.ClickLink(locator.getProperty("Solution_Deployment_Manager"));
	action.WaitForTitle(locator.getProperty("Solution_Deployment_Manager"));
	action.ClickLink(locator.getProperty("Software_Library_Management"));
	action.WaitForTitle(locator.getProperty("Software_Library"));
	action.VerifyTitle(locator.getProperty("Software_Library"));
	action.SwithchFrame("iframe0");
	//action.ClickButton(locator.getProperty("Lib.delete.first"));
	 List<WebElement> rows = action.driver.findElements(By.xpath(locator.getProperty("Lib.tab")));
		int numberofrows = rows.size();
		System.out.println(numberofrows);
		int flag=0;
		boolean b1;
			for(int i=2;i<=(numberofrows+2);i++)
			{
				String str11=action.driver.findElement(By.xpath(locator.getProperty("Lib.Colname")+"["+i+"]/td[2]")).getText();
				System.out.println(str11);
				 b1= str11.equalsIgnoreCase(input.getProperty("FName"));
				if(b1)
				{
					action.driver.findElement(By.xpath(locator.getProperty("Lib.item")+(i-2)+"']")).click();
					flag=1;
					break;
				}
				
			}	
			
			if(flag==0){
				Assert.assertTrue(false);
			}
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Lib.Delete"))));
		action.ClickButton(locator.getProperty("Lib.Delete"));
		action.WaitForTitle(locator.getProperty("Software_Library"));
		action.DoubleClickButton(locator.getProperty("Lib.Delete.Now"));
		action.WaitToClick(locator.getProperty("Lib.Refresh"));
		action.driver.switchTo().defaultContent();
		action.ClickLink(locator.getProperty("Software_Library_Management"));
		action.WaitForTitle(locator.getProperty("Software_Library"));
		action.SwithchFrame("iframe0");
		action.DoubleClickButton(locator.getProperty("Lib.Refresh"));
		Thread.sleep(2000);
		setup.VerifydeleteLibrary(action, input.getProperty("FName"));
		Thread.sleep(1000);
		
		
		

}

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}

}
