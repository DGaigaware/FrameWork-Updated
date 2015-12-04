package com.avaya.sdmclient.runnerdemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.jetty.servlet.Debug;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
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
		
	
		@Test(description="Adding Location",priority=0)
		public void AddLocation() throws IOException, InterruptedException, MyException {

			logClass.startTestCase("Add a new Location on SDM");
			obj.loginToSite(drive);
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationAdd"));
			obj.debugLogging("Adding new Location .. ", "Info");

			// Calling method find id and fill values to fill values for all the elements which can be find by one static ID, Please see the method declaration for more info
			obj.findIDandFillValues(drive, "input.properties", "AddLocation");
			Thread.sleep(250);
			obj.debugLogging("Filled values .. ", "Info");
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationSave"));
			obj.debugLogging("Checking if any errors are occurring or not .. ", "Info");

			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);
			Thread.sleep(450);
			obj.debugLogging("Location added successfully .. ", "Info");
			//obj.refreshItems(drive, "AddLocation");
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationAdd"));
			obj.debugLogging("Adding new Location .. ", "Info");

			// Calling method find id and fill values to fill values for all the elements which can be find by one static ID, Please see the method declaration for more info
			obj.findIDandFillValues(drive, "input.properties", "AddLocation1");
			Thread.sleep(250);
			obj.debugLogging("Filled values .. ", "Info");
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationSave"));
			Thread.sleep(450);
			obj.debugLogging("Location added successfully .. ", "Info");

			// Checking if any error are there or not.. If not then it will continue or it will take a screenshot and continue.
			obj.errorBox(drive, obj.checkError(drive));
			logClass.endTestCase("Added a new Location");

		}

		
		@Test(description="Editing Location",priority=1)
		public void EditLocation() throws IOException, InterruptedException, MyException{

			logClass.startTestCase("Edit Location on SDM");
			//obj.loginToSite(drive);
			// Go home .. It will refresh the page and again click on 'SDM' link .. Please check declaration part of this method for more info
			obj.goHome(drive);
			// Find location in grid i.e. right side table of locations
			obj.findLocationInGrid(drive, obj.readFromFile("input.properties", "AddLocationName:"));
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationEdit"));
			
			obj.findIDandFillValues(drive, "input.properties", "EditLocation");
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("LocationSaveEdit")));

			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationSaveEdit"));
			Thread.sleep(450);
			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);
			obj.debugLogging("Location edited successfully .. ", "Info");

			logClass.endTestCase("Edited Location");
		}


		@Test(description="Deleting Location",priority=2)
		public void DeleteLocation() throws IOException, InterruptedException, MyException{

			logClass.startTestCase("Delete Location on SMGR SDM");

			obj.goHome(drive);
			// Find 'VM management' in left tree
			obj.findLocationOrHost(drive, "VM Management");
			// Find location in left grid
			obj.findLocationInGrid(drive, obj.readFromFile("input.properties", "AddLocationName:"));

			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("LocationDelete"));
			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);
			
			// Check whether location is deleted or not
			if(!obj.checkPresenceOfLocationOrHostOrVM(drive, obj.readFromFile("input.properties", "AddLocationName:")))
				obj.debugLogging("Location deleted successfully .. ", "Info");
			else
				obj.debugLogging("Error occurred while deleting location.\nPlease Check screenshot.", "Error");

			logClass.endTestCase("Deleted Location");
		}

		
		@Test(description="Adding Host to given Location",priority=3)
		public void AddHost() throws IOException, InterruptedException, MyException{

			logClass.startTestCase("Adding Host to given Location");

			// Refresh the page and again come to SDM
			obj.goHome(drive);
			//obj.loginToSite(drive);
			
			// Check if location is present or not, if not then add that location
			if(!obj.checkPresenceOfLocationOrHostOrVM(drive, obj.readFromFile("input.properties", "AddLocationName:"))){
				drive.navigate().refresh();
				obj.logOut(drive);
				obj.debugLogging("Adding Location as location was not already added ...", "Info");
				obj.debugLogging("Pausing the current thread .. ", "Info");
				AddLocation();
				obj.debugLogging("Location added successfully. Now resuming the current thread .. ", "Info");
				obj.goHome(drive);
				//obj.loginToSite(driver);
			}

			// Find location in left grid
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddLocationName:"));
			// Choose hosts tab
			obj.chooseTab(drive, "Hosts");
			obj.debugLogging("Navigated to Host tab.", "Info");

			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("New-Host"));
			obj.debugLogging("Adding new host .. ", "Info");
			Thread.sleep(250);			
		
			// Give one static ID and it will fill all the values in that panel , ID is given in settings file
			obj.findIDandFillValues(drive, "input.properties", "AddHost");
			Thread.sleep(250);
			obj.debugLogging("Filled values .. ", "Info");
		
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("SaveHost"));
			Thread.sleep(1250);
			
			// click on refresh 
			obj.refreshItems(drive, "AddHost");
			Thread.sleep(1500);
			
			// Click on status details
			obj.chooseLink(drive, obj.readFromFile("input.properties", "AddHostHostName:"), "Host", "Status Details");
			obj.waitForPresenceOfElement(drive, By.id(locator.getProperty("vmDeployStatus")));
			
			obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "Host Create/Update Completed"),"Info");
			// To check whether host is successfully added or not .. 
			obj.StatusCheck(drive, "Host Create/Update Completed", 20);
			
			//Adding second host
			
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddLocationName:"));

			obj.chooseTab(drive, "Hosts");
			obj.debugLogging("Navigated to Host tab.", "Info");

			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("New-Host"));
			obj.debugLogging("Adding new host .. ", "Info");
			Thread.sleep(250);			
		
			obj.findIDandFillValues(drive, "input.properties", "AddHost1");
			Thread.sleep(250);
			obj.debugLogging("Filled values .. ", "Info");
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("SaveHost"));
			Thread.sleep(1250);
			
			obj.refreshItems(drive, "AddHost");
			Thread.sleep(1500);
			
			obj.chooseLink(drive, obj.readFromFile("input.properties", "AddHost1HostName:"), "Host", "Status Details");
			obj.waitForPresenceOfElement(drive, By.id(locator.getProperty("vmDeployStatus")));
			
			obj.debugLogging(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "Host Create/Update Completed"),"Info");
			obj.StatusCheck(drive, "Host Create/Update Completed", 20);

			obj.debugLogging("Hosts added successfully .. ", "Info");
			logClass.endTestCase("Added Host Successfully");
		}

		@Test(description="Editing Host to given Location",priority=4)
		public void EditHost() throws IOException, InterruptedException, MyException{

			logClass.startTestCase("Editing Host to given Location");

			obj.goHome(drive);
			//obj.loginToSite(drive);

			// find location in left grid
			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddLocationName:"));
			obj.chooseTab(drive, "Hosts");

			logClass.info("In 'Host' Tab");

			obj.findHostInGrid(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			Thread.sleep(1250);
			
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("EditHost"));
			//drive.findElement(By.xpath(locator.getProperty("EditHost"))).click();
			//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

//			obj.selectLocforEditHost(drive);
//			Thread.sleep(250);
	
			obj.findIDandFillValues(drive, "input.properties", "EditHost");
			Thread.sleep(250);
			
			//obj.checkFocus(drive, By.xpath(locator.getProperty("SaveHostEdit")));

			drive.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();
			// check whther error box is coming or not , if it comes, it will throw an error ..  
			obj.errorBox(drive, obj.checkError(drive));
			Thread.sleep(2500);
			
			obj.refreshItems(drive, "EditHost");

			logClass.endTestCase("Edited Host Successfully");

		}
		
