package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class iframeTest {

	public static void getIframe(final WebDriver driver, final String id) {
	    final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	    System.out.println(iframes.size());
	    for (WebElement iframe : iframes) {
	        //if (iframe.getAttribute("id").equals(id)) {
	        System.out.println(iframe.getAttribute("id"));
	    	// TODO your stuff.
	        }
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.get("https://pdev55vm2.smgrdev.avaya.com");
		
		driver.findElement(By.id("IDToken1")).sendKeys("admin");
	    driver.findElement(By.id("IDToken2")).sendKeys("Avaya123$");
	    driver.findElement(By.xpath(".//*[@id='SubmitButton']")).click();
	    logClass.info("Logged in Successfully");
	    
	    driver.findElement(By.xpath(".//*[@id='Services_SoftwareManagement']/a")).click();
		
		//obj.loginToSite(driver);
		Thread.sleep(1500);
		getIframe(driver, "");
		driver.switchTo().frame("iframe0");
		getIframe(driver, "");
		driver.switchTo().defaultContent();
		WebElement eee = driver.findElement(By.id("navIframe"));
		
			/*System.out.println("ID "+eee.getAttribute("id"));
			System.out.println("Class "+eee.getAttribute("class"));
			System.out.println("Tag "+eee.getTagName());
			System.out.println(eee.getClass());
			System.out.println(eee.getText());
			if(eee.getText().equals("Solution Deployment Manager"))
				{
					eee.click();
					System.out.println("Clicked");
				}
			
			System.out.println(eee.findElements(By.name("Solution Deployment Manager")).size());*/
		/*List<WebElement> abc = eee.findElements(By.className("Services_SoftwareManagement av-accordian-top-parent"));
		System.out.println(abc.size());
		for(WebElement ab : abc)
		System.out.println(ab.getText());*/
		
		System.out.println("Ans"+eee.findElement(By.linkText("Solution Deployment Manager")).getText());
		
			/*List<WebElement> a = eee.findElements(By.tagName("a"));
			System.out.println(a.size());
			for(WebElement aa : a)
			{
				//System.out.println("test"+aa.getText());
				if(aa.getText().equals("Upgrade Jobs Status"))
					aa.click();
			}
			Thread.sleep(1500);*/
			
		eee.findElement(By.linkText("Solution Deployment Manager")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("logoff")).click();
	}

}
