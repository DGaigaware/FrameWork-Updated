package com.avaya.smgr.sdmsetup;



import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utility.UserAction;

public class Sdmsetup{
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	
	public WebDriver driver;
	
	public void SelectLibrary(UserAction action ,String Columnvalue) throws InterruptedException
	{
		
		 List<WebElement> rows = action.driver.findElements(By.name("table_1_subnet:_s"));
		 Thread.sleep(2000);
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_subnet_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{
						action.driver.findElement(By.xpath(".//*[@id='table_1_subnet:"+(i-2)+"']")).click();
						flag=1;
						break;
					}
					
				}
				if(flag==0)
				{
					Assert.assertTrue(b);
				}
	}
	
	
	public void VerifyAdd(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='table_1_subnet_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No data found"))
		{
			Assert.assertTrue(false);
		}
		else
		{
			
		List<WebElement> rows = action.driver.findElements(By.name("table_1_subnet:_s"));
		 Thread.sleep(2000);
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_subnet_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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
	
	
	   }

	public void VerifydeleteLibrary(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='table_1_subnet_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No data found"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			List<WebElement> rows = action.driver.findElements(By.name("table_1_subnet:_s"));
			 Thread.sleep(2000);
				int numberofrows = rows.size();
				int flag=0;
					for(int i=2;i<=numberofrows+1;i++)
					{
						String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_subnet_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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
	
}