package com.avaya.sdmclient.vm;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.avaya.sdmclient._Settings;
import com.avaya.sdmclient.logClass;

import utility.UserAction;

public class _VM {
	_Settings obj = new _Settings();
	WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
	UserAction action = new UserAction();
	
	@Test(description="Adding VM to given Location and Host")
	public void _AddVM() throws InterruptedException, IOException {
		
		obj._addToList();
		boolean _Check;
		
		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";
		
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='newVM_-btnInnerEl']")).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).clear();
		//driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));	
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys("testSM2211");
		logClass.info("Given Name");
		Thread.sleep(450);
		
		obj._errorBox(driver,obj._checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		Thread.sleep(250);

		obj._boundListSelect(driver, "data", 0);
		
		//1 - File Path; 3 - SW Library; 4 - URL 
		switch(_default){
			case _default:
					driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
					logClass.info("Choosen From File");
		
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj._readFromFile("input.txt", "FilePath"));
					driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
					logClass.info("File Path Given");
					Thread.sleep(500);
					break;
			case _URL:
					driver.findElement(By.xpath(".//*[@id='radio4-inputEl']")).click();
					logClass.info("Choosen From URL");
					
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
					driver.findElement(By.xpath(".//*[@id='button-1240-btnIconEl']")).click();
					Thread.sleep(450);
					break;
			case _SWLib:
					driver.findElement(By.xpath(".//*[@id='radio3-inputEl']")).click();
					logClass.info("Choosen From Software Library");
					
					driver.findElement(By.xpath(".//*[@id='combobox-1237-inputEl']")).click();
					WebElement element = driver.findElement(By.id("boundlist-1543-listEl"));
					List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
					for (WebElement e : tmp1 )
						{
							//System.out.println(e.getText()+ "\n Test \n");
							if(e.getText().contains("SM"))
							{
								System.out.println("\nSelected : \n"+e.getText());
								e.click();
							}
						}
					break;
		}
		
		//obj._errorBox(driver);

		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj._boundListSelect(driver, "Profile 5", 1);
		//Thread.sleep(250);
		_Check = obj._checkError(driver);
		obj._errorBox(driver,obj._checkError(driver));
	
		driver.findElement(By.xpath(".//*[@id='gridcolumn-1221-textEl']")).click();
		obj._checkFailureOfHostCapacity(driver);
		
		obj._exec(!_Check);
		
		driver.findElement(By.xpath(".//*[@id='domain-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='domain-inputEl']")).sendKeys("smgrdev.avaya.com");
		
		obj._IPFill(driver, "148.147.162.221", ".//*[@id='textfield-1548-inputEl']");
	
		logClass.info("IP address given");
		
