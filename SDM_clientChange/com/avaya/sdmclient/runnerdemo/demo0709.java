package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class demo0709 {

	pingIP p = new pingIP();
	
	public static void flagChecker(boolean input) throws NoSuchMethodException, SecurityException, InterruptedException, IOException, ParserConfigurationException, SAXException{
		if(input)
		{
			System.out.println("Going to exec method");
			driverForinvoker a = new driverForinvoker();
			a.main(null);
		}
	}
	
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InterruptedException, IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub

		flagChecker(true);
		System.out.println("After");
	}

}