//		@Test(description="Operations to check is the host is AVP or not and perform operations for AVP Host",priority=2)
//
//		public void AVPHostOperations() throws IOException, InterruptedException, MyException{
//			
//			logClass.startTestCase("Checking if the host is AVP or not... (ANd performing operations for the same)");
//
//			obj.loginToSite(drive);
//			//obj.goHome(drive);
//
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddLocationName:"));
//			obj.chooseTab(drive, "Hosts");
//			logClass.info("In 'Host' Tab");
//
//			obj.findHostInGrid(drive, obj.readFromFile("input.properties", "AddHost1HostName:"));
//			
//			drive.findElement(By.id("chgNetwkParamBtn")).click();
//			if(obj.checkError(drive)){
//				obj.errorBox(drive, obj.checkError(drive));
//			}
//			else{
//				System.out.println("AVP host...");
//				System.out.println("Changing network parameters..");
//				obj.changeNetworkParamsHost(drive,"input.properties","ChangeNetWorkParams");
//				obj.findButtonByName(drive, "host_params", "Change Gateway").click();
//				drive.findElement(By.id("changenetworkgateway-inputEl")).clear();
//				drive.findElement(By.id("changenetworkgateway-inputEl")).sendKeys("148.147.162.1");
//				drive.findElement(By.id("saveNetworkParam-btnIconEl")).click();
//				obj.errorBox(drive, obj.checkError(drive));
//				Thread.sleep(4500);
		// Confirm the dialogue box. Please see method declaration for more info .. 
