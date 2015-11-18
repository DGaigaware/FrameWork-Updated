package com.avaya.sdmclient.extraResources;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class execCommand {

	public void executeCommandViaSSHConnection(String hostIP,String username,String password) throws JSchException, IOException, SftpException, InterruptedException{
		String command = "vt100";
		String command1 = "snmpuserconfig -v 1 -r -i 0.0.0.0 -c public --add";
		String command2 = "snmpuserconfig -v 1 -w -i 0.0.0.0 -c private --add";

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
		commander.println(command);    
		commander.println(command1);
		commander.println(command2);
		commander.println("exit");
		commander.close();

		do {
		    Thread.sleep(1000);
		} 
		while(!channel.isEOF());
		session.disconnect();
	}
	
	public static void main(String[] args) throws JSchException, IOException, SftpException, InterruptedException {
		execCommand s = new execCommand();
		s.executeCommandViaSSHConnection("148.147.162.221","admin","Avaya123$");
	}

}
