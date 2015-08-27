package com.avaya.sdmclient.runnerdemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class token2 {

	static List<File> _Files = new ArrayList<>();
	static List<String> _FileNames = new ArrayList<>();
	static List<String> _FilesAbsPath = new ArrayList<>();
	public static void listFilesForFolder(final File folder) {

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				_Files.add(fileEntry);
				System.out.println(fileEntry.getName());
				_FilesAbsPath.add(fileEntry.getAbsolutePath());
				System.out.println(fileEntry.getAbsolutePath());
				_FileNames.add(fileEntry.getName());
			}
		}

	}

	final static File folder = new File("C:\\Users\\bshingala\\Desktop\\FrameWork Updated-17082015\\SDM_Client\\com\\avaya\\sdmclient\\vm\\VM.java");
	final static File folder1 = new File("C:\\Users\\bshingala\\Desktop\\SDM_Client\\");
	final static File file1 = new File("C:\\Users\\bshingala\\Desktop\\xpandid.txt");
	final static File file2 = new File("C:\\Users\\bshingala\\Desktop\\Settings.java");
	public static void main(String[] args) throws IOException {
		System.out.println("Start");
		BufferedWriter output = null;
		File fileOP = new File("C:\\Users\\bshingala\\Desktop\\xpandidrev.txt");
		File ff = new File("C:\\Users\\bshingala\\Desktop\\ne.txt");
		//output = new BufferedWriter(new FileWriter(file));
		//File file = new File("C:\\Users\\bshingala\\Desktop\\all_GUI_Components.java");
		listFilesForFolder(folder1);
		for(String s:_FileNames)
			System.out.println(s);

		System.out.println(_Files.size());
		List<String> trimmed = new ArrayList<>();	

		StringBuilder sb = new StringBuilder();
		List<String> _l = FileUtils.readLines(file1);
		for(String s : _l)
		{
			String[] parts = s.split("\\=");
			if(parts.length==2)
				System.out.println(sb.append(parts[1]).append(" = ").append(parts[0]).append("\n").toString());
			else
				System.out.println(sb.append(parts[0]).append("\\=").append(parts[1]).append(" = ").append(parts[2]).append("\n").toString());
		}


		for(String s : _l)
		{
			String[] parts = s.split("\\=");
			if(parts.length==2)
				System.out.println(sb.append(parts[1]).append("\n").toString());
			else
				System.out.println(sb.append(parts[0]).append("=").append(parts[1]).append("\n").toString());
		}
		for(File f : _Files){
			List<String> _li = FileUtils.readLines(ff);
			List<String> _li2 = FileUtils.readLines(f);

			Properties prop = new Properties();
			prop.load(new FileInputStream("C:\\Users\\bshingala\\Desktop\\xprev.properties"));
			System.out.println(prop.size());
			System.out.println("\n\n"+f.getName()+"\n\n");
			for(int i=0;i<_li2.size();i++){
				for(int k=0;k<_li.size();k++){
					//System.out.println(_li.get(k));
					if(_li2.get(i).contains(_li.get(k).trim())){
						String re = _li.get(k).trim();
						String tbr = prop.getProperty(_li.get(k).trim());
						System.out.println(_li.get(k));
						System.out.println(prop.getProperty(_li.get(k).trim()));
						System.out.println(_li2.get(i).replace(re,tbr)+"\n");
					}
				}

			}
		}
	}

}
