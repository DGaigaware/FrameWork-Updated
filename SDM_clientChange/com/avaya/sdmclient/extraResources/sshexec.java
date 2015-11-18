package com.avaya.sdmclient.extraResources;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class sshexec {

	public static void main(String[] args) throws TaskExecFailException {
		// TODO Auto-generated method stub
		// Initialize a ConnBean object, parameter list is ip, username, password
		ConnBean cb = new ConnBean("148.147.162.180","admin","Avaya123$");
		// Put the ConnBean instance as parameter for SSHExec static method getInstance(ConnBean) to retrieve a singleton SSHExec instance
		SSHExec ssh = SSHExec.getInstance(cb);          
		// Connect to server
		ssh.connect();
		
//		CustomTask sampleTask = new ExecCommand("echo 123");
//		ssh.exec(sampleTask);
	}

}
