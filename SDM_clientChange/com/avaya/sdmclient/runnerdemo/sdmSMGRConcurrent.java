package com.avaya.sdmclient.runnerdemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;
import com.avaya.sdmclient.extraResources.scpFilesFromSMGR;

public class sdmSMGRConcurrent {
	
		Settings obj = new Settings();
		WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
		Properties locator = null;
		@BeforeClass(alwaysRun=true)
		public void setup() throws IOException, InterruptedException
		{
			locator=new Properties();
			locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
		}
		
		/*
		@Test(description="Adding Location",priority=0)
		public void AddLocation() throws IOException, InterruptedException {

			logClass.startTestCase("Add a new Location on SDM");

			obj.loginToSite(driver);

			driver.findElement(By.xpath((locator.getProperty("LocationAdd")))).click();
			logClass.info("Adding new Location");

			obj.findIDandFillValues(driver, "input.properties", "AddLocation");
			Thread.sleep(250);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("LocationSave")));
			
			driver.findElement(By.xpath(locator.getProperty("LocationSave"))).click();
			logClass.info("Saved New Location");

			obj.errorBox(driver, obj.checkError(driver));
			obj.refreshItems(driver, "AddLocation");
			
			logClass.endTestCase("Added a new Location");

		}

		
		@Test(description="Editing Location",priority=1)
		public void EditLocation() throws IOException, InterruptedException{

			logClass.startTestCase("Edit Location on SDM");

			obj.goHome(driver);

			obj.findLocationInGrid(driver, "testLoc");

			driver.findElement(By.xpath(locator.getProperty("LocationEdit"))).click();

			obj.findIDandFillValues(driver, "input.properties", "EditLocation");
			
			obj.waitForPresence(driver, By.xpath(locator.getProperty("LocationSaveEdit")));
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("LocationSaveEdit")));
			
			//driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();
			driver.findElement(By.xpath(locator.getProperty("LocationSaveEdit"))).click();

			driver.switchTo().activeElement();
			driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
			obj.refreshItems(driver, "EditLocation");
			logClass.info("Saved Location");

			logClass.endTestCase("Edited Location");
		}


		@Test(description="Deleting Location",priority=2)
		public void DeleteLocation() throws IOException, InterruptedException{

			logClass.startTestCase("Delete Location on SDM");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, "VM Management");

			obj.findLocationInGrid(driver, obj.readFromFile("input.properties", "AddLocationName:"));

			obj.checkFocus(driver, By.xpath(locator.getProperty("LocationDelete")));
			
			driver.findElement(By.xpath(locator.getProperty("LocationDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted Location");
		}

		
		@Test(description="Adding Host to given Location",priority=3)
		public void AddHost() throws IOException, InterruptedException{

			logClass.startTestCase("Adding Host to given Location");

			obj.goHome(driver);
			//obj.loginToSite(driver);
			
			if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"))){
				driver.navigate().refresh();
				obj.logOut(driver);
				AddLocation();
				System.out.println("Adding Location");
				logClass.info("Location was not there. Adding it and pausing current thread.");
				obj.goHome(driver);
				//obj.loginToSite(driver);
				logClass.info("Added Location as Location was not there beforehand.");
			}

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

			driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
			logClass.info("In 'Host' Tab");

			driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
			logClass.info("Adding new Host");
			Thread.sleep(250);			
		
			obj.findIDandFillValues(driver, "input.properties", "AddHost");
			Thread.sleep(250);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHost")));
			
			driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

			obj.confirmDialogBox(driver);
			Thread.sleep(4500);
			
			obj.refreshItems(driver, "AddHost");
			Thread.sleep(1500);
			
			obj.checkSuccess(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.waitForPresence(driver, By.id(locator.getProperty("vmDeployStatus")));
			
			System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

			obj.StatusCheck(driver, "Host Create/Update Completed", 20);

			logClass.endTestCase("Added Host Successfully");
		}

		@Test(description="Editing Host to given Location",priority=4)
		public void EditHost() throws IOException, InterruptedException{

			logClass.startTestCase("Editing Host to given Location");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
			driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
			logClass.info("In 'Host' Tab");

			obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();
			//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

			obj.selectLocforEditHost(driver);
			Thread.sleep(250);
	
			obj.findIDandFillValues(driver, "input.properties", "EditHost");
			Thread.sleep(250);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHostEdit")));

			driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

			obj.errorBox(driver, obj.checkError(driver));
			Thread.sleep(2500);
			
			obj.refreshItems(driver, "EditHost");
			
			driver.findElement(By.id("chgNetwkParamBtn")).click();
			obj.errorBox(driver, obj.checkError(driver));
	
			driver.findElement(By.id("chgPassBtn")).click();
			obj.errorBox(driver, obj.checkError(driver));
			
			driver.findElement(By.id("updateEsxiHost")).click();
			obj.errorBox(driver, obj.checkError(driver));

			logClass.endTestCase("Edited Host Successfully");

		}

		@Test(description="Deleting Host to given Location",priority=5)

		public void DeleteHost() throws IOException, InterruptedException{
			logClass.startTestCase("Deleting Host to given Location");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationCity:"));

			obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.checkFocus(driver, By.xpath(locator.getProperty("HostDelete")));
			
			driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted Host");
		}*/

