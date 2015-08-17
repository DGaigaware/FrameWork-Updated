package com.avaya.smgr.Tsetup;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.UserAction;

public class Tenantsetup {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	
	public WebDriver driver;
	String Site="html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div[2]/div[2]/div[2]/ul/li[2]/ul/li/a";

	public void SelectTenant1(UserAction action ,String locator, String ElementName) throws InterruptedException {
	action = new UserAction();
	// TODO Auto-generated method stub
		List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
		//System.out.println("Total Rows"+totalRows.size());

		for (int i=1;i<=totalRows.size();i++){
	    	String sValue = null;
	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
	    	if(sValue.equals(ElementName)){
	   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/a"));
	   			sRowValue.click();
	   			Thread.sleep(3000);
	   			break;
	    	}
		}

	}

public void SelectTenant(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
		List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
		//System.out.println("Total Rows"+totalRows.size());

		for (int i=1;i<=totalRows.size();i++){
	    	String sValue = null;
	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
	    	if(sValue.equals(ElementName)){
	   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/a"));
	   			sRowValue.click();
	   			Thread.sleep(3000);
	   			break;
	    	}
		}

	}



public void SelectSite(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
		List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
		//System.out.println("Total Rows"+totalRows.size());

		for (int i=1;i<=totalRows.size();i++){
	    	String sValue = null;
	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/a")).getText();
	    	if(sValue.equals(ElementName)){
	   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/a"));
	   			sRowValue.click();
	   			Thread.sleep(3000);
	   			break;
	    	}
		}

	}


public void VerifyAddedTenant(UserAction action ,String locator, String tenant) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	b=sValue.equals(tenant);
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
public void VerifyAddedSite(UserAction action ,String locator, String site) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/a")).getText();
    	b=sValue.equals(site);
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

public void VerifyDepartment(UserAction action ,String locator, String Dept) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/a")).getText();
    	b=sValue.equals(Dept);
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
public void VerifyTeam(UserAction action ,String locator, String Dept) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/ul/li/a")).getText();
    	b=sValue.equals(Dept);
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

public void SelectTenantTree(UserAction action, String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.equals(ElementName)){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ins"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}

}

public void SelectSiteTree(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/a")).getText();
    	if(sValue.equals(ElementName)){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ins"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}
}
public void SelectDept(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/a")).getText();
    	if(sValue.equals(ElementName)){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/a"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}
}
public void SelectDeptTree(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/a")).getText();
    	if(sValue.equals(ElementName)){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/ins"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}
}
public void SelectTeam(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/ul/li/a")).getText();
    	if(sValue.equals(ElementName)){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li/ul/li/ul/li/a"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}
}

//Element Functions
public void selectElement(UserAction action,String ElementName) throws InterruptedException
{
	List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr"));
   
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			WebElement sRowValue=action.driver.findElement(By.xpath(".//*[@id='all_elements_table:"+(i-2)+":linkAssignElement']"));
   			sRowValue.click();
   			break;
		}
    }
	
}

public void VerifyElementtobeadded(UserAction action,String ElementName) throws InterruptedException
{
	
 int flag=0;
 List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr"));
    
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			Assert.assertTrue(b);
			Thread.sleep(1000);
			flag=1;
			break;
		}
		
}
    if(flag==0)
    {
    	Assert.assertTrue(b);
    }

}

public void VerifyAddedElement(UserAction action,String ElementName) throws InterruptedException
{
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr"));
    
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			Assert.assertTrue(b);
			Thread.sleep(1000);
			flag=1;
			break;
		}
		
}
    if(flag==0)
    {
    	Assert.assertTrue(b);
    }
}

public void DeselectElement(UserAction action,String ElementName) throws InterruptedException
{
	boolean b=false;
	 List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr"));
   
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		 b= str1.equals(ElementName);
		if(b)
		{
			WebElement sRowValue=action.driver.findElement(By.xpath(".//*[@id='selected_elements:"+(i-2)+":linkRemoveElement']"));
   			sRowValue.click();
   			break;
		}
    }
    if(b==false)
    {
    	Assert.assertTrue(b);
    }
	
}


//UPR Functions

public void selectUpr(UserAction action,String ElementName) throws InterruptedException
{
	Thread.sleep(500);
	 List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr"));
    
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			WebElement sRowValue=action.driver.findElement(By.xpath(".//*[@id='template_table:"+(i-2)+":linkAssignTemplate']"));
   			sRowValue.click();
   			break;
		}
    }
  
}


public void VerifyUprtobeadded(UserAction action,String ElementName) throws InterruptedException
{
	
 int flag=0;
 List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr"));
 
 int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			Assert.assertTrue(b);
			Thread.sleep(1000);
			flag=1;
			break;
		}
		
}
    if(flag==0)
    {
    	Assert.assertTrue(b);
    }

}


public void VerifyAddedUPR(UserAction action,String ElementName) throws InterruptedException
{
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr"));
	 
	 int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		boolean b= str1.equals(ElementName);
		if(b)
		{
			Assert.assertTrue(b);
			Thread.sleep(1000);
			flag=1;
			break;
		}
		
		
}
    if(flag==0)
    {
    	Assert.assertTrue(b);
    }
}

public void DeselectUPR(UserAction action,String ElementName) throws InterruptedException
{
	boolean b=false;
	//WebElement tableType=action.driver.findElement(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody"));
   // List<WebElement>rowElmt=tableType.findElements(By.tagName("tr"));
	List<WebElement> totalRows=action.driver.findElements(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr"));
    int n=totalRows.size();
    for(int i=2;i<=n;i++)
    {
    	String str1=action.driver.findElement(By.xpath(".//*[@id='selected_templates_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		 b= str1.equals(ElementName);
		if(b)
		{
			WebElement sRowValue=action.driver.findElement(By.xpath(".//*[@id='selected_templates:"+(i-2)+":linkRemoveTemplate']"));
   			sRowValue.click();
   			break;
		}
    }                       
    if(b==false)
    {
    	Assert.assertTrue(b);
    }
	
}


public void SelectUPR(UserAction action,String Columnvalue) throws Exception
{
	List<WebElement> rows =action.driver.findElements(By.name("table_1:_s"));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']")).click();
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
}

public void VerifyUprname(UserAction action,String Columnvalue) throws Exception
{
	Thread.sleep(500);
	List<WebElement> rows =action.driver.findElements(By.name("table_1:_s"));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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

public void VerifydeleteUprname(UserAction action,String Columnvalue) throws Exception
{
	List<WebElement> rows =action.driver.findElements(By.name("table_1:_s"));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertFalse(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertFalse(b);
		}
}
 

}