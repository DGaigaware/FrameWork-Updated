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
public class execCommand {

	@SuppressWarnings("unused")
	public void scpFile(String hostIP,String username,String password) throws JSchException, IOException, SftpException, InterruptedException{
		String command = "vt100";
		String command1 = "snmpuserconfig -v 1 -r -i 0.0.0.0 -c public --add";
		String command2 = "snmpuserconfig -v 1 -w -i 0.0.0.0 -c private --add";
		
		
		JSch jsch = null;
		Session session = null;
		Session sessionE = null;
		Channel channelold = null;
		Channel forSFTP = null;
		ChannelSftp c = null;
		
		
		
		try {
			jsch = new JSch();
			session = jsch.getSession(username, hostIP, 22);
			session.setPassword(password);
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
			String fsrc = "C:\\Users\\bshingala\\Desktop\\test.sh", fdest = "./";
			c.put(fsrc, fdest);
			//c.get("", "./");
			System.out.println("File upload Completed");
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();	
		}
		
		/*jsch = new JSch();
		session = jsch.getSession(username, hostIP, 22);
		session.setPassword(password);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		// For executing commands on terminal
		channelold = session.openChannel("shell");
		//((ChannelExec)channelold).setCommand(command1);
        channelold.setInputStream(System.in);
        //((ChannelExec)channelold).setErrStream(System.err);
        channelold.setOutputStream(System.out);
        //InputStream input = channelold.getInputStream();
        channelold.connect(3*1000);
		
		
		channelold = session.openChannel("exec");
		((ChannelExec)channelold).setCommand("pwd");
        channelold.setInputStream(null);
        ((ChannelExec)channelold).setErrStream(System.err);
        channelold.setOutputStream(System.out);
        InputStream input = channelold.getInputStream();
        channelold.connect();
        System.out.println("Channel Connected to machine " + hostIP + " server with command: " + command1 ); 
         
        Thread.sleep(2500);

        try{
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line = null;
             
            while((line = bufferedReader.readLine()) != null){
            	System.out.println("OP");
            	System.out.println("Output : "+line);
            }
            bufferedReader.close();
            inputReader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
       
		//c.disconnect();
		session.disconnect();*/
		
		/*session = jsch.getSession(username, hostIP, 22);
		session.setPassword(password);
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		channelold = session.openChannel("shell");
		//((ChannelExec)channelold).setCommand(command2);
        channelold.setInputStream(null);
        ((ChannelExec)channelold).setErrStream(System.err);
         
        InputStream input1 = channelold.getInputStream();
        channelold.connect();
         
        System.out.println("Channel Connected to machine " + hostIP + " server with command: " + command2 ); 
         
        try{
            InputStreamReader inputReader = new InputStreamReader(input1);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line = null;
             
            while((line = bufferedReader.readLine()) != null){
            	System.out.println(line);
            }
            bufferedReader.close();
            inputReader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }*/
        
	}
	
	public static void main(String[] args) throws JSchException, IOException, SftpException, InterruptedException {

		execCommand s = new execCommand();
		s.scpFile("148.147.162.221","admin","Avaya123$");

	}

}
