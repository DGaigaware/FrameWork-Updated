package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class files {

	
	public static List<String> listFileNames(){
		File folder = new File("C:\\Program Files\\Avaya\\AvayaSDMClient\\Default_Artifacts\\");
		File[] listOfFiles = folder.listFiles();
		List<String> filenames = new ArrayList<>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				//System.out.println("File " + listOfFiles[i].getName());
				filenames.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				// System.out.println("Directory " + listOfFiles[i].getName());
				
			}
		}
		
		return filenames;	
	}
	
	public static String makeStrs(String ova,String IP){
		//List<String> appenders = new ArrayList<>();
		String appender = "<test name=\""+ova+"\" preserve-order=\"true\" thread-count=\"1\">\n"+
				"\t<parameter name=\"IP\" value=\""+IP+"\"/>\n"+
					"\t<parameter name=\"VMName\" value=\""+ova+"\"/>\n"+

						"\t\t<classes>\n"+
							"\t\t\t<class name=\"com.avaya.sdmclient.automation.total\" />\n"+
								"\t\t</classes> \n"+

									"</test>\n";
		
		return appender;
		
	}
	public static void generateXML(List<String> ovanames,String IP) throws IOException{
		
		String pre = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
						"<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\" >\n"+
							"<suite name=\"Suite1\" parallel=\"false\" preserve-order=\"true\" verbose=\"2\" thread-count=\"1\">\n";
		String locationtest = "<test name=\"Location\" preserve-order=\"true\" thread-count=\"1\">\n"+
					"\t<classes>\n"+
						"\t\t<class name=\"com.avaya.sdmclient.location.Location\" />\n"+
							"\t</classes> \n"+

								"</test>\n";
		String hosttest = "<test name=\"Host\" preserve-order=\"true\" thread-count=\"1\">\n"+
				"\t<classes>\n"+
					"\t\t<class name=\"com.avaya.sdmclient.host.Host\" />\n"+
						"\t</classes> \n"+

							"</test>\n";
		
		String post = "</suite>";
		
		File file1 = new File(System.getProperty("user.dir")+"\\tempSDMClient"+".xml");

		System.out.println(file1.getAbsolutePath());
		PrintWriter pr = new PrintWriter(file1);
		pr.write(pre+"\n\n");
		pr.write(locationtest+"\n\n");
		pr.write(hosttest+"\n\n");
		
		for(int j=0;j<listFileNames().size();j++){
			System.out.println("########################");
			System.out.println(ovanames.get(j));
			String temp = makeStrs(listFileNames().get(j), IP);
			pr.write(temp+"\n\n");	
		}
		pr.write(post+"\n\n");
		pr.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		List<String> files = listFileNames();
		generateXML(files, "148.147.162.221");
	}

}
