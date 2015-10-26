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
import com.avaya.sdmclient.vm.VM;

	public class SecondThread {
		
		Settings obj = new Settings();
		WebDriver driverC = new FirefoxDriver(obj.selectProfile("Selenium"));
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
			
			JavascriptExecutor js = (JavascriptExecutor)driverC;
			js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

			logClass.startTestCase("Adding VM to given Location and Host");

			//obj.goHome(driver);
			obj.loginToSite(driverC);

			if(!obj.checkPresenceOfLocationOrHostOrVM(driverC, obj.readFromFile("input.properties", "AddHostHostName:"))){
				//addHost1();
				System.out.println("Adding Host");
				obj.goHome(driverC);
				logClass.info("Added Host as Location was not there beforehand.");
			}
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			
			obj.chooseTab(driverC, "Virtual Machines");
			driverC.findElement(By.xpath(locator.getProperty("NewVM"))).click();
			logClass.info("Clicked on - Add new VM");
			Thread.sleep(750);

			driverC.findElement(By.xpath(locator.getProperty("VMName"))).clear();
			driverC.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
			
			logClass.info("Given Name");
			Thread.sleep(250);

			obj.errorBox(driverC,obj.checkError(driverC));

			driverC.findElement(By.xpath(locator.getProperty("DataStore"))).click();
			Thread.sleep(250);
			
			obj.errorBox(driverC,obj.checkError(driverC));

			obj.boundListSelect(driverC, "data", obj.selBoundList(driverC));
			Thread.sleep(1500);
			
			/*obj.comboClick(driver, "combobox-1235","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			obj.comboClick(driver, "combobox-1238", VMName);
			Thread.sleep(2500);*/
			
			obj.comboClick(driverC, "Select Software Library:","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			//obj.maintainedList(driver, obj.comboID(driver, "Select OVAs:"));
			
			obj.comboClick(driverC, "Select OVAs:", VMName);
			Thread.sleep(2500);
			
			/*driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
			Thread.sleep(450);
			obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));*/
			
			obj.selectFP(driverC, shortVMName);

			_Check = obj.checkError(driverC);
			obj.errorBox(driverC,obj.checkError(driverC));

			//driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
			//obj.checkFailureOfHostCapacity(driver);

			obj.exec(!_Check);

			obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), driverC,IP,"test"+shortVMName);

			obj.deployButtonClickForVM(driverC);
			Thread.sleep(450);
			obj.findButton(driverC);
			logClass.info("Accepted EULA");
			//Adding Code
			Thread.sleep(4500);
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));
			
			obj.chooseTab(driverC, "Virtual Machines");
			Thread.sleep(3000);
			
			obj.findVMForHost(driverC, "test"+shortVMName);
			Thread.sleep(4500);

			
			obj.checkSuccessOrFailure(driverC, By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");
//			obj.chooseLink(driverC, "test"+shortVMName,"VM","Status Details");
//			logClass.info("Checking Status Details");
//			
//			obj.waitForPresenceOfElement(driverC, By.id(locator.getProperty("vmDeployStatus")));
//			
//			driverC.switchTo().activeElement();
//			System.out.println(driverC.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());
//
//			System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driverC, 1500, "Completed","test"+shortVMName,"VM","Status Details"));
//			Thread.sleep(1000);
			//obj.StatusCheck(driver, "VM Deployment Completed", 20);
			//obj.closeWindow(driverC);
			Thread.sleep(5000);

		}


		@Test(description="Editing VM to given Location and Host",priority=9)
		@Parameters({"IP", "VMName"})
		public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

			String shortVMName = obj.shortVMName(VMName); 
			
			logClass.startTestCase("Editing VM to given Location and Host");

			//obj.loginToSite(driver);
			obj.goHome(driverC);

			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC, "test"+shortVMName);
			obj.clickButtonxPath(driverC, locator.getProperty("EditVM"));
			//driverC.findElement(By.xpath(locator.getProperty("EditVM"))).click();
			logClass.info("Clicked on - Edit VM");
			Thread.sleep(750);

			//driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();
			
			obj.editVMchooseFPorFQDN(driverC, "FQDN");
			obj.clickButtonxPath(driverC, locator.getProperty("EditIPFQDNVMButton"));
			//driverC.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

			obj.editVM(driverC,IP,"test"+shortVMName+"edited");

			obj.checkFocus(driverC, By.xpath(locator.getProperty("VMEditSave")));
			driverC.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

			obj.errorBox(driverC, obj.checkError(driverC));
			logClass.endTestCase("Edited VM Successfully");
		}


		@Test(description="Stoping VM to given Location and Host",priority=10)
		@Parameters({"IP", "VMName"})
		public void StopVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
			String shortVMName = obj.shortVMName(VMName);
			Thread.sleep(5000);
			logClass.startTestCase("Stop VM to given Location and Host");

			obj.goHome(driverC);
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC, "test"+shortVMName);
			
			Thread.sleep(1500);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));
			obj.clickButtonxPath(driverC, locator.getProperty("StopVM"));
			//driverC.findElement(By.xpath(locator.getProperty("StopVM"))).click();

			obj.confirmDialogBox(driverC);
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(driverC, "Virtual Machines");
			logClass.endTestCase("Stopped VM successfully");
			Thread.sleep(60000);
		}


		@Test(description="Starting VM to given Location and Host",priority=11)
		@Parameters({"IP", "VMName"})
		public void StartVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
			String shortVMName = obj.shortVMName(VMName);
			Thread.sleep(5000);
			logClass.startTestCase("Start VM to given Location and Host");

			obj.goHome(driverC);

			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));
			obj.clickButtonxPath(driverC, locator.getProperty("VMStart"));
			//driverC.findElement(By.xpath(locator.getProperty("VMStart"))).click();

			obj.confirmDialogBox(driverC);
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			obj.chooseTab(driverC, "Virtual Machines");
			logClass.endTestCase("Started VM successfully");
			Thread.sleep(90000);
		}


		@Test(description="Refreshing VM to given Location and Host",priority=12)
		@Parameters({"IP", "VMName"})
		public void RefreshVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
			String shortVMName = obj.shortVMName(VMName);
			Thread.sleep(5000);
			logClass.startTestCase("Refresh VM to given Location and Host");

			obj.goHome(driverC);

			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC,"test"+shortVMName);

			//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
			
			obj.findMoreActionsButton(driverC);
			Thread.sleep(500);
			obj.clickButtonxPath(driverC, locator.getProperty("VMReEstConn"));
			//driverC.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			
			obj.waitForPresenceOfElement(driverC, By.xpath(locator.getProperty("VMReEstConnUN")));
			
			driverC.switchTo().activeElement();

