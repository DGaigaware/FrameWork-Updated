package com.avaya.sdmclient.runnerdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;

public class drivForallSetconc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		Settings obj = new Settings();
		final List<String> ele = new ArrayList<>();
		ele.add("SM-7.0.0.0.700007-e55-01.ova");
		ele.add("BSM-7.0.0.0.700007-e55-01.ova");
		ele.add("CMM-7.0.0.0.700007-e55-01.ova");
		final WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
		class MyRunnable implements Runnable {
			 public void run() {
				 settingsForConcThreads ob = new settingsForConcThreads();
				 try {
					ob.runThread(driver, ele);
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		MyRunnable r = new MyRunnable();
		Thread t = new Thread(r);
		t.start();
		
		System.out.println("End");
	}

}
