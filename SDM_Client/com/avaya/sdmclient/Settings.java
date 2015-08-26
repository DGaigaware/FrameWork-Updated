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
	
	public FirefoxProfile selectProfile(String profile){
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profil = allProfiles.getProfile(profile);
		logClass.confFile();
		return profil;
	}
	
	public String readFromFile(String fileName,String find) throws IOException{
		File file = new File("C:\\Users\\bshingala\\Avaya\\SDMTests\\"+fileName);
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
	
	public void findLocationOrHost(WebDriver driver, String input){
		WebElement table = driver.findElement(By.id("treeview-1014"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='tr']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			//System.out.println(e.getText());
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
	
	public boolean checkLocationOrHost(WebDriver driver, String input){
		WebElement table = driver.findElement(By.id("treeview-1014"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='tr']"));
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
		
		for(String s : tm){
			System.out.println(s);
		}
		return b;
	}
	
	public void checkSuccess(WebDriver driver, String input){
		WebElement table1 = driver.findElement(By.id("gridview-1115"));
		List<WebElement> cells1 = table1.findElements(By.xpath(".//*[local-name(.)='tr']"));
	
		for(WebElement e : cells1)
		{	
			if(e.getText().trim().contains(input))
			{
				System.out.println("next"+e.getText());
				e.click();
				e.findElement(By.linkText("Status Details")).click();
			}
		}
	}
	
	public void findLocationInGrid(WebDriver driver, String Location){
		WebElement table = driver.findElement(By.id("gridview-1054"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	
			if(e.getText().trim().equals(Location))
				e.click();
		}
	}
	
	public void findHostInGrid(WebDriver driver, String Host){
		WebElement table = driver.findElement(By.id("gridview-1115"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
		
		for(WebElement e : cells)
		{	//System.out.println("Test:"+e.getText()+"\n");
			try{
			if(e.getText().trim().equals(Host))
				e.click();
			}
			catch(Exception ex){
				System.out.println("Couldn't find Host");
			}
		}
		
		
	}
	
	public void findVMForHost(WebDriver driver, String Host){
		WebElement table = driver.findElement(By.id("gridview-1190"));
		List<WebElement> cells = table.findElements(By.xpath(".//*[local-name(.)='td']"));
		//System.out.println(cells.size()+"\n\n");
	
		for(WebElement e : cells)
		{	//System.out.println(e.getText());
			if(e.getText().trim().equals(Host))
				e.click();
		}
	}
	
	public void findVCenterInGrid(WebDriver driver, String VCenter){
		WebElement temp = driver.findElement(By.xpath(".//*[@id='gridview-1315']"));
		List<WebElement> tempcells = temp.findElements(By.xpath(".//*[local-name(.)='td']"));
		
		for(WebElement e : tempcells)
		{	
			if(e.getText().trim().equals(VCenter))
				e.click();
		}	
	}
	
	public void boundListSelect(WebDriver driver,String toBeSelected,String s){
		WebElement element = driver.findElement(By.id(s));
		List<WebElement> tmp1 = element.findElements(By.className("x-boundlist-item"));
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
	
	public String IPConvert(WebDriver driver,String IP,int partIndex){
		String[] parts = IP.split("\\.");
		for(int i=0;i<4;i++)
			System.out.println(parts[i]);
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
			driver.findElement(By.xpath(addresses.get(i))).clear();
			driver.findElement(By.xpath(addresses.get(i))).sendKeys(IPConvert(driver, IP, i));
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

		public void comboClick(WebDriver driver, String StartAddress){
			List<WebElement> cols = driver.findElement(By.id("combobox-1238")).findElements(By.xpath(".//*[local-name(.)='td']"));
			String ID;
			for(WebElement e : cols)
				{
					System.out.println(e.getAttribute("id"));
					if(e.getAttribute("id").contains("ext"))
						{
							System.out.println("test "+e.getAttribute("id"));//e.click();
							ID = e.getAttribute("id");
							e.findElement(By.id(ID)).click();
							//e.findElement(By.id(_ID)).click();
						}
				}
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
		
		
		public String fluentWaitCloseOpen(final By locator,WebDriver driver,int time,String Test) throws InterruptedException {
			Thread.sleep(45000);
			closeWindow(driver);
			
			Thread.sleep(1000);
			driver.findElement(By.linkText("Status Details")).click();
			logClass.info("CO "+"Checking Status Details");
			
			WebDriverWait wait = new WebDriverWait(driver, 3000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
			driver.switchTo().activeElement();
			System.out.println(driver.findElement(By.id("vmDeployStatus")).getText());
			return fluentWait(locator, driver, time, Test);
		}
		
		public void StatusCheck(WebDriver driver,String toBeChecked,int time) throws IOException, InterruptedException{
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vmDeployStatus")));
			driver.switchTo().activeElement();
			System.out.println(fluentWait(By.id("vmDeployStatus"), driver, time, toBeChecked));

			if(driver.findElement(By.id("vmDeployStatus")).getText().contains(toBeChecked))
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
			List<WebElement> elem = driver.findElement(By.id("vmDeployStatus")).findElement(By.id("vmDeployStatus_header")).findElement(By.id("vmDeployStatus_header-innerCt")).findElement(By.id("vmDeployStatus_header-targetEl")).findElements(By.tagName("div"));
			//System.out.println(_elem.size());
			
			for(WebElement e : elem)
				{
					//System.out.println(e.getAttribute("class"));
					if(e.getAttribute("class").contains("x-tool"))
					e.click();
				}
		}
		
		
		public void AutoFill(WebDriver driver,String input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ input +"]');return nl");
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				System.out.println("Check : "+s.getAttribute("id"));
				if(s.getTagName().equals("input"))
					{
						returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}
			System.out.println("Ckeck :"+returnID);
			driver.findElement(By.id(returnID)).clear();
			driver.findElement(By.id(returnID)).sendKeys(readFromFile(fileName, input));
		}
	
		public void AutoFillIP(WebDriver driver,String inputForJS,String IP,String fileName){
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^=ipfs"+ inputForJS +"]');return nl");
			List<String> Addresses = new ArrayList<String>();
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("id"));
						returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					
				}
			System.out.println("Final"+returnID);
			
			System.out.println("Test");
			List<WebElement> tempcellsCols = driver.findElement(By.id(returnID)).findElements(By.xpath(".//*[local-name(.)='td']"));
			
			for(WebElement e : tempcellsCols)
				if(e.getAttribute("id").contains("body"))
				{
					System.out.println("To be Printed"+e.getAttribute("id").replace("body", "input"));
					Addresses.add(e.getAttribute("id").replace("body", "input"));
				}
			System.out.println("Before Fill IP");
			IPFill(driver, IP, Addresses.get(0));
			System.out.println("After Fill IP");
		}
		
		public void FillValues(String fileName,String filePath,WebDriver driver) throws IOException, ParserConfigurationException, SAXException{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.parse(new File(filePath));
			NodeList nl =document.getElementsByTagName("Property");
			System.out.println(nl.getLength());
			
			List<String> Labels = new ArrayList<String>();

			NodeList nlLabel = document.getElementsByTagName("Label");
			for(int j=0;j<nlLabel.getLength();j++)
				if(nlLabel.item(j).getParentNode().getNodeName().equals("Property"))
				Labels.add(nlLabel.item(j).getTextContent());
			List<NamedNodeMap> nlmap = new ArrayList<>();
			for(int i=0;i<nl.getLength();i++)
				nlmap.add(nl.item(i).getAttributes());
			String op[][] = new String[nl.getLength()-1][2];
			System.out.println(Labels.size());
			/*for(int i=0;i<nl.getLength();i++)
				System.out.println(nl.item(i).getAttributes());*/
			String opNet[] = new String[2];
			NodeList nlLabelNet = document.getElementsByTagName("Network");
			List<NamedNodeMap> nlmapnet = new ArrayList<>();
			for(int i=0;i<nlLabelNet.getLength();i++)
				nlmapnet.add(nlLabelNet.item(i).getAttributes());
			
			for (int i = 0; i < nlLabelNet.getLength(); i++)
			{
				System.out.println("-inputEl");
				
					//System.out.println(_Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<nlmapnet.get(i).getLength();j++)
					{
						Node attr = nlmapnet.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
						opNet[i]=attr.getNodeValue();
						System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					}
			}
			for (int i = 0; i < nl.getLength()-1; i++)
			{
				System.out.println();
				
					System.out.println(Labels.get(i));
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<nlmap.get(i).getLength();j++)
					{
						Node attr = nlmap.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
					
						System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					}
			}
			
			for (int i = 0; i < nl.getLength()-1; i++)
			{
				System.out.println();
				
					System.out.println(Labels.get(i));
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
							
						else if(attr.getNodeName().contains("ovf:key")){
							System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
							op[i][0] = attr.getNodeValue();
							System.out.println("1st: "+op[i][1]+ " "+ op[i][0]);
						}
					}
			}
			for(int i=0;i<nl.getLength()-5;i++)
				System.out.println(op[i][0]);
			for(int i=0;i<nl.getLength()-5;i++)
				
					{
						System.out.println(op[i][0]);
						if(op[i][1]!=null && op[i][1].equals("Ip"))
							{
								System.out.println("Going to IP " + i);
								AutoFillIP(driver, op[i][0],readFromFile(fileName, op[i][0]),fileName);
							}
						
						else if(op[i][1]!=null && op[i][1].equals("true"))
							{
								System.out.println("Password " + i);
								AutoFillPasswd(driver, op[i][0],fileName);
							}
						else if(op[i][1]==null && op[i][0].equalsIgnoreCase("timezone"))
							{
								System.out.println("Combo " + i);
								AutoFillCombo(driver, op[i][0],fileName);
							}
						else
							{
								System.out.println("Default " + i);
								AutoFill(driver, op[i][0],fileName);
							}
						System.out.println("Loop: \n");
					}
			
			driver.findElement(By.xpath(".//*[@id='tab-1243-btnInnerEl']")).click();
			logClass.info("Selecting NetWorks");
			
			for(int i=0;i<2;i++)
			{
				driver.findElement(By.id(opNet[i]+"-inputEl")).click();
				boundListSelect(driver, "VM Network", selBoundList(driver));
			}
			System.out.println("Filled Values");
		}
		
		public void AutoFillCombo(WebDriver driver,String input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ input +"]');return nl");
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
						returnID = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}
			driver.findElement(By.id(returnID)).click();
			boundListSelect(driver, readFromFile("input1.txt", input.toUpperCase()), selBoundList(driver));
		}
		
		public void AutoFillPasswd(WebDriver driver,String input,String fileName) throws IOException{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String returnID1 = "";
			ArrayList<WebElement>a = (ArrayList<WebElement>) js.executeScript("var nl = Ext.getBody().dom.querySelectorAll('[id^="+ input +"]');return nl");
			for(WebElement s : a)
				{//System.out.println(s.getText());
				//System.out.println(s.getTagName());
				//System.out.println(s.getClass());
				//System.out.println(s.getAttribute("id"));
				if(s.getTagName().equals("input"))
					{
						returnID1 = s.getAttribute("id");
						System.out.println(s.getAttribute("id"));
					}
				}

				driver.findElement(By.id(returnID1)).sendKeys(readFromFile(fileName, input));
				driver.findElement(By.id("conf"+returnID1)).sendKeys(readFromFile(fileName, input));
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

