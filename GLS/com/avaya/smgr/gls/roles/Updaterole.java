package com.avaya.smgr.gls.roles;
/*
 * Test Case related to Roles UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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


public class Updaterole{
	boolean b=true,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	private static final String IDEN_Roles_Grid = "//*[@class='jstree-closed']";
	private static final String IDEN_Roles_Sysadmin = ".//*[@id='node_System_white-space_Administrator']/ins";

@BeforeClass(alwaysRun=true)
public void BeforeClasssetup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	
 }
@BeforeMethod(alwaysRun=true)
	public void setup() throws IOException, InterruptedException{
		
   }

@Test(description="Verify that role is update successfully",priority=1,groups={"Sanity"})
public void GLSFUpdate() throws Exception{
	action.RefreshPage();
	int flag = 0;
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Select System administrators Role
	action.ClickButton(locator.getProperty("Roles.SysIcon"));
	//Select the 
	List<WebElement> totalRows = action.driver.findElements(By.xpath(IDEN_Roles_Grid));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
    	
			

	}
	
	action.ClickButton(locator.getProperty("Role.Edit"));
	action.WaitForObj(locator.getProperty("Role.Edit.Name"));
	action.ClearText(locator.getProperty("Role.Edit.Name"));
	action.entertext(locator.getProperty("Role.Edit.Name"), input.getProperty("Uname"));
	action.ClickButton(locator.getProperty("Role.Edit.Commit"));
	action.WaitForObj(locator.getProperty("Roles.New"));
	List<WebElement> totalRows1 = action.driver.findElements(By.xpath(IDEN_Roles_Grid));
	for (int i=1;i<=totalRows1.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a")).getText();
    	b=sValue.contains(input.getProperty("Uname"));
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
@Test(description="Verify that role deleted successfully",priority=2,groups={"Sanity"})
public void GLSGUserDelete() throws Exception{
	action.RefreshPage();
	int flag=0;
	String RoleName=input.getProperty("Uname");
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Select System administrators Role
	action.ClickButton(locator.getProperty("Roles.SysIcon"));
	//Select the 
	List<WebElement> totalRows = action.driver.findElements(By.xpath(IDEN_Roles_Grid));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a")).getText();
    	if(sValue.contains(RoleName)){
   			WebElement sRowValue= action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
    	

	}
	
	action.ClickButton(locator.getProperty("Roles.Delete"));
	action.WaitForObj(locator.getProperty("Roles.Delete.Cnf"));
	action.ClickButton(locator.getProperty("Roles.Delete.Cnf"));
	action.WaitForObj(locator.getProperty("Roles.SysIcon"));
	action.ClickButton(locator.getProperty("Roles.SysIcon"));
	action.WaitForObj(locator.getProperty("Roles.SysIcon"));
	List<WebElement> totalRows1 = action.driver.findElements(By.xpath(IDEN_Roles_Grid));
	for (int i=1;i<=totalRows1.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[2]/td[1]/div/div[2]/ul/li/ul/li/ul/li["+i+"]/a")).getText();
    	b=sValue.contains(input.getProperty("Uname"));
    	if(b){
    		Assert.assertFalse(b);
			Thread.sleep(1000);
			flag=1;
			break;

    }
    	
		//Assert.assertFalse(b);
		//Thread.sleep(1000);
	}	
	if(flag==0){
		Assert.assertFalse(b);
		Thread.sleep(1000);

	}
}


	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	}
	@AfterClass(alwaysRun=true)
	public void close() throws IOException, InterruptedException{
		action.logout(action);
	
	}

}

