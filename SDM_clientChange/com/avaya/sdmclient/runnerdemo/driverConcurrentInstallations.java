package com.avaya.sdmclient.runnerdemo;

import java.util.Arrays;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class driverConcurrentInstallations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n\nBefore invoked\n\n");

		TestNG testng;
		testng = new TestNG();
		testng.setPreserveOrder(true);
		testng.setVerbose(2);
		XmlSuite suite = new XmlSuite();
		
		suite.setSuiteFiles(Arrays.asList("./concurrent.xml"));
		testng.setXmlSuites(Arrays.asList(suite));

		testng.run();
		    	
	}

}
