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
public class scpFilesFromSMGR2 {

	@SuppressWarnings("unused")
	public void scpFile() throws JSchException, IOException, SftpException{
		String username = "admin";
		String host = "pdev55vm2.smgrdev.avaya.com";
		String pass = "Avaya123$";

		JSch jsch = null;
		Session session = null;
		Session sessionE = null;
		Channel channelold = null;
		Channel forSFTP = null;
		ChannelSftp c = null;
		
		try {
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
			String f1 = "C:\\Users\\bshingala\\Desktop\\vm-mgmt-api.war";
			String f2 = "C:\\Users\\bshingala\\Desktop\\vm-mgmt-ui.war";
			String f3 = "C:\\Users\\bshingala\\Desktop\\sdm.war";
			c.put(f1, fdest);
			c.put(f2, fdest);
			c.put(f3, fdest);
			c.put(fsrc, fdest);
			//c.get("", "./");
			System.out.println("File upload Completed");
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();	
		}
		
		session.disconnect();
	}
	
	public static void main(String[] args) throws JSchException, IOException, SftpException {

		scpFilesFromSMGR2 s = new scpFilesFromSMGR2();
		s.scpFile();

	}

}
