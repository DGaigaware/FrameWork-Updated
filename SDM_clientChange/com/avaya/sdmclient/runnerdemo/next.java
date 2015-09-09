package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.avaya.sdmclient.Settings;

public class next {

	static List<String> completed = new ArrayList<>();
	
	public static String match(List<String> input,String fromFile){
		String returnStr = "";
		for(String s : input){
			if(s.substring(0, s.indexOf("-")).equalsIgnoreCase(fromFile))
				returnStr = s;
		}
		return returnStr;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Settings obj = new Settings();
		
		List<String> ele = new ArrayList<>();
		ele.add("SM-7.0.0.0.700007-e55-01.ova");
		ele.add("BSM-7.0.0.0.700007-e55-01.ova");
		ele.add("CMM-7.0.0.0.700007-e55-01.ova");
		
		File file = new File("C:\\Users\\bshingala\\Avaya\\SDMTests\\"+"inputelem.txt");
		File fileTemp = new File("C:\\Users\\bshingala\\Avaya\\SDMTests\\"+"inputtemp.txt");
		List<String> lines = FileUtils.readLines(file);
		Scanner sc = new Scanner(fileTemp);
		int temp = Integer.parseInt(sc.next());
		System.out.println(temp);
		String output = lines.get(temp);
		PrintWriter pr = new PrintWriter(fileTemp);
		pr.write(""+(temp+1));
		pr.close();
		
		System.out.println(match(ele, output));
	}

}
