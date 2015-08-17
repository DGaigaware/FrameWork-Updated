package com.avaya.smgr.Tenant1.setup;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.UserAction;

public class TenantSetUp {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	String Site="html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div[2]/div[2]/div[2]/ul/li[2]/ul/li/a";

public void SelectTenant(UserAction action ,String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
		List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
		//System.out.println("Total Rows"+10);

		for (int i=1;i<=10;i++){
	    	String sValue = null;
	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
	    	if(sValue.contains(ElementName)){
	   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/a"));
	   			sRowValue.click();
	   			Thread.sleep(1000);
	   			break;
	    	}
		}

	}
public void VerifyAddedTenant(UserAction action ,String locator, String site) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=10;i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	b=sValue.contains(site);
    	if(b){
    		Assert.assertTrue(b);
			//Thread.sleep(1000);
			flag=1;
			break;

    }
    	
	}
	if(flag==0){
		Assert.assertTrue(b);
	}


	
	
}
public void SelectTenantTree(UserAction action ,String locator,String Tenant) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+10);
	WebDriverWait wait = new WebDriverWait(action.driver, 80);
	for (int i=1;i<=10;i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.contains(Tenant)){
   			//WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ins"));
   			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator+"["+i+"]/ins")));
   			element.click();
   			Thread.sleep(1000);
   			break;
    	}
	}

}


public void SelectSite(UserAction action ,String locator,String Tenant, String site) throws InterruptedException {
	// TODO Auto-generated method stub
		List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
		//System.out.println("Total Rows"+10);
		for (int i=1;i<=totalRows.size();i++){
	    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
			if(sValue.equals(Tenant)){
				List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

		    	for(int j=1;j<=SiteRows.size();j++){

	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
	    	if(sValue.contains(site)){
	   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a"));
	   			sRowValue.click();
	   			Thread.sleep(1000);
	   			break;
	    	}
		 }
		}
	}
}

public void SelectSiteTree(UserAction action ,String locator,String Tenant,String site) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	WebDriverWait wait = new WebDriverWait(action.driver, 80);
	//System.out.println("Total Rows"+10);
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
		if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

	    	for(int j=1;j<=SiteRows.size();j++){


	    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
	    	if(sValue.contains(site)){
	   			//WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ins"));
	   			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ins")));

	   			element.click();
	   			Thread.sleep(1000);
	   			break;
	    	}
    	}
	}
}
}
public void VerifyAddedSite(UserAction action ,String locator,String Tenant, String site) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();

		if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

    	for(int j=1;j<=SiteRows.size();j++){

    		sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
    		b=sValue.contains(site);

    		if(b){
    			Assert.assertTrue(b);
    			//Thread.sleep(1000);
    			flag=1;
    			break;
    		}
    	}
    }
	}
	if(flag==0){
		Assert.assertTrue(b);
	}
}


public void VerifyDepartment(UserAction action ,String locator,String Tenant, String site, String Dept) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

    	for(int j=1;j<=SiteRows.size();j++){

    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
    	  if(sValue.equals(site)){
  			List<WebElement> DeptRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li"));

    	    	for(int k=1;k<=DeptRows.size();k++){
    	      	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a")).getText();

    	    	  b=sValue.contains(Dept);
    		      if(b){
    			    Assert.assertTrue(b);
    			    flag=1;
    			    break;
    		}
    	}
    }
	}
		}
	}
	if(flag==0){
		Assert.assertTrue(b);
	}
}
public void SelectDept(UserAction action ,String locator,String Tenant, String site, String Dept) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+10);
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

    	for(int j=1;j<=SiteRows.size();j++){

    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
    	  if(sValue.equals(site)){
  			List<WebElement> DeptRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li"));

    	    	for(int k=1;k<=DeptRows.size();k++){
    	      	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a")).getText();

    	      	  if(sValue.contains(Dept)){
		   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a"));
		   			sRowValue.click();
		   			Thread.sleep(1000);
		   			break;
		    	}
	    	 }
		}
    	}}}
}
	
