package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.extraResources.scpFilesFromSMGR;

public class newaddvm {

	public void AddVM(String IP,String VMName) throws Exception {
		Settings obj = new Settings();
		WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;

		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));

		scpFilesFromSMGR s = new scpFilesFromSMGR();
		s.scpFile();

		String shortVMName = obj.shortVMName(VMName);

		boolean _Check;

		logClass.startTestCase("Adding VM to given Location and Host");

		//obj.goHome(drive);
		obj.loginToSite(drive);

		if(!obj.checkPresenceOfLocationOrHostOrVM(drive, obj.readFromFile("input.properties", "AddHostHostName:"))){
			//AddHost();
			System.out.println("Adding Host");
			obj.goHome(drive);
			logClass.info("Added Host as Location was not there beforehand.");
		}

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.chooseTab(drive, "Virtual Machines");

		drive.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		//Adding loc and host
		drive.findElement(By.id("cmbSelectLocation-inputEl")).click();
		Thread.sleep(450);
		obj.boundListSelect(drive, "testLoc", obj.selBoundList(drive));
		
		drive.findElement(By.id("cmbSelectHost-inputEl")).click();
		Thread.sleep(450);
		obj.boundListSelect(drive, "148.147.162.175", obj.selBoundList(drive));
		Thread.sleep(6000);
		drive.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.boundListSelect(drive, "data", obj.selBoundList(drive));
		Thread.sleep(2500);
		//Uptill here
		
		
		//Next Page
		obj.clickButtonxPath(drive, ".//*[@id='next-btnIconEl']");
		obj.comboClick(drive, "Select Software Library:","SMGR_DEFAULT_LOCAL");
		Thread.sleep(2500);

		obj.maintainedList(drive, obj.comboID(drive, "Select OVAs:"));

		obj.comboClick(drive, "Select OVAs:", VMName);
		Thread.sleep(2500);

		obj.selectFP(drive, shortVMName);
		
		//Uptill here
		
		//Next Page
		obj.clickButtonxPath(drive, ".//*[@id='next-btnIconEl']");
		drive.findElement(By.xpath(locator.getProperty("VMName"))).clear();

		drive.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
		logClass.info("Given Name");
		Thread.sleep(250);

		_Check = obj.checkError(drive);
		String err = obj.errorBox(drive,obj.checkError(drive));

		if(_Check){
			System.out.println("Error:");
			Thread.currentThread().interrupt();
		}

		if(Thread.currentThread().isInterrupted()){
			System.out.println("Cannot Execute Further");
			throw new MyException(err);
		}

		obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), drive,IP,"test"+shortVMName);

		obj.clickButtonxPath(drive, ".//*[@id='deployVMBtn-btnEl']");
		//obj.deployButtonClickForVM(drive);
		Thread.sleep(450);
		obj.findButton(drive);
		logClass.info("Accepted EULA");

//		final WebDriver driver1 = new FirefoxDriver(obj.selectProfile("Selenium"));
//		class MyRunnable implements Runnable {
//			public void run() {
//				settingsForConcThreads ob = new settingsForConcThreads();
//				try {
//					ob.runThread(driver1);
//				} catch (ParserConfigurationException | SAXException | IOException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				} catch (MyException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		MyRunnable r = new MyRunnable();
//		Thread t = new Thread(r);
//		t.start();

		Thread.sleep(9000);

		obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(drive, By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");

		Thread.sleep(5000);
		System.out.println("Completed adding VM "+shortVMName+" with IP "+IP);
		logClass.info("Completed adding VM "+shortVMName+" with IP "+IP);

	}

	public static void main(String args[]) throws Exception{
		newaddvm ac = new newaddvm();
		String ip = "148.147.162.221";
		String vm = "CMM-07.0.0.0.441-e55-0.ova";
		ac.AddVM(ip, vm);
	}

}
