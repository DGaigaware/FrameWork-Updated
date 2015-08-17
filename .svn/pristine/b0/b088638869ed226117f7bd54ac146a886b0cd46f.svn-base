package com.avaya.smgr.GeoSetup;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.UserAction;

public class Geosetup {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	public void SelectCMElement(UserAction action,String Columnvalue) throws Exception
	{
		 List<WebElement> rows = action.driver.findElements(By.name("table_2:_s"));
		 Thread.sleep(2000);
			int numberofrows = rows.size();
			int flag=0;
				for(int i=2;i<=numberofrows+1;i++)
				{
					String str1=action.driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
					boolean b= str1.equalsIgnoreCase(Columnvalue);
					if(b)
					{
						action.driver.findElement(By.xpath(".//*[@id='table_2:"+(i-2)+"']")).click();
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