//			/*driverC.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
//			driverC.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));
//
//			driverC.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
//			driverC.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));
//
//			obj.waitForPresenceOfElement(driverC, By.xpath(locator.getProperty("VMReEstConnConf")));
//			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
//			Thread.sleep(250);
//			driverC.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
//
//			Thread.sleep(5000);
//			
//			obj.chooseLink(driverC, "test"+shortVMName);
//			//driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
//			obj.StatusCheck(driverC, "VM Trust Establishment Completed",50);
//
//			obj.waitForPresenceOfElement(driverC, By.xpath(locator.getProperty("RefreshVM")));
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));
//			Thread.sleep(1500);
//			if(driverC.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
//				driverC.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
//			//Added
//			Thread.sleep(5000);
//			
//			obj.chooseLink(driverC, "test"+shortVMName);
//			System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driverC, 50, "VM Refresh Completed"));
//			obj.StatusCheck(driverC, "VM Refresh Completed", 20);
//			// Uptill Now
//			Thread.sleep(2500);
//			//obj.StatusCheck(driver, "VM Refresh Completed", 50);
//			logClass.endTestCase("VM refreshed Successfully");*/
			
			obj.refreshVMValues(driverC, shortVMName, "inputsm.properties");
			Thread.sleep(750);
			obj.waitForPresenceOfElement(driverC, By.xpath(locator.getProperty("VMReEstConnConf")));
			Thread.sleep(250);
			obj.clickButtonxPath(driverC, locator.getProperty("VMReEstConnConf"));
			//driverC.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
			
			Thread.sleep(1000);
			obj.checkSuccessOrFailure(driverC, By.id("vmDeployStatus"),"test"+shortVMName, 6, true,10,0,"Status Details");
			obj.waitForPresenceOfElement(driverC, By.xpath(locator.getProperty("RefreshVM")));
			Thread.sleep(1500);
			if(driverC.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
				driverC.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
			Thread.sleep(5000);
			
			obj.checkSuccessOrFailure(driverC, By.id("vmDeployStatus"),"test"+ shortVMName, 8, true,10,0,"Status Details");		
			
			Thread.sleep(2500);
			logClass.endTestCase("VM refreshed Successfully");
		}


		@Test(description="Restarting VM to given Location and Host",priority=13)
		@Parameters({"IP", "VMName"})
		public void RestartVM(String IP,String VMName) throws IOException, InterruptedException, MyException{
			String shortVMName = obj.shortVMName(VMName);
			logClass.startTestCase("Restart VM to given Location and Host");

			obj.goHome(driverC);

			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));
			obj.clickButtonxPath(driverC, locator.getProperty("VMRestart"));
			//driverC.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

			obj.confirmDialogBox(driverC);
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.chooseTab(driverC, "Virtual Machines");

			logClass.endTestCase("Restarted VM successfully");
			Thread.sleep(100000);
		}


		@Test(description="Deleting VM to given Location and Host",priority=14)
		@Parameters({"IP", "VMName"})
		public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException, MyException{
			String shortVMName = obj.shortVMName(VMName);
			logClass.startTestCase("Delete VM to given Location and Host");

			obj.goHome(driverC);

			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.chooseTab(driverC, "Virtual Machines");
			obj.findVMForHost(driverC, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));
			obj.clickButtonxPath(driverC, locator.getProperty("VMDelete"));
			//driverC.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

			obj.confirmDialogBox(driverC);
			
			obj.findLocationOrHost(driverC, obj.readFromFile("input.properties", "AddHostHostName:"));

			obj.chooseTab(driverC, "Virtual Machines");

			logClass.endTestCase("Deleted VM successfully");
			
			driverC.quit();
			
			obj.makeIPWhiteBlackList(IP, "WhiteList");
			
			/*final WebDriver driver2 = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				 public void run() {
					 settingsForConcThreads ob = new settingsForConcThreads();
					 try {
						ob.runThread(driver2);
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
			t.join();*/
		}
		
	@Test(description="Starting New Thread",priority=15)
	@Parameters({"IP", "VMName"})
		public void startNewThread(String IP,String VMName) throws InterruptedException{
			//Thread.sleep(4500);
		System.out.println("Completed Opertaions for: "+VMName+" With IP: "+IP);
	
		final WebDriver drv = new FirefoxDriver(obj.selectProfile("Selenium"));
			class MyRunnable implements Runnable {
				 public void run() {
					 settingsForConcThreads ob = new settingsForConcThreads();
					 try {
						ob.runThread(drv);
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
			
		}

}
