package com.avaya.smgr.Worksetup;



import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utility.UserAction;

public class WorkSetup{
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	
	public WebDriver driver;
	
	public void SelectCatagory(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr/td")).getText();
		if(str.equals("No data available in table"))
		{
			Assert.assertTrue(false);
		}
		else
		{
			
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='categoryTable']/tbody/tr"));
		 Thread.sleep(2000);
			int numberofrows = totalRows.size();
			int flag=0;
				for(int i=1;i<=numberofrows;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{
						action.driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]/input")).click();
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
	
	
	public void VerifyAddCatagory(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr/td")).getText();
		if(str.equals("No data available in table"))
		{
			Assert.assertTrue(false);
		}
		else
		{
			
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='categoryTable']/tbody/tr"));
		 Thread.sleep(2000);
			int numberofrows = totalRows.size();
			int flag=0;
				for(int i=1;i<=numberofrows;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr["+i+"]/td[2]")).getText();
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

	public void VerifyAddAttribute(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr/td")).getText();
		if(str.equals("No matching records found"))
		{
			Assert.assertTrue(false);
		}
		else
		{
			
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='attributeTable']/tbody/tr"));
		 Thread.sleep(2000);
			int numberofrows = totalRows.size();
			int flag=0;
				for(int i=1;i<=numberofrows;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr["+i+"]/td[2]")).getText();
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
	
	public void SelectAddAttribute(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr/td")).getText();
		if(str.equals("No matching records found"))
		{
			Assert.assertTrue(false);
		}
		else
		{
			
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='attributeTable']/tbody/tr"));
		 Thread.sleep(2000);
			int numberofrows = totalRows.size();
			int flag=0;
				for(int i=1;i<=numberofrows;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{
						
						action.driver.findElement(By.xpath("html/body/div[2]/div[3]/div[2]/table/tbody/tr["+i+"]/td[1]/input")).click();
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
	public void VerifydeleteCatagory(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr/td")).getText();
		if(str.equals("No data available in table"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='categoryTable']/tbody/tr"));
			 Thread.sleep(2000);
				int numberofrows = totalRows.size();
				int flag=0;
					for(int i=1;i<=numberofrows;i++)
					{
						String str1=action.driver.findElement(By.xpath(".//*[@id='categoryTable']/tbody/tr["+i+"]/td[2]")).getText();
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
	
	public void VerifydeleteAttribute(UserAction action ,String Columnvalue) throws InterruptedException
	{
		String str=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr/td")).getText();
		if(str.equals("No matching records found"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			List<WebElement> totalRows =action.driver.findElements(By.xpath(".//*[@id='attributeTable']/tbody/tr"));
			 Thread.sleep(2000);
				int numberofrows = totalRows.size();
				int flag=0;
					for(int i=1;i<=numberofrows;i++)
					{
						String str1=action.driver.findElement(By.xpath(".//*[@id='attributeTable']/tbody/tr["+i+"]/td[2]")).getText();
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