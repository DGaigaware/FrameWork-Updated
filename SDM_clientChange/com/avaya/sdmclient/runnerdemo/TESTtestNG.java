package com.avaya.sdmclient.runnerdemo;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import com.avaya.sdmclient.vm.VM;

public class TESTtestNG {
	final Class test22 = tryC.class;
	Class tes = demoToRun.class;
	@Test
	public void testTestNGProgramatically(){
	TestListenerAdapter tla = new TestListenerAdapter();
	TestNG testng = new TestNG();
	
	//testng.setXmlSuites((List<XmlSuite>) new Parser("C:\\Users\\bshingala\\Desktop\\FrameWork Updated-17082015\\temp.xml"));
	//testng.setXmlPathInJar("C:\\Users\\bshingala\\Desktop\\FrameWork Updated-17082015\\temp.xml");
	testng.setTestClasses(new Class[] {tes});
	//testng.addMethodSelector(tes.getName(), 0);
	testng.addListener(tla);
	testng.run(); 
	}
	
}
