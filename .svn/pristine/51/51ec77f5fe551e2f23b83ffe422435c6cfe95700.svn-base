package com.avaya.smgr.spm.settings.smgr;
/*Global SNMP Configuration UI
 */
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class GlobalSNMPConfig{
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
@Test(description="Buttons States in the web page",priority=1)
public void GSMNPBtns() throws Exception{
	//Verification of New is enabled
	//Verification of Update and Delete are Disabled
	action.VerifyEnableButton(locator.getProperty("New"));
	action.VerifyDisableButton(locator.getProperty("Update"));
	action.VerifyDisableButton(locator.getProperty("Delete"));
}
@Test(description="Commit and Cancel  buttons are Enabled",priority=2)
public void GSMNPBtns1() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	//Verification of Commit and Cancel  buttons are Enabled
	action.VerifyEnableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Error message where profile name is empty",priority=3)
public void GSMNPProfileError() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	action.VerifyStringValue("Profile Name is null.");
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Error message where User is empty",priority=4)
public void GSMNPUserError() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	//Click on the Commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message when user is empty
	action.VerifyStringValue(locator.getProperty("User_is_null"));
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Error message where Auth Pass is empty",priority=5)
public void GSMNPAuthPassError() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	//action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
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
	action.ClickButton(locator.getProperty("Cancel"));


}
@Test(description="Error message where Priv and Confirm Priv Password is empty",priority=6)
public void GSMNPPrivError() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	Thread.sleep(1000);
	//Verify error message
	action.VerifyStringValue("Confirm Privacy Password is null.");
	action.VerifyStringValue("Privacy Password is null.");
	action.ClickButton(locator.getProperty("Cancel"));

	}
