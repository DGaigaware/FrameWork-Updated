package com.avaya.sdmclient.runnerdemo;

import java.lang.reflect.InvocationTargetException; 
import java.lang.reflect.Method;
import org.testng.annotations.Test;

import com.avaya.sdmclient.vm.VM;

public class Test1 {
	
	
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
	
	
	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, InterruptedException {
		//final Class test2 = sdmSMGR.class;
		final Class test2 = testMs.class;
		final Class test22 = tryC.class;
		Method method = null;
		Object obj = null;
		
		final Class noparams[] = {};
		/*try {
			method = test2.getMethod("addHost", String.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		method.setAccessible(true);

		try {
			obj = method.invoke(test2.newInstance(), "0");

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}*/
		//obj = test2.newInstance();
		int c = test22.getMethods().length;
		
		/*for(int i=0;i<c;i++)
			System.out.println(test22.getMethods()[i]);*/
		
		int d = test2.getMethods().length;
		for(int i=0;i<d;i++)
			System.out.println(test2.getMethods()[i]);
		
		
		/*Method method = test22.getDeclaredMethod("main", noparams);
		//method.setAccessible(true);
		
		try {
			method = test22.getDeclaredMethod("main", noparams);
		} catch (NoSuchMethodException | SecurityException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		obj = method.invoke(method);*/
		/*Thread thread = new Thread (new Runnable() {
			
			@Override
			public void run() {
				try {
					final Object obj = method.invoke(test2.newInstance(), noparams);
					System.out.println("After");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		
		
		/*Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					//final Object obj = method.invoke(test2.newInstance(), noparams);
					System.out.println("Before");
					method.invoke(test22.newInstance());
					System.out.println("After");
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};*/
		
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

