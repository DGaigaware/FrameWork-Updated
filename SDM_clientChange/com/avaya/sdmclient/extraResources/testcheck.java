package com.avaya.sdmclient.extraResources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class testcheck {
	
	public static Object checkStrings (File file,int count,int timout,int initialLineCount,boolean check) throws IOException, InterruptedException, MyException{
		Object o = null;
		List<String> lines = FileUtils.readLines(file);
		Thread.sleep(3000);
		String err1 = "Unable to get the Maintenance lock. Data migration Failed.";
		String err2 = "Some other maintenance operation is already in progress. Please wait for it to finish and try again. Data migration aborted.";
		String err3 = "Skipping DB Rollback as Backup File Validation has failed.";
		String err4 = "Automated Patch application failed";
		String err5 = "System Manager Data Migration failed. For details, see System Manager Data Migration logs";
		String err6 = "SHA1 verification failed. Expected ";
		String err7 = "ERROR: FP/SP patch validation failed.";
		String su1 = "Data Migration Utility Completed Successfully.";
		String su2 = "Feature/Service pack deployment successful.";
		List<String> positiveCheck = new ArrayList<>();
		List<String> negativeCheck = new ArrayList<>();
		
		positiveCheck.add(err1);
		positiveCheck.add(err2);
		positiveCheck.add(err3);
		positiveCheck.add(err4);
		positiveCheck.add(err5);
		positiveCheck.add(err6);
		positiveCheck.add(err7);
		
		negativeCheck.add(su1);
		negativeCheck.add(su2);
		boolean sb = false;
		//int initialLineCount = 0;
		//String s = lines.toString();
		
		if(count<timout){
			Thread.sleep(5000);
			if(check){
			
				for(String s : lines){
					//System.out.println(s);
					if(s.contains(su1) || s.contains(su2)){
						System.out.println("Info: "+s);
						//throw new MyException("Passed .. ");
						sb = true;
					}
					
				}
				System.out.println("Success: "+count);
				if(!sb)
				o = checkStrings(file, count, timout, initialLineCount, false);
			}
			
			else{
				for(String s : lines){
					//System.out.println(s);

					if(s.contains(err7) || s.contains(err6) || s.contains(err5) || s.contains(err4) || s.contains(err3) || s.contains(err2) || s.contains(err1) ){
						System.out.println("Error: "+s);
						throw new MyException("Error occurred .. ");
					}
				}
				count++;
				System.out.println("Fail: "+count);
				o = checkStrings(file, count, timout, initialLineCount, true);
			}
		}
		else{
			System.out.println("Timeout occurred .. \nYou might want to take a look at system.");
			throw new MyException("Timeout .. ");
		}
		return o;
	}
	public static void main(String[] args) throws IOException, InterruptedException, MyException {
		File file = new File("C:\\Users\\bshingala\\Desktop\\DM_logs\\data_migration.log");
		//checkStr(file,0,10);
		checkStrings(file, 0, 10, 0, true);

	}

}
