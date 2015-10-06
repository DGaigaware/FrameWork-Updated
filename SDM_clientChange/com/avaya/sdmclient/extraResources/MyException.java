package com.avaya.sdmclient.extraResources;

public class MyException extends Exception {
	public MyException(String message) {
        super(message);
        System.out.println(message);
    }
}
