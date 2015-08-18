import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class sampleRun {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		obj._addToList();
		final String _default = "Filepath";
		final String _SWLib = "Software Library";
		final String _URL = "URL";
		WebDriver driver = new FirefoxDriver(obj._selectProfile("Selenium"));
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		
		logClass.startTestCase("Adding VM to given Location and Host");

		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "testHost");
		driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		obj._findVMForHost(driver, "testSM221");
		//x-grid-row x-grid-row-selected x-grid-row-alt x-grid-data-row x-grid-row-focused
		
		/*driver.findElement(By.xpath(".//*[@id='tab-1306-btnInnerEl']")).click();
		driver.findElement(By.xpath(".//*[@id='newVM_-btnInnerEl']")).click();
		logClass.info("Clicked on - Add new VM");
		Thread.sleep(750);
		
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).clear();
		driver.findElement(By.xpath(".//*[@id='vmname-inputEl']")).sendKeys("testSM2211");	
		logClass.info("Given Name");
		Thread.sleep(450);
		
		obj._errorBox(driver);
		driver.findElement(By.xpath(".//*[@id='cmbVMDataStore-inputEl']")).click();
		obj._errorBox(driver);
		Thread.sleep(250);

		obj._boundListSelect(driver, "data", 0);
		
		//1 - File Path; 3 - SW Library; 4 - URL 
		switch(_default){
			case _default:
					driver.findElement(By.xpath(".//*[@id='radio1-inputEl']")).click();
					logClass.info("Choosen From File");
		
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='textfield-1228-inputEl']")).sendKeys(obj._readFromFile("input.txt", "FilePath"));
					driver.findElement(By.xpath(".//*[@id='button-1229-btnIconEl']")).click();
					logClass.info("File Path Given");
					Thread.sleep(500);
					break;
			case _URL:
					driver.findElement(By.xpath(".//*[@id='radio4-inputEl']")).click();
					logClass.info("Choosen From URL");
					
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).clear();
					driver.findElement(By.xpath(".//*[@id='txtOVAURL-inputEl']")).sendKeys("http://148.147.214.158/alternate_source/SM-7.0.0.0.700007-e55-01.ova");
					driver.findElement(By.xpath(".//*[@id='button-1240-btnIconEl']")).click();
					Thread.sleep(450);
					break;
			case _SWLib:
					driver.findElement(By.xpath(".//*[@id='radio3-inputEl']")).click();
					logClass.info("Choosen From Software Library");
					
					driver.findElement(By.xpath(".//*[@id='combobox-1237-inputEl']")).click();
					WebElement element = driver.findElement(By.id("boundlist-1543-listEl"));
					List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
					for (WebElement e : tmp1 )
						{
							//System.out.println(e.getText()+ "\n Test \n");
							if(e.getText().contains("SM"))
							{
								System.out.println("\nSelected : \n"+e.getText());
								e.click();
							}
						}
					break;
		}
		
		//obj._errorBox(driver);

		driver.findElement(By.xpath(".//*[@id='cmbSelectFlexiFootPrint-inputEl']")).click();
		Thread.sleep(450);
		
		obj._boundListSelect(driver, "Profile 5", 1);
		//Thread.sleep(250);
		obj._errorBox(driver);
		obj._checkFailure(driver);
	*/
		
	}

}
