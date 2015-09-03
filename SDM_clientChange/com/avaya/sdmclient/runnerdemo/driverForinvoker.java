package com.avaya.sdmclient.runnerdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

public class driverForinvoker {

	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InterruptedException, IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub

		System.out.println("\n\nBefor invoked\n\n");
	
		class MyRunnable implements Runnable {

		    public void run() {
		        // code in the other thread, can reference "var" variable
		    	
		    		try {
		    			System.out.println("in try block\n\n");
		    			TestNG testng;
		    				testng = new TestNG();
		    				testng.setPreserveOrder(true);
		    				testng.setVerbose(2);
		    				XmlSuite suite = new XmlSuite();
		    				
		    				suite.setSuiteFiles(Arrays.asList("./temp.xml"));

		    				testng.setXmlSuites(Arrays.asList(suite));

		    				testng.run();
		    			
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("After");
				
		    }
		}
		
		MyRunnable r = new MyRunnable();
		Thread t = new Thread(r);
		t.start();
		
		
		System.out.println("\n\nAfter invoked\n\n");
		String _fp = "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf";
		tryC c = new tryC();
		c.FillValues("", _fp);
	}

}
