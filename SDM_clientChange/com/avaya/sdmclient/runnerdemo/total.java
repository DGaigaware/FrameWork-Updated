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
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;
import com.avaya.sdmclient.extraResources.MyException;

public class total {
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	/*@Test(description="Adding Location",priority=0)
	public void addLocation() throws IOException, InterruptedException, MyException {

		logClass.startTestCase("Add a new Location on SDM");

		obj.goToSDMCliURL(driver);

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
	public void editLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Edit Location on SDM");

		obj.goToSDMCliURL(driver);

		obj.findLocationInGrid(driver, "testLoc");

		driver.findElement(By.xpath(locator.getProperty("LocationEdit"))).click();

		obj.findIDandFillValues(driver, "input.properties", "EditLocation");

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("LocationSaveEdit")));

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
	public void deleteLocation() throws IOException, InterruptedException{

		logClass.startTestCase("Delete Location on SDM");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, "VM Management");

		obj.findLocationInGrid(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		obj.checkFocus(driver, By.xpath(locator.getProperty("LocationDelete")));

		driver.findElement(By.xpath(locator.getProperty("LocationDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Location");
	}*/

/*
	@Test(description="Adding Host to given Location",priority=3)
	public void addHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Adding Host to given Location");

		obj.goToSDMCliURL(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"))){
			//driver.navigate().refresh();
			obj.goToSDMCliURL(driver);
			//addLocation();
			System.out.println("Adding Location");
			logClass.info("Location was not there. Adding it and pausing current thread.");
			obj.goToSDMCliURL(driver);
			logClass.info("Added Location as Location was not there beforehand.");
		}

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));

		//driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
		logClass.info("Adding new Host");
		Thread.sleep(250);			

		obj.findIDandFillValues(driver, "input.properties", "AddHost");
		Thread.sleep(250);

		obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHost")));

		driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

		Thread.sleep(1200);
		obj.confirmDialogBox(driver);
		Thread.sleep(4500);

		obj.refreshItems(driver, "AddHost");
		Thread.sleep(12500);

		//obj.chooseLink(driver, obj.readFromFile("input.properties", "AddHostHostName:"));
		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));

		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

		obj.StatusCheck(driver, "Host Create/Update Completed", 20);

		logClass.endTestCase("Added Host Successfully");
	}

	@Test(description="Editing Host to given Location",priority=4)
	public void editHost() throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Editing Host to given Location");
		
		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationName:"));
		obj.chooseTab(driver, "Hosts");
		logClass.info("In 'Host' Tab");

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();

		obj.findIDandFillValues(driver, "input.properties", "EditHost");
		Thread.sleep(250);

		obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHostEdit")));

		driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		Thread.sleep(2500);

		obj.refreshItems(driver, "EditHost");

		logClass.endTestCase("Edited Host Successfully");

	}

	@Test(description="Deleting Host to given Location",priority=5)

	public void deleteHost() throws IOException, InterruptedException{
		logClass.startTestCase("Deleting Host to given Location");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddLocationCity:"));

		obj.findHostInGrid(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.checkFocus(driver, By.xpath(locator.getProperty("HostDelete")));

		driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted Host");
	}*/

	@Test(description="Adding VM to given Location and Host",priority=8)

