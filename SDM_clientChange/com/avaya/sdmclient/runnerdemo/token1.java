package com.avaya.sdmclient.runnerdemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class token1 {
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
	public static void main(String[] args) throws IOException {
		System.out.println("Start");
		BufferedWriter output = null;
        //File fileOP = new File("testBy1.txt");
        //output = new BufferedWriter(new FileWriter(file));
		//File file = new File("C:\\Users\\bshingala\\Desktop\\all_GUI_Components.java");
		listFilesForFolder(folder1);
		for(String s:_FileNames)
			System.out.println(s);

		System.out.println(_Files.size());
		List<String> trimmed = new ArrayList<>();
		
		for(File file : _Files)
		{
			System.out.println("\n\n"+file.getName()+"\n\n");
			List<String> _Lines = FileUtils.readLines(file);
			
			for(String s : _Lines)
			{
				//System.out.println(s);
				if(s.contains("By."))
				{
					if(!trimmed.contains(s.substring(s.indexOf("By."), s.indexOf(")")+1)))
						trimmed.add(s.substring(s.indexOf("By."), s.indexOf(")")+1));	
					//System.out.println(s.substring(s.indexOf("By."), s.indexOf(")")+1));
				}
			}
			
			/*Pattern p = Pattern.compile("\"([^\"]*)\"");
			Matcher m ;
			for(String s : _Lines)
				{
				//trimmed.add(s.substring(s.indexOf("By."), s.indexOf(")")+1));
						m = p.matcher(s);
							while (m.find()) {
								System.out.println(m.group(1));
							}
					
				}*/
		}
		
		System.out.println(trimmed.size());
		
		for(String s : trimmed)
			System.out.println(s);
		
		
		
		File f = new File("C:\\Users\\bshingala\\Desktop\\xpandid.txt");
		PrintWriter pr = new PrintWriter(f);
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m ;
		for(int i=0;i<trimmed.size();i++)
			{
				m = p.matcher(trimmed.get(i));
					while (m.find()) 
					{
						System.out.println(i+" "+m.group(1));
						pr.append(m.group(1)+"\n");
					}			
			}
		pr.close();
		
		/*for(int i=0;i<_FilesAbsPath.size();i++){
			File fi = new File(_FilesAbsPath.get(i));
			
			
			BufferedWriter ou = null;
			BufferedWriter ouT = null;
			File fiO = new File(_FileNames.get(i)+".txt");
			File fiOTotal = new File(_FileNames.get(i)+"total.txt");
	        ou = new BufferedWriter(new FileWriter(fiO));
			ouT = new BufferedWriter(new FileWriter(fiOTotal));
			List<String> _Lines = FileUtils.readLines(fi);
			for(String s1 : _Lines)
				if(s.startsWith("By."))
					System.out.println(s);
			
			List<String> _Lines1 = FileUtils.readLines(fi);
			Pattern p = Pattern.compile("\"([^\"]*)\"");
			Matcher m ;
			for(String s : _Lines1)
				{
					m = p.matcher(s);
						while (m.find()) {
							System.out.println(m.group(1));
							ouT.append(m.group(1)+"\n");
						}
				}
			
			for(String s1 : _Lines)
				
				if(s1.contains("By."))
				{
					
					//System.out.println(s1.indexOf("By."));
					String tp = s1.substring(s1.indexOf("By."), s1.indexOf(")")+1);
					String tt = tp.substring(0,	tp.indexOf("(\"")+1);
					//System.out.println(tp.substring(0,	tp.indexOf("(\"")+1));
					System.out.println(s1.substring(s1.indexOf("By."), s1.indexOf(")")+1));
					ou.append(tp+"\n");
					ou.append(s1.replace(tp, tt+"locator.getProperty(\"\"))")+"\n\n");
					System.out.println(s1.replace(tp, tt+"locator.getProperty(\"\"))"));
					s1.replace(tp, tt+"locator.getProperty(\"\"))");
					System.out.println();
				}
				ou.append("\n\n");
				System.out.println("Written successfully");
				ou.close();
				ouT.close();
			
		}*/
	}

}
