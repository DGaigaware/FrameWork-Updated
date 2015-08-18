import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class addvCenter {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		
		logClass.startTestCase("Adding vCenter to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "Pune");
		
		driver.findElement(By.xpath(".//*[@id='menuitem-1022-textEl']")).click();
		logClass.info("Clicked on 'Map vCenter' ");
		
		driver.findElement(By.xpath(".//*[@id='newVcenterBtn-btnInnerEl']")).click();
		logClass.info("Adding vCenter");
		
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1333-inputEl']")).sendKeys(obj._readFromFile("input.txt", "vCenterIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1334-inputEl']")).sendKeys(obj._readFromFile("input.txt", "usernameVC"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1335-inputEl']")).sendKeys(obj._readFromFile("input.txt", "passwordVC"));
				
		Thread.sleep(250);
		driver.findElement(By.xpath(".//*[@id='findHostBtn-btnIconEl']")).click();
		
		obj._errorBox(driver,obj._checkError(driver));
		
		logClass.endTestCase("Added vCenter Succesfully");
		
	}

}