	public void addVM() throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {

		String shortVMName = "BSM";
		
		boolean _Check;

		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		logClass.startTestCase("Adding VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"))){
			//addHost1();
			System.out.println("Adding Host");
			obj.goToSDMCliURL(driver);
			logClass.info("Added Host as host was not there beforehand.");
		}
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		obj.chooseTab(driver, "Virtual Machines");
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.properties", "VMName221"));
		logClass.info("Given Name");
		Thread.sleep(3250);

		obj.errorBox(driver,obj.checkError(driver));

		if(driver.findElement(By.id(locator.getProperty("MEDeploy"))).isEnabled()){
			driver.findElement(By.id(locator.getProperty("MEDeploy"))).click();
			System.out.println("Clicked");
			obj.confirmDialogBox(driver);
		}
		
		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundListID(driver));

		//1 - File Path; 3 - SW Library; 4 - URL
		switch(_SWLib){
		case _default:
			driver.findElement(By.xpath(locator.getProperty("OVAFilePath"))).click();
			logClass.info("Choosen From File");

			driver.findElement(By.xpath(locator.getProperty("FilePathField"))).clear();
			driver.findElement(By.xpath(locator.getProperty("FilePathField"))).sendKeys(obj.readFromFile("input.properties", "SMFilePath"));
			driver.findElement(By.xpath(locator.getProperty("FilePathSubmit"))).click();
			logClass.info("File Path Given");
			Thread.sleep(2500);
			break;
		case _URL:
			driver.findElement(By.xpath(locator.getProperty("OVAURL"))).click();
			logClass.info("Choosen From URL");

			driver.findElement(By.xpath(locator.getProperty("URLInput"))).clear();
			driver.findElement(By.xpath(locator.getProperty("URLInput"))).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
			driver.findElement(By.xpath(locator.getProperty("URLSubmit"))).click();
			Thread.sleep(450);
			break;
		case _SWLib:
			driver.findElement(By.xpath(locator.getProperty("OVASWLib"))).click();
			logClass.info("Choosen From Software Library");

			//driver.findElement(By.xpath(locator.getProperty("SWLibSelect"))).click();
			obj.clickComboSDMCli(driver, "File Name:");
			obj.boundListSelect(driver,shortVMName, obj.selBoundListID(driver));
			break;
		}

		//obj._errorBox(driver);
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
		Thread.sleep(450);

		obj.boundListSelect(driver, "Profile 1", obj.selBoundListID(driver));
		//Thread.sleep(250);
		_Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));

		//driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
		//obj.checkFailureOfHostCapacity(driver);

		obj.exec(!_Check);

		//removed

		obj.FillValues("inputsm.properties", obj.chooseOVFFromSDMClient(shortVMName), driver,"148.147.162.221","test"+shortVMName);

		obj.deployButtonClickForVM(driver);
		
		//driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

		//driver.findElement(By.xpath(locator.getProperty("EULAAccept"))).click();
		logClass.info("Accepted EULA");

		Thread.sleep(9000);
		obj.findLocationOrHost(driver, "testHost");

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, "testSM221");
		
		Thread.sleep(4500);

		driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		logClass.info("Checking Status Details");

		obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
		
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

		System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driver, 1500, "VM Deployment Completed",""));
		Thread.sleep(1000);
		//obj.StatusCheck(driver, "VM Deployment Completed", 20);
		obj.closeWindow(driver);
		Thread.sleep(5000);

	}


	@Test(description="Editing VM to given Location and Host",priority=9)

	public void EditVM() throws InterruptedException, IOException, MyException{

		logClass.startTestCase("Editing VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName175"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));
		
		driver.findElement(By.xpath(locator.getProperty("EditVM"))).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);

		obj.editVMchooseFPorFQDN(driver, "FQDN");
		driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

		obj.editVM(driver,obj.readFromFile("input.properties", "IP"),"edited");
		
		/*driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).sendKeys(IP);

		driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).sendKeys(shortVMName+"edited");*/

		obj.checkFocusOfElement(driver, By.xpath(locator.getProperty("VMEditSave")));
		driver.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited VM Successfully");
	}


	@Test(description="Stoping VM to given Location and Host",priority=10)

	public void StopVM() throws InterruptedException, IOException{

		Thread.sleep(5000);
		logClass.startTestCase("Stop VM to given Location and Host");

		obj.goToSDMCliURL(driver);
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName175"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));
		
		Thread.sleep(1500);

		driver.findElement(By.xpath(locator.getProperty("StopVM"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Stopped VM successfully");
		Thread.sleep(60000);
	}


	@Test(description="Starting VM to given Location and Host",priority=11)

	public void StartVM() throws InterruptedException, IOException{

		Thread.sleep(5000);
		logClass.startTestCase("Start VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName175"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));

		driver.findElement(By.xpath(locator.getProperty("VMStart"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Started VM successfully");
		Thread.sleep(60000);
	}


	@Test(description="Refreshing VM to given Location and Host",priority=12)

	public void RefreshVM() throws InterruptedException, IOException{

		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName175"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));

		obj.findMoreActionsButton(driver);
		Thread.sleep(500);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		driver.switchTo().activeElement();

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);
		//driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		obj.chooseLink(driver, obj.readFromFile("input.properties", "VMName221"),"Status Details");
		obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("RefreshVM")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));

		if(driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		//Added
		Thread.sleep(5000);
		
		obj.chooseLink(driver, obj.readFromFile("input.properties", "VMName221"),"Status Details");
		
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "VM Refresh Completed"));
		obj.StatusCheck(driver, "VM Refresh Completed", 20);
		// Uptill Now
		Thread.sleep(2500);
		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}


	@Test(description="Restarting VM to given Location and Host",priority=13)

	public void RestartVM() throws IOException, InterruptedException{

		logClass.startTestCase("Restart VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName175"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));

		driver.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Restarted VM successfully");
		Thread.sleep(100000);
	}


	@Test(description="Deleting VM to given Location and Host",priority=14)

	public void DeleteVM() throws IOException, InterruptedException{

		logClass.startTestCase("Delete VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));

		driver.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted VM successfully");
	}



}
