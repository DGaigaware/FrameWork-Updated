package com.avaya.smgr.weblm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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


public class Basic {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeMethod
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}



@Test(description="verify the home page of Licenses Link",priority=1)
public void verify_Page() throws Exception
{
	//action.driver.manage().deleteAllCookies();
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	    
	action.ClickLinkByxpath(locator.getProperty("WebLM_Home"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("Exportbtn"))));
	//action.VerifyEnableButton(locator.getProperty("Exportbtn"));
	//action.ClickButton(locator.getProperty("Exportbtn"));
	//Thread.sleep(1000);
	//action.VerifyStringValue("Licenses exported successfully");
}

@Test(description="Verify error message on uploading wrong License file",priority=2)
public void Verify_Intstall() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	action.ClickLinkByxpath(locator.getProperty("Install_license"));
	action.driver.findElement(By.xpath(".//*[@id='loginFormId']/table/tbody/tr/td[2]/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(System.getProperty("user.dir") + "\\Third Party\\imprt\\Import_Soft_Delete.xml");
	Thread.sleep(2000);
	action.SelectRadioButton(locator.getProperty("Acceptradiobtn"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Installbtn"));
	Thread.sleep(2000);
	action.VerifyStringValue("Error occurred while parsing file.");
	
}

@Test(description="Verify the Uninstal the license and verify error message",priority=3)
public void Verify_Uninstall() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("Uninstall"));
	Thread.sleep(1000);
	action.VerifyDisableButton(locator.getProperty("Uninstallbtn"));
	
}

@Test(description="Verify the number of license id generated successfully",priority=4)
public void Verify_Licensing_ID() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
		
	action.ClickLinkByxpath(locator.getProperty("ServerPropwithoutexpnd"));
	
	action.WaitForTitle(locator.getProperty("Server_Properties"));
	action.VerifyTitle(locator.getProperty("Server_Properties"));
	Thread.sleep(2000);
	action.ClickLinkByxpath(locator.getProperty("Centrallogid"));
	action.ClearText(locator.getProperty("Noofidtext"));
	action.entertext(locator.getProperty("Noofidtext"),"5");
	action.ClickButton(locator.getProperty("Generatebtn"));
	Thread.sleep(2000);
	for(int i=2;i<7;i++)
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='generatedIds']/table[2]/tbody/tr["+i+"]/td")).getText();
		Assert.assertEquals(str, "0000"+(i-1));
	}
	
}



@Test(description="Verify the View by feature page",priority=5,enabled=false)
public void Verify_View_Feature() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.VerifyStringValue("License installed on:");
	action.ClickLinkByxpath(locator.getProperty("Viewbyfeature"));
	Thread.sleep(2000);
	action.VerifyStringValue("View by Feature");
	
}

@Test(description="Verify the View by local weblm page",priority=6,enabled=false)
public void VerifyViewlocalweblm() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Viewbylocalweblm"));
	Thread.sleep(1000);
	action.VerifyStringValue("View by local WebLM");

}

@Test(description="Verify the Enterprise configuration page",priority=7,enabled=false)
public void VerifyEnterpriseConf() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Enterpriseconf"));
	Thread.sleep(1000);
	action.VerifyStringValue("Enterprise Configuration");
	
}

@Test(description="Verify Local configuration page ",priority=8,enabled=false)
public void VerifyLocalConf() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Localwebconf"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("Validateconnbtntop"));
	action.VerifyEnableButton(locator.getProperty("Validateconnbtnbottom"));
	action.VerifydeSelectcheckbox(locator.getProperty("SelectallWeblm"));
	action.ClickLinkByxpath(locator.getProperty("Checkall"));
	Thread.sleep(1000);
	action.VerifySelectcheckbox(locator.getProperty("SelectallWeblm"));
	action.ClickLinkByxpath(locator.getProperty("Clearall"));
	Thread.sleep(1000);
	action.VerifydeSelectcheckbox(locator.getProperty("SelectallWeblm"));
	
}


@Test(description="Verify the Add local weblm page",priority=9,enabled=false)
public void Addlocalweblm() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Localwebconf"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Addlocalweblm"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("Confandvalidatebtn"));
	action.VerifyEnableButton(locator.getProperty("Backbtn"));
	action.ClickButton(locator.getProperty("Confandvalidatebtn"));
	String str=action.driver.switchTo().alert().getText();
	Assert.assertEquals(str, "Please enter a value in the Name field.");
    action.driver.switchTo().alert().accept();
    action.entertext(locator.getProperty("Lib.Name"), "akp");
    action.ClickButton(locator.getProperty("Confandvalidatebtn"));
	String str1=action.driver.switchTo().alert().getText();
	Assert.assertEquals(str1, "Please enter a value in the IP address field.");
    action.driver.switchTo().alert().accept();
    action.EntertextUsingKey(locator.getProperty("IP1"), "148", Keys.TAB);
    action.EntertextUsingKey(locator.getProperty("IP2"), "147", Keys.TAB);
    action.EntertextUsingKey(locator.getProperty("IP3"), "168", Keys.TAB);
    action.EntertextUsingKey(locator.getProperty("IP4"), "5", Keys.TAB);
    action.entertext(locator.getProperty("Portnumber"), "52233");
    action.ClickButton(locator.getProperty("Confandvalidatebtn"));
    Thread.sleep(4000);
    action.VerifyStringValue("Local WebLM server did not respond back within the timeout period.");
	
}


