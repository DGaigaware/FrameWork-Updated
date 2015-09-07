package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class changeXML {

	public static void changeXMl(String testName,String IP) throws ParserConfigurationException, SAXException, IOException{
		File file = new File("./temp.xml");
		List<String> lines = FileUtils.readLines(file);
		String addIP = "<parameter name=\"IP\" value=\""+IP+"\"/>";
		String addTestName = "<test name=\""+testName+"\">";
		
		for(String s : lines){
			System.out.println(s);
		}
		for(int i=0;i<lines.size();i++)
		{
			if(lines.get(i).contains("name=\"IP\""))
				{
					System.out.println(lines.get(i));
					lines.remove(i);
					lines.add(i,addIP);
					System.out.println(addIP);
				}
			else if (lines.get(i).contains("test name="))
				{
					lines.remove(i);
					lines.add(i,addTestName);
				}
		}
		
		PrintWriter pr = new PrintWriter(file);
		
		for(String s : lines){
			System.out.println(s);
			pr.write(s+"\n");
		}
		pr.close();
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		pingIP p = new pingIP();
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		By by = By.id(locator.getProperty("VMGrid"));
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		System.out.println("IP available"+p.findAvailableIP(driver, by));
		
		System.out.println(p.chooseOVA());
		
		//changeXMl(p.chooseOVA(), p.findAvailableIP(driver, by));
	}

}
