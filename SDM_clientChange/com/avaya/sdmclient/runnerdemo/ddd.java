package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ddd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	GregorianCalendar gcalendar = new GregorianCalendar();
	 	String fName = "tempSM.xml";
		String opDirectory = System.getProperty("user.dir")+"\\testruns_programmatically\\"+"SM-".substring(0, "SM-".indexOf("-"))+"_"+dateFormat.format(new java.util.Date())+File.separator +gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
		String opDirectory1 = System.getProperty("user.dir")+"\\testruns_programmatically\\"+fName.replace("temp", "").replace(".xml", "")+"_"+dateFormat.format(new java.util.Date())+File.separator +gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
		System.out.println(opDirectory);
		System.out.println(opDirectory1);
	}

}
