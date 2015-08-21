package com.avaya.sdmclient.runnerdemo;
import com.avaya.sdmclient.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
		
		logClass.startTestCase("Add a new Location on SDM");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		driver.findElement(By.xpath(".//*[@id='addnewlocation-btnInnerEl']")).click();
		logClass.info("Adding new Location");
		
		driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewLocation"));
		
		driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).sendKeys(obj._readFromFile("input.txt", "Address"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).sendKeys(obj._readFromFile("input.txt", "City"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).sendKeys(obj._readFromFile("input.txt", "State"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).sendKeys(obj._readFromFile("input.txt", "ZIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).sendKeys(obj._readFromFile("input.txt", "Country"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='savenewlocation-btnInnerEl']")).click();
		logClass.info("Saved New Location");
		
		logClass.endTestCase("Added a new Location");
        
    }
}
