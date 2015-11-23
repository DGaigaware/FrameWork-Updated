package com.avaya.sdmclient.patch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.extraResources.execCommand;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class patchCM {
	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	@Test(description="Adding VM to given Location and Host",priority=8)
	@Parameters({"IP", "VMName"})
	public void AddVMSuite(String IP,String VMName) throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {
		System.out.println("Starting Installation for VM: "+VMName+" "+this.getClass().getName()+" "+this.getClass().getSimpleName());
		String shortVMName = obj.shortVMName(VMName); 
		boolean _Check;
		System.out.println(IP);

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
		drive.findElement(By.id(locator.getProperty("SelLocation"))).click();
		Thread.sleep(450);
		obj.boundListSelect(drive, obj.readFromFile("input.properties", "AddLocationName:"), obj.selBoundList(drive));
		
		drive.findElement(By.id(locator.getProperty("SelHost"))).click();
		Thread.sleep(450);
		obj.boundListSelect(drive, obj.readFromFile("input.properties", "AddHostHostName:"), obj.selBoundList(drive));
		Thread.sleep(6000);
		drive.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.boundListSelect(drive, "data", obj.selBoundList(drive));
		Thread.sleep(2500);
		//Uptill here
		
		
		//Next Page
		obj.clickButtonxPath(drive, locator.getProperty("Next"));
		obj.comboClick(drive, "Select Software Library:","SMGR_DEFAULT_LOCAL");
		Thread.sleep(2500);
		obj.maintainedList(drive, obj.comboID(drive, "Select OVAs:"));
		obj.comboClick(drive, "Select OVAs:", VMName);
		Thread.sleep(2500);
		obj.selectFP(drive, shortVMName);
		
		//Uptill here
		
		//Next Page
		obj.clickButtonxPath(drive, locator.getProperty("Next"));
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

		obj.clickButtonxPath(drive, locator.getProperty("Deploy"));
		//obj.deployButtonClickForVM(drive);
		Thread.sleep(450);
		obj.findEULAAcceptButton(drive);
		logClass.info("Accepted EULA");

		Thread.sleep(9000);

		obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(drive, By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");

		Thread.sleep(5000);
		System.out.println("Completed adding VM "+shortVMName+" with IP "+IP);
		logClass.info("Completed adding VM "+shortVMName+" with IP "+IP);

	}

	
	@Test(dependsOnMethods={"AddVM"},description="Editing VM to given Location and Host",priority=9)
	@Parameters({"IP", "VMName"})
	public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException, JSchException, SftpException{
		obj.debugLogging("Starting to edit VM "+ VMName, "Info");
		
		execCommand exec = new execCommand();
		exec.executeCommandViaSSHConnection(IP, obj.readFromFile("inputsm.properties", "cm_login"), obj.readFromFile("inputsm.properties", "password"));
	}

	
}
