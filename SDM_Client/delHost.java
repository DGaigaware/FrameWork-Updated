import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class delHost {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Deleting Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "NewLocation");
	
		obj._findHostInGrid(driver, obj._readFromFile("input.txt", "NewHostName"));
		
		driver.findElement(By.xpath(".//*[@id='deleteHostBtn-btnInnerEl']")).click();

		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Deleted Host");
	}

}
