package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.avaya.sdmclient.logClass;


public class tryC {

	public static void FillValues(String _tag,String _filePath) throws IOException, ParserConfigurationException, SAXException{
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
				}

				else if(attr.getNodeName().contains("ovf:key")){
					System.out.println(" " + i + " "+ j +attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					_op[i][0] = attr.getNodeValue();
				}
			}
		}

		for (int i = 0; i < nl.getLength()-1; i++)
			System.out.println(_op[i][0]);
		for(int i=0;i<nl.getLength()-1;i++)

		{
			//System.out.println(_op[i][z]);
			if(_op[i][1]!=null)
			{
				System.out.println("Going to IP");
				//obj._AutoFill(driver, _op[i][0]);
			}
			else
			{
				System.out.println("Default");
			}
		}
		//System.out.println(nl.item(16).getNodeName());
		//System.out.println(nl.item(16).getTextContent());

	}

	public static String _ExtractTexts(String _tag,String _FilePath) throws IOException{
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
	
	/*
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
		
		for(int i=0;i<nl.getLength();i++)
		System.out.println(nl.item(i).getAttributes());
		
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
				Node attr = nlmapnet.get(i).item(j);
				//System.out.println(_nlmap.get(i).toString());
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
		
		for(int i=0;i<nl.getLength()-5;i++)
			System.out.println(op[i][0]);
		
		//for(int i=0;i<nl.getLength()-5;i++)			// As last 5 Fields are not Visible in UI

		for(int i=0;i<nl.getLength()-1;i++)
		{
			//System.out.println(op[i][0]);
			if(op[i][1]!=null && op[i][1].equals("Ip"))
			{
				//System.out.println("Going to IP " + i);
				AutoFillIP(driver, op[i][0],readFromFile(fileName, op[i][0]),fileName);
			}

			else if(op[i][1]!=null && op[i][1].equals("true"))
			{
				//System.out.println("Password " + i);
				AutoFillPasswd(driver, op[i][0],fileName);
			}
			
			else if(op[i][1]!=null && op[i][1].equals("boolean"))
			{
				System.out.println("CheckBox " + i);
				AutoFillCheckBox(driver, op[i][0]);
			}
			
			else if(op[i][1]==null && op[i][0].equalsIgnoreCase("timezone"))
			{
				System.out.println("Combo " + i);
				AutoFillCombo(driver, op[i][0],fileName);
			}
			
			else if(op[i][1]!=null && op[i][1].equals("ValueMap"))
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

		driver.findElement(By.xpath(locator.getProperty("NetWorkSelect"))).click();
		logClass.info("Selecting NetWorks");

		for(int i=0;i<2;i++)
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
			driver.findElement(By.id((IDs.get(0)))).clear();
			driver.findElement(By.id((IDs.get(0)))).sendKeys(readFromFile(fileName, input));
		}
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
	}*/


	@SuppressWarnings("unused")
	public static void main() throws Exception {

		String s1 = "<Property ovf:value=\"\" ovf:required=\"true\"";
		String s = "<Property ovf:value=\"\" ovf:type=\"string\" ovf:required=\"true\" ovf:qualifiers=\"MinLen(1),MaxLen(255)\" ovf:userConfigurable=\"true\" ovf:key=\"hostname\"><Label>Short Hostname:</Label><Description>Short hostname for Session Manager</Description></Property>";
		
		String _fpSMGR = "C:\\Program Files\\Avaya\\SDMClient\\SDM_API\\SMGR-7.0.0.0.16266-e55-43.ovf";
		
		String _fp = "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf";
		FillValues("Property", _fp);
		System.out.println("Test");
	}

}
