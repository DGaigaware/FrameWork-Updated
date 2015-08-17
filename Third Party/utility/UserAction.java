package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.google.common.base.Function;

public class UserAction {
 
	private static final TimeUnit SECONDS = null;
	private static final TimeUnit MILLISECONDS = null;

	boolean b,c;
	public WebDriver driver;
	Properties input=null;
	Properties locator=null;
	public void openbrowser(String URL) throws FileNotFoundException, IOException{
	    driver = new FirefoxDriver();
		driver.get(URL);
        driver.manage().window().maximize();
	   	input=new Properties();
	    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	}
	
	
	
	public  void login(String UserId,String password, UserAction action) throws InterruptedException, IOException {
		  Properties prop = new Properties();
		  FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties");
			prop.load(fis);
			action.openbrowser(prop.getProperty("URL"));
			action.driver.manage().deleteAllCookies();
			action.driver.navigate().refresh();
			action.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			action.entertext(prop.getProperty("UserId"), UserId);
			action.entertext(prop.getProperty("Password"), password);
			action.ClickButton(prop.getProperty("LogOn"));
			//action.ClickLink("Administrators");
	  }
	
	public  void logintosecondary(String UserId,String password, UserAction action) throws InterruptedException, IOException {
		  Properties prop = new Properties();
		  FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties");
			prop.load(fis);
			action.openbrowser(prop.getProperty("SecondarygeoURL"));
			action.driver.manage().deleteAllCookies();
			action.driver.navigate().refresh();
			action.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			action.entertext(prop.getProperty("UserId"), UserId);
			action.entertext(prop.getProperty("Password"), password);
			action.ClickButton(prop.getProperty("LogOn"));
			//action.ClickLink("Administrators");
	  }
	
	 public  void logout(UserAction action) throws InterruptedException, IOException {
		 Properties prop = new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties");
		 prop.load(fis);
		 action.driver.switchTo().defaultContent();
		 action.ClickButton(prop.getProperty("LogOff"));
		 action.driver.quit();

	  }
	 public  void input(UserAction action) throws InterruptedException, IOException {
		 Properties prop = new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties");
		 prop.load(fis);
		 action.closebrowser();
	  }
	 public  void Screenshot(ITestResult result,UserAction action) throws InterruptedException, IOException {
	     if(!result.isSuccess())
	        { 
	             String workingDirectory = System.getProperty("user.dir"); 
	              File imageFile=((TakesScreenshot)action.driver).getScreenshotAs(OutputType.FILE);
	              DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	              GregorianCalendar gcalendar = new GregorianCalendar();
	              String failureImageFileName=workingDirectory+ File.separator+"Screenshots"+File.separator+"Screenshots"+ dateFormat.format(new java.util.Date())+File.separator +result.getMethod().getMethodName()+gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)
	                         +".png";
	              File failureImageFile=new File(failureImageFileName);
	              FileUtils.moveFile(imageFile, failureImageFile);
	        }
	}
	public void closebrowser(){
		driver.quit();
	}
	
	public void entertext(String objID, String txt) {
	
		driver.findElement(By.xpath(objID)).sendKeys(txt);
	}
	
	public void EntertextUsingKey(String objID, String Value, Keys Key) {

		driver.findElement(By.xpath(objID)).sendKeys(Value,Key);
		}

	public void SelectRadioButton(String objID) throws FileNotFoundException, IOException{
	    String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
	    WebElement ClkBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objID)));
	    ClkBtn.click();
	}
	
	public void SelectFromdropDown(String objID, String value) throws FileNotFoundException, IOException,Exception{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID))).click();
		Select dropdown = new Select(driver.findElement(By.xpath(objID)));
		dropdown.selectByVisibleText(value);
		Thread.sleep(1000);
		driver.findElement(By.xpath(objID)).sendKeys(Keys.RETURN);

	}
	
	public void SelectFromdropDownbyValue(String objID, String value) throws FileNotFoundException, IOException,Exception{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID))).click();
		Select dropdown = new Select(driver.findElement(By.xpath(objID)));
		dropdown.selectByValue(value);
		Thread.sleep(1000);
		driver.findElement(By.xpath(objID)).sendKeys(Keys.RETURN);

	}
	public void SelectFromdropDown_Index(String objID, int value) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID))).click();
		Select dropdown = new Select(driver.findElement(By.xpath(objID)));
		dropdown.selectByIndex(value);
		driver.findElement(By.xpath(objID)).sendKeys(Keys.RETURN);

	}

	public void SelectCheckBox(String objID) throws FileNotFoundException, IOException, InterruptedException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID)));
		WebElement ele=driver.findElement(By.xpath(objID));
		while(!(ele.isSelected()))
		{
			ele.click();
			Thread.sleep(100);
		}
	    
	}
	
	public void DeselectCheckBox(String objID) throws FileNotFoundException, IOException, InterruptedException{
		
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
		WebDriverWait wait = new WebDriverWait(driver,t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID)));
		WebElement ele=driver.findElement(By.xpath(objID));
		while(ele.isSelected())
		{
			ele.click();
			Thread.sleep(100);
		}
	    
	}
	
	
	public void ClearText(String objID){
		driver.findElement(By.xpath(objID)).clear();;
	}
	public void CloseTab(String objID) throws Exception{
		Thread.sleep(1000);
		driver.findElement(By.xpath(objID)).click();
	}
	public void SwithchFrame(String objID){
		driver.switchTo().defaultContent();
	    driver.switchTo().frame(objID);
	}
		
		public void RefreshPage(){
		driver.navigate().refresh();
	}
		
		public void WaitToClick(String objID)
		{
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objID)));
		}
		 public void SelectTenantTree(WebElement objID,String objID1) {
			    new FluentWait<WebElement>(objID).withTimeout(3, SECONDS)
			        .pollingEvery(100, MILLISECONDS)
			        .until(new Function<WebElement, Boolean>() {
			          public Boolean apply(WebElement w) {
			            return w.isDisplayed();
			          }
			        });
			    objID.click();
			  }
		public void WaitToClickLink(String objID)
		{
			WebDriverWait wait = new WebDriverWait(driver,80);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(objID)));
		}
	//Waiting for Link
	public void ClickLink(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    WebElement Link=wait.until(ExpectedConditions.elementToBeClickable(By.linkText(objID)));
	    Link.click();
	}