		@Test(description="Adding VM to given Location and Host",priority=8)
		@Parameters({"IP", "VMName"})
		public void AddVM(String IP,String VMName) throws Exception {

			scpFilesFromSMGR s = new scpFilesFromSMGR();
			s.scpFile();
			
			String shortVMName = obj.shortVMName(VMName);
			
			boolean _Check;

			JavascriptExecutor js = (JavascriptExecutor)drive;
			js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

			logClass.startTestCase("Adding VM to given Location and Host");

			//obj.goHome(driver);
			obj.loginToSite(drive);

			if(obj.checkLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"))){
				//AddHost();
				System.out.println("Adding Host");
				obj.goHome(drive);
				logClass.info("Added Host as Location was not there beforehand.");
			}
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			
			drive.findElement(By.xpath(locator.getProperty("NewVM"))).click();
			logClass.info("Clicked on - Add new VM");
			Thread.sleep(750);

			drive.findElement(By.xpath(locator.getProperty("VMName"))).clear();
			//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.properties", "VMName"));
			drive.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
			logClass.info("Given Name");
			Thread.sleep(250);

			obj.errorBox(drive,obj.checkError(drive));

			drive.findElement(By.xpath(locator.getProperty("DataStore"))).click();
			Thread.sleep(250);
			
			obj.errorBox(drive,obj.checkError(drive));

			obj.boundListSelect(drive, "data", obj.selBoundListID(drive));
			Thread.sleep(2500);
			
			obj.comboBoxClickAndSelectValue(drive, "Select Software Library:","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			obj.maintainedListOfOVA(drive, obj.comboBoxID(drive, "Select OVAs:"));
			
			obj.comboBoxClickAndSelectValue(drive, "Select OVAs:", VMName);
			Thread.sleep(2500);
			
//			driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
//			Thread.sleep(450);
			//obj.boundListSelect(driver, obj.readFromFile("input.properties", "MinProfile"), obj.selBoundList(driver));
			obj.selectFP(drive, shortVMName);
			
			_Check = obj.checkError(drive);
			String err = obj.errorBox(drive,obj.checkError(drive));

			//driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
			//obj.checkFailureOfHostCapacity(driver);

			//obj.exec(!_Check);
			if(_Check){
				System.out.println("Error:");
				Thread.currentThread().interrupt();
			}
			
			if(Thread.currentThread().isInterrupted()){
				System.out.println("Cannot Execute Further");
				throw new MyException(err);
			}
			
			//removed
			
			obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), drive,IP,"test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("Deploy")));

			//driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

			obj.deployButtonClickForVM(drive);
			Thread.sleep(450);
			obj.findAcceptButtonForEULA(drive,"EulaAgreementWindow");
			logClass.info("Accepted EULA");
			
			//Adding code to get the same page as before
			Thread.sleep(5500);
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
			obj.chooseTab(drive, "Virtual Machines");
			
			//Adding Code for concurrency
			
			final WebDriver driver1 = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				 public void run() {
					 settingsForConcThreads ob = new settingsForConcThreads();
					 try {
						ob.runThread(driver1);
					} catch (ParserConfigurationException | SAXException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
			
			MyRunnable r = new MyRunnable();
			Thread t = new Thread(r);
			t.start();

			Thread.sleep(9000);

			obj.chooseTab(drive, "Virtual Machines");
			Thread.sleep(1500);
			
			obj.findVMForHost(drive, "test"+shortVMName);
			Thread.sleep(4500);

			obj.chooseLink(drive, "test"+shortVMName,"Status Details");
			logClass.info("Checking Status Details");

			obj.waitForPresenceOfElement(drive, By.id(locator.getProperty("vmDeployStatus")));
			
			drive.switchTo().activeElement();
			System.out.println(drive.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

			System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), drive, 1500, "Completed","test"+shortVMName));
			Thread.sleep(1000);
			//obj.StatusCheck(driver, "VM Deployment Completed", 20);
			obj.closeWindow(drive);
			Thread.sleep(5000);
			System.out.println("Completed adding VM "+shortVMName+" with IP "+IP);
			logClass.info("Completed adding VM "+shortVMName+" with IP "+IP);
			
		}

	
		//@Test(dependsOnMethods={"AddVM"},description="Editing VM to given Location and Host",priority=9)
		@Test(description="Editing VM to given Location and Host",priority=9)
		@Parameters({"IP", "VMName"})
		public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
			System.out.println("Starting to edit VM "+ VMName);
			logClass.info("Starting to edit VM "+ VMName);
			String shortVMName = obj.shortVMName(VMName);
			
