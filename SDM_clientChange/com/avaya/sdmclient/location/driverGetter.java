package com.avaya.sdmclient.location;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.avaya.sdmclient.Settings;

public class driverGetter {
	public static WebDriver driver;
	static Settings obj = new Settings();
    @BeforeSuite
    public void start() {
        driver = new FirefoxDriver(obj.selectProfile("Selenium"));
    }


    @AfterSuite
    public void end() {
        driver.close();
        driver.quit();
    }
}
