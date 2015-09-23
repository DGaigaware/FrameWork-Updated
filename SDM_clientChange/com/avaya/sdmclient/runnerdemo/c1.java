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

	public class c1 {
		
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
		public void AddVMSuite(String IP,String VMName) throws InterruptedException, IOException, ParserConfigurationException, SAXException, MyException {

			String shortVMName = VMName.substring(0, VMName.indexOf("-")); 
			
			boolean _Check;

			System.out.println(IP);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

			logClass.startTestCase("Adding VM to given Location and Host");

			//obj.goHome(driver);
			obj.loginToSite(driver);

			if(obj.checkLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"))){
				//addHost1();
				System.out.println("Adding Host");
				obj.goHome(driver);
				logClass.info("Added Host as Location was not there beforehand.");
			}
			
			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();
			driver.findElement(By.xpath(locator.getProperty("NewVM"))).click();
			logClass.info("Clicked on - Add new VM");
			Thread.sleep(750);

			driver.findElement(By.xpath(locator.getProperty("VMName"))).clear();
			//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj._readFromFile("input.properties", "VMName"));
			driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys("test"+shortVMName);
			//driver.findElement(By.xpath(locator.getProperty("VMName"))).sendKeys(obj.readFromFile("inputbsm.txt", "VMName225"));
			logClass.info("Given Name");
			Thread.sleep(250);

			obj.errorBox(driver,obj.checkError(driver));

			driver.findElement(By.xpath(locator.getProperty("DataStore"))).click();
			Thread.sleep(250);
			
			obj.errorBox(driver,obj.checkError(driver));

			obj.boundListSelect(driver, "data", obj.selBoundList(driver));
			Thread.sleep(2500);
			
			/*obj.comboClick(driver, "combobox-1235","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			obj.comboClick(driver, "combobox-1238", VMName);
			Thread.sleep(2500);*/
			
			obj.comboClick(driver, "Select Software Library:","SMGR_DEFAULT_LOCAL");
			Thread.sleep(2500);
			
			//obj.maintainedList(driver, obj.comboID(driver, "Select OVAs:"));
			
			obj.comboClick(driver, "Select OVAs:", VMName);
			Thread.sleep(2500);
			
			/*driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
			Thread.sleep(450);
			obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));*/

			_Check = obj.checkError(driver);
			obj.errorBox(driver,obj.checkError(driver));

			driver.findElement(By.xpath(locator.getProperty("SortColumns"))).click();
			obj.checkFailureOfHostCapacity(driver);

			obj.exec(!_Check);

			//removed

			//obj.FillValues("inputbsm.txt", obj.readFromFile("inputbsm.txt", "BSMOVFPath"), driver);
			//obj.FillValues("inputcmsimplex.txt", obj.readFromFile("inputcmsimplex.txt","Path"), driver);
			obj.FillValues("inputsm.properties", System.getProperty("user.dir")+"\\Third Party\\OVFs\\"+obj.matchFileOVF(shortVMName), driver,IP,"test"+shortVMName);
			
			obj.checkFocus(driver, By.xpath(locator.getProperty("Deploy")));

			driver.findElement(By.xpath(locator.getProperty("Deploy"))).click();

			obj.findButton(driver);
//			driver.findElement(By.xpath(locator.getProperty("EULAAccept"))).click();
			logClass.info("Accepted EULA");

			Thread.sleep(9000);
			obj.findLocationOrHost(driver, "testHost");

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);
			
			Thread.sleep(4500);

			/*driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
			logClass.info("Checking Status Details");*/
			obj.chooseLink(driver, "test"+shortVMName);

			obj.waitForPresence(driver, By.id(locator.getProperty("vmDeployStatus")));
			
			driver.switchTo().activeElement();
			System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());

			System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), driver, 1500, "Completed","test"+shortVMName));
			Thread.sleep(1000);
			//obj.StatusCheck(driver, "VM Deployment Completed", 20);
			obj.closeWindow(driver);
			Thread.sleep(5000);

		}


		@Test(description="Editing VM to given Location and Host",priority=9)
		@Parameters({"IP", "VMName"})
		public void EditVM(String IP,String VMName) throws InterruptedException, IOException, MyException{

			String shortVMName = VMName.substring(0, VMName.indexOf("-")); 
			
			logClass.startTestCase("Editing VM to given Location and Host");

			//obj.loginToSite(driver);
			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);
			
			driver.findElement(By.xpath(locator.getProperty("EditVM"))).click();
			logClass.info("Clicked on - Edit VM");
			Thread.sleep(750);

			driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVM"))).click();

			driver.findElement(By.xpath(locator.getProperty("EditIPFQDNVMButton"))).click();

			driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditIP"))).sendKeys(IP);

			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMEditFQDN"))).sendKeys("test"+shortVMName);

			obj.checkFocus(driver, By.xpath(locator.getProperty("VMEditSave")));
			driver.findElement(By.xpath(locator.getProperty("VMEditSave"))).click();

			obj.errorBox(driver, obj.checkError(driver));
			logClass.endTestCase("Edited VM Successfully");
		}


		@Test(description="Stoping VM to given Location and Host",priority=10)
		@Parameters({"IP", "VMName"})
		public void StopVM(String IP,String VMName) throws InterruptedException, IOException{
			String shortVMName = VMName.substring(0, VMName.indexOf("-"));
			Thread.sleep(5000);
			logClass.startTestCase("Stop VM to given Location and Host");

			obj.goHome(driver);
			
			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);
			
			Thread.sleep(1500);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("StopVM")));

			driver.findElement(By.xpath(locator.getProperty("StopVM"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Stopped VM successfully");
			Thread.sleep(60000);
		}


		@Test(description="Starting VM to given Location and Host",priority=11)
		@Parameters({"IP", "VMName"})
		public void StartVM(String IP,String VMName) throws InterruptedException, IOException{
			String shortVMName = VMName.substring(0, VMName.indexOf("-"));
			Thread.sleep(5000);
			logClass.startTestCase("Start VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			driver.findElement(By.xpath(locator.getProperty("VMStart"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Started VM successfully");
			Thread.sleep(60000);
		}


		@Test(description="Refreshing VM to given Location and Host",priority=12)
		@Parameters({"IP", "VMName"})
		public void RefreshVM(String IP,String VMName) throws InterruptedException, IOException{
			String shortVMName = VMName.substring(0, VMName.indexOf("-"));
			Thread.sleep(5000);
			logClass.startTestCase("Refresh VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver,"test"+shortVMName);

			driver.findElement(By.xpath(locator.getProperty("VMMoreAction"))).click();
			Thread.sleep(500);
			driver.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

			Thread.sleep(500);
			
			obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnUN")));
			
			driver.switchTo().activeElement();

			driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("input.properties", "CustomerName"));

			driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("input.properties", "CustPwd"));

			obj.waitForPresence(driver, By.xpath(locator.getProperty("VMReEstConnConf")));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("VMReEstConnConf"))));
			Thread.sleep(250);
			driver.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

			Thread.sleep(5000);
			driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
			obj.StatusCheck(driver, "VM Trust Establishment Completed",50);

			obj.waitForPresence(driver, By.xpath(locator.getProperty("RefreshVM")));
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
		@Parameters({"IP", "VMName"})
		public void RestartVM(String IP,String VMName) throws IOException, InterruptedException{
			String shortVMName = VMName.substring(0, VMName.indexOf("-"));
			logClass.startTestCase("Restart VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "AddHostHostName:"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMStart")));

			driver.findElement(By.xpath(locator.getProperty("VMRestart"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Restarted VM successfully");
			Thread.sleep(100000);
		}


		@Test(description="Deleting VM to given Location and Host",priority=14)
		@Parameters({"IP", "VMName"})
		public void DeleteVM(String IP,String VMName) throws IOException, InterruptedException{
			String shortVMName = VMName.substring(0, VMName.indexOf("-"));
			logClass.startTestCase("Delete VM to given Location and Host");

			obj.goHome(driver);

			obj.findLocationOrHost(driver, obj.readFromFile("input.properties", "HostName"));

			driver.findElement(By.xpath(locator.getProperty("VM-Tab"))).click();

			obj.findVMForHost(driver, "test"+shortVMName);

			//obj.checkFocus(driver, By.xpath(locator.getProperty("VMDelete")));

			driver.findElement(By.xpath(locator.getProperty("VMDelete"))).click();

			obj.confirmDialogBox(driver);

			logClass.endTestCase("Deleted VM successfully");
			
			driver.quit();
			
			final WebDriver driver2 = new FirefoxDriver(obj.selectProfile("Selenium"));
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
		}
	

}
