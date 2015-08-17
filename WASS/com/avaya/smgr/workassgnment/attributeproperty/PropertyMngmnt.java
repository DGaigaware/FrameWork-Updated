package com.avaya.smgr.workassgnment.attributeproperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
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
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.Worksetup.WorkSetup;

public class PropertyMngmnt {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	WorkSetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
   	setup=new WorkSetup();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify Property management page elements")
public void Verify_Page() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	//Verify Commit and Cancel buttons are enabled
	action.VerifyEnableButton(locator.getProperty("WAT.Commitbtn"));
	action.VerifyEnableButton(locator.getProperty("WAT.Cancelbtn"));
	//verify the table text boxes are disabled
	action.VerifyDisableTextbox(locator.getProperty("defaultvalue1"));
	action.VerifyDisableTextbox(locator.getProperty("defaultvalue2"));
	action.VerifyDisableTextbox(locator.getProperty("defaultvalue3"));
	//Verify the Radio button not selected
	List<WebElement> rows = action.driver.findElements(By.name("propertySelRadio"));
	int numberofrows = rows.size();
	
		for(int i=1;i<=numberofrows;i++)
		{
			boolean b=action.driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]/input")).isSelected();
			
			if(!b)
			{
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
				break;
			}
		}		
}


@Test(description="Verify error message on editing default values")
public void Verify_Edit_Deployment() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	//Select Deployment radio button
	action.SelectCheckBox(locator.getProperty("DeplomentRbtn"));
	//Edit default value

	action.SelectFromdropDown_Index(locator.getProperty("defaultlist"), 1);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("WAT.Commitbtn"))));
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	//verify the following error message
	action.VerifyStringValue("Cause of failure:Cannot change default for this property now.");

}


@Test(description="Verify error message on editing  default values")
public void Verify_Edit_EAHT() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	//Select EAHT Radio button
	action.SelectCheckBox(locator.getProperty("EAHTRbtn"));
	//Edit default value
	
	action.ClearText(locator.getProperty("defaultvalue1"));
	action.entertext(locator.getProperty("defaultvalue1"), "182");
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("WAT.Commitbtn"))));
	//Click on commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	//Verify error message
	action.VerifyStringValue("Cause of failure:Cannot change default for this property now.");
	
}


@Test(description="Verify error message on editing  default values")
public void Verify_Edit_Multiplicity() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("MultiplicityRbtn"))));
	//Select Multiplicity button
	action.SelectCheckBox(locator.getProperty("MultiplicityRbtn"));
	Thread.sleep(2000);
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("defaultvalue2"))));
	//Edit default value
	action.ClearText(locator.getProperty("defaultvalue2"));
	action.entertext(locator.getProperty("defaultvalue2"), "0");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("WAT.Commitbtn"))));
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	//Verify error message
	action.VerifyStringValue("Error: Edit of Property 'Multiplicity' failed. Cause of failure:Cannot change default for this property now.");
	//Thread.sleep(1000);
	//Edit default value
	//action.ClearText(locator.getProperty("defaultvalue2"));
	//action.entertext(locator.getProperty("defaultvalue2"), "11");
	//Thread.sleep(1000);
	//Click on Commit button
	//action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	//Thread.sleep(2000);
	//Verify error message
	//action.VerifyStringValue("Error: Edit of Property 'Multiplicity' failed. Cause of failure:Cannot change default for this property now.");
	//Edit default value
	//action.ClearText(locator.getProperty("defaultvalue2"));
	///action.entertext(locator.getProperty("defaultvalue2"), "2");
	//Thread.sleep(1000);
	//Click on Commit button
	//action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	//Thread.sleep(2000);
	//Verify Successful message
	//action.VerifyStringValue("Edit of Property 'Multiplicity' successful!");
	
	
}


@Test(description="Verify error message on editing  default values")
public void Verify_Edit_Proficiency() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	action.SelectCheckBox(locator.getProperty("ProficiencyRbtn"));
	Thread.sleep(2000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("WAT.Commitbtn"))));
	//Edit default value
	action.ClearText(locator.getProperty("defaultvalue3"));
	action.entertext(locator.getProperty("defaultvalue3"), "0");
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("WAT.Commitbtn"))));

	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	//Verify Error message
	action.VerifyStringValue("Error: Edit of Property 'Proficiency' failed. Cause of failure:Cannot change default for this property now.");
	//Thread.sleep(1000);
	//Edit default value
	//action.ClearText(locator.getProperty("defaultvalue3"));
	//action.entertext(locator.getProperty("defaultvalue3"), "17");
	//Thread.sleep(1000);
	//Click on Commit button
	//action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	//Thread.sleep(2000);
	//Verify Error message
	//action.VerifyStringValue("Error: Edit of Property 'Proficiency' failed. Cause of failure:Cannot change default for this property now.");
	//action.ClearText(locator.getProperty("defaultvalue3"));
	//action.entertext(locator.getProperty("defaultvalue3"), "8");
	//Thread.sleep(1000);
	
	//Click on Commit button
	//action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	//Thread.sleep(2000);
	//Verify Successful message
	//action.VerifyStringValue("Edit of Property 'Service Excluded' successful! ");
	//Thread.sleep(1000);
	
	
}


