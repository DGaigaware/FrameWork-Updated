package com.avaya.sdmclient.extraResources;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class execCommandForDMonSMGR {

	public void executeCommandViaSSHConnection(String hostIP,String username,String password) throws JSchException, IOException, SftpException, InterruptedException{

		String getDmutility="wget http://oscar1.platform.avaya.com/ReleaseToSV/Release7.0/R7.0_Sprint9/DM_Bin/Build81/dmutility-7.0.0.1-DM-20150724.112449-81.bin";  
		String getSMGRbackup="wget http://smgrwiki.platform.avaya.com/attachment/wiki/SIT/SIDT_Automation_Results/sidtsmgr12_Backup_6.3.10.zip"; 
		String upgradeSMGRcmd = "upgradeSMGR /home/admin/dmutility-7.0.0.1-DM-20150724.112449-81.bin -m -v";
		String fPath = "/home/admin/sidtsmgr12_Backup_6.3.10.zip";
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, hostIP, 22);
		session.setPassword(password);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		
		Channel channel = session.openChannel("shell");
		OutputStream inputstream_for_the_channel = channel.getOutputStream();
		PrintStream commander = new PrintStream(inputstream_for_the_channel, true);
		channel.setOutputStream(System.out, true);

		channel.connect();
		commander.println(getDmutility.trim());   
		Thread.sleep(5000);
		commander.println(getSMGRbackup.trim());
		Thread.sleep(5000);
		commander.println(upgradeSMGRcmd.trim());
		Thread.sleep(1000);
		//commander.println("");
		commander.println(fPath.trim());
//		Thread.sleep(750);
//		commander.println("");
		Thread.sleep(1000000);
		commander.println("exit");
		commander.close();

		do {
		    Thread.sleep(1000);
		} 
		while(!channel.isEOF());
		session.disconnect();
	}
	
	public static void main(String[] args) throws JSchException, IOException, SftpException, InterruptedException {
		execCommandForDMonSMGR s = new execCommandForDMonSMGR();
		s.executeCommandViaSSHConnection("148.147.162.180","admin","Avaya123$");
	}

}
