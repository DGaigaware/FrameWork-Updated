package com.avaya.sdmclient.runnerdemo;
import com.avaya.sdmclient.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.logClass;

public class extraOperations {
	List<String> strings = new ArrayList<String>();
    public List<String> _findStr(String _input[]){
        
        Scanner sc ;
        String _op = null ;
        for(String s : _input)
        {
        	sc = new Scanner(s);
        	while(sc.hasNext())
        		if(sc.next().contains("Profile"))
        		{
        			_op = sc.next().replace(",", "");
        			strings.add(_op);
        			//System.out.println(_op);
        		}
        }
        return strings;
    }
    
    public int _compare(int i[]){
        int temp = 100000000;
        for(int index=0;index<i.length;index++)
            if(i[index]<temp)
                temp = i[index];
        System.out.println(temp);
        return temp;
    }
    
    public static void main(String args[]) throws IOException, InterruptedException{
       /* String input[] = {"SM 7.0 Profile 5 Max Devices 23,300","SM 7.0 Profile 1 Max Devices 2,500","SM 7.0 Profile 2 Max Devices 4,500"};
        int tem[] = {12,20,25,6};
        extraOperations ob = new extraOperations();
        int arr[] = {0,0,0} ;
        List<Integer> i= new ArrayList<Integer>();
       
        for(int z=0;z<input.length;z++)
            i.add(Integer.parseInt(ob._findStr(input).get(z)));
            
        for(int y : i)
            System.out.println(y);
        
        for(int z=0;z<input.length;z++)
            arr[z]=i.get(z);
        ob._compare(arr);
        
//        if(i>ob._compare(tem))
//            System.out.println("Greater");
        
        ob._compare(tem);*/
        _Settings obj = new _Settings();
    	WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
        driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		//driver.manage().window().maximize();
		
        obj._addToList();
		boolean _Check;
		
		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";
		
		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='newVM_-btnInnerEl']")).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).clear();
		//driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));	
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys("testSM2211");
		logClass.info("Given Name");
		Thread.sleep(450);
		
		obj._errorBox(driver,obj._checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		Thread.sleep(250);

		obj._boundListSelect(driver, "data", 0);
		
		//1 - File Path; 3 - SW Library; 4 - URL 
		switch(_default){
			case _default:
					driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
					logClass.info("Choosen From File");
		
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj._readFromFile("input.txt", "FilePath"));
					driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
					logClass.info("File Path Given");
					Thread.sleep(500);
					break;
			case _URL:
					driver.findElement(By.xpath(".//*[@id='radio4-inputEl']")).click();
					logClass.info("Choosen From URL");
					
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
					driver.findElement(By.xpath(".//*[@id='button-1240-btnIconEl']")).click();
					Thread.sleep(450);
					break;
			case _SWLib:
					driver.findElement(By.xpath(".//*[@id='radio3-inputEl']")).click();
					logClass.info("Choosen From Software Library");
					
					driver.findElement(By.xpath(".//*[@id='combobox-1237-inputEl']")).click();
					WebElement element = driver.findElement(By.id("boundlist-1543-listEl"));
					List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
					for (WebElement e : tmp1 )
						{
							//System.out.println(e.getText()+ "\n Test \n");
							if(e.getText().contains("SM"))
							{
								System.out.println("\nSelected : \n"+e.getText());
								e.click();
							}
						}
					break;
		}
		
		//obj._errorBox(driver);

		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj._boundListSelect(driver, "Profile 1", 1);
		//Thread.sleep(250);
		_Check = obj._checkError(driver);
		obj._errorBox(driver,obj._checkError(driver));
	
		driver.findElement(By.xpath(".//*[@id='gridcolumn-1221-textEl']")).click();
		obj._checkFailureOfHostCapacity(driver);
		
		obj._exec(!_Check);
		
		driver.findElement(By.xpath(".//*[@id='domain-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='domain-inputEl']")).sendKeys("testsm221.smgrdev.avaya.com");
		
		obj._IPFill(driver, "148.147.162.221", ".//*[@id='textfield-1548-inputEl']");
	
		logClass.info("IP address given");
		
		driver.findElement(By.xpath(".//*[@id='hostname-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));
		logClass.info("Given the Name of VM");
		
		obj._IPFill(driver, "255.255.255.0", ".//*[@id='textfield-1556-inputEl']");
	
		logClass.info("Given NetMask");

		obj._IPFill(driver, "148.147.162.1", ".//*[@id='textfield-1564-inputEl']");
		
		logClass.info("Given Default Gateway");
		
		driver.findElement(By.xpath(".//*[@id='timezone-inputEl']")).click();
		
		obj._boundListSelect(driver, "Asia/Kolkata", 5);
		
		Thread.sleep(2500);
		logClass.info("Selected Given TimeZone");
    }
}
