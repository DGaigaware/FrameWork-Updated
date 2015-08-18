import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class driverDemo {

	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		
		logClass.startTestCase("Editing VM to given Location and Host");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		
		obj._findVMForHost(driver, "testSM221");
		
		driver.findElement(By.linkText("Status Details")).click();
		
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
		
		System.out.println(obj.fluentWait(By.id("vmDeployStatus"), driver));
		
		if(driver.findElement(By.id("vmDeployStatus")).getText().contains("VM Deployment Completed"))
			{
				driver.findElement(By.xpath(".//*[@id='tool-1541-toolEl']")).click();
				System.out.println("Completed Successfully");
			}
		
	}
}
