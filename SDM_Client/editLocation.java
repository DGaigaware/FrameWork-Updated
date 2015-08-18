import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class editLocation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();

		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		
		logClass.startTestCase("Edit Location on SDM");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
	
		obj._findLocationInGrid(driver, "testLoc");
		
		driver.findElement(By.xpath(".//*[@id='editlocation-btnInnerEl']")).click();
		
		driver.findElement(By.xpath(".//*[@id='textareafield-1089-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textareafield-1089-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewAddress"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1090-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1090-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewCity"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1091-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1091-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewState"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1092-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1092-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewZIP"));
		
		driver.findElement(By.xpath(".//*[@id='textfield-1093-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='textfield-1093-inputEl']")).sendKeys(obj._readFromFile("input.txt", "NewCountry"));
		
		driver.findElement(By.xpath(".//*[@id='saveeditlocation-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='saveeditlocation-btnInnerEl']")).click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();
		logClass.info("Saved Location");
		
		logClass.endTestCase("Edited Location");
		
		
		//
	}

}
