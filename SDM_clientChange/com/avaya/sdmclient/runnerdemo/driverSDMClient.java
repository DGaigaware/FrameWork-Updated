package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.extraResources.MyException;

public class driverSDMClient {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException, MyException {
		//System.out.println("Before invoked\n\n");
		
		//files createXML = new files();
		//createXML.main();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	GregorianCalendar gcalendar = new GregorianCalendar();
	 	String opDirectory = System.getProperty("user.dir")+"\\testruns_programmatically_SDMClient\\"+dateFormat.format(new java.util.Date())+File.separator +"SDMClient"+"_"+gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
	 	
	 	TestNG testng;
		testng = new TestNG();
		testng.setPreserveOrder(true);
		testng.setVerbose(2);
		XmlSuite suite = new XmlSuite();
		
		//suite.setSuiteFiles(Arrays.asList(System.getProperty("user.dir")+"\\Third Party\\tempXMLs\\"+fName));
		suite.setSuiteFiles(Arrays.asList("./tempSDMClient.xml"));
		testng.setXmlSuites(Arrays.asList(suite));
		
		System.out.println("Before "+testng.getOutputDirectory());
		testng.setOutputDirectory(opDirectory);
		System.out.println("After "+testng.getOutputDirectory());

		testng.run();
		    	
	}

}
