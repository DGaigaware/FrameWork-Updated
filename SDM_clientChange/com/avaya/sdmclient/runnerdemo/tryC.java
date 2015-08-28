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
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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


	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		String s1 = "<Property ovf:value=\"\" ovf:required=\"true\"";
		String s = "<Property ovf:value=\"\" ovf:type=\"string\" ovf:required=\"true\" ovf:qualifiers=\"MinLen(1),MaxLen(255)\" ovf:userConfigurable=\"true\" ovf:key=\"hostname\"><Label>Short Hostname:</Label><Description>Short hostname for Session Manager</Description></Property>";
		
		String _fpSMGR = "C:\\Program Files\\Avaya\\SDMClient\\SDM_API\\SMGR-7.0.0.0.16266-e55-43.ovf";
		
		String _fp = "C:\\Users\\bshingala\\Downloads\\BSM-7.0.0.0.700007-e55-01_EXTRACT\\BSM-7.0.0.0.700007_OVF10.ovf";
		FillValues("Property", _fpSMGR);
		System.out.println("Test");
	}

}
