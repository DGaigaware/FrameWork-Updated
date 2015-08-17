package com.avaya.smgr.upmsetup;



import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utility.UserAction;

public class Upmsetup{
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	
	public WebDriver driver;
	
	public void AccessSharedaddressname(UserAction action ,String Columnvalue) throws Exception
	{
		Thread.sleep(1000);
		List<WebElement> rows =action.driver.findElements(By.name("tableSharedAddress:_s"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+1;i++)
			{
				String str1=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					WebElement el=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress:"+(i-2)+"']"));
					while(!(el.isSelected()))
							{
						el.click();
					//driver.findElement(By.xpath(".//*[@id='tableSharedAddress:"+(i-2)+"']")).click();
							}
					flag=1;
					break;
				}
	   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	}
	
	public void AccessSecondcolumn(UserAction action,String Columnvalue) throws Exception
	{
		Thread.sleep(1000);
		List<WebElement> rows=action.driver.findElements(By.name("table_1:_s"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+1;i++)
			{
				String str1=action.driver.findElement(By.xpath("//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					WebElement el=action.driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']"));
					while(!(el.isSelected()))
					{
						el.click();
					//driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']")).click();
					}
					flag=1;
					break;
				}
	   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	}

	
	public void Selectpubliccontact(UserAction action ,String chkbyname,String Columnvalue) throws InterruptedException
	{
		Thread.sleep(1000);
		 List<WebElement> rows = action.driver.findElements(By.name(chkbyname));
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='publicContactsTable_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{
						action.driver.findElement(By.xpath(".//*[@id='publicContactsTable:"+(i-2)+"']")).click();
						flag=1;
						break;
					}
					
				}
				if(flag==0)
				{
					Assert.assertTrue(b);
				}
	}
	
	public void Verify_Addedpucliccontact(UserAction action ,String chkbyname,String Columnvalue) throws InterruptedException
	{
		Thread.sleep(3000);
	    List<WebElement> rows =action.driver.findElements(By.name(chkbyname));
	    Thread.sleep(2000);
	    int numberofrows = rows.size();
	    int flag=0;

			 	for(int i=2;i<=numberofrows+1;i++)
			 	{
			 		String str1=action.driver.findElement(By.xpath(".//*[@id='publicContactsTable_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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

	
	public void Verifydeletepubliccontact(UserAction action ,String chkbyname,String Columnvalue) throws InterruptedException
	{
	  
		String str=action.driver.findElement(By.xpath(".//*[@id='publicContactsTable_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(true);
		
		}
		else
		{
		List<WebElement> rows = action.driver.findElements(By.name(chkbyname));
		int numberofrows = rows.size();
		int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='publicContactsTable_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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

	//Address
	public void SelectAddress(UserAction action ,String Columnvalue) throws InterruptedException
	{
		
		String str=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(false);
		
		}
		else
		{
		 List<WebElement> rows = action.driver.findElements(By.name("tableSharedAddress:_s"));
		 Thread.sleep(2000);
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{   
						WebElement ele=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress:"+(i-2)+"']"));
						while(!(ele.isSelected()))
						{
						action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress:"+(i-2)+"']")).click();
						}
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
	public void Verify_AddedAddress(UserAction action ,String Columnvalue) throws InterruptedException
	{
		
		String str=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(false);
		
		}
		else
		{
		Thread.sleep(3000);
	    List<WebElement> rows =action.driver.findElements(By.name("tableSharedAddress:_s"));
	    Thread.sleep(2000);
	    int numberofrows = rows.size();
	    int flag=0;

			 	for(int i=2;i<=numberofrows+1;i++)
			 	{
			 		String str1=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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

	
	public void Verifydeleteaddress(UserAction action ,String Columnvalue) throws InterruptedException
	{
	  
		String str=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(true);
		
		}
		else
		{
		List<WebElement> rows = action.driver.findElements(By.name("tableSharedAddress:_s"));
		int numberofrows = rows.size();
		int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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
	
	//Localized Name
	public void SelectLocalName(UserAction action ,String Columnvalue) throws InterruptedException
	{
		
		String str=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(false);
		
		}
		else
		{
		 List<WebElement> rows = action.driver.findElements(By.name("tableLocalizedNames:_s"));
		 Thread.sleep(2000);
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equals(Columnvalue);
					if(b)
					{
						action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames:"+(i-2)+"']")).click();
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
	public void Verify_AddedLocalname(UserAction action ,String Columnvalue) throws InterruptedException
	{
		
		String str=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(false);
		
		}
		else
		{
		
	    List<WebElement> rows =action.driver.findElements(By.name("tableLocalizedNames:_s"));
	    
	    int numberofrows = rows.size();
	    int flag=0;

			 	for(int i=2;i<=numberofrows+1;i++)
			 	{
			 		String str1=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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

	
	public void Verifydeletelocalname(UserAction action ,String Columnvalue) throws InterruptedException
	{
	  
		String str=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr[2]/td[2]")).getText();
		if(str.equals("No Records found"))
		{
			Assert.assertTrue(true);
		
		}
		else
		{
		List<WebElement> rows = action.driver.findElements(By.name("tableLocalizedNames:_s"));
		int numberofrows = rows.size();
		int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='tableLocalizedNames_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
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
	