//Waiting for Link using xpath
	public void ClickLinkByxpath(String objID){
	    WebDriverWait wait = new WebDriverWait(driver,30);
	    WebElement Link=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objID)));
	    Link.click();
	}
	
	//Waiting for object
	public void ClickButton(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    WebElement Button=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objID)));
	    Button.click();
	}
	
	public void DoubleClickButton(String objID) throws FileNotFoundException, IOException,Exception
	{
		WebElement Commit_Btn=driver.findElement(By.xpath(objID));
		Commit_Btn.sendKeys(org.openqa.selenium.Keys.CONTROL);
		Thread.sleep(1000);
		Actions builder = new Actions(driver);
		//build the action chain.
		Action doubleclick = builder.doubleClick(Commit_Btn).build();
		doubleclick.perform();
	}
	
	
	public void ClickElement(String objID)
	{
	driver.findElement(By.xpath(objID)).click();
	}
	//Waiting for Title
	public void WaitForTitle(String title) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    wait.until(ExpectedConditions.titleContains(title));
	}
	//Wait for Object
	public void WaitForObj(String ObjID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjID)));
	}
	public void VerifyElementValue(String objID, String value) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);	   
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID)));
	    b=driver.findElement(By.xpath(objID)).getText().equals(value);
	   	Assert.assertTrue(b);
	}
	public void VerifyAttributeValue(String objID, String value) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);	   
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID)));
	    String str=driver.findElement(By.xpath(objID)).getAttribute("title");
	    b=str.equals(value);
	   	Assert.assertTrue(b);
	}
	public void VerifyTitle(String title) throws FileNotFoundException, IOException {
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);	   
	    boolean b= wait.until(ExpectedConditions.titleContains(title));
		Assert.assertTrue(b);

	}
	public void VerifyStringValue(String text){
	   b=driver.getPageSource().contains(text);
		Assert.assertTrue(b);
	}
	public void VerifyNoStringValue(String text) {
		b=driver.getPageSource().contains(text);
		Assert.assertFalse(b);		
	}
	public void VerifyNoElementdisplay(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);		
	    Boolean Element=wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objID)));
		Assert.assertTrue(Element);
	}
	public void VerifyElementDisplay(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);	   
	    Boolean Element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objID))).isDisplayed();
		Assert.assertTrue(Element);
	}
	public void VerifyReadOnlyDisplayed(String objID) throws FileNotFoundException, IOException{
	    WebElement Element = driver.findElement(By.xpath(objID));
	    String read_only = Element.getAttribute("readonly");
	    Assert.assertNotNull(read_only);
	}
	public void VerifyEnableButton(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertTrue(Element);
	}
	public void VerifyDisableButton(String objID) throws FileNotFoundException, IOException{
		String s=input.getProperty("MAX_TIME");
	    int t=Integer.parseInt(s);
	    WebDriverWait wait = new WebDriverWait(driver,t);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertFalse(Element);	
	}
	public void VerifyEnableTextbox(String objID){
		WebDriverWait wait = new WebDriverWait(driver,20);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertTrue(Element);	
	}
	public void VerifyDisableTextbox(String objID){
		WebDriverWait wait = new WebDriverWait(driver,20);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertFalse(Element);	
	}
