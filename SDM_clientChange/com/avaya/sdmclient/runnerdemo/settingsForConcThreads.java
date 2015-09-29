package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class settingsForConcThreads {
	
	public String createTempXMl(String testName,String IP) throws ParserConfigurationException, SAXException, IOException{
		File file = new File(System.getProperty("user.dir")+"\\Third Party\\tempXMLs\\"+"temp.xml");
		File file1 = new File(System.getProperty("user.dir")+"\\Third Party\\tempXMLs\\"+"temp"+testName.substring(0, testName.indexOf("-"))+".xml");
		List<String> lines = FileUtils.readLines(file);
		String addIP = "<parameter name=\"IP\" value=\""+IP+"\"/>";
		String VMname = "<parameter name=\"VMName\" value=\""+testName+"\"/>";
		String addTestName = "<test name=\""+testName+"\">";
		
		/*for(String s : lines){
			//System.out.println(s);
		}*/
		
		for(int i=0;i<lines.size();i++)
		{
			if(lines.get(i).contains("name=\"IP\""))
				{
					System.out.println(lines.get(i));
					lines.remove(i);
					lines.add(i,addIP);
					System.out.println(addIP);
				}
			else if(lines.get(i).contains("name=\"VMName\""))
			{
				System.out.println(lines.get(i));
				lines.remove(i);
				lines.add(i,VMname);
				System.out.println(VMname);
			}
			else if (lines.get(i).contains("test name="))
				{
					lines.remove(i);
					lines.add(i,addTestName);
				}
		}
		
		PrintWriter pr = new PrintWriter(file1);
		
		for(String s : lines){
			//System.out.println(s);
			pr.write(s+"\n");
		}
		logClass.info("InnerThread: Changed XML File Successfully");
		pr.close();
		
		return file1.getName();
	}
	
	public static String findAvailableIP(WebDriver driver, By by) throws IOException, InterruptedException, MyException{
		Settings obj = new Settings();
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		
		Properties pr=new Properties();
		pr.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties"));
		
		List<String> IDs = new ArrayList<>();
		List<String> IPsFromGrid = new ArrayList<>();
		List<String> IPs = new ArrayList<>();
		List<String> IPblacklist = new ArrayList<>();
		List<String> IPwhitelist = new ArrayList<>();
		String returnIP="";
		File file = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\"+"inputip.txt");
		List<String> lines = FileUtils.readLines(file);
		Scanner sc;
		for(String s : lines)
		{
			/*sc = new Scanner(s);
			while(sc.hasNext())
				{
					IPs.add(sc.next());
					System.out.println(sc.next());
				}*/
			IPs.add(s);
			System.out.println(s);
		}
		
		logClass.startTestCase("InnerThread: Finding Available IP for Next VM");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(driver, "Virtual Machines");
		logClass.info("InnerThread: Checking if IP is assigned to any VM or not.");
		
		WebElement table = driver.findElement(by);
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		//List<WebElement> cells1 = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		
		for(WebElement e : cells)
		{	
			//System.out.println("ID: "+e.getAttribute("id"));
			IDs.add(e.getAttribute("id"));
			//System.out.println(e.getText()+"\n");
		}

		for(String s : IDs){
			WebElement e = driver.findElement(By.xpath(".//*[@id='"+s+"']/td[3]/div"));
			IPsFromGrid.add(e.getText());
			System.out.println(e.getText());
		}

		for(String s : IPs){
			//for(String s1 : IPsFromGrid){
			if(IPsFromGrid.contains(s))
				IPblacklist.add(s);
			else
				IPwhitelist.add(s);
			//}
		}

		for(String s : IPblacklist){
			System.out.println("BlackListed "+s);
			pr.setProperty(s, "BlackList");
		}
		for(String s : IPwhitelist){
			System.out.println("WhiteListed "+s);
			pr.setProperty(s, "WhiteList");
		}

		if(IPwhitelist.isEmpty())
		{
			System.out.println("Can not proceed further");
			logClass.error("No free IP available for Installation.");
			throw new MyException("No free IP available for Installation.");
			//System.exit(0);
		}
		else
			{
				System.out.println("Checking whether IP is already in use or not..");
				logClass.info("Checking whether IP is already in use or not..");
				
				for(int i=0;i<IPwhitelist.size();i++){
					if(pr.getProperty(IPwhitelist.get(i)).equals("WhiteList"))
						{
							returnIP = IPwhitelist.get(i);
							break;
						}
				}
				
				if(returnIP.isEmpty())
				{
					System.out.println("Can not proceed further");
					logClass.error("No free IP available for Installation as another instance has taken IP.");
					throw new MyException("No free IP available for Installation as another instance has taken IP.");
					//System.exit(0);
				}
				
				System.out.println(returnIP+" IP is available");
				logClass.info(returnIP+" IP is available");
					
				obj.makeIPWhiteBlackList(returnIP, "BlackList");
				//pr.setProperty(returnIP, "BlackList");
				System.out.println("IP is BlackListed as it will be used to install next VM");
				logClass.info("IP is BlackListed as it will be used to install next VM");
			}

		logClass.info("InnerThread: Choosen WhiteListed IP "+returnIP);
		
		for(String s : IPwhitelist)
			System.out.println(s);
		System.out.println("Selected IP : "+returnIP);

		/*for(String s : IPwhitelist){
				runSystemCommand("ping "+s);
			}

			if(runSystemCommand("ping "+IPwhitelist.get(0)))
				returnIP = IPwhitelist.get(0);*/
		return returnIP;
	 }
	 
	public static String match(List<String> input,String fromFile){
			String returnStr = "";
			/*for(String s : input){
				if(s.substring(0, fromFile.length()).equalsIgnoreCase(fromFile))
					returnStr = s;
			}
			return returnStr;*/
			for(String s : input){
		    	if(s.contains("-")){
		    		if(s.substring(0,s.indexOf("-")).equalsIgnoreCase(fromFile))
		    		{
		    			returnStr = s;
		    			System.out.println("Choosen OVA: (-) "+returnStr);
		    		}
		    	}
		    	else if(s.contains("_")){
		    		if(s.substring(0,s.indexOf("_")).equalsIgnoreCase(fromFile))
		    		{
		    			returnStr = s;
		    			System.out.println("Choosen OVA: (_) "+returnStr);
		    		}
		    	}
		    }
			return returnStr;
	}
	
	
	 
	 public String chooseNextOVA(List<String> inputFromBoundlist) throws IOException{
		 	//Settings obj = new Settings();
			String testOVA = "";
			/*List<String> ele = new ArrayList<>();
			ele.add("SM-7.0.0.0.700007-e55-01.ova");
			ele.add("BSM-7.0.0.0.700007-e55-01.ova");
			ele.add("CMM-7.0.0.0.700007-e55-01.ova");*/
			
			File file = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\"+"inputelem.txt");
			File fileTemp = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\"+"inputtemp.txt");
			List<String> lines = FileUtils.readLines(file);
			Scanner sc = new Scanner(fileTemp);
			int temp = Integer.parseInt(sc.next());
			System.out.println("Counter in File: "+temp);
			
			String output = lines.get(temp);
			PrintWriter pr = new PrintWriter(fileTemp);
			pr.write(""+(temp+1));
			pr.close();
			logClass.info("InnerThread: Incremented Count in File");
			
			testOVA = match(inputFromBoundlist, output);
			System.out.println(testOVA);
			logClass.info("InnerThread: Choosen OVA "+testOVA);
			sc.close();
			
			return testOVA;
	 }
	 
	 public List<String> readOVAs() throws IOException{
		 File f = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\ovanames.txt");
		// File f = new File("C:\\Users\\bshingala\\Desktop\\FrameWork Updated-17082015\\Third Party\\Input Files\\ovanames.txt");
		 List<String> lines = FileUtils.readLines(f);
		 return lines;
	 }
	 
	 public void runThread(WebDriver driver) throws ParserConfigurationException, SAXException, IOException, InterruptedException, MyException{
		 Settings obj = new Settings();
		 Properties locator=new Properties();
		 locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		 By by = By.id(locator.getProperty("VMGrid"));
		 String fName = createTempXMl(chooseNextOVA(readOVAs()), findAvailableIP(driver, by));
		 
		 driver.quit();
		 logClass.info("InnerThread: Closing current instance of Browser and starting execution of test with changed XML");
		 try {
			 	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 	GregorianCalendar gcalendar = new GregorianCalendar();
			 	String opDirectory = System.getProperty("user.dir")+"\\testruns_programmatically\\"+dateFormat.format(new java.util.Date())+File.separator +fName.replace("temp", "").replace(".xml", "")+"_"+gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"\\";
 				
			 	System.out.println("in try block\n\n");
 				TestNG testng;
 				
 				testng = new TestNG();
 				testng.setPreserveOrder(true);
 				testng.setVerbose(2);
 				
 				XmlSuite suite = new XmlSuite();
 				suite.setSuiteFiles(Arrays.asList(System.getProperty("user.dir")+"\\Third Party\\tempXMLs\\"+fName));
 				testng.setXmlSuites(Arrays.asList(suite));
 				
 				System.out.println("Before "+testng.getOutputDirectory());
 				testng.setOutputDirectory(opDirectory);
 				System.out.println("After "+testng.getOutputDirectory());
 				
 				testng.run();
 				logClass.info("InnerThread: Test Started");
 				logClass.info(Thread.currentThread().getName()+" "+Thread.currentThread().getId()+" Start");
		} 
		
		catch (SecurityException e) 
		{
				obj.takeScreenShotForDriver(driver);
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	 }
}