			logClass.startTestCase("Editing VM to given Location and Host");

			//obj.loginToSite(driver);
			obj.goHome(drive);

			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			obj.findVMForHost(drive, "test"+shortVMName);
			
			drive.findElement(By.xpath(locator.getProperty("EditVM"))).click();
			logClass.info("Clicked on - Edit VM");
			Thread.sleep(750);

			//driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

			obj.editVMchooseFPorFQDN(drive, "FQDN");
			drive.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

			obj.editVM(drive,IP,"test"+shortVMName+"edited");
			
			/*driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).sendKeys(IP);

			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).sendKeys(shortVMName+"edited");*/

			obj.checkFocusOfElement(drive, By.xpath(locator.getProperty("VMEditSave")));
			drive.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

			obj.errorBox(drive, obj.checkError(drive));
			logClass.endTestCase("Edited VM Successfully");
		}


		//@Test(dependsOnMethods={"AddVM"},description="Stoping VM to given Location and Host",priority=10)
		@Test(description="Stoping VM to given Location and Host",priority=10)
		@Parameters({"IP", "VMName"})
		public void StopVM(String IP,String VMName) throws InterruptedException, IOException{

			String shortVMName = obj.shortVMName(VMName);
			
			Thread.sleep(5000);
			logClass.startTestCase("Stop VM to given Location and Host");

			obj.goHome(drive);
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			obj.findVMForHost(drive,"test"+shortVMName);
			
			Thread.sleep(1500);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));

			drive.findElement(By.xpath(locator.getProperty("StopVM"))).click();

			obj.confirmDialogBox(drive);
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			logClass.endTestCase("Stopped VM successfully");
			Thread.sleep(60000);
		}


		//@Test(dependsOnMethods={"AddVM"},description="Starting VM to given Location and Host",priority=11)
		@Test(description="Starting VM to given Location and Host",priority=11)
		@Parameters({"IP", "VMName"})
		public void StartVM(String IP,String VMName) throws InterruptedException, IOException{

			String shortVMName = obj.shortVMName(VMName);
			
			Thread.sleep(5000);
			logClass.startTestCase("Start VM to given Location and Host");

			obj.goHome(drive);

			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			obj.findVMForHost(drive,"test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			drive.findElement(By.xpath(locator.getProperty("VMStart"))).click();

			obj.confirmDialogBox(drive);
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			logClass.endTestCase("Started VM successfully");
			Thread.sleep(60000);
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
			obj.findVMForHost(drive,"test"+shortVMName);

			//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
			
			obj.findMoreActionsButton(drive);
			Thread.sleep(500);
			drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
			
			drive.switchTo().activeElement();

			/*drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
			drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));

			drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
			drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));

			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
			Thread.sleep(250);
			drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

			Thread.sleep(5000);
			
			//Adding code
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.chooseTab(drive, "Virtual Machines");

			obj.chooseLink(drive, "test"+shortVMName);
			obj.StatusCheck(drive, "VM Trust Establishment Completed",50);

			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));
			Thread.sleep(1500);
			if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
				drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
			//Added
			Thread.sleep(5000);
			
			obj.chooseLink(drive, "test"+shortVMName);
			System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "VM Refresh Completed"));
			obj.StatusCheck(drive, "VM Refresh Completed", 20);
			// Uptill Now
			Thread.sleep(2500);
			//obj.StatusCheck(driver, "VM Refresh Completed", 50);
			logClass.endTestCase("VM refreshed Successfully");*/
			
			obj.refreshVMValues(drive, shortVMName, "inputsm.properties");
			Thread.sleep(750);
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
			Thread.sleep(250);
			drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
			
			Thread.sleep(1000);
			obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+shortVMName, 6, true,10);
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
			Thread.sleep(1500);
			if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
				drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
			Thread.sleep(5000);
			
			obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+ shortVMName, 6, true,10);		
			
			Thread.sleep(2500);
			logClass.endTestCase("VM refreshed Successfully");
		}


		//@Test(dependsOnMethods={"AddVM"},description="Restarting VM to given Location and Host",priority=13)
		@Test(description="Restarting VM to given Location and Host",priority=13)
		@Parameters({"IP", "VMName"})
		public void RestartVM(String IP,String VMName) throws IOException, InterruptedException{

			String shortVMName = obj.shortVMName(VMName);
			
			logClass.startTestCase("Restart VM to given Location and Host");

			obj.goHome(drive);

			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			obj.findVMForHost(drive,"test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			drive.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

			obj.confirmDialogBox(drive);
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			logClass.endTestCase("Restarted VM successfully");
			Thread.sleep(100000);
		}

		//@Test(dependsOnMethods={"AddVM"},description="Deleting VM to given Location and Host",priority=14)
		@Test(description="Deleting VM to given Location and Host",priority=14)
		@Parameters({"IP", "VMName"})
		public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException{

			logClass.startTestCase("Delete VM to given Location and Host");

			String shortVMName = obj.shortVMName(VMName);
			
			obj.goHome(drive);

			//obj.loginToSite(driver);
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "HostName"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(drive, "Virtual Machines");
			obj.findVMForHost(drive, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));

			drive.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

			obj.confirmDialogBox(drive);

			logClass.endTestCase("Deleted VM successfully");
			
			//obj.maintainedList(driver, "combobox-1238-inputEl");
			drive.quit();
			
			obj.makeIPWhiteBlackList(IP,"WhiteList");
			
			/*final WebDriver driver2 = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				 public void run() {
					 settingsForConcThreads ob = new settingsForConcThreads();
					 try {
						ob.runThread(driver2);
					} catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
			
			MyRunnable r = new MyRunnable();
			Thread t = new Thread(r);
			t.start();
			t.join();
		
			System.out.println("Completed All threads");*/
			}
		
		
		@Test(description="Starting New Thread",priority=100)
		@Parameters({"IP", "VMName"})
		public void startNewThread(String IP,String VMName) throws InterruptedException{
			//Thread.sleep(4500);
			System.out.println("Completed Opertaions for: "+VMName+" With IP: "+IP);

			final WebDriver driver3 = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				 public void run() {
					 settingsForConcThreads ob = new settingsForConcThreads();
					 try {
						ob.runThread(driver3);
					} catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
						e.printStackTrace();
					} catch (MyException e) {
						e.printStackTrace();
					}
				 }
			}
			
			MyRunnable r = new MyRunnable();
			Thread t = new Thread(r);
			t.start();
			t.join();
			
			System.out.println("Completed All Threads.");
		}
		
		
}
