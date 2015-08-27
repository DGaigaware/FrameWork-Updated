package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class scrdemo2708 {
	
	static Settings obj = new Settings();
	static WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	
	
	public static void TakeScreenShot(WebDriver driver,String name) throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\bshingala\\Desktop\\"+dateFormat.format(date).toString().replace("/", "")+".png"));
		logClass.error("Something went wrong :(");
		logClass.info("Check Screenshot for the same");
	}
	
	public static void main(String args[]) throws IOException{
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		TakeScreenShot(driver,"a");
	}
}
