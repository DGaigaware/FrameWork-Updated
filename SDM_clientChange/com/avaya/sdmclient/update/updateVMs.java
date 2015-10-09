package com.avaya.sdmclient.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avaya.sdmclient.Settings;
import com.avaya.sdmclient.logClass;

public class updateVMs {
	
	Settings obj = new Settings();
	WebDriver drive = new FirefoxDriver(obj.selectProfile("Selenium"));
	Properties locator = null;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException
	{
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	@Test(description="Refreshing VM to given Location and Host",priority=0)
	@Parameters({"VMName"})
	public void RefreshVM(String VMName) throws InterruptedException, IOException{
		
		Thread.sleep(5000);
		logClass.startTestCase("Refresh VM to given Location and Host");

		obj.goToSDMCliURL(drive);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "TempHost"));

		obj.chooseTab(drive, "Virtual Machines");
		obj.findVMForHost(drive,VMName);

		obj.findMoreActionsButton(drive);
		Thread.sleep(500);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConn"))).click();

		Thread.sleep(500);
		
		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnUN")));
		
		drive.switchTo().activeElement();

		drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		drive.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(obj.readFromFile("inputsm.properties", "UName"));

		drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		drive.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(obj.readFromFile("inputsm.properties", "PWD"));

		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("VMReEstConnConf")));

		Thread.sleep(250);
		drive.findElement(By.xpath(locator.getProperty("VMReEstConnConf"))).click();

		Thread.sleep(5000);

		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "TempHost"));

		obj.chooseTab(drive, "Virtual Machines");

		obj.chooseLink(drive, VMName);
		obj.StatusCheck(drive, "VM Trust Establishment Completed",50);

		obj.waitForPresenceOfElement(drive, By.xpath(locator.getProperty("RefreshVM")));

		Thread.sleep(1500);
		if(drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).isEnabled())
			drive.findElement(By.xpath(locator.getProperty("RefreshVM"))).click();

		Thread.sleep(5000);
		
		obj.chooseLink(drive, VMName);
		System.out.println(obj.fluentWait(By.id(locator.getProperty("vmDeployStatus")), drive, 50, "VM Refresh Completed"));
		obj.StatusCheck(drive, "VM Refresh Completed", 20);

		Thread.sleep(2500);

		logClass.endTestCase("VM refreshed Successfully");
	}

	
	@Test(description="Update SMGR from SDM Client",priority=1)
	@Parameters({"VMName"})
	public void updateVM(String VMName) throws IOException, InterruptedException {

		logClass.startTestCase("Add a new Location on SDM");

		obj.goToSDMCliURL(drive);
		
		obj.findLocationOrHost(drive, obj.readFromFile("input.properties", "TempHost"));

		obj.chooseTab(drive, "Virtual Machines");
		obj.findVMForHost(drive,VMName);
		
		obj.findMoreActionsButton(drive);
		Thread.sleep(500);
		
		drive.findElement(By.id("updatevm")).click();
		
		obj.updateVMFilePath(drive, obj.readFromFile("inputsm.properties", "UpdatePatch"));
		//panelselectOVALocalUpdateVM-body
		
		drive.findElement(By.id("proceedUpdate1VM")).click();
		
		obj.findButtonUpdate(drive);
		
		obj.chooseLink(drive, VMName);
		//obj.fluentWaitCloseOpen(locatorTo, drive, 2200, Test, VMName)
		//System.out.println(obj.fluentWaitCloseOpen(By.id(locator.getProperty("vmDeployStatus")), drive, 2200, "Completed",VMName));
		
		obj.checkSuccessOrFailure(drive, By.id(locator.getProperty("vmDeployStatus")),VMName, 2200, true);
		
		Thread.sleep(1000);
		//obj.StatusCheck(driver, "VM Deployment Completed", 20);
		obj.closeWindow(drive);
	}
}
