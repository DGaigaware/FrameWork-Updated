package com.avaya.sdmclient.extraResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		Settings obj = new Settings();
		WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;

		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		obj.loginToSite(drive);
		Thread.sleep(2500);
		
		obj.goHome(drive);
		
	}

}
