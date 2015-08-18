import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class editHost {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
			
		logClass.startTestCase("Editing Host to given Location");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='tab-1305-btnInnerEl']")).click();
		logClass.info("In 'Host' Tab");
	
		obj._findHostInGrid(driver, "GivenHost");
		
		driver.findElement(By.xpath(".//*[@id='editHostBtn-btnInnerEl']")).click();
		
		System.out.println("\n\n\n");
		
		driver.findElement(By.xpath(".//*[@id='combobox-1137-inputEl']")).click();
		obj._addToList();
		
		obj._boundListSelect(driver, obj._readFromFile("input.txt", "NewLoc"), 4);
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
	    driver.findElement(By.linkText(linkText)).click();*/
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostNameFieldEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostName"));
		
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='hostIpOrFqdnFieldEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostIP"));
		
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='usernameIdEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostUser"));
		
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='paswordIdEdit-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewHostPwd"));
		Thread.sleep(250);
		
		driver.findElement(By.xpath(".//*[@id='saveOnEditHost-btnInnerEl']")).click();
		logClass.endTestCase("Edited Host Succesfully");
	}

}