public void SelectDeptTree(UserAction action ,String locator,String Tenant, String site, String Dept) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+10);

	//System.out.println("Total Rows"+10);
		for (int i=1;i<=totalRows.size();i++){
	    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
	    	if(sValue.equals(Tenant)){
				List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

	    	for(int j=1;j<=SiteRows.size();j++){

	    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
	    	  if(sValue.equals(site)){
	  			List<WebElement> DeptRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li"));

	    	    	for(int k=1;k<=DeptRows.size();k++){
	    	      	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a")).getText();
		    	if(sValue.contains(Dept)){
		   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ins"));
		   			sRowValue.click();
		   			Thread.sleep(1000);
		   			break;
		    	}
	    	 }
		}
	}
}}}
public void VerifyTeam(UserAction action ,String locator,String Tenant, String site, String Dept,String Team) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

    	for(int j=1;j<=SiteRows.size();j++){

    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
    	  if(sValue.equals(site)){
  			List<WebElement> DeptRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li"));

    	    	for(int k=1;k<=DeptRows.size();k++){
    	      	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a")).getText();
	    	if(sValue.contains(Dept)){
	    		List<WebElement> TeamRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li"));
		    	 for(int l=1;l<=TeamRows.size();l++){   
			    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li["+l+"]/a")).getText();
			    	b=sValue.contains(Team);
			    	if(b){
			    		Assert.assertTrue(b);
						Thread.sleep(1000);
						flag=1;
						break;

			    	}
		    	 }
	    	 }
			}
    	
		}
    	}
    	}
	}
	
	if(flag==0){
		Assert.assertTrue(b);
	}
}



public void SelectTeam(UserAction action ,String locator,String Tenant, String site, String Dept,String Team) throws InterruptedException {
	int flag=0;
	List<WebElement> totalRows=action.driver.findElements(By.xpath(locator));
	for (int i=1;i<=totalRows.size();i++){
    	String sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.equals(Tenant)){
			List<WebElement> SiteRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li"));

    	for(int j=1;j<=SiteRows.size();j++){

    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/a")).getText();
    	  if(sValue.equals(site)){
  			List<WebElement> DeptRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li"));

    	    	for(int k=1;k<=DeptRows.size();k++){
    	      	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/a")).getText();
	    	if(sValue.contains(Dept)){
	    		List<WebElement> TeamRows=action.driver.findElements(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li"));
		    	 for(int l=1;l<=TeamRows.size();l++){   
			    	sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li["+l+"]/a")).getText();
			    	b=sValue.contains(Team);
			    	  sValue = action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li["+l+"]/a")).getText();
				    	if(sValue.contains(Team)){
				   			WebElement sRowValue=action.driver.findElement(By.xpath(locator+"["+i+"]/ul/li["+j+"]/ul/li["+k+"]/ul/li["+l+"]/a"));
				   			sRowValue.click();
				   			Thread.sleep(1000);
				   			break;
				    	}
		    	 }
	    	 }
			}
    	
		}
    	}
    	}
	}
}
public void SelectUPR(UserAction action ,String Columnvalue) throws Exception
{
	 List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='template_table_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='template_table:"+(i-2)+":linkAssignTemplate']")).click();
					flag=1;
					break;
				}
    
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
}
public void SelectCMElement(UserAction action ,String Columnvalue) throws Exception
{
	 List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='all_elements_table:"+(i-2)+":linkAssignElement']")).click();
					flag=1;
					break;
				}
    
			}
			if(flag==0)
			{
				//Assert.assertTrue(b);
			}
	
}

public void SelectSMElement(UserAction action ,String Columnvalue) throws Exception
{
	 List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr"));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+2;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='all_elements_table_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					action.driver.findElement(By.xpath(".//*[@id='all_elements_table:"+(i-2)+":linkAssignElement']")).click();
					flag=1;
					break;
				}
    
			}
			if(flag==0)
			{
				//Assert.assertTrue(b);
			}
	
}

public void UnAssignCMElement(UserAction action, String Columnvalue) throws InterruptedException {
	 List<WebElement> rows = action.driver.findElements(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr"));
	 for (WebElement rowElmt : rows)
     {
     	
     	List<WebElement> columns = rowElmt.findElements(By.tagName("td"));
        	for(WebElement cols:columns){
        		if(cols.getText().contains(Columnvalue)){
        			for(int i=2;i<=columns.size();i++)
        			{
        				String str1=action.driver.findElement(By.xpath(".//*[@id='selected_elements_core_table_content']/tbody/tr["+i+"]/td[4]")).getText();
        				boolean b= str1.equals(Columnvalue);
        				if(b)
        				{
        					action.driver.findElement(By.xpath(".//*[@id='selected_elements:"+(i-2)+":linkRemoveElement']")).click();
        					Thread.sleep(5000);
        					break;
        				}
            
        			}

					
        		}
        	}

      }
}
}

	



	
