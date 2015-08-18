import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class editVM {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Editing VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testcmm221");
		driver.findElement(By.xpath(".//*[@id='editvm-btnInnerEl']")).click();
		logClass.info("Clicked on - Edit VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='radiofield-1248-inputEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='editVMChangeVMIPFQDN-btnIconEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1254-inputEl']")).sendKeys("148.147.162.221");
		
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1255-inputEl']")).sendKeys("testcmm221");
		
		driver.findElement(By.xpath(".//*[@id='saveEditVM-btnInnerEl']")).click();
		
		logClass.endTestCase("Edited VM Successfully");
	}

}
