package com.avaya.sdmclient.extraResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.avaya.sdmclient.Settings;

public class CheckOVFForGivenOVA {

	public static String find(String input){
		String returnStr = "";
		File folder = new File(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        fileNames.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    
		    for(String s : fileNames){
		    	if(s.contains("-")){
		    		if(s.substring(0,s.indexOf("-")).equalsIgnoreCase(input))
		    		{
		    			returnStr = s;
		    			System.out.println(returnStr);
		    			break;
		    		}
		    		break;
		    	}
		    	else if(s.contains("_")){
		    		if(s.substring(0,s.indexOf("_")).equalsIgnoreCase(input))
		    		{
		    			returnStr = s;
		    			System.out.println(returnStr);
		    			break;
		    		}
		    		break;
		    	}
		    }
		return returnStr;
	}
	
	public static void main(String[] args) {

		find("BSM");
		Settings s = new Settings();
		//s.chooseOVF("");
		s.chooseOVF("CMM-07.0.0.0.441-e55-0.ova");
		s.chooseOVF("US-7.0.0.0.0.12-e55-01_OVF10.ova");
		s.chooseOVF("BSM-7.0.0.0.700007-e55-01.ova");
		s.chooseOVF("SM-7.0.0.0.700007-e55-01.ova");
		s.chooseOVF("AAM-07.0.0.0.441-e55-0.ova");
		s.chooseOVF("AES-7.0.0.0.0.13.20150629-e50-00.ova");
		s.chooseOVF("SecureAccessLinkGateway-2.5.1.0-vApp-e55-08.ova");
		s.chooseOVF("EDP-3.1.0.0.310009_OVF10.ova");
		s.chooseOVF("MediaServer_7.7.0.226_A10_2015.07.02_OVF10.ova");
		s.chooseOVF("CM-Duplex-07.0.0.0.441-e55-0.ova");
		s.chooseOVF("CM-Simplex-07.0.0.0.441-e55-0.ova");
	}

}
