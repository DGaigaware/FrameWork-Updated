package com.avaya.smgr.Tenant4.dashboard;
/*
 * Test Case related to Roles UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Dashboard{
	boolean b=true,match=false;;
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
	action.login(input.getProperty("Tenant.Login"),input.getProperty("Password"),action);
   }
	@Test(description="Verify that Tenant admin log is displaying in Dash board",priority=1,groups={"Sanity"})
	public void DashBoardLogon() throws Exception{
		
		action.RefreshPage();
		action.VerifyElementValue(locator.getProperty("LoginUser"), input.getProperty("Tenant.Login"));
		
	}
	@Test(description="Verify that Tenant dash board is displaying correctly")
	public void DashboardElements() throws Exception{
		
		action.RefreshPage();
		action.VerifyElementDisplay(locator.getProperty("Tenant"));
		action.VerifyElementDisplay(locator.getProperty("Level1"));
		action.VerifyElementDisplay(locator.getProperty("Level2"));
		action.VerifyElementDisplay(locator.getProperty("Level3"));
		action.VerifyElementDisplay(locator.getProperty("Graph"));
		action.VerifyElementDisplay(locator.getProperty("Navigation"));
		Thread.sleep(2000);
		
	}
	
	@Test(description="Verify that Tenant information is displaying correctly")
	public void DashboardTenant() throws Exception{
		action.RefreshPage();
		//Verify that Tenant check box is checked by default
		//action.SelectCheckBox(locator.getProperty("Tenant"));
		//Verify Level1 elements
		String site1=input.getProperty("UpdateSite")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site1"), site1);
		String site2=input.getProperty("site2")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site2"), site2);
		//Verify department
		String dept1=input.getProperty("UpdateDept")+" "+"("+input.getProperty("UpdateSite")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept1"), dept1);
		String dept2=input.getProperty("Dept2")+" "+"("+input.getProperty("site2")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept2"), dept2);
		//Verify Team
		String team1=input.getProperty("UpdatTeam")+" "+"("+input.getProperty("UpdateDept")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team1"), team1);
		String team2=input.getProperty("team2")+" "+"("+input.getProperty("Dept2")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team2"), team2);
		Thread.sleep(1000);
		
	}
	@Test(description="Verify that Selected Site1 information is displaying correctly")
	public void DashboardSite1() throws Exception{
		action.RefreshPage();
		//Verify that Tenant check box is checked by default
		action.ClickButton(locator.getProperty("Level.Site1"));
		//Verify Level1 elements
		String site1=input.getProperty("UpdateSite")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site1"), site1);
		//Verify department
		String dept1=input.getProperty("UpdateDept")+" "+"("+input.getProperty("UpdateSite")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept1"), dept1);
		//Verify Team
		String team1=input.getProperty("UpdatTeam")+" "+"("+input.getProperty("UpdateDept")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team1"), team1);
	}
	
	@Test(description="Verify that Selected Site2 information is displaying correctly")
	public void DashboardSite2() throws Exception{
		action.RefreshPage();
		String site2=input.getProperty("site2")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site2"), site2);
		action.SelectCheckBox(locator.getProperty("Tenantsitechk2"));
		//Verify department
		String dept2=input.getProperty("Dept2")+" "+"("+input.getProperty("site2")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept2"), dept2);
		//Verify Team
		String team2=input.getProperty("team2")+" "+"("+input.getProperty("Dept2")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team2"), team2);
		Thread.sleep(1000);
		
	}
	@Test(description="Verify that Selected Dept1 information is displaying correctly")
	public void DashboardDept1() throws Exception{
		action.RefreshPage();
		//Verify that Tenant check box is checked by default
		action.ClickButton(locator.getProperty("Level.Site1"));
		action.ClickButton(locator.getProperty("Leve2.Dept1"));
		//Verify Level1 elements
		String site1=input.getProperty("UpdateSite")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site1"), site1);
		//Verify department
		String dept1=input.getProperty("UpdateDept")+" "+"("+input.getProperty("UpdateSite")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept1"), dept1);
		//Verify Team
		String team1=input.getProperty("UpdatTeam")+" "+"("+input.getProperty("UpdateDept")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team1"), team1);
	}
	@Test(description="Verify that Selected Dept2 information is displaying correctly")
	public void DashboardDept2() throws Exception{
		action.RefreshPage();
		//Verify that Tenant check box is checked by default
		action.ClickButton(locator.getProperty("Level.Site2"));
		action.ClickButton(locator.getProperty("Leve2.Dept2"));
		//Verify Level1 elements
		String site2=input.getProperty("site2")+" "+"("+input.getProperty("UpdateTname")+")";
		action.VerifyElementValue(locator.getProperty("Level.Site2"), site2);
		//Verify department
		String dept2=input.getProperty("Dept2")+" "+"("+input.getProperty("site2")+")";
		action.VerifyElementValue(locator.getProperty("Leve2.Dept2"), dept2);
		//Verify Team
		String team2=input.getProperty("team2")+" "+"("+input.getProperty("Dept2")+")";
		action.VerifyElementValue(locator.getProperty("Level3.Team2"), team2);
		Thread.sleep(1000);
		
	}
	@Test(description="Verify that Selected Site1 information is displaying correctly")
	public void UPMSite1() throws Exception{
		action.RefreshPage();
		//Select the Site1
		action.ClickButton(locator.getProperty("Level.Site1"));
		Thread.sleep(1000);
		//Click on the User Management Link
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");

		//Verify Level1 is checked by default
		action.VerifydeSelectcheckbox(locator.getProperty("Tenantchk1"));

	}
	//not working
	@Test(description="Verify that Selected Site2 information is displaying correctly")
	public void UPMSite2() throws Exception{
		action.RefreshPage();
		//Select the Site2
		action.ClickButton(locator.getProperty("Level.Site2"));
		//Verify Level1 elements
		Thread.sleep(1000);
		//Click on the User Management Link
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");

		//Verify Level2 is checked by default
		action.VerifydeSelectcheckbox(locator.getProperty("Tenantchk2"));
		
	}
	
	
	@AfterClass(alwaysRun=true)
	
	public void Screenshots1() throws IOException, InterruptedException{
		  
		action.logout(action);
		}

}
