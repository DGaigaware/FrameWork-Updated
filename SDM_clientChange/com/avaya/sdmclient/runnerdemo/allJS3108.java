package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.Settings;

public class allJS3108 {

	
	
	public static String findID(WebDriver driver){
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "var allElements = Ext.getBody().dom.getElementsByTagName(\"*\"); var allIds = [];for (var i = 0, n = allElements.length; i < n; ++i) {if(allElements[i].textContent==\"VM Management\"){var a = allElements[i].getAttribute(\"id\");return a}}";
		
		String ans = (String) js.executeScript(script);
		System.out.println("Before");
		System.out.println(ans);
		System.out.println("After");
		return ans;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		obj.goToSite(driver);
		findID(driver);
	}

}
