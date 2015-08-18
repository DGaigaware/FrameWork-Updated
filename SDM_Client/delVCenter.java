import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class delVCenter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Adding vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		obj._findVCenterInGrid(driver, obj._readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='deleteVcenterBtn-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1006-btnIconEl']")).click();
		
		logClass.endTestCase("Deleted Successfully");
	}

}
