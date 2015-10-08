package com.avaya.sdmclient.extraResources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bouncycastle.math.ec.ECCurve.Fp;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class checkOVFParameters {

	public static void findParameters(String filePath) throws IOException, ParserConfigurationException, SAXException, InterruptedException{
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
	}
	
	public static List<String> filePaths(){
		File folder = new File(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getAbsolutePath());
		        fileNames.add(listOfFiles[i].getAbsolutePath());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    
		   return fileNames;
	}
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
		
		List<String> fPaths = filePaths();
		 
		for(String s : fPaths){
			System.out.println("\n\n"+s+"\n\n");
			findParameters(s);
		}
	}

}