@Test(description="Verify error message on editing default values")
public void Verify_Edit_ServiceExcluded() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	//Select Service excluded button
	action.SelectCheckBox(locator.getProperty("ServiceExcludedRbtn"));
	Thread.sleep(1000);
	Thread.sleep(2000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("defaultservicelist"))));
	//Edit default value
	action.SelectFromdropDown_Index(locator.getProperty("defaultservicelist"), 2);
	Thread.sleep(1000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Error: Edit of Property 'Service Excluded' failed. Cause of failure:Cannot change default for this property now.");
	//Thread.sleep(1000);
	//Edit default value
	//action.SelectFromdropDown_Index(locator.getProperty("defaultservicelist"), 0);
	//Thread.sleep(1000);
	//Click on Commit button
	//action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	//Thread.sleep(2000);
	//Verify Successful message
	//action.VerifyStringValue("Edit of Property 'Service Excluded' successful!");
	//Thread.sleep(1000);
	
}

@Test(description="Verify error message on editing default values")
public void Verify_Edit_Hunt_Group() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	//Select Service excluded button
	action.SelectCheckBox(locator.getProperty("HuntGroupRbtn"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("defaultHuntlist"))));
	//Edit default value
	action.ClearText(locator.getProperty("defaultHuntlist"));
	action.entertext(locator.getProperty("defaultHuntlist"), "2");
	Thread.sleep(1000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Error: Edit of Property 'Hunt Group for provider' failed. Cause of failure:Cannot change default for this property now.");
}

@Test(description="Verify the left and right move buttons for selecting categories")
public void Verify_LeftRight_Buttons() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	Thread.sleep(1000);
	action.SelectCheckBox(locator.getProperty("ServiceExcludedRbtn"));
	//Verify Right move button
	action.SelectFromdropDown(locator.getProperty("Aviallist"), "Channel");
	Thread.sleep(1000);
	//action.ClickButton(locator.getProperty("catagoryoption1"));
	action.ClickButton(locator.getProperty("Rightarrow"));
	Thread.sleep(1000);
	//Verify category moved in selected list
    String cat=new Select(action.driver.findElement(By.xpath(locator.getProperty("Selectcatlist")))).getFirstSelectedOption().getText();
   System.out.println(cat);
    boolean b=cat.equals("Channel");
    Assert.assertTrue(b);
    
	String str=action.driver.findElement(By.xpath(locator.getProperty("catagoryoption1"))).getText();
	boolean b1=str.equals("Channel");
	Assert.assertFalse(b1);
	Thread.sleep(1000);
	//Verify Left Move button
	action.ClickButton(locator.getProperty("Leftarrow"));
	Thread.sleep(1000);
	//Verify category moved back to available category
	String cat1=new Select(action.driver.findElement(By.xpath(locator.getProperty("Aviallist")))).getFirstSelectedOption().getText();
	System.out.println(cat1);
    boolean b3=cat1.equals("Channel");
    Assert.assertTrue(b3);
	//action.VerifyElementValue(locator.getProperty("catagoryoption3"), "Channel");
	String str1=action.driver.findElement(By.xpath(locator.getProperty("SelectedCatagories"))).getText();
	boolean b2=str1.equals("Channel");
	Assert.assertFalse(b2);
	
}

@Test(description="Verify the up and down move buttons for selecting categories")
public void Verify_UPDown_Buttons() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	action.SelectCheckBox(locator.getProperty("ServiceExcludedRbtn"));
	//Verify Right move button
	action.ClickButton(locator.getProperty("catagoryoption1"));
	action.ClickButton(locator.getProperty("Rightarrow"));
	Thread.sleep(1000);
	action.ClickButton(locator.getProperty("catagoryoption1"));
	action.ClickButton(locator.getProperty("Rightarrow"));
	Thread.sleep(1000);
	//Verify  Move down button
	action.ClickButton(locator.getProperty("Selectedoption2"));
	action.ClickButton(locator.getProperty("Movedown"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Selectedoption2"), "Channel");
	action.VerifyElementValue(locator.getProperty("Selectedoption1"), "Sport");
	//Verify  Move Up button.
	action.ClickButton(locator.getProperty("Moveup"));
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Selectedoption1"), "Channel");
	action.VerifyElementValue(locator.getProperty("Selectedoption2"), "Sport");
	
}

@Test(description="Verify the Selecton of Categories for multi-valued popup")
public void Add_Catagory() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Property_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Property_Management"));
	action.VerifyTitle(locator.getProperty("Property_Management"));
	action.SwithchFrame("iframe0");
	action.SelectCheckBox(locator.getProperty("ProficiencyRbtn"));
	//Verify Right move button
	action.ClickButton(locator.getProperty("catagoryoption1"));
	action.ClickButton(locator.getProperty("Rightarrow"));
	Thread.sleep(1000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("WAT.Commitbtn"));
	Thread.sleep(1000);
	//Verify Successful message
	action.VerifyStringValue("Edit of Property 'Proficiency' successful!");
	Thread.sleep(1000);
	action.VerifyElementValue(locator.getProperty("Selectedoption1"), "Channel");
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass
public void Close() throws IOException, InterruptedException
{
	action.logout(action);
}

}
