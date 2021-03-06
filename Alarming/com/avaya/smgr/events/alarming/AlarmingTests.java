package com.avaya.smgr.events.alarming;
/*
 * Test Case related to AlarmingTest UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AlarmingTests{
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	int total=0;
	static int Beforetotal=0;
	static String Alarm_Total;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	
   }
@BeforeMethod(alwaysRun=true)
public void Beforemethodsetup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
}

@Test(description="Verification of title Alarming is showing correctly")
public void AlarmTitle() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Alarming"));
	action.SwithchFrame("iframe0");
	//Verify the button states correctly(Enabled for View and Change status and Disabled for More status)
	action.VerifyEnableButton(locator.getProperty("Alarm.MoreActions"));
	action.VerifyDisableButton(locator.getProperty("Alarms.ChgStatus"));
	action.VerifyDisableButton(locator.getProperty("RTS.View"));
	String Alarm_Total=(action.driver.findElement(By.xpath(locator.getProperty("Alarms.total"))).getText()).substring(0, 2);
	total=Integer.valueOf(Alarm_Total.trim());
	System.out.println(Integer.valueOf(total));
	Thread.sleep(1000);

}


@Test(description="Verify that the Test Alarm is generated successfully",priority=1,groups={"Sanity"})
public void AlarmGenerate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, Manage Servicability agent,Servicability agents
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Serviceability_Agents"));
	action.ClickLink(locator.getProperty("Serviceability_Agents"));
	action.SwithchFrame("iframe0");
	String URL=action.driver.getCurrentUrl();
	String tr="/tr",td="/td";
//Select the URl for test alarm generate
	List<WebElement> totalRows = action.driver.findElements(By.xpath(locator.getProperty("Table.Row")));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(locator.getProperty("Table.Row")+"["+i+"]"+td+"[2]")).getText();
		if(URL.contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(locator.getProperty("Table1")+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	//Click on the Generate Test Alarm
	action.DoubleClickButton(locator.getProperty("Alarm.Generate"));
	Thread.sleep(2000);
 }
@Test(description="Verification of test Alarms is showing correctly",priority=2,groups={"Sanity"})
public void AlarmVerify() throws Exception{
	action.RefreshPage();
	int j=0;
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	String Alarm_Total=(action.driver.findElement(By.xpath(locator.getProperty("Alarms.total"))).getText()).substring(0, 2);
	int After_total=Integer.parseInt(Alarm_Total.trim());
	int Before_total=total+1;
	    while((After_total)!=(Before_total)&&j<1200000){
			action.ClickButton(locator.getProperty("Alarms.Refresh"));
			Thread.sleep(2000);
			Alarm_Total=(action.driver.findElement(By.xpath(locator.getProperty("Alarms.total"))).getText()).substring(0, 2);
			After_total=Integer.parseInt(Alarm_Total.trim());

			j++;
		}
	    action.ClickButton(locator.getProperty("Alarms.Refresh"));
		Thread.sleep(2000);
	    List<WebElement> totalRows = action.driver.findElements(By.xpath(locator.getProperty("Alarm.Table.Row")));
		int flag=0;
			int numberofrows = totalRows.size();
				for(int i=2;i<=numberofrows+1;i++)
				{
	
					String str1=action.driver.findElement(By.xpath(locator.getProperty("Alarm.Table.Row")+"["+i+"]/td[7]")).getText();
					boolean b= str1.equalsIgnoreCase("test alarm");
					if(b)
					{
						Assert.assertTrue(b);
						flag=1;
						break;
					}
					if(flag==0){
						Assert.assertTrue(b);
					}
				}
				

	}




@Test(description="Verification of title Alarms is View correctly",priority=3,groups={"Sanity"})
public void AlarmView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	//Verify the button states correctly(Enabled for View and Change status and Disabled for More status)
	action.ClickButton(locator.getProperty("Alarm.First"));
	action.WaitToClick(locator.getProperty("RTS.View"));
	action.ClickButton(locator.getProperty("RTS.View"));
	action.WaitForTitle("Alarm View");
	//verify view page
	action.VerifyTitle("Alarm View");
	Thread.sleep(1000);
}

@Test(description="Verification of title Alarm Status is changed from Raised to Acknowledge correctly",priority=4)
public void AlarmAcknowledge() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	//Verify the button states correctly(Enabled for View and Change status and Disabled for More status)
	action.ClickButton(locator.getProperty("Alarm.First"));
	action.WaitToClick(locator.getProperty("Alarm.dropdown"));
	action.ClickButton(locator.getProperty("Alarm.dropdown"));
	action.WaitToClick(locator.getProperty("alarm.Ack"));
	action.ClickButton(locator.getProperty("alarm.Ack"));
	List<WebElement> rows = action.driver.findElements(By.xpath(locator.getProperty("Alarm.Table.Row")));
	
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(locator.getProperty("Alarm.Table.Row")+"["+i+"]/td[4]")).getText();
			boolean b= str1.equalsIgnoreCase("Acknowledged");
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
}	
@Test(description="Verification of title Alarm Status is changed from Acknowledge to cleared correctly",priority=5,groups={"Sanity"})
public void AlarmCleared() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	//Verify the button states correctly(Enabled for View and Change status and Disabled for More status)
	action.ClickButton(locator.getProperty("Alarm.First"));
	action.WaitToClick(locator.getProperty("Alarm.dropdown"));
	action.ClickButton(locator.getProperty("Alarm.dropdown"));
	action.WaitToClick(locator.getProperty("alarm.Cleard"));
	action.ClickButton(locator.getProperty("alarm.Cleard"));
	List<WebElement> rows = action.driver.findElements(By.xpath(locator.getProperty("Alarm.Table.Row")));
	
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(locator.getProperty("Alarm.Table.Row")+"["+i+"]/td[4]")).getText();

			boolean b= str1.equalsIgnoreCase("Cleared");
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
}	

@Test(description="Verification selected alarm is deleted successfully",priority=6,groups={"Sanity"})
public void AlarmDeleted() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Events
	action.ClickLink(locator.getProperty("Events"));
	action.ClickLink(locator.getProperty("Alarms"));
	action.SwithchFrame("iframe0");
	//Verify the button states correctly(Enabled for View and Change status and Disabled for More status)
	action.ClickButton(locator.getProperty("Alarm.First"));
	Thread.sleep(500);
	action.WaitToClick(locator.getProperty("RTS.View"));
	WebElement element=action.driver.findElement(By.xpath(locator.getProperty("alarm.more")));
	element.sendKeys(Keys.RETURN);
	action.WaitForObj(locator.getProperty("alarm.delete"));	
	action.WaitToClick(locator.getProperty("alarm.delete"));
	Thread.sleep(500);
	action.ClickButton(locator.getProperty("alarm.delete"));
	 action.driver.switchTo().alert();
     action.driver.switchTo().alert().accept();
 	action.SwithchFrame("iframe0");
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), "test alarm");
	Thread.sleep(1000);

}	

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);

}

@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		//action.logout(action);
	}
 }

