package com.avaya.sdmclient.runnerdemo;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class demoToRun {
	//TESTtestNG
	   @Test
	   @Parameters("IP")
	   public void parameterTest(String IP) {
	      System.out.println("Parameterized value is : " + IP);
	   }
	   
	   
	   @Test
	   @Parameters("IP")
	   public void t1(String IP){
		   System.out.println(IP);
	   }
	
}
