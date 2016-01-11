package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.extraResources.MyException;

public class driverConcurrentInstallations {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException, MyException {
		//System.out.println("Before invoked\n\n");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	GregorianCalendar gcalendar = new GregorianCalendar();
	 	String opDirectory = System.getProperty("user.dir")+"\\testruns_programmatically\\"+dateFormat.format(new java.util.Date())+File.separator +"tempSM".replace("temp", "").replace(".xml", "")+"_"+gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
		
	 	//Adding code to take SM (First element) from file
	 	
	 	/*Settings obj = new Settings();
	 	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator=new Properties();
	 	settingsForConcThreads ob = new settingsForConcThreads();
	 	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	 	By by = By.id(locator.getProperty("VMGrid"));
	 	String fName = ob.createTempXMl(ob.chooseNextOVA(ob.readOVAs()), ob.findAvailableIP(driver, by));
	 	System.out.println(fName);
	 	driver.quit();*/
	 	
	 	//Uptill here
	 	
	 	TestNG testng;
		testng = new TestNG();
		testng.setPreserveOrder(true);
		testng.setVerbose(2);
		XmlSuite suite = new XmlSuite();
		
		//suite.setSuiteFiles(Arrays.asList(System.getProperty("user.dir")+"\\Third Party\\tempXMLs\\"+fName));
		suite.setSuiteFiles(Arrays.asList("./concurrent.xml"));
		testng.setXmlSuites(Arrays.asList(suite));
		
		System.out.println("Before "+testng.getOutputDirectory());
		testng.setOutputDirectory(opDirectory);
		System.out.println("After "+testng.getOutputDirectory());

		testng.run();
		    	
	}

}
