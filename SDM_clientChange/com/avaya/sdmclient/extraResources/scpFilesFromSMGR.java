package com.avaya.sdmclient.extraResources;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.avaya.sdmclient.logClass;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
public class scpFilesFromSMGR {

	@SuppressWarnings("unused")
	public void scpFile() throws JSchException, IOException, SftpException{
		String username = "admin";
		String host = "pdev55vm2.smgrdev.avaya.com";
		String pass = "Avaya123$";
		String strToscp = "";
		String command = "find /swlibrary/swLib/local  -name \"*.ovf\"";

		JSch jsch = null;
		Session session = null;
		Session sessionE = null;
		Channel channelold = null;
		Channel forSFTP = null;
		ChannelSftp c = null;
		
		List<String> files = new ArrayList<>();

		//Deleting old OVFs so that if new version comes up, they won't collide
		
		File folder = new File(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		for(File f : listOfFiles){
			if(f.exists()){
				f.delete();
				System.out.println("Deleted: "+f.getName());
			}
		}
		
		
/*		try {
			jsch = new JSch();
			session = jsch.getSession(username, host, 22);
			session.setPassword(pass);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channelold = session.openChannel("sftp");
			channelold.connect();
			c = (ChannelSftp) channelold;
		} 
		catch (Exception e) 
		{ 	
			e.printStackTrace();	
		}
		
		
		try {
			System.out.println("Starting File Upload:");
			String fsrc = "./logfile.log", fdest = "./";
			c.put(fsrc, fdest);
			//c.get("", "./");
			System.out.println("File upload Completed");
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();	
		}*/
		
		jsch = new JSch();
		session = jsch.getSession(username, host, 22);
		session.setPassword(pass);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		channelold = session.openChannel("exec");
		((ChannelExec)channelold).setCommand(command);
        channelold.setInputStream(null);
        ((ChannelExec)channelold).setErrStream(System.err);
         
        InputStream input = channelold.getInputStream();
        channelold.connect();
         
        System.out.println("Channel Connected to machine " + host + " server with command: " + command ); 
         
        try{
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line = null;
             
            while((line = bufferedReader.readLine()) != null){
                strToscp = line;
                files.add(line);
            	//System.out.println(line);
            }
            bufferedReader.close();
            inputReader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
       
        //for(String s : files)
        for(int i=0;i<files.size();i++)
        {
        	System.out.println(files.get(i));
        	
        	//try {
        		forSFTP = session.openChannel("sftp");
        		forSFTP.connect();
        		c = (ChannelSftp) forSFTP;
        		//System.out.println("Starting File Download:");
        		//String fsrc = "./logfile.log", fdest = "./";
        		//c = (ChannelSftp) channelold;
        		//System.out.println(System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
        		c.get(files.get(i),System.getProperty("user.dir")+"\\Third Party\\OVFs\\");
        		//c.put(fsrc, fdest);
        		//c.get("", "./");
        		System.out.println("Download completed "+files.get(i));
        		logClass.info(files.get(i)+" is downloaded from Upstream.("+host+")");
        	//} 
        	/*/catch (Exception e) 
        	{	
        		e.printStackTrace();	
        	}*/
        }
        
		//c.disconnect();
		session.disconnect();
	}
	
	public static void main(String[] args) throws JSchException, IOException, SftpException {

		scpFilesFromSMGR s = new scpFilesFromSMGR();
		s.scpFile();

	}

}