//				obj.confirmDialogBox(drive);
//			}
//			
//			drive.findElement(By.id("chgPassBtn")).click();
//			if(obj.checkError(drive)){
//				obj.errorBox(drive, obj.checkError(drive));
//			}
//			else{
//				System.out.println("AVP host...");
//				System.out.println("Changing password..");
//				obj.changePassWordHost(drive,"input.properties","UpdatePwdHost");
//			}
//
//			drive.findElement(By.id("updateEsxiHost")).click();
//			if(obj.checkError(drive)){
//				obj.errorBox(drive, obj.checkError(drive));
//			}
//			else{
//				System.out.println("AVP host...");
//				System.out.println("Updating AVP Host..");
//				obj.updateESXiHost(drive,"input.properties","UpdateHost");
//			}
//		}
//
//		@Test(description="Deleting Host to given Location",priority=5)
//
//		@Test(description="Deleting Host to given Location",priority=5)
//		public void DeleteHost() throws IOException, InterruptedException, MyException{
//			logClass.startTestCase("Deleting Host to given Location");
//
//			obj.goHome(drive);
//
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddLocationName:"));
//
//			obj.findHostInGrid(drive, obj.readFromFile("input.properties", "AddHost1HostName:"));
//
//			obj.checkFocus(drive, By.xpath(locator.getProperty("HostDelete")));
//			
//			drive.findElement(By.xpath(locator.getProperty("HostDelete"))).click();
//
		// Confirm the dialogue box. Please see method declaration for more info .. 
