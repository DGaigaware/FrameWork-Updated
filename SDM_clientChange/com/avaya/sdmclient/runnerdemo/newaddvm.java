package com.avaya.sdmclient.runnerdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.extraResources.scpFilesFromSMGR;

public class newaddvm {
	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	/*public void AddVM(String IP,String VMName) throws Exception {
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

	
	*/
	
	
	@Test(description="Editing VM to given Location and Host",priority=9)
	@Parameters({"IP", "VMName"})
	public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
		System.out.println("Starting to edit VM "+ VMName);
		logClass.info("Starting to edit VM "+ VMName);
		String shortVMName = obj.shortVMName(VMName);
		
		logClass.startTestCase("Editing VM to given Location and Host");

		obj.loginToSite(drive);
		//obj.goHome(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(drive, "test"+shortVMName);
		
		Thread.sleep(900);
		obj.clickButtonxPath(drive, locator.getProperty("EditVM"));
		//drive.findElement(By.xpath(locator.getProperty("EditVM"))).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);

		//driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

		obj.editVMchooseFPorFQDN(drive, "FQDN");
		drive.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

		obj.editVM(drive,IP,"test"+shortVMName+"edited");

		//obj.checkFocus(drive, By.xpath(locator.getProperty("VMEditSave")));
		obj.clickButtonxPath(drive, locator.getProperty("VMEditSave"));
		//drive.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

		obj.errorBox(drive, obj.checkError(drive));
		logClass.endTestCase("Edited VM Successfully");
	}


	//@Test(dependsOnMethods={"AddVM"},description="Stoping VM to given Location and Host",priority=10)
	@Test(description="Stoping VM to given Location and Host",priority=10)
	@Parameters({"IP", "VMName"})
	public void StopVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

		String shortVMName = obj.shortVMName(VMName);
		
		Thread.sleep(5000);
		logClass.startTestCase("Stop VM to given Location and Host");

		obj.goHome(drive);
		
		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(drive,"test"+shortVMName);
		
		Thread.sleep(1500);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));

		obj.clickButtonxPath(drive, locator.getProperty("StopVM"));
		//drive.findElement(By.xpath(locator.getProperty("StopVM"))).click();

		obj.confirmDialogBox(drive);
		
//		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(drive, "Virtual Machines");
		logClass.endTestCase("Stopped VM successfully");
		Thread.sleep(60000);
	}

	//@Test(dependsOnMethods={"AddVM"},description="Starting VM to given Location and Host",priority=11)
	@Test(description="Starting VM to given Location and Host",priority=11)
	@Parameters({"IP", "VMName"})
	public void StartVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

		String shortVMName = obj.shortVMName(VMName);
		
		Thread.sleep(5000);
		logClass.startTestCase("Start VM to given Location and Host");

		obj.goHome(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(drive,"test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

		obj.clickButtonxPath(drive, locator.getProperty("VMStart"));
		//drive.findElement(By.xpath(locator.getProperty("VMStart"))).click();

		obj.confirmDialogBox(drive);
		
//		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(drive, "Virtual Machines");
		logClass.endTestCase("Started VM successfully");
		Thread.sleep(90000);
	}


	//@Test(dependsOnMethods={"AddVM"},description="Refreshing VM to given Location and Host",priority=12)
	@Test(description="Refreshing VM to given Location and Host",priority=12)
	@Parameters({"IP", "VMName"})
	public void RefreshVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

		String shortVMName = obj.shortVMName(VMName);
		
		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		obj.goHome(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(drive,"test"+shortVMName);

		//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		
		obj.findMoreActionsButton(drive);
		Thread.sleep(500);
		obj.clickButtonxPath(drive, locator.getProperty("VMReEstConn"));
		//drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		drive.switchTo().activeElement();

//		drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
//		drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));
//
//		drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
//		drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));
//
//		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
//		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
//		Thread.sleep(250);
//		drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
//
//		Thread.sleep(5000);
//		
//		//Adding code
//		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(drive, "Virtual Machines");
//
//		obj.chooseLink(drive, "test"+shortVMName);
//		obj.StatusCheck(drive, "VM Trust Establishment Completed",50);
//
//		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
//		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));
//		Thread.sleep(1500);
//		if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
//			drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
//		//Added
//		Thread.sleep(5000);
//		
//		obj.chooseLink(drive, "test"+shortVMName);
//		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "VM Refresh Completed"));
//		obj.StatusCheck(drive, "VM Refresh Completed", 20);
//		// Uptill Now
//		Thread.sleep(2500);
//		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
//		logClass.endTestCase("VM refreshed Successfully");
		
		obj.refreshVMValues(drive, shortVMName, "inputsm.properties");
		Thread.sleep(750);
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
		Thread.sleep(250);
		obj.clickButtonxPath(drive, locator.getProperty("VMReEstConnConf"));
		//drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
		
		Thread.sleep(1000);
		obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+shortVMName, 6, true,10,0,"Status Details");
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
		Thread.sleep(1500);
		if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		Thread.sleep(5000);
		obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+ shortVMName, 6, true,10,0,"Status Details");		
		
		Thread.sleep(2500);
		logClass.endTestCase("VM refreshed Successfully");
	}


	//@Test(dependsOnMethods={"AddVM"},description="Restarting VM to given Location and Host",priority=13)
	@Test(description="Restarting VM to given Location and Host",priority=13)
	@Parameters({"IP", "VMName"})
	public void RestartVM(String IP,String VMName) throws IOException, InterruptedException, MyException{

		String shortVMName = obj.shortVMName(VMName);
		
		logClass.startTestCase("Restart VM to given Location and Host");

		obj.goHome(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(drive,"test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));
		obj.clickButtonxPath(drive, locator.getProperty("VMRestart"));
		//drive.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

		Thread.sleep(1500);
		obj.confirmDialogBox(drive);
		
//		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(drive, "Virtual Machines");
		logClass.endTestCase("Restarted VM successfully");
		Thread.sleep(100000);
	}

	//@Test(dependsOnMethods={"AddVM"},description="Deleting VM to given Location and Host",priority=14)
	@Test(description="Deleting VM to given Location and Host",priority=14)
	@Parameters({"IP", "VMName"})
	public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Delete VM to given Location and Host");

		String shortVMName = obj.shortVMName(VMName);
		
		obj.goHome(drive);

		//obj.loginToSite(drive);
		
		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(drive, "Virtual Machines");
		Thread.sleep(1550);
		obj.findVMForHost(drive, "test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));
		obj.clickButtonxPath(drive, locator.getProperty("VMDelete"));
		//drive.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

		obj.confirmDialogBox(drive);

		logClass.endTestCase("Deleted VM successfully");
		
		//obj.maintainedList(driver, "combobox-1238-inputEl");
		drive.quit();
		
		obj.makeIPWhiteBlackList(IP,"WhiteList");
		
//		final WebDriver driver2 = new FirefoxDriver(obj.selectProfile("Selenium"));
//		class MyRunnable implements Runnable {
//			 public void run() {
//				 settingsForConcThreads ob = new settingsForConcThreads();
//				 try {
//					ob.runThread(driver2);
//				} catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
//					
//					e.printStackTrace();
//				} catch (MyException e) {
//					
//					e.printStackTrace();
//				}
//			 }
//		}
//		
//		MyRunnable r = new MyRunnable();
//		Thread t = new Thread(r);
//		t.start();
//		t.join();
//	
//		System.out.println("Completed All threads");
		}
	
}
