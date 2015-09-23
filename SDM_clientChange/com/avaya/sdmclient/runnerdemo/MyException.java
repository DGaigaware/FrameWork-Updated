package com.avaya.sdmclient.runnerdemo;

public class MyException extends Exception {
	public MyException(String message) {
        super(message);
        System.out.println(message);
    }
}