//			obj.confirmDialogBox(drive); 
//
//			logClass.endTestCase("Deleted Host");
//		}

		@Test(description="Adding VM to given Location and Host",priority=8)
		@Parameters({"IP", "VMName"})
		public void AddVM(String IP,String VMName) throws Exception {
			scpFilesFromSMGR s = new scpFilesFromSMGR();
			s.scpFile();

			String shortVMName = obj.shortVMName(VMName);

			boolean _Check;

			logClass.startTestCase("Adding VM to given Location and Host");

			obj.goHome(drive);
			//obj.loginToSite(drive);

			if(!obj.checkPresenceOfLocationOrHostOrVM(drive, obj.readFromFile("input.properties", "AddHostHostName:"))){
				obj.debugLogging("Adding host as host is not added .. ", "Info");
				obj.debugLogging("Pausing the current thread .. ", "Info");
				AddHost();
				obj.debugLogging("Added new host successfully. Resuming the current thread .. ", "Info");
				obj.goHome(drive);
			}

			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.chooseTab(drive, "Virtual Machines");

			// Click button by it's xPath value
			obj.clickButtonxPath(drive,locator.getProperty("NewVM"));
			Thread.sleep(750);

			//Adding loc and host
			drive.findElement(By.id(locator.getProperty("SelLocation"))).click();
			Thread.sleep(450);
			obj.boundListSelect(drive, obj.readFromFile("input.properties", "AddLocationName:"), obj.selBoundList(drive));
			
			// Select host click
			drive.findElement(By.id(locator.getProperty("SelHost"))).click();
			Thread.sleep(450);
			// Select host name
			obj.boundListSelect(drive, obj.readFromFile("input.properties", "AddHostHostName:"), obj.selBoundList(drive));
			Thread.sleep(6000);
			// click on datastore
			drive.findElement(By.xpath(locator.getProperty("DataStore"))).click();
			Thread.sleep(250);
			// choose datastore
			obj.boundListSelect(drive, "data", obj.selBoundList(drive));
			Thread.sleep(2500);
			//Uptill here
			
			
			//Next Page
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("Next"));
			obj.comboClick(drive, "Select Software Library:","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			// Add all ova names in a file which is maintained outside of code ovanames.txt
			obj.maintainedList(drive, obj.comboID(drive, "Select OVAs:"));
			obj.comboClick(drive, "Select OVAs:", VMName);
			Thread.sleep(2500);
			// Select footprint related to each element
			obj.selectFP(drive, shortVMName);
			
			//Uptill here
			
			//Next Page
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("Next"));
			drive.findElement(By.xpath(locator.getProperty("VMName"))).clear();
			drive.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
			logClass.info("Given Name");
			Thread.sleep(250);

			_Check = obj.checkError(drive);
			String err = obj.errorBox(drive,obj.checkError(drive));

			// Check whther any error is there or not, if yes, interrupt the thread and come out of it
			if(_Check){
				System.out.println("Error:");
				Thread.currentThread().interrupt();
			}

			if(Thread.currentThread().isInterrupted()){
				System.out.println("Cannot Execute Further");
				throw new MyException(err);
			}

			// Fill all the values related to VM
			obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), drive,IP,"test"+shortVMName);

			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("Deploy"));
			//obj.deployButtonClickForVM(drive);
			Thread.sleep(450);
			// Find EULA accept button and click on that
			obj.findEULAAcceptButton(drive);
			logClass.info("Accepted EULA");
			
			// Start a new thread for concurrent installation
			final WebDriver driver1 = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				public void run() {
					settingsForConcThreads ob = new settingsForConcThreads();
					try {
						ob.runThread(driver1);
					} catch (ParserConfigurationException | SAXException | IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (MyException e) {
						e.printStackTrace();
					}
				}
			}

			MyRunnable r = new MyRunnable();
			Thread t = new Thread(r);
			t.start();

			Thread.sleep(9000);

			obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
			// Check for successful deployment or failed attempt
			obj.checkSuccessOrFailure(drive, By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");

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
			Thread.sleep(1500);
			obj.findVMForHost(drive, "test"+shortVMName);
			
			Thread.sleep(900);
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("EditVM"));
			//drive.findElement(By.xpath(locator.getProperty("EditVM"))).click();
			logClass.info("Clicked on - Edit VM");
			Thread.sleep(750);

			//driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

			obj.editVMchooseFPorFQDN(drive, "FQDN");
			drive.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

			obj.editVM(drive,IP,"test"+shortVMName+"edited");

			//obj.checkFocus(drive, By.xpath(locator.getProperty("VMEditSave")));
			// Click button by it's xPath value
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
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("StopVM"));
			//drive.findElement(By.xpath(locator.getProperty("StopVM"))).click();
			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);
			
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//			obj.chooseTab(drive, "Virtual Machines");
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
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("VMStart"));
			//drive.findElement(By.xpath(locator.getProperty("VMStart"))).click();
			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);
			
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//			obj.chooseTab(drive, "Virtual Machines");
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
			// Find 'more actions' button
			obj.findMoreActionsButton(drive);
			Thread.sleep(500);
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("VMReEstConn"));
			//drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
			
			drive.switchTo().activeElement();

