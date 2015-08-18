import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class refreshVM {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Delete VM to given Location and Host");
		
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
		driver.findElement(By.xpath(".//*[@id='userNameRestablishConn-inputEl']")).sendKeys("dadmin");
		
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='passwordRestablishConn-inputEl']")).sendKeys("dadmin01");
		
		driver.findElement(By.xpath(".//*[@id='reestablishConnProceed-btnIconEl']")).click();
		
		Thread.sleep(5000);
		
		if(driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).isEnabled())
		driver.findElement(By.xpath(".//*[@id='refreshvm-btnInnerEl']")).click();
		
		System.out.println("dfbgbdfhbhdf");
	}

}
