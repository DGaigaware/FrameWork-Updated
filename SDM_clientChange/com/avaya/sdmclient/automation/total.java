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

	@Test(description="Adding VM to given Location and Host",priority=8)
	@Parameters({"IP", "VMName"})
	public void addVM(String IP,String VMName) throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {

		String shortVMName = obj.shortVMName(VMName);
		
		boolean _Check;

		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";

		logClass.startTestCase("Adding VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		if(!obj.checkPresenceOfLocationOrHostOrVM(driver, obj.readFromFile("input.properties", "AddHostHostName:"))){
			driver.navigate().refresh();
			//Host host = new Host();
			//host.addHost();
			System.out.println("Adding Host");
			obj.goToSDMCliURL(driver);
			logClass.info("Added Host as host was not there beforehand.");
		}
		
		
		//
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

		//driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
		obj.chooseTab(driver, "Virtual Machines");
		
		driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);

		if(driver.findElement(By.id(locator.getProperty("MEDeploy"))).isEnabled()){
			driver.findElement(By.id(locator.getProperty("MEDeploy"))).click();
			System.out.println("Clicked");
			obj.confirmDialogBox(driver);
		}
		
		driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
		//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.properties", "VMName"));
		driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
		logClass.info("Given Name");
		Thread.sleep(250);

		obj.errorBox(driver,obj.checkError(driver));

		driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));

		obj.boundListSelect(driver, "data", obj.selBoundList(driver));
		Thread.sleep(2500);
		//
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
					obj.boundListSelect(driver,VMName, obj.selBoundList(driver));
					break;
				}
		
		obj.selectFP(driver, shortVMName);
		
		_Check = obj.checkError(driver);
		String err = obj.errorBox(driver,obj.checkError(driver));

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
		
		obj.FillValues("inputsm.properties", obj.chooseOVF(VMName), driver,IP,"test"+shortVMName);

		obj.deployButtonClickForVM(driver);
		Thread.sleep(450);
		obj.findEULAAcceptButton(driver);
		logClass.info("Accepted EULA");
	
		Thread.sleep(7000);
		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));
		obj.chooseTab(driver, "Virtual Machines");
		Thread.sleep(1500);
		
		obj.findVMForHost(driver, "test"+shortVMName);
		Thread.sleep(4500);
		obj.chooseLink(driver, "test"+shortVMName,"VM","Status Details");
		obj.checkSuccessOrFailure(driver, By.id(locator.getProperty("vmDeployStatus")), "test"+shortVMName, 6, true, 250,0, "Status Details");

		Thread.sleep(5000);
		System.out.println("Completed adding VM "+shortVMName+" with IP "+IP);
		logClass.info("Completed adding VM "+shortVMName+" with IP "+IP);

	}


	@Test(description="Editing VM to given Location and Host",priority=9)
	@Parameters({"IP", "VMName"})
	public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

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

		obj.checkFocus(driver, By.xpath(locator.getProperty("VMEditSave")));
		driver.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited VM Successfully");
	}


	@Test(description="Stoping VM to given Location and Host",priority=10)
	@Parameters({"IP", "VMName"})
	public void StopVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

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
	@Parameters({"IP", "VMName"})
	public void StartVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

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
	@Parameters({"IP", "VMName"})
	public void RefreshVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

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
		obj.chooseLink(driver, obj.readFromFile("input.properties", "VMName221"),"VM","Status Details");
		obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

		obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("RefreshVM")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));

		if(driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
		//Added
		Thread.sleep(5000);
		
		obj.chooseLink(driver, obj.readFromFile("input.properties", "VMName221"),"VM","Status Details");
		
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "VM Refresh Completed"));
		obj.StatusCheck(driver, "VM Refresh Completed", 20);
		// Uptill Now
		Thread.sleep(2500);
		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}


	@Test(description="Restarting VM to given Location and Host",priority=13)
	@Parameters({"IP", "VMName"})
	public void RestartVM(String IP,String VMName) throws IOException, InterruptedException, MyException{

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
	@Parameters({"IP", "VMName"})
	public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException, MyException{

		logClass.startTestCase("Delete VM to given Location and Host");

		obj.goToSDMCliURL(driver);

		obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName"));

		obj.chooseTab(driver, "Virtual Machines");

		obj.findVMForHost(driver, obj.readFromFile("input.properties", "VMName221"));

		driver.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

		obj.confirmDialogBox(driver);

		logClass.endTestCase("Deleted VM successfully");
		
		driver.quit();
	}



}
