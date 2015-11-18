package com.avaya.sdmclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.extraResources.MyException;


@SuppressWarnings({"unchecked","unused"})

public class Settings {
	
	//Setup properties file to take input IDs from it..
	Properties locator = null;
	public void setup() throws IOException, InterruptedException
	{
		locator = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	// Go to SDM Client URL on browser (pass driver instance as parameter)
	public void goToSDMCliURL(WebDriver driver) throws IOException, InterruptedException{
		setup();
		driver.manage().window().maximize();
		driver.get(locator.getProperty("SDMCliURL"));
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		driver.manage().timeouts().implicitlyWait(11500, TimeUnit.MILLISECONDS);
	}
	
	//Choose a firefox profile for Tests (Give name of profile as input parameter)
	public FirefoxProfile selectProfile(String profileName){
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profil = allProfiles.getProfile(profileName);
		boolean acceptUntrustedSsl = true;
		profil.setAcceptUntrustedCertificates(acceptUntrustedSsl);
		logClass.confFile();
		return profil;
	}
	
	//Take a screenshot with timestamp in it's name if error is there (pass driver instance as parameter)
	public void takeScreenShotForDriver(WebDriver driver) throws IOException{
		String workingDirectory = System.getProperty("user.dir"); 
	    File imageFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		GregorianCalendar gcalendar = new GregorianCalendar();
		String failureImageFileName=workingDirectory+ File.separator+"Screenshots"+File.separator+"Screenshots"+ dateFormat.format(new java.util.Date())+File.separator +gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+"_"+gcalendar.getTimeInMillis()+".png";
		File failureImageFile=new File(failureImageFileName);
		FileUtils.moveFile(imageFile, failureImageFile);
		logClass.error("Something went wrong :(");
		logClass.info("Check Screenshot for the same");
	}

	//Read inputs from file (Firstly it was only from text file,then added code to take input from properties file)
	//pass File name and key as parameter
	public String readFromFile(String fileName,String findProperty) throws IOException{
		File file = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\"+fileName);
		
		//Older Version to read from file
		
		/*List<String> lines = FileUtils.readLines(file);
		Scanner sc;
		String output = null;
		for(String s : lines)
		{
			sc = new Scanner(s);
			while(sc.hasNext())
				if(sc.next().equals(find))
					if(sc.hasNext())
						output = sc.next().trim();
		}*/
		
		Properties pr = new Properties();
		pr.load(new FileReader(file));
		
		String output = pr.getProperty(findProperty);
		System.out.println("Read from file "+fileName +" for "+findProperty+": "+output);
		return output;
	}
	
	//Choose OVF file which is to be parsed during installation of VM
	
	/*public String matchFileOVF(String input){
		File folder = new File(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
		String returnStr = "";
		for(File f : folder.listFiles()){
			System.out.println(f.getName());
			if(f.getName().substring(0,input.length()).equals(input))
				returnStr = f.getName();
		}
		System.out.println("Choosen OVF: "+returnStr);
		return returnStr;
	}*/

	//Wait Explicitly for given element for it's presence
	//pass driver instance and By instance of particular element as parameter
	public void waitForPresenceOfElement(WebDriver driver,By locate){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locate));
	}
	
	//Choose tab to choose options like Location/host/VM
	public void chooseTab(WebDriver driver,String selectTab){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script1 = "var nl = document.querySelectorAll('[id^=\"tabbar\"]'); return nl;";
		ArrayList<WebElement> elem1 = (ArrayList<WebElement>) js.executeScript(script1);
		
		/*for(WebElement e : elem1)
			System.out.println(e.getAttribute("id"));*/
		
		String script2 = "var nl = document.getElementById(\""+elem1.get(0).getAttribute("id")+"\").querySelectorAll('[id^=\"tab\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		for(WebElement e : elem2){
			if(e.getText().equals(selectTab))
			{
				//System.out.println("Selected tab is: "+e.getText());
				e.click();
			}
		}
	}
	
	//Select tab to choose Network Parameters
	public void selNetworkTabForVMinstallation(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		/*String script1 = "var nl = document.querySelectorAll('[id^=\"tabbar\"]'); return nl;";
		ArrayList<WebElement> elem1 = (ArrayList<WebElement>) js.executeScript(script1);*/
		
		/*for(WebElement e : elem1)
			System.out.println(e.getAttribute("id"));*/
		
		String script2 = "var nl = document.querySelectorAll('[id^=\"tab-\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		/*for(WebElement e : elem2)
			System.out.println(e.getAttribute("id"));*/
		for(WebElement e : elem2){
			if(e.getText().contains("Network Parameters"))
			{
				//System.out.println("Selected tab is: "+e.getText());
				e.click();
			}
		}
	}
	
	//Click on deploy button for VM
	public void deployButtonClickForVM(WebDriver driver) throws MyException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		float opacity = 0;
		String script2 = "var nl = document.getElementById(\"frmVMdeployment\").querySelectorAll('[id^=\"panel-\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		System.out.println(elem2.size());
		
		String scr = "var nl = document.getElementById(\""+elem2.get(elem2.size()-1).getAttribute("id").replace("-targetEl", "")+"\").querySelectorAll('[id^=\"button-\"]'); return nl;";
		ArrayList<WebElement> ele = (ArrayList<WebElement>) js.executeScript(scr);
		for(WebElement ee : ele)
			{
				if(ee.getText().equals("Deploy"))
				{	//System.out.println(ee.getAttribute("id"));
					//System.out.println(ee.getText());
					opacity = Float.parseFloat(driver.findElement(By.id(ee.getAttribute("id"))).getCssValue("opacity"));
					System.out.println(opacity);
					if(opacity==1)
					{
						ee.click();
						break;
					}
					else{
						throw new MyException("All mandatory fields are not filled properly, please fill it properly.\n Exiting..");
					}
				}
			}
	}
	
	//To check whether location or host is available or not (So that we can add if they are not there)
	//pass driver instance and string which is to be find as parameter
	public boolean checkPresenceOfLocationOrHostOrVM(WebDriver driver, String input) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocOrHostGrid")));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Row"))));
		List<String> tm = new ArrayList<>();
		boolean b = true;
		System.out.println("Entries on Left Tree: "+cells.size());

		for(WebElement e : cells)
		{
			//System.out.println(e.getText());
			tm.add(e.getText());
			/*if(e.isDisplayed())
					if(e.getText().trim().equals(_input))
						System.out.println(e.getAttribute("id"));*/
		}

		if(!tm.contains(input)){
			System.out.println("Couldn't Find Location or host in Tree: "+input);
			b=false;
		}

		/*for(String s : tm){
				System.out.println(s);
			}*/
		return b;
	}
		
	//Find location or host by name
	//pass driver instance and string which is to be find as parameter
	public void findLocationOrHost(WebDriver driver, String input) throws IOException, InterruptedException, MyException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocOrHostGrid")));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Row"))));
		//System.out.println(cells.size()+"\n\n");
		
		if(checkPresenceOfLocationOrHostOrVM(driver, input) && input !=null)
		{
			for(WebElement e : cells)
			{
				if(e.getText().trim().equals(input))
					e.click();
			}
		}
		
		else{
			System.out.println("Please check whether location name is null or location is present or not.");
			logClass.error("Error occurred: Either location name or host name is null or location or host does not exist ..");
			throw new MyException("Error occurred: Either location name or host name is null or location or host does not exist ..");
		}
	}

	
	//Find Location (right side) in grid (table)
	//pass driver instance and location name which is to be find as parameter
	public void findLocationInGrid(WebDriver driver, String locationName) throws IOException, InterruptedException, MyException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocationInGrid")));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Row"))));
		//System.out.println(cells.size()+"\n\n");

		if(checkPresenceOfLocationOrHostOrVM(driver, locationName) && locationName !=null)
		{
			for(WebElement e : cells)
			{
				//System.out.println(e.getText()+" :"+locationName);
				if(e.getText().trim().contains(locationName))
				{
					e.click();
					System.out.println("Clicked on "+locationName);
				}
			}
		}
		else{
			System.out.println("Please check whether location name is null or location is present or not.");
			logClass.error("Error occurred: Either location name is null or location does not exist ..");
			throw new MyException("Error occurred: Either location name is null or location does not exist ..");
		}
	}

	//Find host (right side) in grid (table)
	//pass driver instance and location name which is to be find as parameter
	public void findHostInGrid(WebDriver driver, String hostName) throws IOException, InterruptedException, MyException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("HostInGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		//System.out.println(cells.size()+"\n\n");
		if(checkPresenceOfLocationOrHostOrVM(driver, hostName) && hostName !=null)
		{
			for(WebElement e : cells)
			{	
				if(e.getText().trim().equals(hostName))
					e.click();
			}
		}
		else{
			System.out.println("Please check whether host name is null or host is present or not.");
			logClass.error("Error occurred: Either host name is null or host does not exist ..");
			throw new MyException("Error occurred: Either host name is null or host does not exist ..");
		}
	}

	//Find VM (right side) for particular host in grid (table)
	//pass driver instance and VM name which is to be find as parameter
	public void findVMForHost(WebDriver driver, String vmName) throws IOException, InterruptedException, MyException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Column"))));

		if(checkPresenceOfLocationOrHostOrVM(driver, vmName) && vmName !=null)
		{
			for(WebElement e : cells)
			{	//System.out.println(e.getText());
				if(e.getText().trim().equals(vmName))
					e.click();
			}
		}
		else{
			System.out.println("Please check whether VM name is null or VM is present or not.");
			logClass.error("Error occurred: Either VM name is null or VM does not exist ..");
			throw new MyException("Error occurred: Either VM name is null or VM does not exist ..");
		}
	}

	//Find vCenter (right side) in grid (table)
	//pass driver instance and VCenter name which is to be find as parameter
	public void findvCenterInGrid(WebDriver driver, String VCenter) throws IOException, InterruptedException, MyException{
		setup();
		WebElement temp = driver.findElement(By.id(locator.getProperty("VCenterInGrid")));
		List<WebElement> tempcells = temp.findElements(By.xpath((locator.getProperty("Column"))));

		if(VCenter !=null)
		{
			for(WebElement e : tempcells)
			{
				if(e.getText().trim().equals(VCenter))
					e.click();
			}
		}
		else{
			System.out.println("Please check whether VCenter is null or VCenter is present or not.");
			logClass.error("Error occurred: Either VCenter is null or VCenter does not exist ..");
			throw new MyException("Error occurred: Either VCenter is null or VCenter does not exist ..");
		}
	}

	//Select element from dropdown
	//pass driver instance,value and id for that boundlist which is to be find as parameter
	public void boundListSelect(WebDriver driver,String toBeSelected,String idForBoundlist) throws IOException, InterruptedException{
		setup();
		WebElement element = driver.findElement(By.id((idForBoundlist)));
		List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		for (WebElement e : tmp1 )
		{
			//System.out.println(e.getAttribute("id"));
			//System.out.println(e.getText()+ " Test "+e.getAttribute("id"));
			if(e.getText().contains(toBeSelected))
			{
				//System.out.println("Selected : "+e.getText());
				e.click();
				System.out.println("Clicked on: "+e.getText()+e.getAttribute("id"));
			}
		}
	}

	//Check resources and log them if host resource verification fails
	//pass driver instance as parameter
	public void checkFailureOfHostCapacity(WebDriver driver) throws IOException, InterruptedException{
		setup();
		WebElement temp = driver.findElement(By.id(locator.getProperty("HostCapacity")));
		List<WebElement> tempcells = temp.findElements(By.xpath((".//*[local-name(.)='td']/div/span")));
		List<WebElement> tempcellsRow = temp.findElements(By.xpath((".//*[local-name(.)='tr']")));
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
			System.out.println("Reason For Failure : "+tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
			logClass.error("Reason For Failure : "+tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
		}
	}

	//Split IP address string with '.' 
	//pass driver instance IP and number of the part that is needed to be retrieved as parameter
	public String IPConvert(WebDriver driver,String IP,int partIndex){
		String[] parts = IP.split("\\.");
		/*for(int i=0;i<4;i++)
			System.out.println(parts[i]);*/
		return parts[partIndex];
	}

	//Remove alphabetical characters from string
	//pass String as parameter which is to be processed
	public String removeAlpha(String str){
		StringBuilder sc = new StringBuilder();
		for (int i=0;i<str.length();i++)
			if (!Character.isDigit(str.charAt(i)))
				continue;
			else
				sc.append(str.charAt(i));
		return sc.toString();
	}

	//Make fields (IDs) for IP where it is to be filled
	public List<String> makeFields(String input){
		List<String> fields = new ArrayList<String>();
		int it = Integer.parseInt(removeAlpha(input));
		for(int i=0;i<4;i++)
			fields.add(".//*[@id='textfield-"+(it+(i*2))+"-inputEl']");
		return fields;
	}

	public void fillIPInBoxes(WebDriver driver,String IP,String startAddress){
		List<String> addresses = makeFields(startAddress);
		for(int i=0;i<4;i++)
		{
			driver.findElement(By.xpath((addresses.get(i)))).clear();
			driver.findElement(By.xpath((addresses.get(i)))).sendKeys(IPConvert(driver, IP, i));
		}
	}

	public boolean checkError(WebDriver driver){
		boolean b = false;
		try{
			if(driver.findElement(By.id(locator.getProperty("DialogueBox"))).isDisplayed())
					b = true;
		}
		catch(Exception ex){
			b=false;
		}
		return b;
	}

	public String errorBox(WebDriver driver,boolean check) throws IOException, MyException{
		String errMsg = "";
		if(check)
		{
			//try{
				if(driver.findElement(By.id(locator.getProperty("DialogueBox"))).isDisplayed())
				{
					driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText();
					logClass.error(driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
					logClass.error("Error : Check the Log and Screenshot for the same.");
					System.out.println("Error Message : "+driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
					errMsg = driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText()+"\nCheck Screenshot for the same.\n";
					takeScreenShotForDriver(driver);
					logClass.info("Screenshot taken");

					driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
					System.out.println("Confirmed");
					throw new MyException(errMsg);
				}
//			}
//			catch(Exception ex){
//				System.out.println("No Errors Uptill Now.");
//			}
		}
		return errMsg;
	}

	//ID for boundlist (rendered last in DOM)
	public String selBoundList(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\"boundlist\"]');return nl");
		// Select the Last Boundlist which is rendered in DOM as last
		//System.out.println(a.get(a.size()-1).getAttribute("id"));
		return a.get(a.size()-1).getAttribute("id");
	}

	public void exec(boolean in) {
		if(in == true) {
			return;
		}
		System.out.println("Can not Execute Further. Check the Log for the same.");
		logClass.error("Check Log and Screenshot for the same.");
		
		//System.exit(0);
	}

	public void confirmDialogBox(WebDriver driver) throws IOException{
		driver.switchTo().activeElement();

		try{
			if(driver.findElement(By.id(locator.getProperty("DialogueBox"))).isDisplayed())
				{
					logClass.info("Action being performed: "+driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
					//System.out.println("Action being performed: "+driver.findElement(By.xpath(locator.getProperty(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
				}
		}
		catch(Exception ex){
			takeScreenShotForDriver(driver);
			System.out.println("Couldn't find any text");
		}

		try{
			driver.findElement(By.xpath(locator.getProperty("ConfButton1"))).click();
		}
		
		catch(Exception ex){
			System.out.println("\n");
		}
		try{
			driver.switchTo().activeElement();
			Thread.sleep(450);
			//System.out.println(driver.switchTo().activeElement().getText());
			logClass.info("Confirmation: "+driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
			driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
			//System.out.println("Confirmed");
		}
		catch(Exception ex){
			System.out.println("Confirmed");
		}
	}

	public String findTextInBetweenTags(String tag,String FilePath) throws IOException{
		String check = "";
		String ans = "";
		File file = new File(FilePath);
		final Pattern pattern = Pattern.compile("<"+tag+">(.+?)</"+tag+">");
		List<String> _Lines = FileUtils.readLines(file);
		for(String l: _Lines)
			if(pattern.matcher(l).find())
				check = l;

		final Matcher matcher = pattern.matcher(check);
		while(matcher.find())
		{
			System.out.println(matcher.group(1).toString());
			ans = matcher.group(1).toString();
		}
		return ans;
	}

	public void comboClick(WebDriver driver, String selCombo,String input) throws IOException, InterruptedException{
		String cmbid = comboID(driver, selCombo);
		
		driver.findElement(By.id(cmbid)).click();
		driver.findElement(By.id(cmbid)).clear();
		driver.findElement(By.id(cmbid)).sendKeys(input);
		Thread.sleep(2000);
		
		setup();
		WebElement element = driver.findElement(By.id((selBoundList(driver))));
		List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		for (WebElement e : tmp1 )
		{
			//System.out.println(e.getText()+ "\n Test \n");
			if(e.getText().equals(input))
			{
				System.out.println("Selected value for: "+selCombo+" "+input+" "+e.getText());
				e.click();
			}
		}
		//boundListSelect(driver, input, selBoundList(driver));
	}
	
	public String comboID(WebDriver driver,String select){
		String returnID = "";
		String sc1 = "var nl = document.getElementById(\"VMdepfrmDynPart-body\").querySelectorAll('[id^=\"combobox\"]'); return nl;";
		
		JavascriptExecutor exec = (JavascriptExecutor) driver;
		List<WebElement> allPanels = (List<WebElement>) exec.executeScript(sc1);
		System.out.println(allPanels.size());
		
		for(WebElement e : allPanels){
			//System.out.println(e.getAttribute("id"));
			if(e.getAttribute("id").contains("labelEl") && e.getText().equals(select)){
				//System.out.println(e.getText());
				//System.out.println(e.getAttribute("id"));
				returnID = e.getAttribute("id").replace("labelEl", "inputEl");
				System.out.println("ID for selected ComboBox '"+select+"' is: "+returnID);
			}
		}
		return returnID;
	}

	@SuppressWarnings("deprecation")
	public String fluentWait(final By locator,WebDriver driver,int time,String Test) throws InterruptedException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		//Thread.sleep(45000);
		//_closeWindow(driver);
		wait.until(ExpectedConditions.textToBePresentInElement(locator, Test));
		logClass.info(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
		return driver.findElement(locator).getText();
	}


	public String fluentWaitCloseOpen(final By locatorTo,WebDriver driver,int time,String Test,String VMName,String vmOrHost,String linkText) throws InterruptedException, IOException, MyException {
		Thread.sleep(45000);
		closeWindow(driver);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		/*
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Status Details")));
		Thread.sleep(1000);
		driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		*/
		chooseLink(driver, VMName,vmOrHost,linkText);
		logClass.info("Checking Status Details");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());
		return fluentWait(locatorTo, driver, time, Test);
	}
	
	public void chooseLink(WebDriver driver,String Name,String vmOrHost,String linkText) throws IOException, InterruptedException, MyException{
		setup();
		String id = "";
		if(vmOrHost.equals("VM")){
			id = locator.getProperty("VMGrid");
		}
		else if(vmOrHost.equals("Host")){
			id = locator.getProperty("HostInGrid");
		}
		else{
			throw new MyException("Please verify input: Only allowed inputs are 'VM' or 'Host'");
		}
		
		Thread.sleep(3500);
		WebElement table = driver.findElement(By.id(id));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Row"))));
		String rowID = "";
		//System.out.println(cells.size()+"\n\n");
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		
		for(WebElement e : cells)
		{	
			if(e.getText().contains(Name))
				{
					rowID = e.getAttribute("id");
					System.out.println(rowID);
				}
		}
		driver.findElement(By.id(rowID)).findElement(By.linkText(linkText)).click();
		//el.findElement(By.className("deployinprogress")).click();
	}

	public void maintainedList(WebDriver driver,String ID) throws IOException, InterruptedException{
		driver.findElement(By.id(ID)).click();
		driver.findElement(By.id(ID)).sendKeys("random");
		
		File f = new File(System.getProperty("user.dir")+"\\Third Party\\Input Files\\"+"ovanames.txt");
		
		List<String> availableOVAs = new ArrayList<>();
		setup();
		//System.out.println(selBoundList(driver));
		Thread.sleep(2000);
		//System.out.println(selBoundList(driver));
		WebElement element = driver.findElement(By.id(selBoundList(driver)));
		//System.out.println("First "+element.getText());
		List<WebElement> a = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		//System.out.println("Size "+a.size());
		
		if(!availableOVAs.isEmpty())
			{	
			for(int i=0;i<availableOVAs.size();i++)
				availableOVAs.remove(i);
			}
		//System.out.println("Size "+availableOVAs.size());		
		
		for(WebElement e : a){
			availableOVAs.add(e.getText());
			//System.out.println("Text "+e.getText());
		}
		
		PrintWriter pr = new PrintWriter(f);
		
		for(String s : availableOVAs)
			{
				pr.write(s+"\n");
				System.out.println("Written "+s+" Successfully in File.");
			}
		
		pr.close();
		
	}
	
	public void StatusCheck(WebDriver driver,String toBeChecked,int time) throws IOException, InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		driver.switchTo().activeElement();
		System.out.println(fluentWait(By.id(locator.getProperty("vmDeployStatus")), driver, time, toBeChecked));

		if(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText().contains(toBeChecked))
			{
				closeWindow(driver);
				System.out.println("Completed Successfully");
			}

		else if(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText().contains("failed"))
			{
				System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());
				logClass.error(driver.findElement(By.id(locator.getProperty("vmDeployStatus "))).getText());
				takeScreenShotForDriver(driver);
				closeWindow(driver);
			}

	}

	public void closeWindow(WebDriver driver){
		List<WebElement> elem = driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).findElement(By.id(("vmDeployStatus_header"))).findElement(By.id(("vmDeployStatus_header-innerCt"))).findElement(By.id(("vmDeployStatus_header-targetEl"))).findElements(By.tagName("div"));
		//System.out.println(_elem.size());

		for(WebElement e : elem)
		{
			//System.out.println(e.getAttribute("class"));
			if(e.getAttribute("class").contains("x-tool"))
				e.click();
		}
	}
	
	public List<String> getViewFrame(WebDriver driver,String input){
		List<String> types = new ArrayList<>();
		List<String> ret = new ArrayList<>();
		String returnStr = "";
		String refStrForRefreshItems = "";
		
		types.add("newlocationview");
		types.add("editlocationview");
		types.add("host_new");
		types.add("host_edit");
		types.add("panelConfigParameters");
		types.add("networkParamEditForm");
		types.add("host_passwordChange");
		
		if(input.equals("AddLocation") || input.equals("AddLocation1"))
			{
				returnStr = types.get(0);
				refStrForRefreshItems = "locationgridpanel";
			}
		else if(input.equals("EditLocation"))
			{
				returnStr = types.get(1);
				refStrForRefreshItems = "locationgridpanel";
			}
		else if(input.equals("AddHost") || input.equals("AddHost1"))
			{
				returnStr = types.get(2);
				refStrForRefreshItems = "host_view";
			}
		else if(input.equals("EditHost"))
			{
				returnStr = types.get(3);
				refStrForRefreshItems = "host_view";
			}//
		else if(input.equals("AddVM"))
		{
			returnStr = types.get(4);
			refStrForRefreshItems = "";
		}
		else if(input.equals("ChangeNetWorkParams"))
		{
			returnStr = types.get(5);
			refStrForRefreshItems = "";
		}
		else if(input.equals("UpdatePwdHost"))
		{
			returnStr = types.get(6);
			refStrForRefreshItems = "";
		}
		ret.add(returnStr);
		ret.add(refStrForRefreshItems);
		
		for(String s : ret)
			System.out.println("Choosen values: "+s);
		
		return ret;
	}
	
	public void findIDandFillValues(WebDriver driver,String filename,String input) throws IOException, InterruptedException{
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String sc = "var nl = document.getElementById(\"" + getViewFrame(driver, input).get(0) + "\").getElementsByTagName(\"input\"); return nl;";
		//String sc = "var nl = document.getElementById(\""+getViewFrame(driver, input)+"\").querySelectorAll('[id^=\"text\"]'); return nl;";
		String returnID = "";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc);
		//System.out.println("No of fields in OVF: "+elem.size());
		
		if(input.equals("EditHost")){
			selectLocforEditHost(driver);
			//driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();
			Thread.sleep(250);
			boundListSelect(driver, readFromFile(filename, "EditHostLocation:"), selBoundList(driver));
		}
		
		else if(input.equals("AddLocation") || input.equals("EditLocation") || input.equals("AddLocation1")){
			
			String sc1 = "var nl = document.getElementById(\""+ getViewFrame(driver, input).get(0) + "\").querySelectorAll(\"textarea\"); return nl;";
			ArrayList<WebElement> elems = (ArrayList<WebElement>) js.executeScript(sc1);
			WebElement ee = driver.findElement(By.id(elems.get(0).getAttribute("id").replace("inputEl", "labelEl")));
			
			//System.out.println("Main: "+ee.getText());
			returnID = elems.get(0).getAttribute("id");
			//System.out.println(returnID);
			
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(readFromFile(filename, input+ee.getText().replace("*", "").replace(" ", "")));
		}
		
		for(WebElement e : elem){
			//System.out.println("Main: "+driver.findElement(By.id(e.getAttribute("id").replace("input", "label"))).getText());
			if(e.getAttribute("readonly")==null){
			//System.out.println("Attribb"+e.getAttribute("readonly"));
			WebElement el = driver.findElement(By.id(e.getAttribute("id").replace("input", "label")));
			//System.out.println(el.getText());
			returnID = e.getAttribute("id");
			//System.out.println(returnID);
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(readFromFile(filename, input+el.getText().replace("*", "").replace(" ", "")));
			}
		}
		
		//System.out.println("After");
	}
	
	public void refreshItems(WebDriver driver,String methodBy){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script1 = "var nl = document.getElementById(\"" + getViewFrame(driver, methodBy).get(1) + "\").querySelectorAll('[id^=\"pagingtoolbar\"]'); return nl;";
		ArrayList<WebElement> elem1 = (ArrayList<WebElement>) js.executeScript(script1);
		
		/*for(WebElement e : elem1)
			System.out.println(e.getAttribute("id"));*/
		
		/*System.out.println("Before");
		System.out.println(elem1.size());*/
		
		String script2 = "var nl = document.getElementById(\""+elem1.get(0).getAttribute("id")+"\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		/*System.out.println("Before");
		System.out.println(elem2.size());*/
		
		List<String> buttons = new ArrayList<>();
		for(WebElement e : elem2){
			if(e.getAttribute("id").contains("btnEl"))
			{
				buttons.add(e.getAttribute("id"));
				//System.out.println("Buttons: "+e.getAttribute("id"));
			}
		}
		
		waitForPresenceOfElement(driver, By.id(buttons.get(buttons.size()-1)));
		driver.findElement(By.id(buttons.get(buttons.size()-1))).click();
		System.out.println("Refreshed page successfully.");
			
	}

		public void FillValues(String fileName,String filePath,WebDriver driver,String IP,String hostName) throws IOException, ParserConfigurationException, SAXException, InterruptedException{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File(filePath));
			
			NodeList nl =document.getElementsByTagName("Property");
			NodeList nlLabel = document.getElementsByTagName("Label");
			NodeList nlLabelNet = document.getElementsByTagName("Network");
			
			List<NamedNodeMap> nlmapnet = new ArrayList<>();
			List<String> Labels = new ArrayList<String>();
			List<NamedNodeMap> nlmap = new ArrayList<>();
			//System.out.println(nl.getLength());

			for(int j=0;j<nlLabel.getLength();j++)
				if(nlLabel.item(j).getParentNode().getNodeName().equals("Property"))
					Labels.add(nlLabel.item(j).getTextContent());

			for(int i=0;i<nl.getLength();i++)
				nlmap.add(nl.item(i).getAttributes());
			
			String op[][] = new String[nl.getLength()-1][2];
			//System.out.println(Labels.size());
			
			/*for(int i=0;i<nl.getLength();i++)
			System.out.println(nl.item(i).getAttributes());*/
			
			String opNet[] = new String[nlLabelNet.getLength()];
			
			for(int i=0;i<nlLabelNet.getLength();i++)
				nlmapnet.add(nlLabelNet.item(i).getAttributes());

			for (int i = 0; i < nlLabelNet.getLength(); i++)
			{
				//System.out.println(_Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<nlmapnet.get(i).getLength();j++)
				{
					//System.out.println(_nlmap.get(i).toString());
					Node attr = nlmapnet.get(i).item(j);
					opNet[i]=attr.getNodeValue();
					//System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
				}
			}
			for (int i = 0; i < nl.getLength()-1; i++)
			{
				//System.out.println(Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<nlmap.get(i).getLength();j++)
				{
					//System.out.println(_nlmap.get(i).toString());
					Node attr = nlmap.get(i).item(j);
					//System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
				}
			}

			for (int i = 0; i < nl.getLength()-1; i++)
			{
				//System.out.println(Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<nlmap.get(i).getLength();j++)
				{
					Node attr = nlmap.get(i).item(j);
					//System.out.println(_nlmap.get(i).toString());
					if(attr.getNodeName().contains("vmw:qualifiers")){
						System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
						op[i][1] = attr.getNodeValue();
						System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
					}

					else if(attr.getNodeName().contains("ovf:password")){
						System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
						op[i][1] = attr.getNodeValue();
						System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
					}
					
					else if(attr.getNodeValue().contains("boolean")){
						System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
						op[i][1] = attr.getNodeValue();
						System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
					}
					
					else if(attr.getNodeValue().contains("ValueMap")){
						System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
						op[i][1] = "ValueMap";//attr.getNodeValue();
						System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
					}

					else if(attr.getNodeName().contains("ovf:key")){
						System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
						op[i][0] = attr.getNodeValue();
						System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
					}
				}
			}
			
			for(int i=0;i<op.length;i++)
				System.out.println(op[i][0]);
			
			//for(int i=0;i<nl.getLength()-5;i++)			// As last 5 Fields are not Visible in UI

			for(int i=0;i<nl.getLength()-1;i++)
			{
				//System.out.println(op[i][0]);
				if(op[i][1]!=null && op[i][1].equals("Ip") && op[i][0].equals("ip0")){
					System.out.println("Filled IP "+IP+" for VM " + i);
					AutoFillIP(driver, op[i][0],IP,fileName);
				}
				
				else if(op[i][1]==null && op[i][0].equals("ip0")){
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println(id+" IP as textbox ..");
					driver.findElement(By.id(id)).clear();
					driver.findElement(By.id(id)).sendKeys(IP);
					System.out.println("Filled IP "+IP+" for ID: "+id);
				}
				
				else if(op[i][0].equalsIgnoreCase("hostname")){
					System.out.println("Filled hostname for VM "+i + " "+hostName);
					autoFillhostnameforVM(driver, op[i][0], hostName);
				}
				
				else if(op[i][1]!=null && op[i][1].equals("Ip"))
				{
					//String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println("Going to IP " + i);
					AutoFillIP(driver, op[i][0],readFromFile(fileName, op[i][0]),fileName);
				}
				
				else if(op[i][1]!=null && op[i][1].equals("true"))
				{
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println("Password " + i);
					//AutoFillPasswd(driver, op[i][0],fileName);
					autoFillPasswdforVM(driver, id, fileName);
				}
				
				else if(op[i][1]!=null && op[i][1].equals("boolean"))
				{
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println("CheckBox " + i);
					//AutoFillCheckBox(driver, op[i][0]);
					autoFillCheckBoxforVM(driver, id);
				}
				
				else if(op[i][1]!=null && op[i][1].equals("ValueMap"))
				{
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println("Combo " + i);
					//AutoFillCombo(driver, op[i][0],fileName);
					autoFillComboforVM(driver, id, fileName);
				}
				
				else
				{
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
					System.out.println("Default " + i);
					//AutoFill(driver, op[i][0],fileName);
					//System.out.println("a");
					autoFilltextforVM(driver, id, fileName);
					//System.out.println("b");
				}
				System.out.println("Loop: \n");
			}

			//driver.findElement(By.xpath(locator.getProperty("NetWorkSelect"))).click();
			selNetworkTabForVMinstallation(driver);
			logClass.info("Selecting NetWorks");

			for(int i=0;i<nlLabelNet.getLength();i++)
			{
				driver.findElement(By.id((opNet[i]+"-inputEl"))).click();
				boundListSelect(driver, "VM Network", selBoundList(driver));
			}
			System.out.println("Filled Values");
		}
		
		public void AutoFill(WebDriver driver,String input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\""+ input +"\"]');return nl");
			List<String> IDs = new ArrayList<>();
			System.out.println(a.size());
			
			if(a.size()>0){
				for(WebElement s : a)
				{//System.out.println(s.getText());
					//System.out.println(s.getTagName());
					//System.out.println(s.getClass());
					//System.out.println("Check : "+s.getAttribute("id"));
					if(s.getTagName().equals("input"))
					{
						IDs.add(s.getAttribute("id"));
						returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}
				System.out.println("Ckeck :"+IDs.get(0));
				driver.findElement(By.id((IDs.get(0)))).clear();	// Take the first one as the two different ID can start with same string
				driver.findElement(By.id((IDs.get(0)))).sendKeys(readFromFile(fileName, input));
			}
		}

		//public void AutoFillIP(WebDriver driver,String inputForJS,String IP,String fileName){
		public void AutoFillIP(WebDriver driver,String id,String IP,String fileName){
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			List<String> Addresses = new ArrayList<String>();
			/*ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=ipfs"+ inputForJS +"]');return nl");
			
			for(WebElement s : a)
			{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("id"));
				returnID = s.getAttribute("id");
				System.out.println(s.getAttribute("id"));

			}*/
			returnID  = "ipfs"+id;
			//driver.findElement(By.id("ipfs"+id));
			System.out.println("Final ID: "+returnID);

			//System.out.println("Test");
			List<WebElement> tempcellsCols = driver.findElement(By.id((returnID))).findElements(By.xpath(".//*[local-name(.)='td']"));

			for(WebElement e : tempcellsCols)
				if(e.getAttribute("id").contains("body"))
				{
					//System.out.println("To be Printed"+e.getAttribute("id").replace("body", "input"));
					Addresses.add(e.getAttribute("id").replace("body", "input"));
				}
			//System.out.println("Before Fill IP");
			fillIPInBoxes(driver, IP, Addresses.get(0));
			//System.out.println("After Fill IP");
		}

		public void AutoFillCombo(WebDriver driver,String input,String fileName) throws IOException, InterruptedException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\""+ input +"\"]');return nl");
			System.out.println(a.size());
			System.out.println(input);
			for(WebElement s : a)
			{
				//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("role"));
				//System.out.println("IDs: "+s.getAttribute("id"));
				if(s.getAttribute("id").contains("input"))
				{
					returnID = s.getAttribute("id");
					//System.out.println(s.getAttribute("id"));
					//System.out.println("test P : "+s.getAttribute("role"));
				}
			}
			driver.findElement(By.id((returnID))).click();
			boundListSelect(driver, readFromFile(fileName, input.toUpperCase()), selBoundList(driver));
		}

		public void AutoFillPasswd(WebDriver driver,String input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID1 = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\""+ input +"\"]');return nl");
			for(WebElement s : a)
			{
				//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getAttribute("id"));
				if(s.getTagName().equals("input"))
				{
					returnID1 = s.getAttribute("id");
					System.out.println(s.getAttribute("id"));
				}
			}

			driver.findElement(By.id((returnID1))).sendKeys(readFromFile(fileName, input));
			driver.findElement(By.id(("conf"+returnID1))).sendKeys(readFromFile(fileName, input));
		}
		
		public void AutoFillCheckBox(WebDriver driver,String input) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID1 = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\""+ input +"\"]');return nl");
			
			for(WebElement s : a)
			{
				//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getAttribute("id"));
				if(s.getTagName().equals("input"))
				{
					returnID1 = s.getAttribute("id");
					System.out.println(s.getAttribute("id"));
				}
			}
			
			driver.findElement(By.id(returnID1)).click();
		}


	public void check(WebDriver driver,List<String> inputIP){

		WebElement table = driver.findElement(By.id(locator.getProperty("VCVMList")));
		List<WebElement> cells = table.findElements(By.xpath(locator.getProperty(".//*[local-name(.)='td']")));
		System.out.println(cells.size()+"\n\n");
		for(int i=0;i<inputIP.size();i++)
		{
			for(WebElement e : cells)
				{	
					//System.out.println(e.getText());
					if(e.getText().trim().equals(inputIP.get(i)))
					{
						e.click();
						//System.out.println("Added: "+e.getText());
					}
				}
		}
	}
	
	public String shortVMName(String input){
		String refInput = "";
		
		if(input.contains("-")){
		    	refInput = input.substring(0, input.indexOf("-"));
		    	System.out.println("RefInput: "+refInput);
		}
		else if(input.contains("_")){
		    	refInput = input.substring(0, input.indexOf("_"));
		    	System.out.println("RefInput: "+refInput);
		}
		 
		return refInput;
	}
	
	public String chooseOVF(String input){
		String returnStr = "";
		String fPath = System.getProperty("user.dir")+"\\Third Party\\OVFs\\";
		
		File folder = new File(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        //System.out.println("File " + listOfFiles[i].getName());
		        fileNames.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    
		   String refInput = shortVMName(input);
		    
		    for(String s : fileNames){
		    	if(s.contains("-")){
		    		if(s.substring(0,s.indexOf("-")).equalsIgnoreCase(refInput))
		    		{
		    			returnStr = s;
		    			System.out.println("Choosen OVF: (-) "+returnStr);
		    		}
		    	}
		    	else if(s.contains("_")){
		    		if(s.substring(0,s.indexOf("_")).equalsIgnoreCase(refInput))
		    		{
		    			returnStr = s;
		    			System.out.println("Choosen OVF: (_) "+returnStr);
		    		}
		    	}
		    }
		return fPath+returnStr;
	}
	
	public void loginToSite(WebDriver driver) throws IOException, InterruptedException{
		setup();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(11500, TimeUnit.MILLISECONDS);
		//driver.get("https://pdev55vm2.smgrdev.avaya.com");
		driver.get(locator.getProperty("SMGRURL"));
		
		driver.findElement(By.id("IDToken1")).sendKeys("admin");
	    driver.findElement(By.id("IDToken2")).sendKeys("Avaya123$");
	    driver.findElement(By.xpath(".//*[@id='SubmitButton']")).click();
	    logClass.info("Logged in Successfully");
	    
	    driver.findElement(By.xpath(".//*[@id='Services_SoftwareManagement']/a")).click();
	    
	    driver.switchTo().frame("iframe0");
	    
	    driver.findElement(By.xpath(".//*[@id='vmMgmtId-textEl']")).click();
		logClass.info("Clicked on VM management");
		
	}
	
	public void goHome(WebDriver driver) throws InterruptedException{
		Thread.sleep(1000);
		
		/*driver.navigate().refresh();
		logOut(driver);
		loginToSite(driver);*/
		
		driver.switchTo().defaultContent();
		WebElement e = driver.findElement(By.id("navIframe"));
		
		//System.out.println("Ans"+eee.findElement(By.linkText("Solution Deployment Manager")).getText());

		e.findElement(By.linkText("Solution Deployment Manager")).click();
		Thread.sleep(1500);
		
		driver.switchTo().frame("iframe0");
	    
	    driver.findElement(By.xpath(".//*[@id='vmMgmtId-textEl']")).click();
		logClass.info("Clicked on VM management");
	}
	
	public void logOut(WebDriver driver){
		driver.findElement(By.xpath(".//*[@id='logoff']")).click();
	}
	
	public void checkFocus(WebDriver driver, By toLocate) throws IOException{
		System.out.println(driver.findElement(toLocate).getCssValue("opacity"));
		if(Float.parseFloat((driver.findElement(toLocate).getCssValue("opacity")))!=1)
			{
				logClass.error("Something went wrong. Check Screenshot");
				//System.out.println("Something went wrong. Check Screenshot");
				takeScreenShotForDriver(driver);
			}
	}
	
	public void findButton(WebDriver driver) throws InterruptedException{
		//EulaAgreementWindoweulaAgreementVMUpdatePanel
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script2 = "var nl = document.getElementById(\"EulaAgreementWindow\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		/*System.out.println("Before");
		System.out.println(elem2.size());*/
		
		List<String> buttons = new ArrayList<>();
		for(WebElement e : elem2){
			//System.out.println(e.getText());
			if(e.getAttribute("id").contains("btnEl") && driver.findElement(By.id(e.getAttribute("id"))).getText().equals("Accept"))
			{
				buttons.add(e.getAttribute("id"));
				//System.out.println("Buttons: "+e.getAttribute("id"));
				//System.out.println(driver.findElement(By.id(e.getAttribute("id"))).getText());
			}
		}
		
		waitForPresenceOfElement(driver, By.id(buttons.get(0)));
		Thread.sleep(750);
		driver.findElement(By.id(buttons.get(buttons.size()-1))).click();
		System.out.println("Clicked");
		logClass.info("Accepted EULA ,clicked.");
	}
	
	public String findIDandFillValuesForVM(WebDriver driver,String filename,String inputIDfromOVF){
		List<String> IDs = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String sc = "var nl = document.getElementById(\"panelConfigParameters\").getElementsByTagName(\"input\"); return nl;";
		//String sc = "var nl = document.getElementById(\""+getViewFrame(driver, input)+"\").querySelectorAll('[id^=\"text\"]'); return nl;";
		String returnID = "";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc);
		//System.out.println("Before");
		System.out.println(elem.size());
		for(WebElement e:elem)
		{
			IDs.add(e.getAttribute("id"));
			//System.out.println(e.getAttribute("id")); 
			//System.out.println(e.getAttribute("role"));
		}
		
		for(String s : IDs){
			//if((inputIDfromOVF+"-inputEl").equalsIgnoreCase(s))
			if(s.contains(inputIDfromOVF))
				{
					returnID = s;
					break;
				}
		}
		return returnID;
	}
	
	public void autoFilltextforVM(WebDriver driver,String id,String fileName) throws IOException{
		if(!id.isEmpty()){
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));;
		}
	}
	
	public void autoFillhostnameforVM(WebDriver driver,String id,String hostname){
		if(!id.isEmpty()){
			driver.findElement(By.id(id+"-inputEl")).clear();
			driver.findElement(By.id(id+"-inputEl")).sendKeys(hostname);;
		}
	}
	
	public void autoFillPasswdforVM(WebDriver driver,String id,String fileName) throws IOException{
		System.out.println("ID: "+id);
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));
		driver.findElement(By.id(("conf"+id))).clear();
		driver.findElement(By.id(("conf"+id))).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));
	}

	public void autoFillComboforVM(WebDriver driver,String id,String fileName) throws IOException, InterruptedException{
		driver.findElement(By.id(id)).click();
		boundListSelect(driver, readFromFile(fileName, id.replace("-inputEl", "")), selBoundList(driver));
	}

	public void autoFillCheckBoxforVM(WebDriver driver,String id){
		driver.findElement(By.id(id)).click();
	}
	
	public void makeIPWhiteBlackList(String IP,String list) throws FileNotFoundException, IOException{
		Properties pr=new Properties();
		pr.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties"));
		
		File f = new File(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties");
		OutputStream out = null;
		
		pr.setProperty(IP, list);
		out = new FileOutputStream( f );
        pr.store(out, "Changed TimeStamp: ");
        out.close();
        
        System.out.println("Done Changing Properties File.");
		logClass.info(IP+ " IP is made " + list );
		System.out.println(IP + " IP is made " + list );
	}
	
	public void selectFP(WebDriver driver,String shortvmname) throws IOException, InterruptedException{
		if(driver.findElement(By.xpath(locator.getProperty("FootPrint"))).getAttribute("disabled")==null)
		{
			driver.findElement(By.xpath(locator.getProperty("FootPrint"))).click();
			Thread.sleep(450);
			System.out.println(locator.getProperty("FP"+shortvmname));
			boundListSelect(driver, locator.getProperty("FP"+shortvmname), selBoundList(driver));
		}
	}
	
	public void findMoreActionsButton(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script2 = "var nl = document.getElementById(\"confighostgrid\").querySelectorAll('[id^=\"button-\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		System.out.println(elem2.size());
		
		for(WebElement ee : elem2)
			{
				if(ee.getText().equals("More Actions"))
				{	//System.out.println(ee.getAttribute("id"));
					//System.out.println(ee.getText());
					ee.click();
					break;
				}
			}
	}
	
	public void selectLocforEditHost(WebDriver driver) throws IOException, InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script2 = "var nl = document.getElementById(\"hostEditForm\").querySelectorAll('[id^=\"combobox\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		System.out.println(elem2.size());
		
		driver.findElement(By.id(elem2.get(0).getAttribute("id").concat("-inputEl"))).click();
		//boundListSelect(driver, toBeSelected, selBoundList(driver));
	}
	
	public void editVMchooseFPorFQDN(WebDriver driver,String chooseOption){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script2 = "var nl = document.getElementById(\"VMEditForm\").querySelectorAll('[id^=\"radiofield\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		System.out.println(elem2.size());
		
		for(WebElement e : elem2){
			if(e.getText().contains(chooseOption))
			{
				System.out.println("Changing: "+e.getText());
				e.click();
			}
		}
	}
	
	public void clickLinksOnLeftMenu(WebDriver driver,String linkText) throws MyException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.querySelectorAll('[id^=\"menuitem\"]');return nl;";
		
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		
		if(linkText!=null && !linkText.isEmpty())
		{
			for(WebElement e : elem2){
				if(e.getText().equals(linkText))
				{
					//System.out.println("Changing: "+e.getText());
					e.click();
					break;
				}
			}
		}
		else{
			throw new MyException("Please give valid input String.. ");
		}
	}
	
	public void editVM(WebDriver driver,String IP,String FQDN){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.getElementById(\"VMEditForm-body\").querySelectorAll('[id^=\"textfield\"]');return nl;";
		
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		
		for(WebElement e : elem2){
			if(e.getText().equals("VM IP:")){
				System.out.println(e.getAttribute("id"));
				driver.findElement(By.id(e.getAttribute("id")+"-inputEl")).clear();
				driver.findElement(By.id(e.getAttribute("id")+"-inputEl")).sendKeys(IP);
				break;
			}
		}
		for(WebElement e : elem2){
			if(e.getText().equals("VM FQDN:")){
				System.out.println(e.getAttribute("id"));
				driver.findElement(By.id(e.getAttribute("id")+"-inputEl")).clear();
				driver.findElement(By.id(e.getAttribute("id")+"-inputEl")).sendKeys(FQDN);
				break;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public Object checkSuccessOrFailure(WebDriver driver,By locator,String vmName,int timeOutForException,boolean b,int noOfTimeOut,int counter,String linkText) throws MyException, IOException{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutForException, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS);
		Object o = null;
		boolean check = false;
		//int counter = 0;
		
		if(counter<noOfTimeOut){
			if(b){
				try{
					//chooseLink(driver, vmName,"VM",linkText);
					driver.switchTo().activeElement();
					System.out.println("SuccessTest");
					wait.until(ExpectedConditions.textToBePresentInElement(locator, "Completed"));
					System.out.println("SuccessTest Passed");
					System.out.println("Task Completed Successfully..");
					logClass.info("Task Completed Successfully..");
					logClass.info(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
					//check = true;
					closeWindow(driver);
					System.out.println("Total time taken in seconds: "+counter*timeOutForException);
					counter = noOfTimeOut+1;
				}
				
				
				
				catch(Exception e){
					//closeWindow(driver);
					System.out.println("Testing for Completion or failure..");
					//System.out.println(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
					b=false;
					counter++;
					System.out.println("Count cycle : "+counter);
					o = checkSuccessOrFailure(driver, locator, vmName,timeOutForException, b,noOfTimeOut,counter,linkText);
				}
			}
			
			else{
				try{
					//chooseLink(driver, vmName,"VM",linkText);
					driver.switchTo().activeElement();
					System.out.println("FailureTest");
					wait.until(ExpectedConditions.textToBePresentInElement(locator, "failed"));
					System.out.println("FailedTest");
					System.out.println("Task Failed..");
					logClass.info("Failed test due to some issue .. Check Scrrenshot for the same.");
					logClass.error(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
					check = true;
					takeScreenShotForDriver(driver);
					closeWindow(driver);
					System.out.println("Total time taken in seconds: "+counter*timeOutForException);
					counter = noOfTimeOut+1;
				}
				catch(Exception e){
					System.out.println("Testing for failure or Completion..");
					//System.out.println(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
					b=true;
					counter++;
					System.out.println("Count cycle : "+counter);
					o = checkSuccessOrFailure(driver, locator, vmName,timeOutForException, b,noOfTimeOut,counter,linkText);
				}
			}
		}
		else{
			System.out.println("Timed out..");
			takeScreenShotForDriver(driver);
			throw new MyException("Operation Timed out... ");
		}
		
		if(check){
			System.out.println("Completed task (failed).. ");
			throw new MyException("Test case failed .. Check screenshot for the same.");
			//o = System.out.toString();println("Passed");
		}
//		else 
//			System.out.println("Completed task..");
		
		return o;
		
	}
	
	public void clickComboSDMCli(WebDriver driver,String label){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.querySelectorAll('[id^=\"combobox\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		String tempID = "";
		
		for(WebElement e : elem2){
			System.out.println(e.getAttribute("id")+" "+e.getText());
			if(e.getText().equals(label)){
				System.out.println(e.getAttribute("id"));
				tempID = e.getAttribute("id").concat("-inputEl");
				driver.findElement(By.id(tempID)).click();
				break;
			}
		}
	}
	
	public void refreshVMValues(WebDriver driver,String shortVMname,String inputFileName) throws IOException{
		String uName = "";
		String pwd = "";
		final String SM = "SM";
		final String BSM = "BSM";
		final String CMM = "CMM";
		final String EDP = "EDP";
		final String US = "US";
		final String AAM = "AAM";
		final String AES = "AES";
		final String SecureAccessLinkGateway = "SecureAccessLinkGateway";
		final String MediaServer = "MediaServer";
		switch(shortVMname){
		case SM:
			uName = readFromFile(inputFileName, "CUSTLOGIN");
			pwd = readFromFile(inputFileName, "CUSTPWD");
			break;
			
		case BSM:
			uName = readFromFile(inputFileName, "CUSTLOGIN");
			pwd = readFromFile(inputFileName, "CUSTPWD");
			break;

		case CMM:
			uName = readFromFile(inputFileName, "msg_login");
			pwd = readFromFile(inputFileName, "msg_password");
			break;

		case EDP:
			uName = readFromFile(inputFileName, "CUSTLOGIN");
			pwd = readFromFile(inputFileName, "CUSTPWD");
			break;
			
		case US:
			uName = readFromFile(inputFileName, "UName");
			pwd = readFromFile(inputFileName, "USPWD");
			break;

		case AAM:
			uName = readFromFile(inputFileName, "msg_login");
			pwd = readFromFile(inputFileName, "msg_password");
			break;

		case AES:
			uName = readFromFile(inputFileName, "UName");
			pwd = readFromFile(inputFileName, "AESPWD");
			break;

		case SecureAccessLinkGateway:
			uName = readFromFile(inputFileName, "UName");
			pwd = readFromFile(inputFileName, "SALPWD");
			break;

		case MediaServer:
			uName = readFromFile(inputFileName, "CUSTLOGIN");
			pwd = readFromFile(inputFileName, "CUSTPWD");
			break;
			
		default:
			uName = readFromFile(inputFileName, "CustPwd");
			pwd = readFromFile(inputFileName, "CustPwd");
			break;
		
		}
		
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnUN"))).sendKeys(uName);

		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).clear();
		driver.findElement(By.xpath(locator.getProperty("VMReEstConnPw"))).sendKeys(pwd);

	}
	
	public void updateVMFilePath(WebDriver driver,String filePath){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sc = "var nl = document.getElementById(\"panelselectOVALocalUpdateVM-body\").querySelectorAll('[id^=\"textfield\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println(elem2.size());
		String tempID = "";
		
		for(WebElement e : elem2){
			System.out.println(e.getAttribute("id")+" "+e.getText());
			if(e.getText().equals("Select bin file from Local SDM Client:")){
				System.out.println(e.getAttribute("id"));
				tempID = e.getAttribute("id").concat("-inputEl");
				break;
			}
		}
		
		driver.findElement(By.id(tempID)).clear();
		driver.findElement(By.id(tempID)).sendKeys(filePath);;
	}
	
	public void findButtonUpdate(WebDriver driver) throws InterruptedException{
		//EulaAgreementWindoweulaAgreementVMUpdatePanel
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script2 = "var nl = document.getElementById(\"eulaAgreementVMUpdatePanel\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		/*System.out.println("Before");
		System.out.println(elem2.size());*/
		
		List<String> buttons = new ArrayList<>();
		for(WebElement e : elem2){
			//System.out.println(e.getText());
			if(e.getAttribute("id").contains("btnEl") && driver.findElement(By.id(e.getAttribute("id"))).getText().equals("Accept"))
			{
				buttons.add(e.getAttribute("id"));
				//System.out.println("Buttons: "+e.getAttribute("id"));
				//System.out.println(driver.findElement(By.id(e.getAttribute("id"))).getText());
			}
		}
		
		waitForPresenceOfElement(driver, By.id(buttons.get(0)));
		Thread.sleep(750);
		driver.findElement(By.id(buttons.get(buttons.size()-1))).click();
		System.out.println("Clicked");
		logClass.info("Accepted EULA ,clicked.");
	}
	
	public String chooseOVFFromSDMClient(String vmname) throws MyException{
		String returnStr = "";
		File folder = new File("C:\\Program Files\\Avaya\\SDMClient\\Default_Artifacts\\");
		File[] listOfFiles = folder.listFiles();
		List<String> dirNames = new ArrayList<>();
		String folderName = "";
		String returnOVFPath = "";

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				//System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				// System.out.println("Directory " + listOfFiles[i].getName());
				dirNames.add(listOfFiles[i].getName());
			}
		}

		for(String s : dirNames){
			if(shortVMName(s).equalsIgnoreCase(vmname)){
				System.out.println("Choosen Folder for OVF: "+s);
				folderName = s;
				break;
			}
		}

		if(!folderName.isEmpty()){
			File retFile = new File("C:\\Program Files\\Avaya\\SDMClient\\Default_Artifacts\\"+folderName);
			File[] retOVF = retFile.listFiles();

			for (int i = 0; i < retOVF.length; i++) {
				if (retOVF[i].isFile()) {
					returnOVFPath = retOVF[i].getAbsolutePath();
					System.out.println("Choosen OVF is: "+returnOVFPath);
				}
			}
		}   
		else
			throw new MyException("OVF not found for "+vmname+" at C:\\Program Files\\Avaya\\SDMClient\\Default_Artifacts\\ . Please check if OVA and OVF are present at given location.");
		return returnOVFPath;    
	}
	
	public void changeNetworkParamsHost(WebDriver driver,String fileName,String input) throws IOException, InterruptedException{
		findIDandFillValues(driver,fileName,input);
	}
	
	public void changePassWordHost(WebDriver driver,String fileName,String input) throws IOException, InterruptedException{
		findIDandFillValues(driver, fileName, input);
	}
	
	public void updateESXiHost(WebDriver driver,String fileName,String input) throws IOException, InterruptedException{
		//findIDandFillValues(driver, fileName, input);
		System.out.println("AVP Host can be updated...");
	}
	
	public WebElement findButtonByName(WebDriver driver,String staticIdFrom,String buttonName) throws MyException{
		WebElement returnEle = null;
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "var nl = document.getElementById(\""+staticIdFrom+"\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elements = (ArrayList<WebElement>) js.executeScript(script);
		
		if(!elements.isEmpty()){
			for(WebElement e : elements){
				if(e.getText().equals(buttonName)){
					System.out.println(e.getAttribute("id"));
					returnEle = e;
					break;
				}
				break;
			}
		}
		if(returnEle==null){
			throw new MyException("Couldn't find button, check the Static ID which is given and button name");
		}
		return returnEle;
	}
	
	public void autoReloadVMMgmtTree(WebDriver driver) throws InterruptedException, IOException{
		//autoLoadCheckBox-inputEl
		driver.findElement(By.id("autoLoadCheckBox-inputEl")).click();
		Thread.sleep(1000);
		confirmDialogBox(driver);
	}
	
	public void findComboBoxForGraphs(WebDriver driver,String staticIdFrom,String eleText) throws MyException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "var nl = document.getElementById(\""+staticIdFrom+"\").querySelectorAll('[id^=\"combobox\"]');return nl;";
		ArrayList<WebElement> elements = (ArrayList<WebElement>) js.executeScript(script);
		String returnEleID = null;
		
		if(!elements.isEmpty()){
			for(WebElement e : elements){
				if(e.getText().replace("*", "").equals(eleText)){
					System.out.println(e.getAttribute("id"));
					returnEleID = e.getAttribute("id");
					break;
				}
			}
		}
		if(returnEleID==null){
			throw new MyException("Couldn't find ComboBox, check the Static ID which is given and ComboBox name");
		}
		
		driver.findElement(By.id(returnEleID+"-inputEl")).click();
		//return returnEleID;
	}
	
	public void clickButtonxPath(WebDriver driver,String buttonxPath) throws IOException, MyException{
		float opacity = Float.parseFloat(driver.findElement(By.xpath(buttonxPath)).getCssValue("opacity"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonxPath)));
		
		if(opacity==1 && driver.findElement(By.xpath(buttonxPath)).isEnabled()){
			System.out.println("Opacity of button "+driver.findElement(By.xpath(buttonxPath)).getText()+" is: "+opacity);
			System.out.println("Is element enabled: "+driver.findElement(By.xpath(buttonxPath)).isEnabled());
			driver.findElement(By.xpath(buttonxPath)).click();
		}
		else{
			takeScreenShotForDriver(driver);
			throw new MyException("Check if button is enabled or not..");
		}
	}
	
	public Object checkCompletion(WebDriver driver,String vmName,int counter,int noOfTimeOut,int timeOutForException) throws MyException, IOException, InterruptedException{
		Thread.sleep((timeOutForException*noOfTimeOut)/2);
		
		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((locator.getProperty("Row"))));
		String rowID = "";
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutForException, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS);
		Object o = null;
		boolean check = false;
		//.//*[@id='gridview-1193-record-ext-record-713']/td[8]/div
		
		if(counter<(noOfTimeOut/2)){
		
				try{
					for(WebElement e : cells)
					{	
						if(e.getText().contains(vmName))
							{
								rowID = e.getAttribute("id");
								System.out.println(rowID);
							}
					}
					
					if(rowID==null || rowID.isEmpty()){
						takeScreenShotForDriver(driver);
						throw new MyException("VM does not exist.. Please check for the same.");
					}
				
					String xPathForColumn = ".//*[@id='"+rowID+"']/td[8]/div";
					System.out.println("Sample: "+driver.findElement(By.xpath(xPathForColumn)).getText());
					
					WebElement e = driver.findElement(By.xpath(xPathForColumn));
					
					wait.until(ExpectedConditions.textToBePresentInElement(e, "VM_START:COMPLETED.."));
					System.out.println("SuccessTest Passed");
					System.out.println("Task Completed Successfully..");
					logClass.info("Task Completed Successfully..");
					//check = true;
					System.out.println("Total time taken in seconds: "+((counter*timeOutForException)+timeOutForException*noOfTimeOut/2));
					counter = noOfTimeOut+1;
				}
				
				catch(Exception exception){
					
					System.out.println("Testing for Completion or failure..");
					//System.out.println(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
					counter++;
					System.out.println("Count cycle : "+counter);
					o = checkCompletion(driver, vmName, counter, noOfTimeOut, timeOutForException);
				}
			
		}
		else{
			System.out.println("Timed out..");
			takeScreenShotForDriver(driver);
			throw new MyException("Operation Timed out... ");
		}
		
		return o;
	}

}

