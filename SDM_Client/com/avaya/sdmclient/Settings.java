package com.avaya.sdmclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
	
	public FirefoxProfile selectProfile(String _profile){
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile(_profile);
		logClass.confFile();
		return profile;
	}
	
	public String readFromFile(String _fileName,String _find) throws IOException{
		File file = new File("C:\\Users\\bshingala\\Avaya\\SDMTests\\"+_fileName);
		List<String> lines = FileUtils.readLines(file);
		Scanner sc;
		String _output = null;
		for(String s : lines)
			{
				sc = new Scanner(s);
					while(sc.hasNext())
						if(sc.next().equals(_find))
							if(sc.hasNext())
							_output = sc.next().trim();
			}
		System.out.println(_output);
		return _output;
	}
	
	public void findLocationOrHost(WebDriver driver, String _input){
		WebElement table = driver.findElement(By.id("treeview-1014"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='tr']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			//System.out.println(e.getText());
			try{
				if(e.isDisplayed())
					if(e.getText().trim().equals(_input))
						e.click();
			}
			catch(Exception ex){
				System.out.println("Couldn't find");
			}
		}
	}
	
	public void findLocationInGrid(WebDriver driver, String _Location){
		WebElement table = driver.findElement(By.id("gridview-1054"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			if(e.getText().trim().equals(_Location))
				e.click();
		}
	}
	
	public void findHostInGrid(WebDriver driver, String _Host){
		WebElement table = driver.findElement(By.id("gridview-1115"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
		
		for(WebElement e : cells)
		{	//System.out.println("Test:"+e.getText()+"\n");
			try{
			if(e.getText().trim().equals(_Host))
				e.click();
			}
			catch(Exception ex){
				System.out.println("Couldn't find Host");
			}
		}
		
		
	}
	
	public void findVMForHost(WebDriver driver, String _Host){
		WebElement table = driver.findElement(By.id("gridview-1190"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	//System.out.println(e.getText());
			if(e.getText().trim().equals(_Host))
				e.click();
		}
	}
	
	public void findVCenterInGrid(WebDriver driver, String _VCenter){
		WebElement temp = driver.findElement(By.xpath(".//*[@id='gridview-1315']"));
		List<WebElement> tempcells = temp.findElements(By.xpath(".//*[local-name(.)='td']"));
		
		for(WebElement e : tempcells)
		{	
			if(e.getText().trim().equals(_VCenter))
				e.click();
		}	
	}
	
	public void boundListSelect(WebDriver driver,String _toBeSelected,String s){
		WebElement element = driver.findElement(By.id(s));
		List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
		for (WebElement e : tmp1 )
			{
				//System.out.println(e.getText()+ "\n Test \n");
				if(e.getText().contains(_toBeSelected))
				{
					//System.out.println("\nSelected : \n"+e.getText());
					e.click();
				}
			}
	}
	
	public void checkFailureOfHostCapacity(WebDriver driver){
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
	
	public String IPConvert(WebDriver driver,String _IP,int _partIndex){
		String[] parts = _IP.split("\\.");
		for(int i=0;i<4;i++)
			System.out.println(parts[i]);
		return parts[_partIndex];
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

	public List<String> makeFields(String _input){
		List<String> _Fields = new ArrayList<String>();
		int _it = Integer.parseInt(removeAlpha(_input));
		for(int i=0;i<4;i++)
			_Fields.add(".//*[@id='textfield-"+(_it+(i*2))+"-inputEl']");
		return _Fields;
	}
	
	public void IPFill(WebDriver driver,String _IP,String _startAddress){
		List<String> _addresses = makeFields(_startAddress);
		for(int i=0;i<4;i++)
		{
			driver.findElement(By.xpath(_addresses.get(i))).clear();
			driver.findElement(By.xpath(_addresses.get(i))).sendKeys(IPConvert(driver, _IP, i));
		}	
	}

	public boolean checkError(WebDriver driver){
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
	
	public void errorBox(WebDriver driver,boolean check){
		if(check)
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
	
	@SuppressWarnings({ "unused" })
	public String selBoundList(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=\"boundlist\"]');return nl");
		for(WebElement s : a)
			{//System.out.println(s.getText());
			//System.out.println(s.getTagName());
			//System.out.println(s.getClass());
			//System.out.println(s.getAttribute("id"));
			}
		return a.get(a.size()-1).getAttribute("id");
	}
	
	public void exec(boolean _in) {
	    if(_in == true) {
	        return;
	    }
	    System.out.println("Can not Execute Further. Check the Log for the same.");
	    logClass.error("Check Log and Screenshot for the same.");
	    System.exit(0);
	}
	
	public void confirmDialogBox(WebDriver driver) throws IOException{
		driver.switchTo().activeElement();
		
		try{
			if(driver.findElement(By.id("messagebox-1001")).isDisplayed())
			{
				logClass.info("Action being performed: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());		
				//System.out.println("Action being performed: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
			}
		}
		catch(Exception ex){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:\\Users\\bshingala\\Desktop\\screenshotSDMfail1.png"));
			logClass.error("Something went wrong :(");
			logClass.info("Check Screenshot for the same");
			System.out.println("Couldn't find any text");
		}
		
		try{
		driver.findElement(By.xpath(".//*[@id='button-1006-btnIconEl']")).click();
		}
		catch(Exception ex){
			System.out.println("\n");
		}
		try{
			driver.switchTo().activeElement();
			//System.out.println(driver.switchTo().activeElement().getText());
			logClass.info("Confirmation: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());		
			//System.out.println("Confirmation: "+driver.findElement(By.xpath(".//*[@id='messagebox-1001-displayfield-inputEl']")).getText());
			driver.findElement(By.xpath(".//*[@id='button-1005-btnIconEl']")).click();
			//System.out.println("Confirmed");
		}
		catch(Exception ex){
			//System.out.println("Check Logs.");
			//logClass.info("Check Logs");
		}
	}

		public String ExtractText(String _tag,String _FilePath) throws IOException{
			String _check = "";
			String ans = "";
			File file = new File(_FilePath);
			final Pattern pattern = Pattern.compile("<"+_tag+">(.+?)</"+_tag+">");
			List<String> _Lines = FileUtils.readLines(file);
			for(String l: _Lines)
				if(pattern.matcher(l).find())
					_check = l;
			
			final Matcher matcher = pattern.matcher(_check);
			while(matcher.find())
			{
				System.out.println(matcher.group(1).toString());
				ans = matcher.group(1).toString();
			} 	
			return ans;
		}

		public void comboClick(WebDriver driver, String _StartAddress){
			List<WebElement> _cols = driver.findElement(By.id("combobox-1238")).findElements(By.xpath(".//*[local-name(.)='td']"));
			String _ID;
			for(WebElement e : _cols)
				{
					System.out.println(e.getAttribute("id"));
					if(e.getAttribute("id").contains("ext"))
						{
							System.out.println("test "+e.getAttribute("id"));//e.click();
							_ID = e.getAttribute("id");
							e.findElement(By.id(_ID)).click();
							//e.findElement(By.id(_ID)).click();
						}
				}
		}

		@SuppressWarnings("deprecation")
		public String fluentWait(final By locator,WebDriver driver,int _time,String _Test) throws InterruptedException {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(_time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			//Thread.sleep(45000);
			//_closeWindow(driver);
			wait.until(ExpectedConditions.textToBePresentInElement(locator, _Test));
			logClass.info(driver.findElement(locator).getText().replaceAll("[\r\n]+", " :;"));
		    return driver.findElement(locator).getText();
		}
		
		
		public String fluentWaitCloseOpen(final By locator,WebDriver driver,int _time,String _Test) throws InterruptedException {
			Thread.sleep(45000);
			closeWindow(driver);
			
			Thread.sleep(1000);
			driver.findElement(By.linkText("Status Details")).click();
			logClass.info("CO "+"Checking Status Details");
			
			WebDriverWait wait = new WebDriverWait(driver, 3000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
			driver.switchTo().activeElement();
			System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
			return fluentWait(locator, driver, _time, _Test);
		}
		
		public void StatusCheck(WebDriver driver,String _toBeChecked,int _time) throws IOException, InterruptedException{
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
			driver.switchTo().activeElement();
			System.out.println(fluentWait(By.id("vmDeployStatus"), driver, _time, _toBeChecked));

			if(driver.findElement(By.id("vmDeployStatus")).getText().contains(_toBeChecked))
			{
				closeWindow(driver);
				System.out.println("Completed Successfully");
			}

			else if(driver.findElement(By.id("vmDeployStatus")).getText().contains("failed"))
			{
				System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
				logClass.error(driver.findElement(By.id("vmDeployStatus")).getText());
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("C:\\Users\\bshingala\\Desktop\\screenshotSDMfail.png"));
				logClass.error("Something went wrong :(");
				logClass.info("Check Screenshot for the same");
				closeWindow(driver);
			}
			
		}
		
		public void closeWindow(WebDriver driver){
			List<WebElement> _elem = driver.findElement(By.id("vmDeployStatus")).findElement(By.id("vmDeployStatus_header")).findElement(By.id("vmDeployStatus_header-innerCt")).findElement(By.id("vmDeployStatus_header-targetEl")).findElements(By.tagName("div"));
			//System.out.println(_elem.size());
			
			for(WebElement e : _elem)
				{
					//System.out.println(e.getAttribute("class"));
					if(e.getAttribute("class").contains("x-tool"))
					e.click();
				}
		}
		
		
		public void AutoFill(WebDriver driver,String _input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String _returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ _input +"]');return nl");
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				System.out.println("Check : "+s.getAttribute("id"));
				if(s.getTagName().equals("input"))
					{
						_returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}
			System.out.println("Ckeck :"+_returnID);
			driver.findElement(By.id(_returnID)).clear();
			driver.findElement(By.id(_returnID)).sendKeys(readFromFile(fileName, _input));
		}
	
		public void AutoFillIP(WebDriver driver,String _inputForJS,String _IP,String fileName){
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String _returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=ipfs"+ _inputForJS +"]');return nl");
			List<String> _Addresses = new ArrayList<String>();
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("id"));
						_returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					
				}
			System.out.println("Final"+_returnID);
			
			System.out.println("Test");
			List<WebElement> tempcellsCols = driver.findElement(By.id(_returnID)).findElements(By.xpath(".//*[local-name(.)='td']"));
			
			for(WebElement e : tempcellsCols)
				if(e.getAttribute("id").contains("body"))
				{
					System.out.println("To be Printed"+e.getAttribute("id").replace("body", "input"));
					_Addresses.add(e.getAttribute("id").replace("body", "input"));
				}
			System.out.println("Before Fill IP");
			IPFill(driver, _IP, _Addresses.get(0));
			System.out.println("After Fill IP");
		}
		
		public void FillValues(String fileName,String _filePath,WebDriver driver) throws IOException, ParserConfigurationException, SAXException{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.parse(new File(_filePath));
			NodeList nl =document.getElementsByTagName("Property");
			System.out.println(nl.getLength());
			
			List<String> _Labels = new ArrayList<String>();

			NodeList nlLabel = document.getElementsByTagName("Label");
			for(int j=0;j<nlLabel.getLength();j++)
				if(nlLabel.item(j).getParentNode().getNodeName().equals("Property"))
				_Labels.add(nlLabel.item(j).getTextContent());
			List<NamedNodeMap> _nlmap = new ArrayList<>();
			for(int i=0;i<nl.getLength();i++)
				_nlmap.add(nl.item(i).getAttributes());
			String _op[][] = new String[nl.getLength()-1][2];
			System.out.println(_Labels.size());
			/*for(int i=0;i<nl.getLength();i++)
				System.out.println(nl.item(i).getAttributes());*/
			String _opNet[] = new String[2];
			NodeList nlLabelNet = document.getElementsByTagName("Network");
			List<NamedNodeMap> _nlmapnet = new ArrayList<>();
			for(int i=0;i<nlLabelNet.getLength();i++)
				_nlmapnet.add(nlLabelNet.item(i).getAttributes());
			
			for (int i = 0; i < nlLabelNet.getLength(); i++)
			{
				System.out.println("-inputEl");
				
					//System.out.println(_Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<_nlmapnet.get(i).getLength();j++)
					{
						Node attr = _nlmapnet.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
						_opNet[i]=attr.getNodeValue();
						System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					}
			}
			for (int i = 0; i < nl.getLength()-1; i++)
			{
				System.out.println();
				
					System.out.println(_Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<_nlmap.get(i).getLength();j++)
					{
						Node attr = _nlmap.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
					
						System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					}
			}
			
			for (int i = 0; i < nl.getLength()-1; i++)
			{
				System.out.println();
				
					System.out.println(_Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<_nlmap.get(i).getLength();j++)
					{
						Node attr = _nlmap.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
						if(attr.getNodeName().contains("vmw:qualifiers")){
							System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
							_op[i][1] = attr.getNodeValue();
							System.out.println("1st: "+_op[i][1]+ " "+ _op[i][0]);
						}
						
						else if(attr.getNodeName().contains("ovf:password")){
							System.out.println(" " + i + " "+ j +attr.getNodeName()+ " = \"" + attr.getNodeValue() + "\"");
							_op[i][1] = attr.getNodeValue();
							System.out.println("1st: "+_op[i][1]+ " "+ _op[i][0]);
						}
							
						else if(attr.getNodeName().contains("ovf:key")){
							System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
							_op[i][0] = attr.getNodeValue();
							System.out.println("1st: "+_op[i][1]+ " "+ _op[i][0]);
						}
					}
			}
			for(int i=0;i<nl.getLength()-5;i++)
				System.out.println(_op[i][0]);
			for(int i=0;i<nl.getLength()-5;i++)
				
					{
						System.out.println(_op[i][0]);
						if(_op[i][1]!=null && _op[i][1].equals("Ip"))
							{
								System.out.println("Going to IP " + i);
								AutoFillIP(driver, _op[i][0],readFromFile(fileName, _op[i][0]),fileName);
							}
						
						else if(_op[i][1]!=null && _op[i][1].equals("true"))
							{
								System.out.println("Password " + i);
								AutoFillPasswd(driver, _op[i][0],fileName);
							}
						else if(_op[i][1]==null && _op[i][0].equalsIgnoreCase("timezone"))
							{
								System.out.println("Combo " + i);
								AutoFillCombo(driver, _op[i][0],fileName);
							}
						else
							{
								System.out.println("Default " + i);
								AutoFill(driver, _op[i][0],fileName);
							}
						System.out.println("Loop: \n");
					}
			
			driver.findElement(By.xpath(".//*[@id='tab-1243-btnInnerEl']")).click();
			logClass.info("Selecting NetWorks");
			
			for(int i=0;i<2;i++)
			{
				driver.findElement(By.id(_opNet[i]+"-inputEl")).click();
				boundListSelect(driver, "VM Network", selBoundList(driver));
			}
			System.out.println("Filled Values");
		}
		
		public void AutoFillCombo(WebDriver driver,String _input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String _returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ _input +"]');return nl");
			//System.out.println(a.size());
			//System.out.println(a.get(0).getText());
			//System.out.println(_input);
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				System.out.println(s.getAttribute("role"));
				System.out.println("IDs: "+s.getAttribute("id"));
				//if(s.getTagName().equals("input"))
					{
						_returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}
			driver.findElement(By.id(_returnID)).click();
			boundListSelect(driver, readFromFile("input1.txt", _input.toUpperCase()), selBoundList(driver));
		}
		
		public void AutoFillPasswd(WebDriver driver,String _input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String _returnID1 = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ _input +"]');return nl");
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("id"));
				if(s.getTagName().equals("input"))
					{
						_returnID1 = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}

				driver.findElement(By.id(_returnID1)).sendKeys(readFromFile(fileName, _input));
				driver.findElement(By.id("conf"+_returnID1)).sendKeys(readFromFile(fileName, _input));
		}
		
		public void check(WebDriver driver,List<String> inputIP){
			
			WebElement table = driver.findElement(By.id("gridview-1369"));
			List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
			System.out.println(cells.size()+"\n\n");
			for(int i=0;i<inputIP.size();i++)
			{
				for(WebElement e : cells)
			
				{	//System.out.println(e.getText());
					if(e.getText().trim().equals(inputIP.get(i)))
						{
							e.click();
							System.out.println("Added: "+e.getText());
						}
				}
			}
		}
}

