package com.avaya.sdmclient.runnerdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class invokerM {
	
	
	public class MyRunnable implements Runnable {

		final Class noparams[] = {};
	    //private int var;
		private Object obj;
		private Method method;
		Class test222 = testMs.class;
		
	    public MyRunnable(Method method) {
	        //this.obj = obj;
	        this.method = method;
	    }

	    public void run() {
	        // code in the other thread, can reference "var" variable
	    	try {
	    		System.out.println("Before");
				obj = method.invoke(test222.newInstance());
				System.out.println("After");
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	
	public void invMet() throws NoSuchMethodException, SecurityException, InterruptedException{
	final Class test2 = testMs.class;
	final Class test22 = tryC.class;
	Method method = null;
	Object obj = null;
	
	final Class noparams[] = {};
	
	
	int d = test2.getMethods().length;
	for(int i=0;i<d;i++)
		System.out.println(test2.getMethods()[i]);
	
	
	//Method ms[] = test2.getMethods();
	Method ms[] = {test2.getDeclaredMethod("adLoc", noparams),test2.getDeclaredMethod("editLoc", noparams)};
	//method = test22.getMethod("main", noparams);
	
	System.out.println("Start");
	
	//MyRunnable r = new MyRunnable(method);
	
	for(Method m : ms){
		MyRunnable r = new MyRunnable(m);
		Thread thr = new Thread(r);
		System.out.println("\n\n"+thr.getName()+"\n\n");
		thr.start();
		thr.join();
		
	}
	
	
	System.out.println("End");
}
}