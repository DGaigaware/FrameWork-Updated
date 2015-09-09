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

class index{
	String flagI[] = {"","",""};
}


public class pingIP {

	static Settings obj = new Settings();
	
	static String flag[] = {"","",""};
	
	public String[][] sel(){
		String [][]a = new String[3][2];
		a[0][0] = "BSM-7.0.0.0.700007-e55-01.ova";
		a[1][0] = "SM-7.0.0.0.700007-e55-01.ova";
		a[2][0] = "BSM-7.0.0.0.700007-e55-01.ova";
		a[0][1] = flag[0];
		a[1][1] = flag[1];
		a[2][1] = flag[2];
		
		return a;
	}
	
	 public static boolean runSystemCommand(String command) throws IOException {
		 boolean b = false;
		 String str = "";
		 String s="";
		
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
				// reading output stream of the command
			while ((str = inputStream.readLine()) != null) {
				//s=s.concat(s);
				System.out.println(str);
				s=str;
			}
		
			System.out.println(s);
		 if(s.contains("unreachable")){
				b = true;
			}
		 return b;
	}
	 
	 public static String findAvailableIP(WebDriver driver, By by) throws IOException{
		 WebElement table = driver.findElement(by);
		 List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
			List<WebElement> cells1 = table.findElements(By.xpath((".//*[local-name(.)='td']")));
			List<String> IDs = new ArrayList<>();
			List<String> IPsFromGrid = new ArrayList<>();
			List<String> IPs = new ArrayList<>();
			List<String> IPblacklist = new ArrayList<>();
			List<String> IPwhitelist = new ArrayList<>();
			String returnIP="";
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
				//System.out.println("BlackListed"+s);
			}
			for(String s : IPwhitelist){
				//System.out.println("WhiteListed"+s);
			}
			
			if(IPwhitelist.isEmpty())
				{
					System.out.println("Can not proceed further");
					System.exit(0);
				}
			else
				returnIP = IPwhitelist.get(0);
			
			/*for(String s : IPwhitelist){
				runSystemCommand("ping "+s);
			}
			
			if(runSystemCommand("ping "+IPwhitelist.get(0)))
				returnIP = IPwhitelist.get(0);*/
			return returnIP;
	 }
	 
	 public String chooseOVA(){
			List<String> list = new ArrayList<>();
			String input[][] = sel();
			
			String returnOVA="";
			
			for(int i=0;i<3;i++){
				if(input[i][1].isEmpty())
					{
						System.out.println("Choosen "+input[i][0]);
						input[i][1]="1";
						flag[i] = "1";
						//flagI[i]="1";
						returnOVA = input[i][0];
						break;
					}
			}
			
			for(int i=0;i<3;i++){
				System.out.println(input[i][1]);
			}
			return returnOVA;
		}
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		String ip1 = "google.com";
		String ip = "148.147.162.224";
		
		//runSystemCommand("ping " + ip1);
		
		
		WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		By by = By.id(locator.getProperty("VMGrid"));
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(driver);
		//obj.goHome(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

		System.out.println("IP available"+findAvailableIP(driver, by));
		
			
	}
}