		driver.findElement(By.xpath(".//*[@id='hostname-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));
		logClass.info("Given the Name of VM");
		
		obj._IPFill(driver, "255.255.255.0", ".//*[@id='textfield-1556-inputEl']");
	
		logClass.info("Given NetMask");

		obj._IPFill(driver, "148.147.162.1", ".//*[@id='textfield-1564-inputEl']");
		
		logClass.info("Given Default Gateway");
		
		driver.findElement(By.xpath(".//*[@id='timezone-inputEl']")).click();
		
		obj._boundListSelect(driver, "Asia/Kolkata", 5);
		logClass.info("Selected Given TimeZone");
		
		driver.findElement(By.xpath(".//*[@id='CUSTLOGIN-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='CUSTLOGIN-inputEl']")).sendKeys("cust");
		
		driver.findElement(By.xpath(".//*[@id='CUSTPWD-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='CUSTPWD-inputEl']")).sendKeys("cust01");
		
		driver.findElement(By.xpath(".//*[@id='confCUSTPWD-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='confCUSTPWD-inputEl']")).sendKeys("cust01");
		logClass.info("Given Login ID and Password");
		
		obj._IPFill(driver, "148.147.162.35", ".//*[@id='textfield-1579-inputEl']");
		
		logClass.info("Given Primary SMGR IP");
			
		driver.findElement(By.xpath(".//*[@id='enrollment_pw-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='enrollment_pw-inputEl']")).sendKeys("123456");
		
		driver.findElement(By.xpath(".//*[@id='confenrollment_pw-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='confenrollment_pw-inputEl']")).sendKeys("123456");
		logClass.info("Given Enrollment Password");
		
		driver.findElement(By.xpath(".//*[@id='tab-1243-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='Public-inputEl']")).click();
		obj._boundListSelect(driver, "VM Network", 6);
		
		driver.findElement(By.xpath(".//*[@id='Out of Band Management-inputEl']")).click();
		obj._boundListSelect(driver, "VM Network", 7);
		logClass.info("Selected Network Parameters");
		
		driver.findElement(By.xpath(".//*[@id='button-1245-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='button-1589-btnInnerEl']")).click();
		logClass.info("Accepted EULA");
		
		Thread.sleep(9000);
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM221");
		
		driver.findElement(By.linkText("Status Details")).click();
		logClass.info("Checking Status Details");
		
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
		
		System.out.println(obj.fluentWait(By.id("vmDeployStatus"), driver, 1500, "VM Deployment Completed"));
		
		if(driver.findElement(By.id("vmDeployStatus")).getText().contains("VM Deployment Completed"))
			{
				driver.findElement(By.xpath(".//*[@id='tool-1541-toolEl']")).click();
				System.out.println("Completed Successfully");
			}
		
	}
	
	
	@Test(description="Editing VM to given Location and Host",priority=1)

	public void _EditVM() throws InterruptedException{
		
		logClass.startTestCase("Editing VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM2211");
		driver.findElement(By.xpath(".//*[@id='editvm-btnInnerEl']")).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='radiofield-1248-inputEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='editVMChangeVMIPFQDN-btnIconEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).sendKeys("148.147.162.221");
		
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).sendKeys("testSM2211");
		
		driver.findElement(By.xpath(".//*[@id='saveEditVM-btnInnerEl']")).click();
		
		logClass.endTestCase("Edited VM Successfully");
	}

	
	@Test(description="Stoping VM to given Location and Host",priority=2)

	public void _StopVM() throws InterruptedException{
		
		Thread.sleep(5000);
		logClass.startTestCase("Stop VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM2211");
		
		driver.findElement(By.xpath(".//*[@id='stopvm-btnInnerEl']")).click();
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Stopped VM successfully");
	}

	
	@Test(description="Starting VM to given Location and Host",priority=3)

	public void _StartVM() throws InterruptedException{
		
		Thread.sleep(5000);
		logClass.startTestCase("Start VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM2211");
		
		driver.findElement(By.xpath(".//*[@id='startvm-btnInnerEl']")).click();
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Started VM successfully");
	}

	
	@Test(description="Refreshing VM to given Location and Host",priority=4)

	public void _RefreshVM() throws InterruptedException, IOException{

		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testcmm221");
		
		driver.findElement(By.xpath(".//*[@id='button-1192-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='bulkReestablishConnection-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		
		driver.findElement(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")).sendKeys("cust");
		
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).sendKeys("cust01");
		
		driver.findElement(By.xpath(".//*[@id='reestablishConnProceed-btnIconEl']")).click();
		
		Thread.sleep(5000);
		
		obj._StatusCheck(driver, "VM Trust Establishment Completed",50);
		
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='refreshvm-btnInnerEl']")));
		
		if(driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).isEnabled())
		driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).click();
		
		Thread.sleep(2500);
		obj._StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}

	
	@Test(description="Restarting VM to given Location and Host",priority=5)

	public void _RestartVM(){

		logClass.startTestCase("Restart VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM221");
		
		driver.findElement(By.xpath(".//*[@id='restartvm-btnInnerEl']")).click();
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Restarted VM successfully");
	}

	
	@Test(description="Deleting VM to given Location and Host",priority=6)

	public void _DeleteVM(){

		logClass.startTestCase("Delete VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM221");
		
		driver.findElement(By.xpath(".//*[@id='removevms-btnInnerEl']")).click();
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Deleted VM successfully");
	}
	
}
