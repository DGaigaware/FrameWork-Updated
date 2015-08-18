import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class addHost {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Adding Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
				
		obj._findLocationOrHost(driver, "Pune");

		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
		
		driver.findElement(By.xpath(".//*[@id='newHostBtn_-btnInnerEl']")).click();
		logClass.info("Adding new Host");
		
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameField-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnField-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameId-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordId-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostPwd"));
		
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='saveNewHost']")).click();
	
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();

		logClass.endTestCase("Added Host Succesfully");
		
	}

}