@Test(description="Error message where Auth Pass and Cnf AuthPass is Missmatch",priority=7)
public void GSMNPAuthPassMissMatchError() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	//	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("Commit"));
	Thread.sleep(1000);
	//Verify error message where  Auth Pass and Cnf AuthPass is Mismatch
	action.VerifyStringValue("Authentication Password and Confirm Authentication password is not matching. - Please enter non null and valid values.");
	action.ClickButton(locator.getProperty("Cancel"));

}
@Test(description="Error message where Priv and Confirm Priv Password is mismatch",priority=8)
public void GSMNPPrivMismatch() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	action.ClearText(locator.getProperty("GSNMP.Profile"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.User"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.ClearText(locator.getProperty("GSNMP.AuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfAuthPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.PrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.PrivPwd"), input.getProperty("Password"));
	action.ClearText(locator.getProperty("GSNMP.CnfPrivPwd"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	action.entertext(locator.getProperty("GSNMP.CnfPrivPwd"), input.getProperty("Uname"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles_Details"));
	Thread.sleep(2000);
	//Verify error message when user is empty
	action.VerifyStringValue("Privacy Password and Confirm Privacy password is not matching.");
	action.ClickButton(locator.getProperty("Cancel"));

	}
@Test(description="Verification of type dropdown values",priority=9)
public void GSMNPTypeDrop() throws Exception{
	action.ClickButton(locator.getProperty("New"));
	//Verify the Drop down values with our expected values
	String[] Expected={"V1","V3"};
	action.VerifydropDownValues(locator.getProperty("GSNMP.Type"),Expected);
	}
@Test(description="Verification of Authentication dropdown values",priority=10)
	public void GSMNPAuthenticationDrop() throws Exception{
	String[] Authentication={"None","MD5","SHA"};
	//Verify the Drop down values with our expected values
	action.VerifydropDownValues(locator.getProperty("GSNMP.Auth"),Authentication);
	Thread.sleep(1000);

	}
	@Test(description="Verification of Privilages dropdown values",priority=11)
	public void GSMNPPrivilagesDrop() throws Exception{
    String[] Privilages={"None","Read/Write","Read"};
	//Verify the Drop down values with our expected values
	action.VerifydropDownValues(locator.getProperty("GSNMP.Priv"),Privilages);
	Thread.sleep(1000);

	}
	@Test(description="Verification of Privacy dropdown values",priority=12)
	public void GSMNPPrivacyDrop() throws Exception{
	String[] Privacy={"AES","DES","None"};
	//Verify the Drop down values with our expected values
	action.VerifydropDownValues(locator.getProperty("GSNMP.Privacy"),Privacy);
	Thread.sleep(1000);
	action.DoubleClickButton(locator.getProperty("Done"));
	}
@Test(description="Global SNMP Profile is added",groups={"Sanity"})
public void GSMNPadded() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Global_SNMP_Configuration"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	action.ClickButton(locator.getProperty("New"));
	action.entertext(locator.getProperty("GSNMP.Profile"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("GSNMP.User"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("GSNMP.AuthPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.CnfAuthPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.PrivPwd"), input.getProperty("Password"));
	action.entertext(locator.getProperty("GSNMP.CnfPrivPwd"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	//Verify Global SNMP Profile is added
	Verify_Add_Secondcolumn(input.getProperty("Uname"));
	Thread.sleep(1000);
	}
@Test(description="Global SNMP Profile is updated",dependsOnMethods="GSMNPadded")
public void GSMNPUpdate() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Global_SNMP_Configuration"));
		action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("Uname");
	List<WebElement> totalRows = action.driver.findElements(By.xpath(locator.getProperty("Tablerow")));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(locator.getProperty("Tablerow")+"["+i+"]"+"/td[2]")).getText();
		if(sValue.contains(Actual)){
			WebElement sRowValue= action.driver.findElement(By.xpath(locator.getProperty("Table1")+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	
	action.ClickButton(locator.getProperty("EditBtn_Tab"));	
	Thread.sleep(1000);
	action.VerifyStringValue("Edit Snmp Access Profile :");
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	
}

@Test(description="Global SNMP Profile is Deleted",dependsOnMethods="GSMNPUpdate")
public void GSMNPDelete() throws Exception{
	action.RefreshPage();
	//1)Navigate to Configuration page, setting,Global SNMP Configuration
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Global_SNMP_Configuration"));
	action.WaitForTitle(locator.getProperty("SNMP_Access_Profiles"));
	action.SwithchFrame("iframe0");
	
	//Select first item in the table
	String Actual=input.getProperty("Uname");
	List<WebElement> totalRows = action.driver.findElements(By.xpath(locator.getProperty("Tablerow")));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(locator.getProperty("Tablerow")+"["+i+"]"+"/td[2]")).getText();
		if(sValue.contains(Actual)){
			WebElement sRowValue= action.driver.findElement(By.xpath(locator.getProperty("Table1")+(i-2)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	//Click on the Delete button
	action.ClickButton(locator.getProperty("Delete"));
	//action.WaitForText("SNMP Access Profiles");
	action.WaitForTitle("Snmp Access Profile/s Delete Confirmation.");
	action.VerifyStringValue("Snmp Access Profile/s Delete Confirmation.");
	//Verify the button is enable for Cancel and Delete
	action.VerifyEnableButton(locator.getProperty("Delete_Cnf"));
	action.VerifyEnableButton(locator.getProperty("Cancel_Cnf"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	Thread.sleep(2000);
	//action.WaitForText("SNMP Access Profiles"); 
	 
	 
	//Verify the profile is deleted
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("Uname"));
	//action.Verify_Delete_Secondcolumn(input.getProperty("Uname"));
	Thread.sleep(1000);
}

public void Verify_Add_Secondcolumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	action.driver.findElement(By.id("table_1_enableFiltering")).click();
	WebDriverWait wait = new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("GSNMP.Filter.Name"))));
	action.driver.findElement(By.xpath(locator.getProperty("GSNMP.Filter.Name"))).sendKeys(Columnvalue);
	action.driver.findElement(By.xpath(locator.getProperty("Gls.Filer.Name"))).click();
	Thread.sleep(1000);
	List<WebElement> rows = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=action.driver.findElement(By.xpath(locator.getProperty("Tablerow")+"["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
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
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

 @AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
}
