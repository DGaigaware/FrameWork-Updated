package com.avaya.sdmclient.runnerdemo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class pingIP {

	 public static void runSystemCommand(String command) {
		try{
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
				// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				System.out.println(s);
			}
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		String ip1 = "google.com";
		String ip = "148.147.162.224";
		//runSystemCommand("ping " + ip1);
		
		Settings obj = new Settings();
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		List<WebElement> cells1 = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		List<String> IDs = new ArrayList<>();
		List<String> IPsFromGrid = new ArrayList<>();
		List<String> IPs = new ArrayList<>();
		List<String> IPblacklist = new ArrayList<>();
		List<String> IPwhitelist = new ArrayList<>();
		//System.out.println(cells.size()+"\n\n");

		IPs.add("148.147.162.221");
		IPs.add("148.147.162.225");
		
		for(WebElement e : cells)
		{	
			System.out.println("ID: "+e.getAttribute("id"));
			IDs.add(e.getAttribute("id"));
			System.out.println(e.getText()+"\n");
			/*if(e.getText().trim().equals("148.147.162.221"))
				e.click();*/
		}
		//.//*[@id='gridview-1190-record-ext-record-22']/td[3]/div
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
			System.out.println("BlackListed"+s);
		}
		for(String s : IPwhitelist){
			System.out.println("WhiteListed"+s);
		}
		
		for(String s : IPwhitelist)
			runSystemCommand("ping "+s);
	}
}