@Test(description="Verify the delete local weblm page",priority=10,enabled=false)
public void Deletelocalweblm() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Localwebconf"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Deletelocalweblm"));
	action.VerifyEnableButton(locator.getProperty("Deleteweblmbtn"));
	action.VerifyEnableButton(locator.getProperty("Resetweblmbtn"));
	action.SelectCheckBox(locator.getProperty("Deletechk1"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Deleteweblmbtn"));
	Thread.sleep(1000);
	Alert alert=action.driver.switchTo().alert();
	alert.dismiss();
	
}


@Test(description="Verify the modify local weblm page ",priority=11,enabled=false)
public void Modifylocalweblm() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Localwebconf"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Modifyweblm"));
	action.VerifyEnableButton(locator.getProperty("Deleteweblmbtn"));
	action.VerifyEnableButton(locator.getProperty("Resetweblmbtn"));
	action.SelectCheckBox(locator.getProperty("Deletechk1"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Deleteweblmbtn"));
	Thread.sleep(2000);
	action.ClearText(locator.getProperty("Lib.Name"));
	action.entertext(locator.getProperty("Lib.Name"), "asps");
	action.ClickButton(locator.getProperty("ModifyBtn"));
	action.VerifyStringValue("System configuration updated.");
	
}

@Test(description="Verify the Usages links and corrosponding pages",priority=12,enabled=false)
public void VerifyUsage() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	Thread.sleep(2000);
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Usage"));
	action.VerifyStringValue("Usage Summary");
	Thread.sleep(2000);
	action.ClickLinkByxpath(locator.getProperty("Usagebylocalweblm"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("QuerySystembtn"));
	action.VerifyEnableButton(locator.getProperty("QuerySystembtnbttom"));
	action.ClickLinkByxpath(locator.getProperty("Enterpriseusage"));
	Thread.sleep(1000);
	action.VerifyStringValue("Enterprise Usage");
	
	action.ClickLinkByxpath(locator.getProperty("Queryusage"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("QueryUsagebtntop"));
	action.VerifyEnableButton(locator.getProperty("QueryUsagebtnbottom"));
	
	
}

@Test(description="Verify Allocation links and corrosponding pages ",priority=13,enabled=false)
public void VerifyAllocation() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("Allocation"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("AllocByfeature"));
	Thread.sleep(1000);
	action.VerifyStringValue(" View by Feature");
	action.ClickLinkByxpath(locator.getProperty("AllocBylocalweblm"));
	Thread.sleep(1000);
	action.VerifyStringValue("View by Local WebLM");
	action.ClickLinkByxpath(locator.getProperty("ChangeAllocation"));
	Thread.sleep(1000);
	action.VerifyDisableButton(locator.getProperty("Submitallocation"));
	action.VerifyDisableButton(locator.getProperty("Resetallocation"));
}
	

@Test(description="Verify periodic status page",priority=15,enabled=false)
public void Verifyperiodicstatus() throws Exception
{
	
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	
	action.ClickLinkByxpath(locator.getProperty("SampleELF"));
	Thread.sleep(1000);
	action.ClickLinkByxpath(locator.getProperty("PeriodicStatus"));
	Thread.sleep(2000);
	action.VerifyStringValue("Periodic Allocation");
	
}

@Test(priority=5)
public void verifyHelpage() throws Exception
{
	action.RefreshPage();
	action.ClickLink(locator.getProperty("Licenses"));
	action.WaitForTitle(locator.getProperty("Web_License_Manager"));
	action.VerifyTitle(locator.getProperty("Web_License_Manager"));
	action.SwithchFrame("iframe0");
	Thread.sleep(2000);
	String str=action.driver.getWindowHandle();
	action.ClickLinkByxpath(locator.getProperty("Helpweblm"));
	Thread.sleep(1000);
	for (String winHandle : action.driver.getWindowHandles())
	{
	    action.driver.switchTo().window(winHandle); 
	}
	Thread.sleep(2000);
	action.VerifyTitle("Administering Avaya Aura® System Manager");
	//Close help page	
	action.driver.close(); 
	action.driver.switchTo().window(str);
}


@AfterMethod
public void Close(ITestResult result) throws IOException, InterruptedException
{
	action.Screenshot(result, action);
	action.logout(action);
}


}
