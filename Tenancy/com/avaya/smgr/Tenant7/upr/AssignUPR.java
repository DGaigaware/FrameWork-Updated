package com.avaya.smgr.Tenant7.upr;

import java.io.FileInputStream;
import com.avaya.smgr.Tsetup.Tenantsetup;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignUPR {
	boolean b=false,match=false;;
	Tenantsetup usetup=null;
	UserAction action =null;
	Properties locator=null;
	com.avaya.smgr.Tenant1.setup.TenantSetUp setup= null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	String Site="html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div[2]/div[2]/div[2]/ul/li[2]/ul/li/a";
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	
   }
@BeforeMethod(alwaysRun=true)
public void setup1() throws IOException, InterruptedException{
	setup = new com.avaya.smgr.Tenant1.setup.TenantSetUp();
	usetup=new com.avaya.smgr.Tsetup.Tenantsetup();
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }	
@Test(description="Create UPR with CM and SM",priority=1)
public void createUpr() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
	action.VerifyTitle(locator.getProperty("New_User_Provisioning_Rule"));
	//Fill up the required fields of Upr
	Thread.sleep(3000);
	//enter upr name
	action.entertext(locator.getProperty("Uprname"),input.getProperty("UPR.Name"));
	Thread.sleep(1000);
	//Select SIP
	action.SelectFromdropDown_Index(locator.getProperty("UPR.SIP"), 1);
	Thread.sleep(3000);
	//Communication password
	action.entertext(locator.getProperty("Upr.comprofilepass"),input.getProperty("Password"));
	action.entertext(locator.getProperty("Upr.confirmpass"),input.getProperty("Password"));
	WebElement Membership_Tab=action.driver.findElement(By.linkText(locator.getProperty("Communication_Profile")));
	Membership_Tab.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);

	Membership_Tab.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
	action.ClickButton(locator.getProperty("UPR.sessionMgr"));
	Thread.sleep(1000);
	action.SelectFromdropDown_Index(locator.getProperty("Upr.smlist"), 1);
	Thread.sleep(3000);

	action.SelectFromdropDown_Index(locator.getProperty("Upr.smlisthl"),1);
	Thread.sleep(3000);

	action.ClickButton(locator.getProperty("Users.SMCheckBox"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("Upr.exttypecheckbox"));
	Thread.sleep(1000);

	action.SelectFromdropDown_Index(locator.getProperty("Upr.cmlist"),1);
	Thread.sleep(5000);
	action.SelectFromdropDown_Index(locator.getProperty("Upr.templatelist"),2);
	Thread.sleep(5000);

	//Click on Commit Button and Verify the title of the page
	WebElement Commit=action.driver.findElement(By.xpath(locator.getProperty("Commit")));
	Commit.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(1000);

	Commit.sendKeys(org.openqa.selenium.Keys.ENTER);
	//action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	action.VerifyTitle(locator.getProperty("User_Provisioning_Rules"));
	usetup.VerifyUprname(action,input.getProperty("UPR.Name"));
}
/*
@Test(description="UnAssign the Elements, if already there",priority=2,enabled=false)
public void UnAssignElements() throws Exception
{
	action.driver.navigate().refresh();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateSite"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.ClickButton(locator.getProperty("Tenantedit"));
    Thread.sleep(1000);
    //Select the Upr From table
	WebElement Elements=action.driver.findElement(By.linkText(locator.getProperty("Elements")));
	Elements.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	Elements.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
	
    setup.UnAssignCMElement(action,locator.getProperty("CM"));
	Thread.sleep(3000);
	WebElement User_Provisioning_Rule=action.driver.findElement(By.linkText(locator.getProperty("User_Provisioning_Rule")));
	User_Provisioning_Rule.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	User_Provisioning_Rule.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
	//action.ClickLink(locator.getProperty("User_Provisioning_Rule"));

	//Elements.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	//Elements.sendKeys(org.openqa.selenium.Keys.ENTER);
	//Thread.sleep(3000);

	//setup.UnAssignCMElement(action,locator.getProperty("Session_Manager"));
	//Thread.sleep(3000);
	
	WebElement Commit=action.driver.findElement(By.xpath(locator.getProperty("Commit")));
    Commit.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	Commit.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
    
}
	*/
