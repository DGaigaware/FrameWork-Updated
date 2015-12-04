package com.avaya.sdmclient.automation;

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
import com.avaya.sdmclient.host.Host;
import com.avaya.sdmclient.location.Location;
import com.avaya.sdmclient.location.driverGetter;

public class total extends Location {
	Settings obj = new Settings();
	Properties locator = null;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
//		if(driver!=null){
//			driver = new FirefoxDriver(obj.selectProfile("Selenium"));
//		}
//		else{
//			obj.debugLogging("Driver is already initialised .. \n Performing op", "Info");
//		}
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	//WebDriver driver = driverGetter.driver;
	@Test(description="Adding VM to given Location and Host",priority=8)
	@Parameters({"IP", "VMName"})
	public void addVM(String IP,String VMName) throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {

		String shortVMName = obj.shortVMName(VMName);
		
		boolean _Check;

		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";

		logClass.startTestCase("Adding VM to given Location and Host");

		obj.goToSDMCliURL(getDriver());

		if(!obj.checkPresenceOfLocationOrHostOrVM(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"))){
			getDriver().navigate().refresh();
			//Host host = new Host();
			//host.addHost();
			System.out.println("Adding Host");
			obj.goToSDMCliURL(getDriver());
			logClass.info("Added Host as host was not there beforehand.");
		}
		
		
		//
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		
		getDriver().findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		if(getDriver().findElement(By.id(locator.getProperty("MEDeploy"))).isEnabled()){
			getDriver().findElement(By.id(locator.getProperty("MEDeploy"))).click();
			System.out.println("Clicked");
			obj.confirmDialogBox(getDriver());
		}
		
		getDriver().findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.properties", "VMName"));
		getDriver().findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(getDriver(),obj.checkError(getDriver()));

		getDriver().findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(getDriver(),obj.checkError(getDriver()));

		obj.boundListSelect(getDriver(), "data", obj.selBoundList(getDriver()));
		Thread.sleep(2500);
		//
		//1 - File Path; 3 - SW Library; 4 - URL
				switch(_SWLib){
				case _default:
					getDriver().findElement(By.xpath(locator.getProperty("OVAFilePath"))).click();
					logClass.info("Choosen From File");

					getDriver().findElement(By.xpath(locator.getProperty("FilePathField"))).clear();
					getDriver().findElement(By.xpath(locator.getProperty("FilePathField"))).sendKeys(obj.readFromFile("input.properties", "SMFilePath"));
					getDriver().findElement(By.xpath(locator.getProperty("FilePathSubmit"))).click();
					logClass.info("File Path Given");
					Thread.sleep(2500);
					break;
				case _URL:
					getDriver().findElement(By.xpath(locator.getProperty("OVAURL"))).click();
					logClass.info("Choosen From URL");

					getDriver().findElement(By.xpath(locator.getProperty("URLInput"))).clear();
					getDriver().findElement(By.xpath(locator.getProperty("URLInput"))).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
					getDriver().findElement(By.xpath(locator.getProperty("URLSubmit"))).click();
					Thread.sleep(450);
					break;
				case _SWLib:
					getDriver().findElement(By.xpath(locator.getProperty("OVASWLib"))).click();
					logClass.info("Choosen From Software Library");

					//driver.findElement(By.xpath(locator.getProperty("SWLibSelect"))).click();
					obj.clickComboSDMCli(getDriver(), "File Name:");
					obj.boundListSelect(getDriver(),VMName, obj.selBoundList(getDriver()));
					break;
				}
		
		Thread.sleep(7500);
		obj.selectFP(getDriver(), shortVMName);
		
		_Check = obj.checkError(getDriver());
		String err = obj.errorBox(getDriver(),obj.checkError(getDriver()));

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
		
		obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), getDriver(),IP,"test"+shortVMName);

		obj.deployButtonClickForVM(getDriver());
		Thread.sleep(450);
		obj.findEULAAcceptButton(getDriver());
		logClass.info("Accepted EULA");
	
		Thread.sleep(7000);
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		
		obj.findVMForHost(getDriver(), "test"+shortVMName);
		Thread.sleep(4500);
		obj.chooseLink(getDriver(), "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(getDriver(), By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");

		Thread.sleep(5000);
		System.out.println("Completed adding VM "+shortVMName+" with IP "+IP);
		logClass.info("Completed adding VM "+shortVMName+" with IP "+IP);

	}


	@Test(description="Editing VM to given Location and Host",priority=9)
	@Parameters({"IP", "VMName"})
	public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

		logClass.startTestCase("Editing VM to given Location and Host");

		obj.goToSDMCliURL(getDriver());
		obj.debugLogging("Starting to edit VM .. "+VMName, "Info");
		String shortVMName = obj.shortVMName(VMName);

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(getDriver(), "test"+shortVMName);
		Thread.sleep(900);
		
		obj.clickButtonxPath(getDriver(), locator.getProperty("EditVM"));
		Thread.sleep(750);
		obj.editVMchooseFPorFQDN(getDriver(), "FQDN");
		getDriver().findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

		obj.editVM(getDriver(),IP,"test"+shortVMName+"edited");
		obj.clickButtonxPath(getDriver(), locator.getProperty("VMEditSave"));
		//getDriver().findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

		obj.errorBox(getDriver(), obj.checkError(getDriver()));
		logClass.endTestCase("Edited VM Successfully");
	}


	@Test(description="Stoping VM to given Location and Host",priority=10)
	@Parameters({"IP", "VMName"})
	public void StopVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

		Thread.sleep(5000);
		logClass.startTestCase("Stop VM to given Location and Host");
		String shortVMName = obj.shortVMName(VMName);
		obj.goToSDMCliURL(getDriver());
		
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(getDriver(),"test"+shortVMName);
		
		Thread.sleep(1500);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));

		obj.clickButtonxPath(getDriver(), locator.getProperty("StopVM"));
		//getDriver().findElement(By.xpath(locator.getProperty("StopVM"))).click();

		obj.confirmDialogBox(getDriver());
		
