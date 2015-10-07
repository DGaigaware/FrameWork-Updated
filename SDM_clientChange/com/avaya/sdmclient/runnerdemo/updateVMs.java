package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class updateVMs {
	
	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	@Test(description="Adding Location",priority=0)
	public void updateVM() throws IOException, InterruptedException {

		logClass.startTestCase("Add a new Location on SDM");

		obj.loginToSite(drive);
		
		
		
	}
}
