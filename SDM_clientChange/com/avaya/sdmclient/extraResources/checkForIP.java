package com.avaya.sdmclient.extraResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.runnerdemo.settingsForConcThreads;

public class checkForIP {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, MyException {
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		Properties pr=new Properties();
		pr.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties"));
		System.out.println(pr.getProperty("148.147.162.221"));
		System.out.println(pr.getProperty("148.147.162.225"));
		
		By by = By.id(locator.getProperty("VMGrid"));
		System.out.println(settingsForConcThreads.findAvailableIP(driver, by));
	}

}
