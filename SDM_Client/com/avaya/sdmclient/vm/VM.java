package com.avaya.sdmclient.vm;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

import utility.UserAction;

public class VM {
	Settings obj = new Settings();
	WebDriver driver = new FirefoxDriver(obj.selectProfile("Selenium"));
	UserAction action = new UserAction();
	
	@Test(description="Adding Location")
	public void AddLocation() throws IOException, InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Add a new Location on SDM");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		driver.findElement(By.xpath(".//*[@id='addnewlocation-btnInnerEl']")).click();
		logClass.info("Adding new Location");
		
		driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='Name-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewLocation"));
		
		driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textareafield-1072-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FAddress"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1073-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FCity"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1074-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FState"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1075-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FZIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1076-inputEl']")).sendKeys(obj.readFromFile("input.txt", "FCountry"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='savenewlocation-btnInnerEl']")).click();
		logClass.info("Saved New Location");
		
		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Added a new Location");
		
	}
	
	@Test(description="Adding Host to given Location",priority=1)	
	public void addHost() throws IOException, InterruptedException{
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Adding Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
				
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "NewLocation"));

		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
		
		driver.findElement(By.xpath(".//*[@id='newHostBtn_-btnInnerEl']")).click();
		logClass.info("Adding new Host");
		
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).sendKeys(obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).sendKeys(obj.readFromFile("input.txt", "HostIP175"));
		
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).sendKeys(obj.readFromFile("input.txt", "username175"));
		
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).sendKeys(obj.readFromFile("input.txt", "password175"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='saveNewHost']")).click();
	
		obj.confirmDialogBox(driver);

		logClass.endTestCase("Added Host Succesfully");
	}		
	
	@Test(description="Adding VM to given Location and Host",priority=2)
	
	public void AddVMSuite() throws InterruptedException, IOException, ParserConfigurationException, SAXException {

		boolean Check;
		
		final String defaultOp = "Filepath";
		final String SWLib = "Software Library";
		final String URL = "URL";
		
		driver.manage().timeouts().implicitlyWait(6500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='newVM_-btnInnerEl']")).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).clear();
		//driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj._readFromFile("input.txt", "VMName"));	
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys(obj.readFromFile("input.txt", "VMName221"));
		logClass.info("Given Name");
		Thread.sleep(250);
		
		obj.errorBox(driver,obj.checkError(driver));
		
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		Thread.sleep(250);
		obj.errorBox(driver,obj.checkError(driver));
		
		obj.boundListSelect(driver, "data", obj.selBoundList(driver));
		
		//1 - File Path; 3 - SW Library; 4 - URL 
		switch(defaultOp){
			case defaultOp:
					driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
					logClass.info("Choosen From File");
		
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj.readFromFile("input.txt", "SMFilePath"));
					driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
					logClass.info("File Path Given");
					Thread.sleep(2500);
					break;
			case URL:
					driver.findElement(By.xpath(".//*[@id='radio4-inputEl']")).click();
					logClass.info("Choosen From URL");
					
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
					driver.findElement(By.xpath(".//*[@id='button-1240-btnIconEl']")).click();
					Thread.sleep(450);
					break;
			case SWLib:
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
		Thread.sleep(500);
		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj.boundListSelect(driver, "Profile 1", obj.selBoundList(driver));
		//Thread.sleep(250);
		Check = obj.checkError(driver);
		obj.errorBox(driver,obj.checkError(driver));
	
		driver.findElement(By.xpath(".//*[@id='gridcolumn-1221-textEl']")).click();
		obj.checkFailureOfHostCapacity(driver);
		
		obj.exec(!Check);
		
		//removed
		
		obj.FillValues("inputsm.txt", "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf", driver);
		
		driver.findElement(By.xpath(".//*[@id='button-1245-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='button-1589-btnInnerEl']")).click();
		logClass.info("Accepted EULA");
		
		Thread.sleep(9000);
		obj.findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, "testSM221");
		
		driver.findElement(By.linkText("Status Details")).click();
		logClass.info("Checking Status Details");
		
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
		
		System.out.println(obj.fluentWaitCloseOpen(By.id("vmDeployStatus"), driver, 1500, "VM Deployment Completed"));
		Thread.sleep(1000);
		//obj.StatusCheck(driver, "VM Deployment Completed", 20);
		obj.closeWindow(driver);
		Thread.sleep(5000);
		
	}
	
	
	/*@Test(description="Editing VM to given Location and Host",priority=3)

	public void EditVM() throws InterruptedException, IOException{
		
		logClass.startTestCase("Editing VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		driver.findElement(By.xpath(".//*[@id='editvm-btnInnerEl']")).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='radiofield-1248-inputEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='editVMChangeVMIPFQDN-btnIconEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).sendKeys(obj.readFromFile("input.txt", "IPI"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).sendKeys(obj.readFromFile("input.txt", "NewVMName"));
		
		driver.findElement(By.xpath(".//*[@id='saveEditVM-btnInnerEl']")).click();
		
		obj.errorBox(driver, obj.checkError(driver));
		logClass.endTestCase("Edited VM Successfully");
	}

	
	@Test(description="Stoping VM to given Location and Host",priority=4)

	public void StopVM() throws InterruptedException, IOException{
		
		Thread.sleep(5000);
		logClass.startTestCase("Stop VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		
		driver.findElement(By.xpath(".//*[@id='stopvm-btnInnerEl']")).click();
		
		obj.confirmDialogBox(driver);
		
		logClass.endTestCase("Stopped VM successfully");
		Thread.sleep(60000);
	}

	
	@Test(description="Starting VM to given Location and Host",priority=5)

	public void StartVM() throws InterruptedException, IOException{
		
		Thread.sleep(5000);
		logClass.startTestCase("Start VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		
		driver.findElement(By.xpath(".//*[@id='startvm-btnInnerEl']")).click();
		
		obj.confirmDialogBox(driver);
		
		logClass.endTestCase("Started VM successfully");
		Thread.sleep(60000);
	}
*/
	/*
	@Test(description="Refreshing VM to given Location and Host",priority=6)

	public void RefreshVM() throws InterruptedException, IOException{

		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		
		driver.findElement(By.xpath(".//*[@id='button-1192-btnInnerEl']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath(".//*[@id='bulkReestablishConnection-btnInnerEl']")).click();
		
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")));
		driver.switchTo().activeElement();
		
		driver.findElement(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")).sendKeys(obj.readFromFile("input.txt", "CustomerName"));
		
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).sendKeys(obj.readFromFile("input.txt", "CustPwd"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='reestablishConnProceed-btnIconEl']")));
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='reestablishConnProceed-btnIconEl']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.linkText("Status Details")).click();
		obj.StatusCheck(driver, "VM Trust Establishment Completed",50);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='refreshvm-btnInnerEl']")));
		
		if(driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).isEnabled())
		driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).click();
		//Added
		Thread.sleep(5000);
		WebElement table1 = driver.findElement(By.id("gridview-1190"));
		List<WebElement> cells1 = table1.findElements(By.xpath(".//*[local-name(.)='td']"));
		for(WebElement e : cells1)
			
		{	
			if(e.getText().trim().contains("..."))
			{
				System.out.println("next"+e.getText());
				e.findElement(By.linkText("Status Details")).click();
			}
			
		}
		System.out.println(obj.fluentWait(By.id("vmDeployStatus"), driver, 50, "VM Refresh Completed"));
		obj.StatusCheck(driver, "VM Refresh Completed", 20);
		// Uptill Now
		Thread.sleep(2500);
		//obj.StatusCheck(driver, "VM Refresh Completed", 50);
		logClass.endTestCase("VM refreshed Successfully");
	}

	
	@Test(description="Restarting VM to given Location and Host",priority=7)

	public void RestartVM() throws IOException, InterruptedException{

		logClass.startTestCase("Restart VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName175"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		
		driver.findElement(By.xpath(".//*[@id='restartvm-btnInnerEl']")).click();
		
		obj.confirmDialogBox(driver);
		
		logClass.endTestCase("Restarted VM successfully");
		Thread.sleep(100000);
	}
	*/
	
	@Test(description="Deleting VM to given Location and Host",priority=8)

	public void DeleteVM() throws IOException{

		logClass.startTestCase("Delete VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj.findLocationOrHost(driver, obj.readFromFile("input.txt", "HostName"));
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj.findVMForHost(driver, obj.readFromFile("input.txt", "VMName221"));
		
		driver.findElement(By.xpath(".//*[@id='removevms-btnInnerEl']")).click();
		
		obj.confirmDialogBox(driver);
		
		logClass.endTestCase("Deleted VM successfully");
	}
	
}
