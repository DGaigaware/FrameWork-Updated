package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;

public class demo1109 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;

		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));

		List<String> ele = new ArrayList<>();
		ele.add("SM-7.0.0.0.700007-e55-01.ova");
		ele.add("BSM-7.0.0.0.700007-e55-01.ova");
		ele.add("CMM-7.0.0.0.700007-e55-01.ova");
		// TODO Auto-generated method stub
		settingsForConcThreads ob = new settingsForConcThreads();
		ob.runThread(driver);
	}

}
