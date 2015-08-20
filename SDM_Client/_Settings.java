import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class _Settings {
	
	final String _dataStore = "boundlist-1542-listEl";
	final String _FilePathFootPrintSelect = "boundlist-1591-listEl";
	final String _SWLibFileSelect = "boundlist-1546-listEl";
	final String _SWLibandURLFootPrintSelect = "boundlist-1545-listEl";
	final String _LocationSelect = "boundlist-1537-listEl";
	final String _TimeZoneSelect = "boundlist-1594-listEl";
	final String _NetworkPublicSelect = "boundlist-1595-listEl";
	final String _NetworkOOBMSelect = "boundlist-1596-listEl";
	final List<String> _bListGetComponent = new ArrayList<String>();
	
	public void _addToList(){
		_bListGetComponent.add(_dataStore);
		_bListGetComponent.add(_FilePathFootPrintSelect);
		_bListGetComponent.add(_SWLibFileSelect);
		_bListGetComponent.add(_SWLibandURLFootPrintSelect);
		_bListGetComponent.add(_LocationSelect);
		_bListGetComponent.add(_TimeZoneSelect);
		_bListGetComponent.add(_NetworkPublicSelect);
		_bListGetComponent.add(_NetworkOOBMSelect);
	}
	
	public FirefoxProfile _selectProfile(String _profile){
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile(_profile);
		logClass.confFile();
		return profile;
	}
	
	public String _readFromFile(String _fileName,String _find) throws IOException{
		File file = new File(_fileName);
		List<String> lines = FileUtils.readLines(file);
		Scanner sc;
		String _output = null;
		for(String s : lines)
			{
				sc = new Scanner(s);
					while(sc.hasNext())
						if(sc.next().contains(_find))
							if(sc.hasNext())
							_output = sc.next();
			}
		return _output;
	}
	
	public void _findLocationOrHost(WebDriver driver, String _input){
		WebElement table = driver.findElement(By.id("treeview-1014"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='tr']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			//System.out.println(e.getText());
			if(e.getText().trim().equals(_input))
				e.click();
		}
	}
	
	public void _findLocationInGrid(WebDriver driver, String _Location){
		WebElement table = driver.findElement(By.id("gridview-1054"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			if(e.getText().trim().equals(_Location))
				e.click();
		}
	}
	
	public void _findHostInGrid(WebDriver driver, String _Host){
		WebElement table = driver.findElement(By.id("gridview-1115"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	//System.out.println("Test:"+e.getText()+"\n");
			if(e.getText().trim().equals(_Host))
				e.click();
		}
	}
	
	public void _findVMForHost(WebDriver driver, String _Host){
		WebElement table = driver.findElement(By.id("gridview-1190"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	//System.out.println(e.getText());
			if(e.getText().trim().equals(_Host))
				e.click();
		}
	}
	
	public void _findVCenterInGrid(WebDriver driver, String _VCenter){
		WebElement temp = driver.findElement(By.xpath(".//*[@id='gridview-1315']"));
		List<WebElement> tempcells = temp.findElements(By.xpath(".//*[local-name(.)='td']"));
		
		for(WebElement e : tempcells)
		{	
			if(e.getText().trim().equals(_VCenter))
				e.click();
		}	
	}

	public void _boundListSelect(WebDriver driver,String _toBeSelected,int i){
		WebElement element = driver.findElement(By.id(_bListGetComponent.get(i)));
		List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
		for (WebElement e : tmp1 )
			{
				//System.out.println(e.getText()+ "\n Test \n");
				if(e.getText().contains(_toBeSelected))
				{
					System.out.println("\nSelected : \n"+e.getText());
					e.click();
				}
			}
	}
	
	public void _checkFailureOfHostCapacity(WebDriver driver){
		WebElement temp = driver.findElement(By.xpath(".//*[@id='gridview-1222']"));
		List<WebElement> tempcells = temp.findElements(By.xpath(".//*[local-name(.)='td']/div/span"));
		List<WebElement> tempcellsRow = temp.findElements(By.xpath(".//*[local-name(.)='tr']"));
		int count=0;

		for(int i=0;i<tempcells.size();i++)
		{
			if(tempcells.get(i).getAttribute("class").contains("failure-host-icon"))
			{
				count++;
			}
		}
		
		int index[] = new int[count];
		
		for(int i=0;i<tempcells.size();i++)
			{
				if(tempcells.get(i).getAttribute("class").contains("failure-host-icon"))
				{
					//System.out.println("Test:"+tempcells.get(i).getText()+" Number "+i+"\n");
					index[i]=i;
				}
			}
		
		for(int i=0;i<count;i++)
			{
				System.out.println(tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
				logClass.error("Reason For Failure : "+tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
			}
	}
	
	public String _IPConvert(WebDriver driver,String _IP,int _partIndex){
		String[] parts = _IP.split("\\."); 
		return parts[_partIndex];
	}
	
	public String _removeAlpha(String str){
		StringBuilder sc = new StringBuilder();
	            for (int i=0;i<str.length();i++)
		        if (!Character.isDigit(str.charAt(i)))
		            continue;
		        else 
		            sc.append(str.charAt(i));
	        return sc.toString();
	}

	public List<String> _makeFields(String _input){
		List<String> _Fields = new ArrayList<String>();
		int _it = Integer.parseInt(_removeAlpha(_input));
		for(int i=0;i<4;i++)
			_Fields.add(".//*[@id='textfield-"+(_it+(i*2))+"-inputEl']");
		return _Fields;
	}
	
	public void _IPFill(WebDriver driver,String _IP,String _startAddress){
		List<String> _addresses = _makeFields(_startAddress);
		for(int i=0;i<4;i++)
		{
			driver.findElement(By.xpath(_addresses.get(i))).clear();
			driver.findElement(By.xpath(_addresses.get(i))).sendKeys(_IPConvert(driver, _IP, i));
		}	
	}
	
	@SuppressWarnings("deprecation")
	public String fluentWait(final By locator,WebDriver driver) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(500, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.textToBePresentInElement(locator, "VM Deployment Completed"));
		logClass.info(driver.findElement(locator).getText());
	    return driver.findElement(locator).getText();
	}
	
	public boolean _checkError(WebDriver driver){
		boolean b = false;
		try{
			if(driver.findElement(By.id("messagebox-1001")).isDisplayed())
				b = true;
		}
		catch(Exception ex){
			b=false;
		}
		return b;
	}
	public void _errorBox(WebDriver driver,boolean _check){
		if(_check)
		{
			try{
				if(driver.findElement(By.id("messagebox-1001")).isDisplayed())
				{
					driver.findElement(By.id("messagebox-1001-displayfield-inputEl")).getText();
					logClass.error(driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
					logClass.error("Error : Check the Log and Screenshot for the same.");
					System.out.println(driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
				
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("C:\\Users\\bshingala\\Desktop\\screenshotSDM.png"));
					logClass.error("Something went wrong :(");
					logClass.info("Check Screenshot for the same");
				
					driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();
				}
			}
			catch(Exception ex){
				System.out.println("No Errors Uptill Now.");
			}
		}
	}
	
	public void _exec(boolean _in) {
	    if(_in == true) {
	        return;
	    }
	    System.out.println("Can not Execute Further. Check the Log for the same.");
	    logClass.error("Check Log and Screenshot for the same.");
	    System.exit(0);
	}
	
	public void _confirmDialogBox(WebDriver driver){
		driver.switchTo().activeElement();
		
		try{
			if(driver.findElement(By.id("messagebox-1001")).isDisplayed())
			{
				logClass.info("Action being performed: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());		
				System.out.println("Action being performed: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
			}
		}
		catch(Exception ex){
			System.out.println("Couldn't find any text");
		}
		
		driver.findElement(By.xpath(".//*[@id='button-1006-btnIconEl']")).click();
		
		try{
			driver.switchTo().activeElement();
			driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();
			System.out.println("Confirmed");
		}
		catch(Exception ex){
			System.out.println("Confirmed");
			logClass.info("Confirmed");
		}
	}
	
}

