package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class demo2209 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		Properties pr=new Properties();
		pr.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties"));
		
		File f = new File(System.getProperty("user.dir") + "\\Third Party\\Input Files\\black-whitelistedIP.properties");
		OutputStream out = null;
		
		pr.setProperty("148.147.162.225", "WhiteList");
		System.out.println("Done");
		
		out = new FileOutputStream( f );
        pr.store(out, "Change Date: ");
        out.close();
	}

}