@Test(description="Assign the UPR in the Tenant Site",priority=3,dependsOnMethods="createUpr")
public void AssignUprSetup() throws Exception
{
	action.RefreshPage();
	//Click on tenant Management Link
	action.ClickLink(locator.getProperty("Tenant_Management"));
	action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
	action.SwithchFrame("iframe0");
	setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
	setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"));
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.ClickButton(locator.getProperty("Tenantedit"));
    Thread.sleep(1000);
    //Select the Upr From table
    setup.SelectUPR(action,input.getProperty("UPR.Name"));
	Thread.sleep(3000);
	WebElement Elements=action.driver.findElement(By.linkText(locator.getProperty("Elements")));
	Elements.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	Elements.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
	
    setup.SelectCMElement(action,locator.getProperty("Communication_Manager"));
	action.entertext(locator.getProperty("Tenant.cm.Number"),input.getProperty("SingleDigitValue"));
	action.entertext(locator.getProperty("Tenent.cm.loc"),input.getProperty("SingleDigitValue"));
	setup.SelectSMElement(action,locator.getProperty("Session_Manager"));
	Thread.sleep(3000);
	//action.entertext(locator.getProperty("Tenant.cm.Number"),input.getProperty("SingleDigitValue"));
	//action.entertext(locator.getProperty("Tenent.cm.loc"),input.getProperty("SingleDigitValue"));
   //Click on Commit button
    WebElement Commit=action.driver.findElement(By.xpath(locator.getProperty("Commit")));
    Commit.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(3000);
	Commit.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(3000);
   // action.ClickButton(locator.getProperty("Commit"));
    Thread.sleep(3000);
    
    
}





@Test(description="Verify that role UPR is attached successfully",priority=4,dependsOnMethods="AssignUprSetup")
public void AssignUpr() throws Exception
{
		action.RefreshPage();
		Thread.sleep(1000);
		action.RefreshPage();
		Thread.sleep(1000);
		action.RefreshPage();

		//Click on tenant Management Link
		action.ClickLink(locator.getProperty("Tenant_Management"));
		action.WaitForTitle(locator.getProperty("Multi-Tenancy_Management"));
		action.VerifyTitle(locator.getProperty("Multi-Tenancy_Management"));
		action.SwithchFrame("iframe0");
		setup.SelectTenantTree(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"));
		setup.SelectSite(action,locator.getProperty("Tenant.Total"),input.getProperty("UpdateTname"),input.getProperty("UpdateSite"));
	    
		action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
		int flag=0;
		List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr"));
		for (int i=2;i<=totalRows.size();i++){
	    	String sValue = null;
	    	sValue = action.driver.findElement(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
	    	b=sValue.contains(input.getProperty("UPR.Name"));
	    	if(b){
	    		Assert.assertTrue(b);
				Thread.sleep(1000);
				flag=1;
				break;

	    }
	    	
		}
		if(flag==0){
			Assert.assertTrue(b);
		}
		
}
@Test(description="Verify the UPR name is showing under dropdown",priority=5,dependsOnMethods="AssignUpr")
public void UPRDrpdown() throws Exception
{
	action.RefreshPage();
	Thread.sleep(1000);
	action.RefreshPage();
	Thread.sleep(1000);
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(5000);
	Verifydropdownvalue(locator.getProperty("Users.Uprdropdown"),input.getProperty("UPR.Name"));
	Thread.sleep(2000);
 }
@Test(description="Verify the Elements are showing when selected UPR",priority=5,dependsOnMethods="AssignUpr")
public void UPRCMSM() throws Exception
{
	action.RefreshPage();
	Thread.sleep(1000);
	action.RefreshPage();
	Thread.sleep(1000);
	action.RefreshPage();

	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	action.VerifyTitle(locator.getProperty("New_User_Profile"));
	action.SelectFromdropDown(locator.getProperty("Users.tenantlist"),input.getProperty("UpdateTname"));
	Thread.sleep(2000);
	action.SelectFromdropDown(locator.getProperty("Users.sitelist"),input.getProperty("UpdateSite"));
	Thread.sleep(5000);
	action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"),input.getProperty("UPR.Name"));
	Thread.sleep(2000);
	action.ClickButton(locator.getProperty("Users.Uprchangeok"));
	Thread.sleep(2000);
	WebElement Membership_Tab=action.driver.findElement(By.linkText(locator.getProperty("Communication_Profile")));
	Membership_Tab.sendKeys(org.openqa.selenium.Keys.CONTROL);
	Thread.sleep(5000);

	Membership_Tab.sendKeys(org.openqa.selenium.Keys.ENTER);
	Thread.sleep(5000);
	action.VerifyEnableCheckbox(locator.getProperty("Users.SMCheckBox"));
	action.VerifyEnableCheckbox(locator.getProperty("Upr.checkbox2"));
	Thread.sleep(2000);

 }
public void Verifydropdownvalue(String locator, String Expected) throws InterruptedException {
	boolean match=false;
	 WebElement dropdown = action.driver.findElement(By.xpath(locator));  
	 Select select = new Select(dropdown);  

	 List<WebElement> options = select.getOptions();  
	 for(int i = 0; i < options.size(); i++)
	 {
		 String actual=options.get(i).getText();
		 if(actual.equals(Expected)){
			 Assert.assertEquals(actual, Expected);
	    match=true;
	    break;
		 }
	 }
	 Assert.assertTrue(match);


}
	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{

		action.Screenshot(result, action);
		action.logout(action);

	}

	@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException
	{
	}

}
