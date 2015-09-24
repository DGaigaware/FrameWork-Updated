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

	public class sdmSMGR {
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
		public void AddLocation() throws IOException, InterruptedException {

			logClass.startTestCase("Add a new Location on SDM");

			obj.loginToSite(driver);

			driver.findElement(By.xpath((locator.getProperty("LocationAdd")))).click();
			logClass.info("Adding new Location");

			obj.findIDandFillValues(driver, "input.txt", "AddLocation");
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

			obj.findIDandFillValues(driver, "input.txt", "EditLocation");
			
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

			obj.findLocationInGrid(driver, obj.readFromFile("input.txt", "AddLocationName:"));

			obj.checkFocus(driver, By.xpath(locator.getProperty("LocationDelete")));
			
			driver.findElement(By.xpath(locator.getProperty("LocationDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted Location");
		}

		
		@Test(description="Adding Host to given Location",priority=3)
		public void addHost() throws IOException, InterruptedException{

			logClass.startTestCase("Adding Host to given Location");

			obj.goHome(driver);
			//obj.loginToSite(driver);
			
			if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationName:"))){
				driver.navigate().refresh();
				obj.logOut(driver);
				AddLocation();
				System.out.println("Adding Location");
				logClass.info("Location was not there. Adding it and pausing current thread.");
				obj.goHome(driver);
				//obj.loginToSite(driver);
				logClass.info("Added Location as Location was not there beforehand.");
			}

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationName:"));

			driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
			logClass.info("In 'Host' Tab");

			driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
			logClass.info("Adding new Host");
			Thread.sleep(250);			
		
			obj.findIDandFillValues(driver, "input.txt", "AddHost");
			Thread.sleep(250);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHost")));
			
			driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

			obj.confirmDialogBox(driver);
			Thread.sleep(4500);
			
			obj.refreshItems(driver, "AddHost");
			Thread.sleep(1500);
			
			obj.checkSuccess(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			obj.waitForPresence(driver, By.id(locator.getProperty("vmDeployStatus")));
			
			System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, 50, "Host Create/Update Completed"));

			obj.StatusCheck(driver, "Host Create/Update Completed", 20);

			logClass.endTestCase("Added Host Succesfully");
		}

		@Test(description="Editing Host to given Location",priority=4)
		public void _EditHost() throws IOException, InterruptedException{

			logClass.startTestCase("Editing Host to given Location");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationName:"));
			driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
			logClass.info("In 'Host' Tab");

			obj.findHostInGrid(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("EditHost"))).click();
			//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();

			Thread.sleep(250);
	
			obj.findIDandFillValues(driver, "input.txt", "EditHost");
			Thread.sleep(250);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("SaveHostEdit")));

			driver.findElement(By.xpath(locator.getProperty("SaveHostEdit"))).click();

			obj.errorBox(driver, obj.checkError(driver));
			Thread.sleep(2500);
			
			obj.refreshItems(driver, "EditHost");

			logClass.endTestCase("Edited Host Successfully");

		}

		@Test(description="Deleting Host to given Location",priority=5)

		public void DeleteHost() throws IOException, InterruptedException{
			logClass.startTestCase("Deleting Host to given Location");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddLocationCity:"));

			obj.findHostInGrid(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			obj.checkFocus(driver, By.xpath(locator.getProperty("HostDelete")));
			
			driver.findElement(By.xpath(locator.getProperty("HostDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted Host");
		}*/
/*
		@Test(description="Adding Location",priority=6)
		public void AddLocation() throws IOException, InterruptedException {

			logClass.startTestCase("Add a new Location on SDM");

			obj.goHome(driver);

			driver.findElement(By.xpath(locator.getProperty("LocationAdd"))).click();
			logClass.info("Adding new Location");

			driver.findElement(By.xpath(locator.getProperty("LocationName"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationName"))).sendKeys(obj.readFromFile("input.txt", "NewLocation"));

			driver.findElement(By.xpath(locator.getProperty("LocationAddress"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationAddress"))).sendKeys(obj.readFromFile("input.txt", "FAddress"));

			driver.findElement(By.xpath(locator.getProperty("LocationCity"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationCity"))).sendKeys(obj.readFromFile("input.txt", "FCity"));

			driver.findElement(By.xpath(locator.getProperty("LocationState"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationState"))).sendKeys(obj.readFromFile("input.txt", "FState"));

			driver.findElement(By.xpath(locator.getProperty("LocationZIP"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationZIP"))).sendKeys(obj.readFromFile("input.txt", "FZIP"));

			driver.findElement(By.xpath(locator.getProperty("LocationCountry"))).clear();
			driver.findElement(By.xpath(locator.getProperty("LocationCountry"))).sendKeys(obj.readFromFile("input.txt", "FCountry"));

			Thread.sleep(250);
			driver.findElement(By.xpath(locator.getProperty("LocationSave"))).click();
			logClass.info("Saved New Location");

			obj.errorBox(driver, obj.checkError(driver));
			logClass.endTestCase("Added a new Location");

		}

		@Test(description="Adding Host to given Location",priority=7)
		public void addHost() throws IOException, InterruptedException{

			logClass.startTestCase("Adding Host to given Location");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"));

			driver.findElement(By.xpath(locator.getProperty("Host-Tab"))).click();
			logClass.info("In 'Host' Tab");

			driver.findElement(By.xpath(locator.getProperty("New-Host"))).click();
			logClass.info("Adding new Host");

			driver.findElement(By.xpath(locator.getProperty("HostName"))).clear();
			driver.findElement(By.xpath(locator.getProperty("HostName"))).sendKeys(obj.readFromFile("input.txt", "HostName175"));

			driver.findElement(By.xpath(locator.getProperty("HostIP"))).clear();
			driver.findElement(By.xpath(locator.getProperty("HostIP"))).sendKeys(obj.readFromFile("input.txt", "HostIP175"));

			driver.findElement(By.xpath(locator.getProperty("HostUserName"))).clear();
			driver.findElement(By.xpath(locator.getProperty("HostUserName"))).sendKeys(obj.readFromFile("input.txt", "username175"));

			driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).clear();
			driver.findElement(By.xpath(locator.getProperty("HostPassWord"))).sendKeys(obj.readFromFile("input.txt", "password175"));

			Thread.sleep(250);
			driver.findElement(By.xpath(locator.getProperty("SaveHost"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Added Host Succesfully");
		}
*/
		@Test(description="Adding VM to given Location and Host",priority=8)

		public void AddVMSuite() throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {

			boolean _Check;

			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

			logClass.startTestCase("Adding VM to given Location and Host");

			//obj.goHome(driver);
			obj.loginToSite(driver);

			if(obj.checkLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"))){
				//addHost();
				System.out.println("Adding Host");
				obj.goHome(driver);
				logClass.info("Added Host as Location was not there beforehand.");
			}
			
			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
			logClass.info("Clicked on - Add new VM");
			Thread.sleep(750);

			driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
			//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.txt", "VMName"));
			driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("input.txt", "VMName221"));
			logClass.info("Given Name");
			Thread.sleep(250);

			obj.errorBox(driver,obj.checkError(driver));

			driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
			Thread.sleep(250);
			
			obj.errorBox(driver,obj.checkError(driver));

			obj.boundListSelect(driver, "data", obj.selBoundList(driver));
			Thread.sleep(2500);
			
			obj.comboClick(driver, "combobox-1235","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			obj.comboClick(driver, "combobox-1238", "SM-7.0.0.0.700007-e55-01.ova");
			Thread.sleep(2500);
			
			driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
			Thread.sleep(450);
			obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));

			_Check = obj.checkError(driver);
			obj.errorBox(driver,obj.checkError(driver));

			driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
			obj.checkFailureOfHostCapacity(driver);

			obj.exec(!_Check);

			//removed

			obj.FillValues("inputsm.txt", obj.readFromFile("input.txt", "SMOVFPath"), driver,"","");

			obj.checkFocus(driver, By.xpath(locator.getProperty("Deploy")));

			driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

			obj.findButton(driver);
//			driver.findElement(By.xpath(locator.getProperty("EULAAccept"))).click();
			logClass.info("Accepted EULA");

			Thread.sleep(9000);
			obj.findLocationOrHost(driver, "testHost");

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "testSM221");
			
			Thread.sleep(4500);

			driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
			logClass.info("Checking Status Details");

			obj.waitForPresenceOfElement(driver, By.id(locator.getProperty("vmDeployStatus")));
			
			driver.switchTo().activeElement();
			System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

			System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driver, 1500, "Completed",""));
			Thread.sleep(1000);
			//obj.StatusCheck(driver, "VM Deployment Completed", 20);
			obj.closeWindow(driver);
			Thread.sleep(5000);

		}

	
		@Test(description="Editing VM to given Location and Host",priority=9)

		public void EditVM() throws InterruptedException, IOException, MyException{

			logClass.startTestCase("Editing VM to given Location and Host");

			//obj.loginToSite(driver);
			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
			
			driver.findElement(By.xpath(locator.getProperty("EditVM"))).click();
			logClass.info("Clicked on - Edit VM");
			Thread.sleep(750);

			driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

			driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

			driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).sendKeys(obj.readFromFile("input.txt", "IPI"));

			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).sendKeys(obj.readFromFile("input.txt", "NewVMName"));

			obj.checkFocus(driver, By.xpath(locator.getProperty("VMEditSave")));
			driver.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

			obj.errorBox(driver, obj.checkError(driver));
			logClass.endTestCase("Edited VM Successfully");
		}


		@Test(description="Stoping VM to given Location and Host",priority=10)

		public void StopVM() throws InterruptedException, IOException{

			Thread.sleep(5000);
			logClass.startTestCase("Stop VM to given Location and Host");

			obj.goHome(driver);
			
			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
			
			Thread.sleep(1500);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));

			driver.findElement(By.xpath(locator.getProperty("StopVM"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Stopped VM successfully");
			Thread.sleep(60000);
		}


		@Test(description="Starting VM to given Location and Host",priority=11)

		public void StartVM() throws InterruptedException, IOException{

			Thread.sleep(5000);
			logClass.startTestCase("Start VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			driver.findElement(By.xpath(locator.getProperty("VMStart"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Started VM successfully");
			Thread.sleep(60000);
		}


		@Test(description="Refreshing VM to given Location and Host",priority=12)

		public void RefreshVM() throws InterruptedException, IOException{

			Thread.sleep(5000);
			logClass.startTestCase("Refresh VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));

			driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
			Thread.sleep(500);
			driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			
			obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
			
			driver.switchTo().activeElement();

			driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.txt", "CustomerName"));

			driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.txt", "CustPwd"));

			obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
			Thread.sleep(250);
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

			Thread.sleep(5000);
			driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
			obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

			obj.waitForPresenceOfElement(driver, By.xpath(locator.getProperty("RefreshVM")));
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("RefreshVM"))));

			if(driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
				driver.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();
			//Added
			Thread.sleep(5000);
			WebElement table1 = driver.findElement(By.id(locator.getProperty("VMGrid")));
			List<WebElement> cells1 = table1.findElements(By.xpath((".//*[local-name(.)='td']")));
			for(WebElement e : cells1)

			{
				if(e.getText().trim().contains("..."))
				{
					System.out.println("next"+e.getText());
					e.findElement(By.linkText(locator.getProperty("Status Details"))).click();
				}

			}
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

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			driver.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Restarted VM successfully");
			Thread.sleep(100000);
		}


		@Test(description="Deleting VM to given Location and Host",priority=14)

		public void DeleteVM() throws IOException, InterruptedException{

			logClass.startTestCase("Delete VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));

			driver.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted VM successfully");
		}

	

	

}