public void VerifyEnableCheckbox(String objID){
		WebDriverWait wait = new WebDriverWait(driver,20);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertTrue(Element);	
	}
public void VerifyDisableCheckbox(String objID){
		WebDriverWait wait = new WebDriverWait(driver,20);
	    Boolean Element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objID))).isEnabled();
		Assert.assertFalse(Element);	
	}
public void VerifySelectcheckbox(String objID)
	{
		boolean s4=driver.findElement(By.xpath(objID)).isSelected();
	    Assert.assertTrue(s4);
	}
	
	public void VerifydeSelectcheckbox(String objID)
	{
		boolean s4=driver.findElement(By.xpath(objID)).isSelected();
	    Assert.assertFalse(s4);
	}
	public void VerifydropDownValues(String objID, String[] expected2) {
		Select dropdown = new Select(driver.findElement(By.xpath(objID)));
		int i,count=0;
		boolean match=false;
	    List<WebElement> select1 = dropdown.getOptions();  
	      for(WebElement we:select1)  
	    	  
	       {  
	        for(i=0; i<expected2.length; i++){
	        	if(we.getText().equals(expected2[i])){

	        		count++;	
	        		match=true;
	        	}
	        } 
	    	if(match==false){
	           	Assert.assertEquals(dropdown.getOptions().get(i).getText(), expected2[i]);

	       	}

	       }
       	Assert.assertEquals(count,expected2.length);
  	}
	public void VerifyaddedEntry(String objID, String Name) {
		 WebElement table = driver.findElement(By.xpath(objID));
	        for (WebElement rowElmt : table.findElements(By.tagName("tr")))
	        {
	        	
	        	List<WebElement> columns = rowElmt.findElements(By.tagName("td"));
	           	for(WebElement cols:columns){
	           		if(cols.getText().contains(Name)){
	            		b=cols.getText().contains(Name);
	            		Assert.assertTrue(b);
	           		}
	           	}

	         }
	        Assert.assertTrue(b);
	}
	public void VerifyDeleteEntry(String objID, String Name) {
		boolean match=false;
		 WebElement table = driver.findElement(By.xpath(objID));
	        for (WebElement rowElmt : table.findElements(By.tagName("tr")))
	        {
	        	
	        	List<WebElement> columns = rowElmt.findElements(By.tagName("td"));
	           	for(WebElement cols:columns){
	           		if(cols.getText().contains(Name)){
	           			match=true;
	 		    	   Assert.assertFalse(cols.getText().contains(Name));

	           		}
	           		else{
	           				Assert.assertFalse(cols.getText().contains(Name));
	           		}

		
	           	}
	                     }
	       
	}
	
	public void Verify_Search_column(String Columnvalue) throws Exception
	{
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='table_1']"));
		int numberofrows = rows.size();
		int flag=0;
			for(int i=3;i<=numberofrows+2;i++)
			{
				String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					Assert.assertTrue(b);
					flag=1;
					break;
				}
	   
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
		
	}

	
	public void Verify_Added_ThirdFourth_Columns(String Third_Column, String Fifth_Coulmn) {
		WebElement tableType=driver.findElement(By.xpath("//*[@id='table_1_core_table_content']/tbody/tr"));
	    List<WebElement>rowElmt=tableType.findElements(By.xpath("//tr/td[3]"));
	    String getData;
    
	    //Store Values in  two list displayedNames and SortedNames
	    for(int i=2;i<rowElmt.size();i++)
	    {

	        getData=rowElmt.get(i).getText();
	        if(getData.contains(Third_Column))
	        {
	        	b=getData.contains(Third_Column);
	        	Assert.assertTrue(b);
	        //System.out.println(getData);
	    	String Fifth_Actual=tableType.findElement(By.xpath("//tr/td[5]")).getText();
			c=Fifth_Actual.contains(Fifth_Coulmn);
	    		if(c){
		        	Assert.assertTrue(c);

	    		}
	      
	        }
	        
	    }
		Assert.assertTrue(b);
    	Assert.assertTrue(c);

	}
	
	// New Functions
	
