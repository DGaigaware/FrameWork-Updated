import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class delLocation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("Selenium");
		logClass.confFile();

		WebDriver driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		Settings obj = new Settings();
		
		logClass.startTestCase("Delete Location on SDM");
		
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(".//*[@id='menuitem-1014-textEl']")).click();
		logClass.info("Clicked on VM management");
		
		obj._findLocationOrHost(driver, "VM Management");
		
		obj._findLocationInGrid(driver, obj._readFromFile("input.txt", "Location"));
		
		driver.findElement(By.xpath(".//*[@id='deletelocation-btnInnerEl']")).click();	
		
		obj._confirmDialogBox(driver);
		
		logClass.endTestCase("Deleted Location");
	}

}
