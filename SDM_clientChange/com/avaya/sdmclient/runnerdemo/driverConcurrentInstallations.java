package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class driverConcurrentInstallations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n\nBefore invoked\n\n");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	GregorianCalendar gcalendar = new GregorianCalendar();
	 	String opDirectory = System.getProperty("user.dir")+"\\testruns_programmatically\\"+dateFormat.format(new java.util.Date())+File.separator +"testSM".replace("temp", "").replace(".xml", "")+"_"+gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
		
	 	TestNG testng;
		testng = new TestNG();
		testng.setPreserveOrder(true);
		testng.setVerbose(2);
		XmlSuite suite = new XmlSuite();
		
		suite.setSuiteFiles(Arrays.asList("./concurrent.xml"));
		testng.setXmlSuites(Arrays.asList(suite));
		
		System.out.println("Before "+testng.getOutputDirectory());
		testng.setOutputDirectory(opDirectory);
		System.out.println("After "+testng.getOutputDirectory());

		testng.run();
		    	
	}

}