public void Verify_Add_Fifthcolumn(String Columnvalue) throws Exception
    {
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Loginnamefilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	//new Select(driver.findElement(By.xpath(locator.getProperty("Pagesizelist")))).selectByValue("ALL");
	  Thread.sleep(1000);
		List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
		
		int numberofrows = rows.size();
		int flag=0;
			for(int i=3;i<=numberofrows+2;i++)
			{
				String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
				boolean b= str1.equalsIgnoreCase(Columnvalue);
				if(b)
				{
					Assert.assertTrue(b);
					flag=1;
					break;
				}
       
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
		
	}


public void Verify_Add_Fifthcolumnwithoutfilter(String Columnvalue) throws Exception
{
   new Select(driver.findElement(By.xpath(locator.getProperty("Pagesizelist")))).selectByValue("All");
   //Thread.sleep(4000);
   String str=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr[2]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertFalse(true);
	
	}
	else
	{
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	}
}
public void Verify_Add_Secondcolumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	WebDriverWait wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Secondcolfilter"))));
	driver.findElement(By.xpath(locator.getProperty("Secondcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}


public void Verify_Add_Thirdcolumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	WebDriverWait wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Thirdcolfilter"))));
	driver.findElement(By.xpath(locator.getProperty("Thirdcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
			boolean b= Columnvalue.contains(str1);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}


public void Verify_Add_Forthcolumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	WebDriverWait wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Fourthcolfilter"))));
	driver.findElement(By.xpath(locator.getProperty("Fourthcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[4]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}




public void SelectElementByLoginname(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath(locator.getProperty("Loginnamefilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	// Thread.sleep(4000);
	 List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	 Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=3;i<=numberofrows+2;i++)
			{
				String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
				boolean b= str1.equalsIgnoreCase(Columnvalue);
				if(b)
				{
					driver.findElement(By.xpath(".//*[@id='table_1:"+(i-3)+"']")).click();
					flag=1;
					break;
				}
    
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
}

public void SelectElementByLoginnamewithoutfilter(String Columnvalue) throws Exception
{
	 List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	// Thread.sleep(2000);
		int numberofrows = rows.size();
		int flag=0;
			for(int i=2;i<=numberofrows+1;i++)
			{
				String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
				boolean b= str1.equals(Columnvalue);
				if(b)
				{
					driver.findElement(By.xpath(".//*[@id='table_1:"+(i-2)+"']")).click();
					flag=1;
					break;
				}
    
			}
			if(flag==0)
			{
				Assert.assertTrue(b);
			}
	
}





//Need to be Complete
public void Verify_Add_SecondcolumnInventory(String Columnvalue) throws Exception
{
	new Select(driver.findElement(By.xpath(locator.getProperty("Pagesizelist2")))).selectByValue("All");
	Thread.sleep(4000);
    List<WebElement> rows =driver.findElements(By.name(locator.getProperty("tablebyname")));
    //Thread.sleep(2000);
    int numberofrows = rows.size();
    int flag=0;

		 	for(int i=2;i<=numberofrows+1;i++)
		 	{
		 		String str1=driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
		 		boolean b= str1.equals(Columnvalue);
		 		if(b)
		 		{
		 			Assert.assertTrue(b);
		 			flag=1;
		 			break;
		 		}	
	       
		 	}
	    
	  if(flag==0)
	    {
	    	Assert.assertTrue(b);
	    }  
  }



public void Verify_Third_Fifth_Column(String ThirdColumnvalue,String FifthColumnvalue) throws Exception
{
	//new Select(driver.findElement(By.id("table_2_pageSizeSelect"))).selectByValue("ALL");
	//new Select(driver.findElement(By.xpath(locator.getProperty("Pagesizelist")))).selectByValue("ALL");
	//Thread.sleep(4000);
    List<WebElement> rows =driver.findElements(By.name(locator.getProperty("tablebyname")));
    Thread.sleep(2000);
    int numberofrows = rows.size();
    int flag=0;

		 	for(int i=2;i<=numberofrows+1;i++)
		 	{
		 		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
		 		boolean b= str1.contains(ThirdColumnvalue);
		 		String str2=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
		 		boolean b1= str2.equals(FifthColumnvalue);
		 		if(b)
		 		{
		 			
		 			Assert.assertTrue(b1);
		 			flag=1;
		 			break;
		 		}	
	       
		 	}
	    
	  if(flag==0)
	    {
	    	Assert.assertTrue(b);
	    }  

 }


public void Verify_Delete_Fifthcolumn(String Columnvalue) throws Exception
{
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(1000);
	driver.findElement(By.xpath(".//*[@id='user_display_name_filter']")).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
  Thread.sleep(1000);
   String str=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr[3]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertTrue(true);
	
	}
	else
	{
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	System.out.println(numberofrows);
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[4]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertFalse(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertFalse(b);
		}
	}
}


public void Verify_Delete_Forthcolumn(String Columnvalue) throws Exception
{
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Fourthcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
  Thread.sleep(1000);
   String str=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr[3]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertTrue(true);
	
	}
	else
	{
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[4]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertFalse(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertFalse(b);
		}
	}
}

public void Verify_Delete_Thirdcolumn(String Columnvalue) throws Exception
{
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Thirdcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
  Thread.sleep(1000);
   String str=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr[3]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertTrue(true);
	
	}
	else
	{
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[3]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertFalse(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertFalse(b);
		}
	}
}

public void Verify_Delete_Secondcolumn(String Columnvalue) throws Exception
{
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Secondcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
  Thread.sleep(1000);
   String str=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr[3]/td[2]")).getText();
	if(str.equals("No Records found"))
	{
		Assert.assertTrue(true);
	
	}
	else
	{
	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
	for(int i=3;i<=numberofrows+2;i++)
	{
		String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertFalse(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertFalse(b);
		}
	}	
	
 }



public void SelectElement(String locator, String ElementName) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows =driver.findElements(By.xpath(locator));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = driver.findElement(By.xpath(locator+"["+i+"]/a")).getText();
    	if(sValue.contains(ElementName)){
   			WebElement sRowValue=driver.findElement(By.xpath(locator+"["+i+"]/a"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}

}

public void SelectChkboxSecColmn(String locator, String ElementName,String Checkbox) throws InterruptedException {
	// TODO Auto-generated method stub
	List<WebElement> totalRows = driver.findElements(By.xpath(locator));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= driver.findElement(By.xpath(locator+"["+i+"]"+"/td[2]")).getText();
		if(ElementName.contains(sValue)){
			WebElement sRowValue= driver.findElement(By.xpath(Checkbox+(i-2)+"']"));
			String s=input.getProperty("MAX_TIME");
		    int t=Integer.parseInt(s);
			WebDriverWait wait = new WebDriverWait(driver,t);
		    WebElement ClkBtn=wait.until(ExpectedConditions.elementToBeClickable(sRowValue));
		    ClkBtn.click();
 			break;
		}
	}

}

public void waitForTextBeVisible(WebElement element) throws Exception{

    //WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));
	String text=element.getText();

//new WebDriverWait(driver,30).until(ExpectedConditions.textToBePresentInElement(element,text));

new FluentWait<WebElement>(element).
withTimeout(40, TimeUnit.SECONDS).
pollingEvery(100,TimeUnit.MILLISECONDS).
until(new Function<WebElement  , Boolean>() {
   @Override
   public Boolean apply(WebElement element) {
       return element.getText().contains("SUCCESSFULL");
   }
}
);
}

public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	} 



//Filter Functions
public void SearchSecondColumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath(locator.getProperty("Secondcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	List<WebElement> totalRows = driver.findElements(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr"));
	int numberofrows = totalRows.size();
	
	System.out.println(numberofrows);
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=driver.findElement(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div[1]/div[2]/div/table/tbody/tr/td/table/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}


public void SearchThirdColumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath(locator.getProperty("Thirdcolfilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	
	List<WebElement> totalRows = driver.findElements(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr"));
	int numberofrows = totalRows.size();
	System.out.println(numberofrows);
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=driver.findElement(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr["+i+"]/td[3]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}

public void SearchFifthColumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	driver.findElement(By.xpath(locator.getProperty("Filter"))).click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath(locator.getProperty("Loginnamefilter"))).sendKeys(Columnvalue);
	driver.findElement(By.xpath(locator.getProperty("Filter.Apply"))).click();
	Thread.sleep(1000);
	List<WebElement> totalRows = driver.findElements(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr"));
	int numberofrows = totalRows.size();
	
	System.out.println(numberofrows);
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=driver.findElement(By.xpath("html/body/div[5]/div/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr["+i+"]/td[5]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
}

public void alert(boolean accept) throws Exception {
	// TODO Auto-generated method stub
	if(accept){
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}

}


}