//		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(getDriver(), "Virtual Machines");
		logClass.endTestCase("Stopped VM successfully");
		Thread.sleep(60000);
	}


	@Test(description="Starting VM to given Location and Host",priority=11)
	@Parameters({"IP", "VMName"})
	public void StartVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
		String shortVMName = obj.shortVMName(VMName);
		Thread.sleep(5000);
		logClass.startTestCase("Start VM to given Location and Host");

		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(getDriver(),"test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

		obj.clickButtonxPath(getDriver(), locator.getProperty("VMStart"));
		//getDriver().findElement(By.xpath(locator.getProperty("VMStart"))).click();

		obj.confirmDialogBox(getDriver());
		
//		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));
//
//		obj.chooseTab(getDriver(), "Virtual Machines");
		logClass.endTestCase("Started VM successfully");
		Thread.sleep(90000);
	}


	@Test(description="Refreshing VM to given Location and Host",priority=12)
	@Parameters({"IP", "VMName"})
	public void RefreshVM(String IP,String VMName) throws InterruptedException, IOException, MyException{
		String shortVMName = obj.shortVMName(VMName);
		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(getDriver(),"test"+shortVMName);

		//driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
		
		obj.findMoreActionsButton(getDriver());
		Thread.sleep(500);
		obj.clickButtonxPath(getDriver(), locator.getProperty("VMReEstConn"));
		//getDriver().findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(getDriver(), By.xpath(locator.getProperty("VMReEstConnUN")));
		
		getDriver().switchTo().activeElement();
		
		obj.refreshVMValues(getDriver(), shortVMName, "inputsm.properties");
		Thread.sleep(750);
		obj.waitForPresenceOfElement(getDriver(), By.xpath(locator.getProperty("VMReEstConnConf")));
		Thread.sleep(250);
		obj.clickButtonxPath(getDriver(), locator.getProperty("VMReEstConnConf"));
		//getDriver().findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();
		
		Thread.sleep(1000);
		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1500);
		obj.findVMForHost(getDriver(),"test"+shortVMName);
		obj.chooseLink(getDriver(), "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(getDriver(), By.id("vmDeployStatus"),"test"+shortVMName, 6, true,10,0,"Status Details");
		obj.waitForPresenceOfElement(getDriver(), By.xpath(locator.getProperty("RefreshVM")));
		Thread.sleep(1500);
		if(getDriver().findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			getDriver().findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		Thread.sleep(5000);
		obj.chooseLink(getDriver(), "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(getDriver(), By.id("vmDeployStatus"),"test"+ shortVMName, 6, true,10,0,"Status Details");		
		
		Thread.sleep(2500);
		logClass.endTestCase("VM refreshed Successfully");
	}


//	@Test(description="Restarting VM to given Location and Host",priority=13)
//	@Parameters({"IP", "VMName"})
//	public void RestartVM(String IP,String VMName) throws IOException, InterruptedException, MyException{
//		String shortVMName = obj.shortVMName(VMName);
//		logClass.startTestCase("Restart VM to given Location and Host");
//
//		obj.goToSDMCliURL(getDriver());
//
//		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "HostName175"));
//
//		obj.chooseTab(getDriver(), "Virtual Machines");
//
//		obj.findVMForHost(getDriver(), obj.readFromFile("input.properties", "VMName221"));
//
//		getDriver().findElement(By.xpath(locator.getProperty("VMRestart"))).click();
//
//		obj.confirmDialogBox(getDriver());
//
//		logClass.endTestCase("Restarted VM successfully");
//		Thread.sleep(100000);
//	}


	@Test(description="Deleting VM to given Location and Host",priority=14)
	@Parameters({"IP", "VMName"})
	public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException, MyException{
		String shortVMName = obj.shortVMName(VMName);
		logClass.startTestCase("Delete VM to given Location and Host");

		obj.goToSDMCliURL(getDriver());

		obj.findLocationOrHost(getDriver(), obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(getDriver(), "Virtual Machines");
		Thread.sleep(1550);
		obj.findVMForHost(getDriver(), "test"+shortVMName);

		//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));
		obj.clickButtonxPath(getDriver(), locator.getProperty("VMDelete"));
		//getDriver().findElement(By.xpath(locator.getProperty("VMDelete"))).click();

		obj.confirmDialogBox(getDriver());

		logClass.endTestCase("Deleted VM successfully");
		
		//obj.maintainedList(driver, "combobox-1238-inputEl");
		//getDriver().quit();
		
		obj.makeIPWhiteBlackList(IP,"WhiteList");
		
		//driver.quit();
	}



}
