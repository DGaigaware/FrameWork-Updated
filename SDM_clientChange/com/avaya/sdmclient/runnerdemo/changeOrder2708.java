package com.avaya.sdmclient.runnerdemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class changeOrder2708 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		final File file1 = new File("C:\\Users\\bshingala\\Desktop\\xprev.properties");
		final File file2 = new File("C:\\Users\\bshingala\\Desktop\\Settings.java");
			System.out.println("Start");
			BufferedWriter output = null;
			File fileOP = new File("C:\\Users\\bshingala\\Desktop\\xpandidrev.txt");
			File ff = new File("C:\\Users\\bshingala\\Desktop\\ne.txt");

			/*StringBuilder sb = new StringBuilder();
			List<String> _l = FileUtils.readLines(file1);
			for(String s : _l){
				String[] parts = s.split("\\=");
			if(parts.length==3)
				System.out.println(sb.append(parts[2]).append(" = ").append(parts[0].replace("\\", "")).append("=").append(parts[1]).append("\n").toString());
			}*/
			/*List<String> ss = FileUtils.readLines(file1);
			for(String s : ss)
				System.out.println(s.trim());*/
			List<String> ss = FileUtils.readLines(file1);
			Properties prop = new Properties();
			prop.load(new FileInputStream("C:\\Users\\bshingala\\Desktop\\xprev.properties"));
			System.out.println("Before");
			System.out.println(prop.getProperty("LocOrHostGrid"));
			System.out.println("After");
	}

}
