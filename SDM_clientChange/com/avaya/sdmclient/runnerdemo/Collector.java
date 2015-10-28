package com.avaya.sdmclient.runnerdemo;

public class Collector  extends Thread{

	static boolean checker = false;
	Class c = this.getClass();
	String methodName = "me";
	/*public static void collectGarbage(boolean invoker,String param){
		Thread.currentThread().setDaemon(true);
		
		if(invoker){
			
		}
	}
	
	class WorkerThread extends Thread {

	    public WorkerThread() {
	        setDaemon(true) ;   // When false, (i.e. when it's a user thread),
	                // the Worker thread continues to run.
	                // When true, (i.e. when it's a daemon thread),
	                // the Worker thread terminates when the main 
	                // thread terminates.
	    }

	    public void run() {
	        int count=0 ;
	        while (true) {
	            System.out.println("Hello from Worker "+count++) ;
	            try {
	                sleep(5000);
	            } catch (InterruptedException e) {}
	        }
	    }
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		class MyRunnable implements Runnable {
			 public void run() {
				 System.out.println("Try block:");
				 collectGarbage(checker, "ABC");
				 try {
					
				} catch (Exception ex) {
					
					ex.printStackTrace();
				} 
			 }
		}
		
		MyRunnable r = new MyRunnable();
		Thread t = new Thread(r);
		t.start();
		t.join();
		System.out.println("Completed");
		
	}*/
	
	public void run() {
        System.out.println("Entering run method");

        try {
        	System.out.println("In run Method: currentThread() is" + Thread.currentThread());

        	while (true) {
        		Thread.sleep(500);
        		System.out.println("In run method: woke up again");
        		if(checker){
        			System.out.println("Flag raised");
        			System.out.println(c.getName());
        			
        		}
        		
        		checker = true;
        	}
        } catch (InterruptedException e) {
			
			e.printStackTrace();
		} 
        
        finally {
        	System.out.println("Leaving run Method");
        }
    }
	
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Entering main Method");

        Collector t = new Collector();
        t.setDaemon(true);
        t.start();
        
        Thread.sleep(3000);
        
        System.out.println("Leaving main method");
    }


}
