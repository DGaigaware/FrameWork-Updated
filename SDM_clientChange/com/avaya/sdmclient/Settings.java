package com.avaya.sdmclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
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


@SuppressWarnings("unchecked")
public class Settings {

	Properties locator = null;
	public void setup() throws IOException, InterruptedException
	{
		locator = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\xprev.properties"));
	}
	
	public void goToSite(WebDriver driver) throws IOException, InterruptedException{
		setup();
		driver.manage().window().maximize();
		driver.get("https://localhost/vm-mgmt-ui/pages/dashboardClient.html");
		driver.findElement(By.xpath(locator.getProperty("VM-Management"))).click();
		logClass.info("Clicked on VM management");
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
	}
	
	public void waitForPresence(WebDriver driver,By locate){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locate));
	}
	
	public void TakeScreenShot(WebDriver driver) throws IOException{
		String workingDirectory = System.getProperty("user.dir"); 
	    File imageFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		GregorianCalendar gcalendar = new GregorianCalendar();
		String failureImageFileName=workingDirectory+ File.separator+"Screenshots"+File.separator+"Screenshots"+ dateFormat.format(new java.util.Date())+File.separator +gcalendar.get(Calendar.HOUR)+gcalendar.get(Calendar.MINUTE)+gcalendar.getTimeInMillis()+".png";
		File failureImageFile=new File(failureImageFileName);
		FileUtils.moveFile(imageFile, failureImageFile);
		logClass.error("Something went wrong :(");
		logClass.info("Check Screenshot for the same");
	}
	
	public FirefoxProfile selectProfile(String profile){
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profil = allProfiles.getProfile(profile);
		logClass.confFile();
		return profil;
	}

	public String readFromFile(String fileName,String find) throws IOException{
		//File file = new File("C:\\Users\\bshingala\\Avaya\\SDMTests\\"+fileName);
		File file = new File(System.getProperty("user.dir")+"\\Input Files\\"+fileName);
		List<String> lines = FileUtils.readLines(file);
		Scanner sc;
		String output = null;
		for(String s : lines)
		{
			sc = new Scanner(s);
			while(sc.hasNext())
				if(sc.next().equals(find))
					if(sc.hasNext())
						output = sc.next().trim();
		}
		System.out.println(output);
		return output;
	}

	public void findLocationOrHost(WebDriver driver, String input) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocOrHostGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		//System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{
			//System.out.println("Locatest"+e.getText());
			try{
				if(e.isDisplayed())
					if(e.getText().trim().equals(input))
						e.click();
			}
			catch(Exception ex){
				System.out.println("Couldn't find");
			}
		}
	}

	public boolean checkLocationOrHost(WebDriver driver, String input) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocOrHostGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		List<String> tm = new ArrayList<>();
		boolean b = false;
		System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{
			//System.out.println(e.getText());
			tm.add(e.getText());
			/*if(e.isDisplayed())
				if(e.getText().trim().equals(_input))
					System.out.println(e.getAttribute("id"));*/
		}

		if(!tm.contains(input)){
			System.out.println("Couldn't Find");
			b=true;
		}

		/*for(String s : tm){
			System.out.println(s);
		}*/
		return b;
	}

	public void checkSuccess(WebDriver driver, String input) throws IOException, InterruptedException{
		setup();
		WebElement table1 = driver.findElement(By.id(locator.getProperty("HostInGrid")));
		List<WebElement> cells1 = table1.findElements(By.xpath((".//*[local-name(.)='tr']")));

		for(WebElement e : cells1)
		{
			if(e.getText().trim().contains(input))
			{
				System.out.println("next"+e.getText());
				e.click();
				e.findElement(By.linkText(locator.getProperty("Status Details"))).click();
			}
		}
	}

	public void findLocationInGrid(WebDriver driver, String Location) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("LocationInGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		//System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{
			if(e.getText().trim().equals(Location))
				e.click();
		}
	}

	public void findHostInGrid(WebDriver driver, String Host) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("HostInGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		//System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{	//System.out.println("Test:"+e.getText()+"\n");
			try{
				if(e.getText().trim().equals(Host))
					e.click();
			}
			catch(Exception ex){
				System.out.println("Couldn't find Host");
				TakeScreenShot(driver);
			}
		}


	}

	public void findVMForHost(WebDriver driver, String Host) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='td']")));
		//System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{	//System.out.println(e.getText());
			if(e.getText().trim().equals(Host))
				e.click();
		}
	}

	public void findvCenterInGrid(WebDriver driver, String VCenter) throws IOException, InterruptedException{
		setup();
		WebElement temp = driver.findElement(By.id(locator.getProperty("VCenterInGrid")));
		List<WebElement> tempcells = temp.findElements(By.xpath((".//*[local-name(.)='td']")));

		for(WebElement e : tempcells)
		{
			if(e.getText().trim().equals(VCenter))
				e.click();
		}
	}

	public void boundListSelect(WebDriver driver,String toBeSelected,String s) throws IOException, InterruptedException{
		setup();
		WebElement element = driver.findElement(By.id((s)));
		List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		for (WebElement e : tmp1 )
		{
			//System.out.println(e.getText()+ "\n Test \n");
			if(e.getText().contains(toBeSelected))
			{
				//System.out.println("\nSelected : \n"+e.getText());
				e.click();
			}
		}
	}

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
			System.out.println(tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
			logClass.error("Reason For Failure : "+tempcellsRow.get(index[i]).getText().toString().replaceAll("[\r\n]+", " :"));
		}
	}

	public String IPConvert(WebDriver driver,String IP,int partIndex){
		String[] parts = IP.split("\\.");
		/*for(int i=0;i<4;i++)
			System.out.println(parts[i]);*/
		return parts[partIndex];
	}

	public String removeAlpha(String str){
		StringBuilder sc = new StringBuilder();
		for (int i=0;i<str.length();i++)
			if (!Character.isDigit(str.charAt(i)))
				continue;
			else
				sc.append(str.charAt(i));
		return sc.toString();
	}

	public List<String> makeFields(String input){
		List<String> _Fields = new ArrayList<String>();
		int _it = Integer.parseInt(removeAlpha(input));
		for(int i=0;i<4;i++)
			_Fields.add(".//*[@id='textfield-"+(_it+(i*2))+"-inputEl']");
		return _Fields;
	}

	public void IPFill(WebDriver driver,String IP,String startAddress){
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

	public void errorBox(WebDriver driver,boolean check){
		if(check)
		{
			try{
				if(driver.findElement(By.id(locator.getProperty("DialogueBox"))).isDisplayed())
				{
					driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText();
					logClass.error(driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
					logClass.error("Error : Check the Log and Screenshot for the same.");
					System.out.println(driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
					TakeScreenShot(driver);
					logClass.info("Screenshot taken");

					driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
					System.out.println("Confirmed");
				}
			}
			catch(Exception ex){
				System.out.println("No Errors Uptill Now.");
			}
		}
	}

	public String selBoundList(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\"boundlist\"]');return nl");
		// Select the Last Boundlist which is rendered in DOM as last
		return a.get(a.size()-1).getAttribute("id");
	}

	public void exec(boolean in) {
		if(in == true) {
			return;
		}
		System.out.println("Can not Execute Further. Check the Log for the same.");
		logClass.error("Check Log and Screenshot for the same.");
		System.exit(0);
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
			TakeScreenShot(driver);
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
			//System.out.println(driver.switchTo().activeElement().getText());
			logClass.info("Confirmation: "+driver.findElement(By.id(locator.getProperty("DialogueBoxText"))).getText());
			driver.findElement(By.xpath(locator.getProperty("ConfButton"))).click();
			//System.out.println("Confirmed");
		}
		catch(Exception ex){
		}
	}

	public String ExtractText(String tag,String FilePath) throws IOException{
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

	public void comboClick(WebDriver driver, String StartAddress,String input) throws IOException, InterruptedException{
		driver.findElement(By.id(StartAddress+"-inputEl")).click();
		driver.findElement(By.id(StartAddress+"-inputEl")).sendKeys(input);
		Thread.sleep(2000);
		
		setup();
		WebElement element = driver.findElement(By.id((selBoundList(driver))));
		List<WebElement> tmp1 = element.findElements(By.className(locator.getProperty("CSSForBoundList")));
		for (WebElement e : tmp1 )
		{
			//System.out.println(e.getText()+ "\n Test \n");
			if(e.getText().equals(input))
			{
				//System.out.println("\nSelected : \n"+e.getText());
				e.click();
			}
		}
		//boundListSelect(driver, input, selBoundList(driver));
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


	public String fluentWaitCloseOpen(final By locatorTo,WebDriver driver,int time,String Test) throws InterruptedException {
		Thread.sleep(45000);
		closeWindow(driver);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Status Details")));
		Thread.sleep(1000);
		driver.findElement(By.linkText(locator.getProperty("Status Details"))).click();
		
		//chooseLink(driver, host);
		logClass.info("CO "+"Checking Status Details  ");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.getProperty("vmDeployStatus"))));
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.id(locator.getProperty("vmDeployStatus"))).getText());
		return fluentWait(locatorTo, driver, time, Test);
	}
	
	public void chooseLink(WebDriver driver,String VMName) throws IOException, InterruptedException{
		setup();
		WebElement table = driver.findElement(By.id(locator.getProperty("VMGrid")));
		List<WebElement> cells = table.findElements(By.xpath((".//*[local-name(.)='tr']")));
		//System.out.println(cells.size()+"\n\n");

		for(WebElement e : cells)
		{	System.out.println(e.getText());
			if(e.getText().contains(VMName))
				e.findElement(By.linkText("Status Details")).click();
			System.out.println("After");
		}
		//findVMForHost(driver, host);
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
				TakeScreenShot(driver);
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
		String refStr = "";
		
		types.add("newlocationview");
		types.add("editlocationview");
		types.add("host_new");
		types.add("host_edit");
		types.add("panelConfigParameters");
		
		if(input.equals("AddLocation"))
			{
				returnStr = types.get(0);
				refStr = "locationgridpanel";
			}
		else if(input.equals("EditLocation"))
			{
				returnStr = types.get(1);
				refStr = "locationgridpanel";
			}
		else if(input.equals("AddHost"))
			{
				returnStr = types.get(2);
				refStr = "host_view";
			}
		else if(input.equals("EditHost"))
			{
				returnStr = types.get(3);
				refStr = "host_view";
			}//
		else if(input.equals("AddVM"))
		{
			returnStr = types.get(4);
			refStr = "";
		}
		ret.add(returnStr);
		ret.add(refStr);
		
		for(String s : ret)
			System.out.println(s);
		
		return ret;
	}
	
	public void findIDandFillValues(WebDriver driver,String filename,String input) throws IOException, InterruptedException{
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String sc = "var nl = document.getElementById(\"" + getViewFrame(driver, input).get(0) + "\").getElementsByTagName(\"input\"); return nl;";
		//String sc = "var nl = document.getElementById(\""+getViewFrame(driver, input)+"\").querySelectorAll('[id^=\"text\"]'); return nl;";
		String returnID = "";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println("Before");
		System.out.println(elem.size());
		
		if(input.equals("EditHost")){
			driver.findElement(By.xpath(locator.getProperty("HostSelectDD"))).click();
			Thread.sleep(250);
			boundListSelect(driver, readFromFile(filename, "EditHostLocation:"), selBoundList(driver));
		}
		
		else if(input.equals("AddLocation") || input.equals("EditLocation")){
			
			String sc1 = "var nl = document.getElementById(\""+ getViewFrame(driver, input).get(0) + "\").querySelectorAll(\"textarea\"); return nl;";
			ArrayList<WebElement> elems = (ArrayList<WebElement>) js.executeScript(sc1);
			WebElement ee = driver.findElement(By.id(elems.get(0).getAttribute("id").replace("inputEl", "labelEl")));
			
			System.out.println("Main: "+ee.getText());
			returnID = elems.get(0).getAttribute("id");
			System.out.println(returnID);
			
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(readFromFile(filename, input+ee.getText().replace("*", "").replace(" ", "")));
		}
		
		for(WebElement e : elem){
			System.out.println("Main: "+driver.findElement(By.id(e.getAttribute("id").replace("input", "label"))).getText());
			if(e.getAttribute("readonly")==null){
			System.out.println("Attribb"+e.getAttribute("readonly"));
			WebElement el = driver.findElement(By.id(e.getAttribute("id").replace("input", "label")));
			System.out.println(el.getText());
			returnID = e.getAttribute("id");
			System.out.println(returnID);
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(readFromFile(filename, input+el.getText().replace("*", "").replace(" ", "")));
			}
		}
		
		System.out.println("After");
	}
	
	public void refreshItems(WebDriver driver,String methodBy){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String script1 = "var nl = document.getElementById(\"" + getViewFrame(driver, methodBy).get(1) + "\").querySelectorAll('[id^=\"pagingtoolbar\"]'); return nl;";
		ArrayList<WebElement> elem1 = (ArrayList<WebElement>) js.executeScript(script1);
		
		for(WebElement e : elem1)
			System.out.println(e.getAttribute("id"));
		
		System.out.println("Before");
		System.out.println(elem1.size());
		
		String script2 = "var nl = document.getElementById(\""+elem1.get(0).getAttribute("id")+"\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		System.out.println("Before");
		System.out.println(elem2.size());
		
		List<String> buttons = new ArrayList<>();
		for(WebElement e : elem2){
			if(e.getAttribute("id").contains("btnEl"))
			{
				buttons.add(e.getAttribute("id"));
				System.out.println("Buttons: "+e.getAttribute("id"));
			}
		}
		
		waitForPresence(driver, By.id(buttons.get(buttons.size()-1)));
		driver.findElement(By.id(buttons.get(buttons.size()-1))).click();
		System.out.println("Refreshed");
			
	}

		public void FillValues(String fileName,String filePath,WebDriver driver) throws IOException, ParserConfigurationException, SAXException, InterruptedException{
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
			System.out.println(Labels.size());
			
			/*for(int i=0;i<nl.getLength();i++)
			System.out.println(nl.item(i).getAttributes());*/
			
			String opNet[] = new String[2];
			
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
					System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
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
					System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
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
			
			/*for(int i=0;i<nl.getLength()-5;i++)
				System.out.println(op[i][0]);*/
			
			//for(int i=0;i<nl.getLength()-5;i++)			// As last 5 Fields are not Visible in UI

			for(int i=0;i<nl.getLength()-1;i++)
			{
				//System.out.println(op[i][0]);
				if(op[i][1]!=null && op[i][1].equals("Ip"))
				{
					String id = findIDandFillValuesForVM(driver, fileName, op[i][0]);
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
					System.out.println("a");
					autoFilltextforVM(driver, id, fileName);
					System.out.println("b");
				}
				System.out.println("Loop: \n");
			}

			driver.findElement(By.xpath(locator.getProperty("NetWorkSelect"))).click();
			logClass.info("Selecting NetWorks");

			for(int i=0;i<2;i++)
			{
				driver.findElement(By.id((opNet[i]+"-inputEl"))).click();
				boundListSelect(driver, "VM Network", selBoundList(driver));
			}
			System.out.println("Filled Values");
		}
		
		@SuppressWarnings("unused")
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
			System.out.println("Final"+returnID);

			//System.out.println("Test");
			List<WebElement> tempcellsCols = driver.findElement(By.id((returnID))).findElements(By.xpath(".//*[local-name(.)='td']"));

			for(WebElement e : tempcellsCols)
				if(e.getAttribute("id").contains("body"))
				{
					//System.out.println("To be Printed"+e.getAttribute("id").replace("body", "input"));
					Addresses.add(e.getAttribute("id").replace("body", "input"));
				}
			System.out.println("Before Fill IP");
			IPFill(driver, IP, Addresses.get(0));
			System.out.println("After Fill IP");
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
				System.out.println("IDs: "+s.getAttribute("id"));
				if(s.getAttribute("id").contains("input"))
				{
					returnID = s.getAttribute("id");
					System.out.println(s.getAttribute("id"));
					System.out.println("test P : "+s.getAttribute("role"));
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
						System.out.println("Added: "+e.getText());
					}
				}
		}
	}
	
	public void loginToSite(WebDriver driver){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4500, TimeUnit.MILLISECONDS);
		driver.get("https://pdev55vm2.smgrdev.avaya.com");
		
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
		
		driver.navigate().refresh();
		logOut(driver);
		loginToSite(driver);
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
				TakeScreenShot(driver);
			}
	}
	
	public void findButton(WebDriver driver){
		//EulaAgreementWindow
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script2 = "var nl = document.getElementById(\"EulaAgreementWindow\").querySelectorAll('[id^=\"button\"]');return nl;";
		ArrayList<WebElement> elem2 = (ArrayList<WebElement>) js.executeScript(script2);
		
		System.out.println("Before");
		System.out.println(elem2.size());
		
		List<String> buttons = new ArrayList<>();
		for(WebElement e : elem2){
			System.out.println(e.getText());
			if(e.getAttribute("id").contains("btnEl") && driver.findElement(By.id(e.getAttribute("id"))).getText().equals("Accept"))
			{
				buttons.add(e.getAttribute("id"));
				System.out.println("Buttons: "+e.getAttribute("id"));
				System.out.println(driver.findElement(By.id(e.getAttribute("id"))).getText());
			}
		}
		
		waitForPresence(driver, By.id(buttons.get(0)));
		driver.findElement(By.id(buttons.get(buttons.size()-1))).click();
		System.out.println("Clicked");
	}
	
	public String findIDandFillValuesForVM(WebDriver driver,String filename,String inputIDfromOVF){
		List<String> IDs = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String sc = "var nl = document.getElementById(\"panelConfigParameters\").getElementsByTagName(\"input\"); return nl;";
		//String sc = "var nl = document.getElementById(\""+getViewFrame(driver, input)+"\").querySelectorAll('[id^=\"text\"]'); return nl;";
		String returnID = "";
		
		ArrayList<WebElement> elem = (ArrayList<WebElement>) js.executeScript(sc);
		System.out.println("Before");
		System.out.println(elem.size());
		for(WebElement e:elem)
		{
			IDs.add(e.getAttribute("id"));
			//System.out.println(e.getAttribute("id")); 
			//System.out.println(e.getAttribute("role"));
		}
		
		for(String s : IDs){
			if((inputIDfromOVF+"-inputEl").equalsIgnoreCase(s))
				returnID = s;
		}
		return returnID;
	}
	
	public void autoFilltextforVM(WebDriver driver,String id,String fileName) throws IOException{
		if(!id.isEmpty()){
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));;
		}
	}
	
	public void autoFillIPforVM(WebDriver driver,String id,String fileName){
		driver.findElement(By.id("ipfs"+id)).click();
	}
	
	public void autoFillPasswdforVM(WebDriver driver,String id,String fileName) throws IOException{
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));
		driver.findElement(By.id(("conf"+id))).sendKeys(readFromFile(fileName, id.replace("-inputEl", "")));
	}

	public void autoFillComboforVM(WebDriver driver,String id,String fileName) throws IOException, InterruptedException{
		driver.findElement(By.id(id)).click();
		boundListSelect(driver, readFromFile(fileName, id.replace("-inputEl", "")), selBoundList(driver));
	}

	public void autoFillCheckBoxforVM(WebDriver driver,String id){
		driver.findElement(By.id(id)).click();
	}
}

