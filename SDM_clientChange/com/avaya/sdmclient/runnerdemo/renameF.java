package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class renameF {

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
	
	final static File folder1 = new File("C:\\Users\\bshingala\\Desktop\\temp1\\");
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		listFilesForFolder(folder1);
		
		 for(int ii=0;ii<_Files.size();ii++){
	        	
	        	List<String> _li2 = FileUtils.readLines(_Files.get(ii));
	        
	        	File newf = new File("C:\\Users\\bshingala\\Desktop\\tt\\"+_Files.get(ii).getName().replace(".javatemp.txt", ".")+"java"); 
	        	PrintWriter pri = new PrintWriter(newf);
	        	
	        	System.out.println(_Files.get(ii).getName()+"\n\n");
	        	
	        	for(int i=0;i<_li2.size();i++){
	        		if(_li2.get(i).contains("By.")){
	        			int sI = _li2.get(i).indexOf("By.");
	        			//int eI = _li2.get(i).indexOf("(");
	        			//System.out.println(s.indexOf("By."));
	        			//System.out.println(s.indexOf("("));
	        			int tempeI = _li2.get(i).indexOf("(", sI);
	        			String tp = _li2.get(i).substring(sI,tempeI);
	        			String temp = _li2.get(i).replace(tp, "locator.getProperty");
	        			System.out.println(tp);
	        			System.out.println(_li2.get(i).replace(tp, "locator.getProperty"));
	        			_li2.remove(i);
	        			_li2.add(i, temp);
	        			
	        		}	
	        	pri.write(_li2.get(i)+"\n");
	        	}
	        	
	       pri.close(); 			
		 }

}
	
}