//			drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
//			drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));
//
//			drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
//			drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));
//
//			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
//			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
//			Thread.sleep(250);
//			drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
//
//			Thread.sleep(5000);
//			
//			//Adding code
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//			obj.chooseTab(drive, "Virtual Machines");
//
//			obj.chooseLink(drive, "test"+shortVMName);
//			obj.StatusCheck(drive, "VM Trust Establishment Completed",50);
//
//			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));
//			Thread.sleep(1500);
//			if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
//				drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
//			//Added
//			Thread.sleep(5000);
//			
//			obj.chooseLink(drive, "test"+shortVMName);
//			System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "VM Refresh Completed"));
//			obj.StatusCheck(drive, "VM Refresh Completed", 20);
//			// Uptill Now
//			Thread.sleep(2500);
//			//obj.StatusCheck(driver, "VM Refresh Completed", 50);
//			logClass.endTestCase("VM refreshed Successfully");
			
			// give username and password according to the element
			obj.refreshVMValues(drive, shortVMName, "inputsm.properties");
			Thread.sleep(750);
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));
			Thread.sleep(250);
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("VMReEstConnConf"));
			//drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
			
			Thread.sleep(1000);
			obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
			// check whether test case is passed or failed..
			obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+shortVMName, 6, true,10,0,"Status Details");
			obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));
			Thread.sleep(1500);
			if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
				drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
			Thread.sleep(5000);
			obj.chooseLink(drive, "test"+shortVMName,"VM","Status Details");
			// check whether test case is passed or failed..
			obj.checkSuccessOrFailure(drive, By.id("vmDeployStatus"),"test"+ shortVMName, 6, true,10,0,"Status Details");		
			
			Thread.sleep(2500);
			logClass.endTestCase("VM refreshed Successfully");
		}


//		//@Test(dependsOnMethods={"AddVM"},description="Restarting VM to given Location and Host",priority=13)
//		@Test(description="Restarting VM to given Location and Host",priority=13)
//		@Parameters({"IP", "VMName"})
//		public void RestartVM(String IP,String VMName) throws IOException, InterruptedException, MyException{
//
//			String shortVMName = obj.shortVMName(VMName);
//			
//			logClass.startTestCase("Restart VM to given Location and Host");
//
//			obj.goHome(drive);
//
//			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
//
//			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
//			obj.chooseTab(drive, "Virtual Machines");
//			Thread.sleep(1500);
//			obj.findVMForHost(drive,"test"+shortVMName);
//
//			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));
		// Click button by it's xPath value
//			obj.clickButtonxPath(drive, locator.getProperty("VMRestart"));
//			//drive.findElement(By.xpath(locator.getProperty("VMRestart"))).click();
//
//			Thread.sleep(1500);
		// Confirm the dialogue box. Please see method declaration for more info .. 
//			obj.confirmDialogBox(drive);
//			
////			obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "AddHostHostName:"));
////
////			obj.chooseTab(drive, "Virtual Machines");
//			logClass.endTestCase("Restarted VM successfully");
//			Thread.sleep(100000);
//		}

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
			// Click button by it's xPath value
			obj.clickButtonxPath(drive, locator.getProperty("VMDelete"));
			//drive.findElement(By.xpath(locator.getProperty("VMDelete"))).click();
			// Confirm the dialogue box. Please see method declaration for more info .. 
			obj.confirmDialogBox(drive);

			logClass.endTestCase("Deleted VM successfully");
			
			//obj.maintainedList(driver, "combobox-1238-inputEl");
			drive.quit();
			// make the ip whitelist so that next instance can take that ip..
			obj.makeIPWhiteBlackList(IP,"WhiteList");
			
//			final WebDriver driver2 = new FirefoxDriver(obj.selectProfile("Selenium"));
//			class MyRunnable implements Runnable {
//				 public void run() {
//					 settingsForConcThreads ob = new settingsForConcThreads();
//					 try {
//						ob.runThread(driver2);
//					} catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
//						
//						e.printStackTrace();
//					} catch (MyException e) {
//						
//						e.printStackTrace();
//					}
//				 }
//			}
//			
//			MyRunnable r = new MyRunnable();
//			Thread t = new Thread(r);
//			t.start();
//			t.join();
//		
//			System.out.println("Completed All threads");
			}
		
		// Start new thread as test otherwise it will fail ..
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
			// Compulsory coz otherwise current thread will stop and subsequent child threads will stop
			t.join();
			
			System.out.println("Completed All Threads.");
		}
		
		
}
